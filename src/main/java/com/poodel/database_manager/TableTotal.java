package com.poodel.database_manager;

import com.poodel.fixer_resourses.CurrencyType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.*;
import java.util.*;

public class TableTotal {

    private static final String URL_TO_SEND = "http://data.fixer.io/api/latest?access_key=1787cfc17beaea6bf7ba65cb4a26aebe";

    private HashMap<String, Double> getRequiredCoursesFromFixer(String CUR){
        ArrayList<String> currencys = new ArrayList<>(getAbreviatures());
        HashMap<String, Double> jsonCur = new HashMap<>();


//        System.out.println(currencys.get(0));
        try
        {
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
            jsonCur.put(CUR, new Double(String.valueOf(res.getDouble(CUR))));

//            System.out.println(res);

        }
        catch(Exception e)
        {e.printStackTrace();}
        return jsonCur;

    }

    public Double getTotal(String cur) {
        Double result = 0.0;
        ArrayList<String> currencys = new ArrayList<>(getAbreviatures());
        currencys.add(cur);
        HashMap<String, Double> jsonCur = new HashMap<>();
        HashMap<String, Double> tableCur = new HashMap<>();
        tableCur = getCurrencys();

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

        } catch (NullPointerException e){
        }
        return result;
    }

//    [PLN, USD]
//    {PLN=27.0, USD=1526.0}
    private HashSet<String> getAbreviatures(){
        HashSet<String> currencys = new HashSet<>();
        HashMap<String, Double> curAmount = new HashMap<>();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CURRENCY FROM EXPENSES; ");

            while (rs.next()) {
                currencys.add(rs.getString("CURRENCY"));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return currencys;

        }
    private HashMap<String, Double> getCurrencys(){
        HashSet<String> currencys = new HashSet<>();
        HashMap<String, Double> curAmount = new HashMap<>();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CURRENCY FROM EXPENSES; ");

            while (rs.next()) {
                currencys.add(rs.getString("CURRENCY"));
            }
            rs.close();

            for (String str: currencys
                 ) {
                PreparedStatement statement =  c.prepareStatement("SELECT SUM(AMOUNT) FROM EXPENSES WHERE CURRENCY='" + str + "';");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                curAmount.put(str, result.getDouble(1));
                }
                result.close();
            }
//            System.out.println(curAmount.toString());
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return curAmount;
    }


}
