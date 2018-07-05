package com.poodel.database_manager;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.*;
import java.util.*;

public class TableTotal {

    private static final String URL_TO_SEND = "http://data.fixer.io/api/latest?access_key=1787cfc17beaea6bf7ba65cb4a26aebe&symbols=USD,AUD,CAD,PLN,MXN&format=1";
    private String connectToUrl(String sds){
        try
        {
            
        }
        catch(Exception e)
        {e.printStackTrace();}
        return null;
    }
//
//    public static void main (String S[]){
//        TableTotal tableTotal = new TableTotal();
//        tableTotal.connectToUrl(URL_TO_SEND);
//    }
//

//    [PLN, USD]
//    {PLN=27.0, USD=1526.0}
    private HashMap<String, Double> getCurrencys(){
        HashSet<String> currencys = new HashSet<>();
        HashMap<String, Double> curAmount = new HashMap<>();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

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
            System.out.println(curAmount.toString());
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return curAmount;
    }

    private String [] getAmount(HashSet<String> currencys){
        String[] result = null;


        return result;
    }
    public static void main(String args[]) throws Exception {

        TableTotal tableTotal = new TableTotal();

        System.out.println(tableTotal.getCurrencys().toString());

        geocoding("Lviv");
    }

    public static void geocoding(String addr) throws Exception
    {
        // build a URL
        String s = "http://maps.google.com/maps/api/geocode/json?" +
                "sensor=false&address=";
        s += URLEncoder.encode(addr, "UTF-8");
        URL url = new URL(s);

        // read from the URL
        Scanner scan = new Scanner(url.openStream());
        String str = new String();
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();

        // build a JSON object
        JSONObject obj = new JSONObject(str);
        if (! obj.getString("status").equals("OK"))
            return;

        // get the first result
        JSONObject res = obj.getJSONArray("results").getJSONObject(0);
        System.out.println(res.getString("formatted_address"));
        JSONObject loc =
                res.getJSONObject("geometry").getJSONObject("location");
        System.out.println("lat: " + loc.getDouble("lat") +
                ", lng: " + loc.getDouble("lng"));
    }
}
