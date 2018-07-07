package com.poodel.database_manager;

import org.json.JSONObject;

import java.math.RoundingMode;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Date;

public class TableTotal {

    private static final String URL_TO_SEND ="http://data.fixer.io/api/latest?access_key=1787cfc17beaea6bf7ba65cb4a26aebe";

    //      {EUR=1.0, PLN=4.361108, USD=1.175912, UAH=30.944113}
//   getRequiredCoursesFromFixer WORKING PERFECT
    private HashMap<String, Double> getRequiredCoursesFromFixer(String baseCurrency) {
        ArrayList<String> currencys = new ArrayList<>(getAbbreviationsOfCurrenciesInTable());
        HashMap<String, Double> jsonCur = new HashMap<>();
        try {
            URL url = new URL(URL_TO_SEND);
            // read from the URL
            Scanner scan = new Scanner(url.openStream());
            String str = "";
            while (scan.hasNext())
                str += scan.nextLine();
            scan.close();
            // build a JSON object
            JSONObject obj = new JSONObject(str);
            // get the result
            JSONObject res = obj.getJSONObject("rates");
            for (String currency : currencys) {
                jsonCur.put(currency, new Double(String.valueOf(res.getDouble(currency))));
            }
            jsonCur.put(baseCurrency, new Double(String.valueOf(res.getDouble(baseCurrency))));

//            System.out.println(res);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonCur;

    }

    ///     [EUR, PLN, USD] =
//   getAbbreviations WORKING PERFECT
    private HashSet<String> getAbbreviationsOfCurrenciesInTable() {
        HashSet<String> currencys = new HashSet<>();
        HashMap<String, Double> curAmount = new HashMap<>();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            c.setAutoCommit(true);
//            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CURRENCY FROM EXPENSES; ");

            while (rs.next()) {
                currencys.add(rs.getString("CURRENCY"));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        finally {
            // finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
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

    //      {EUR=2.0, PLN=2.0, USD=2.0 }
//   getAmmountOfCurrenciesInTable WORKING PERFECT
    private HashMap<String, Double> getAmmountOfCurrenciesInTable() {
        HashSet<String> currencys = new HashSet<>();
        HashMap<String, Double> curAmount = new HashMap<>();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            c.setAutoCommit(true);
//            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CURRENCY FROM EXPENSES; ");

            while (rs.next()) {
                currencys.add(rs.getString("CURRENCY"));
            }
            rs.close();

            for (String str : currencys
                    ) {
                PreparedStatement statement = c.prepareStatement("SELECT SUM(AMOUNT) FROM EXPENSES WHERE CURRENCY='" + str + "';");
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    curAmount.put(str, result.getDouble(1));
                }
                result.close();
            }
//            System.out.println(curAmount.toString());
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        finally {
            // finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
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

    ////***********************/////


    public void getTotal(String baseCurrency) {
        double result = 0.0;
        TableTotal tableTotal = new TableTotal();
        ArrayList<String> abbreviationsOfCurrenciesInTable = new ArrayList<>(tableTotal.getAbbreviationsOfCurrenciesInTable());
        HashMap<String, Double> ammountOfCurrenciesInTable = new HashMap<>(tableTotal.getAmmountOfCurrenciesInTable());
        HashMap<String, Double> requiredCoursesFromFixer = new HashMap<>(tableTotal.getRequiredCoursesFromFixer(baseCurrency));
        ArrayList<Double> result4Each = new ArrayList<>();

        for (String curAbbrevFromTable : abbreviationsOfCurrenciesInTable
                ) {
            result4Each.add((ammountOfCurrenciesInTable.get(curAbbrevFromTable) *
                    (requiredCoursesFromFixer.get(baseCurrency) / requiredCoursesFromFixer.get(curAbbrevFromTable))));
        }
 //       System.out.println(result4Each);
        for (double ammount : result4Each) {result += ammount;}

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        System.out.println(df.format(result)+ " " + baseCurrency);


    }
}


// _______________________________________________________
    ////***********************/////
//    public static void main (String []d){
//        ArrayList<String> currencys = new ArrayList<>(getAbbreviations());
//        System.out.println(currencys);
//
//        TableInsert tableInsert = new TableInsert();
//        tableInsert.addRecord("2019-12-12", "1", "PLN", "weeed");
//        TableList tableList = new TableList();
//        //    tableList.displayExpenses();
//
//        get("EUR");
//
//        ArrayList<String> currencys1 = new ArrayList<>(getAbbreviations());
//        System.out.println(getAbbreviations());
//
//        HashMap<String, Double> res = getRequiredCoursesFromFixer("UAH");
//        System.out.println(res);
//
//
//    }
//}
/*    public Double getTotal(String cur) {
        Double result = 0.0;
        ArrayList<String> currencys = new ArrayList<>(getAbbreviationsOfCurrenciesInTable());
        currencys.add(cur);
        HashMap<String, Double> jsonCur = new HashMap<>();
        HashMap<String, Double> tableCur = new HashMap<>();
        tableCur = getAmmountOfCurrenciesInTable();

        jsonCur = getRequiredCoursesFromFixer(cur);
//        System.out.println(currencys);
        double curCourse = jsonCur.get(cur);
        try {

            for (String val : currencys
                    ) {
                double temp = tableCur.get(val) * (curCourse / jsonCur.get(val));
//                System.out.println(temp);
                result += temp;
            }

        } catch (Exception e){
            System.out.println(e.getClass().getName() +"; " + e.getMessage());

        }
        return result/2;
    }
*/

