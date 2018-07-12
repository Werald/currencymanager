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
     * @param URL_TO_SEND -  Ссылка с GET-запросом, API-key и форматированием.
     */
    private static final String URL_TO_SEND ="http://data.fixer.io/api/latest?access_key=1787cfc17beaea6bf7ba65cb4a26aebe&format=1";

    /**
     * Функция для получения курсов валют через HTTP-запрос у @link{fixer.io} и парсинга JSON-обьекта с актуальными курсами.
     *
     * @param baseCurrency - валюта, к которой производится финальное приведение расходов (задается пользователем)
     * @return коллекция HashMap<ВАЛЮТА, КУРС>
     * Output: (~50% match) {EUR=1.0, PLN=4.361108, USD=1.175912, UAH=30.944113}
     */
    private HashMap<String, Double> getRequiredCoursesFromFixer(String baseCurrency) {
        ArrayList<String> currencys = new ArrayList<>(getAbbreviationsOfCurrenciesInTable());
        HashMap<String, Double> jsonCur = new HashMap<>();
        try {
            URL url = new URL(URL_TO_SEND);
            Scanner scan = new Scanner(url.openStream());
            StringBuilder str = new StringBuilder();

            while (scan.hasNext())
                str.append(scan.nextLine());
            scan.close();

            JSONObject obj = new JSONObject(str.toString());
            JSONObject res = obj.getJSONObject("rates");
            for (String currency : currencys) {
                jsonCur.put(currency, new Double(String.valueOf(res.getDouble(currency))));
            }
            jsonCur.put(baseCurrency, new Double(String.valueOf(res.getDouble(baseCurrency))));
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

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CURRENCY FROM EXPENSES;");
            while (rs.next()) {
                currencys.add(rs.getString("CURRENCY"));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (c != null)
                    c.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
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

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
            stmt = c.createStatement();

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
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (c != null)
                    c.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
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

        TableTotal tableTotal = new TableTotal();
        ArrayList<String> shortCur = new ArrayList<>(tableTotal.getAbbreviationsOfCurrenciesInTable());
        HashMap<String, Double> amountOfCur = new HashMap<>(tableTotal.getAmmountOfCurrenciesInTable());
        HashMap<String, Double> coursesOfCur = new HashMap<>(tableTotal.getRequiredCoursesFromFixer(baseCurrency));
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


