package com.poodel.database_manager;

import org.json.JSONObject;

import java.math.RoundingMode;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Класс-Реализация "total" - приведение всех расходов в БД к единой валюте-знаменателю (задается пользователем)
 */
public class TableTotal {

    /**
     * Функция для получения нужных курсов валют через HTTP-запрос у @link{fixer.io} и парсинга JSON-обьекта с актуальными курсами.
     *
     * @param baseCurrency - валюта, к которой производится финальное приведение расходов (задается пользователем)
     * @return коллекция HashMap<ВАЛЮТА, КУРС>
     * Output: (~50% match) {EUR=1.0, PLN=4.361108, USD=1.175912, UAH=30.944113}
     */
    private HashMap<String, Double> getRequiredCoursesFromFixer(String baseCurrency) {

        StringBuilder URL_TO_SEND = new StringBuilder("http://data.fixer.io/api/latest?access_key=1787cfc17beaea6bf7ba65cb4a26aebe&symbols=");
        ArrayList<String> currencies = new ArrayList<>(getAbbreviationsOfCurrenciesInTable());
        currencies.add(baseCurrency);
        for (String cur: currencies) {
            URL_TO_SEND.append(cur).append(",");
        }
        HashMap<String, Double> jsonCur = new HashMap<>();
        try {
            URL url = new URL(URL_TO_SEND.toString());
            Scanner scan = new Scanner(url.openStream());
            StringBuilder str = new StringBuilder();
            while (scan.hasNext())
                str.append(scan.nextLine());
            scan.close();

            JSONObject obj = new JSONObject(str.toString());
            Boolean isSuccess = obj.getBoolean("success");
            if(isSuccess){
                JSONObject res = obj.getJSONObject("rates");
                for (String currency : currencies) {
                    jsonCur.put(currency, new Double(String.valueOf(res.getDouble(currency))));
                }
            } else {
                JSONObject error = obj.getJSONObject("error");
                System.out.println("Bad request to fixer.io! \n error code: " + error.getInt("code") + " - "+
                error.getString("info"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonCur;
    }

    /**
     * Функция для сбора всех типов задействованных в расходах валют.
     *
     * @return коллекция уникальных значений аббревиатур валют из БД.
     * Output: (~50% match) [EUR, PLN, USD]
     */
    private HashSet<String> getAbbreviationsOfCurrenciesInTable() {

        HashSet<String> currencys = new HashSet<>();
        try( Connection c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
             Statement stmt = c.createStatement()
        ) {
            ResultSet rs = stmt.executeQuery("SELECT CURRENCY FROM EXPENSES;");
            while (rs.next()) {
                currencys.add(rs.getString("CURRENCY"));
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return currencys;
    }

    /**
     * Функция собирающая в коллекцию уникальные пары (Валюта)-(Сумма_расходов).
     *
     * @return коллекция HashMap<Валюта, Количество_расходов_в_ЭТОЙ_валюте>.
     * Output: (~50% match) {EUR=2.0, PLN=2.0, USD=2.0 }
     */
    private HashMap<String, Double> getAmmountOfCurrenciesInTable() {

        HashSet<String> currencys = new HashSet<>();
        HashMap<String, Double> curAmount = new HashMap<>();

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
             Statement stmt = c.createStatement()
        ) {

            ResultSet rs = stmt.executeQuery("SELECT CURRENCY FROM EXPENSES;");
            while (rs.next()) {
                currencys.add(rs.getString("CURRENCY"));
            }
            rs.close();

            for (String str : currencys) {
                PreparedStatement statement = c.prepareStatement("SELECT SUM(AMOUNT) FROM EXPENSES WHERE CURRENCY='" + str + "';");
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    curAmount.put(str, result.getDouble(1));
                }
                result.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return curAmount;
    }


    /**
     * Функция, выполняющая приведение всех валют из БД к валюте-знаменателю 'baseCurrency',
     * результат округляется до 2х знаков после запятой и выводится в консоль.
     *
     * @param baseCurrency валюта, к которой производится общее приведение(задается пользователем)
     */
    public void countTotal(String baseCurrency) {

        ArrayList<String> shortCur = new ArrayList<>(getAbbreviationsOfCurrenciesInTable());
        HashMap<String, Double> amountOfCur = new HashMap<>(getAmmountOfCurrenciesInTable());
        HashMap<String, Double> coursesOfCur = new HashMap<>(getRequiredCoursesFromFixer(baseCurrency));
        ArrayList<Double> result4Each = new ArrayList<>();
        double result = 0.0;

        for (String curAbbrevFromTable : shortCur) {
            result4Each.add((amountOfCur.get(curAbbrevFromTable) *
                    (coursesOfCur.get(baseCurrency) / coursesOfCur.get(curAbbrevFromTable))));
        }
        for (double amount : result4Each) {
            result += amount;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        System.out.println(df.format(result)+ " " + baseCurrency);
    }

} /* Output:
5.42 EUR
*///:~


