package com.poodel.fixer_resourses;

import com.poodel.database_manager.TableTotal;
import com.sun.deploy.net.HttpRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class XecdapiCur {
    private HttpClient client = null;

    private static final String URL_TO_SEND =
            "https://xecdapi.xe.com/v1/convert_from.json/?from=USD&to=EUR,PLN,RUB&amount=1";


    private void getRequiredCoursesFromFixer(){
        ArrayList<String> currencys = new ArrayList<>(TableTotal.getAbbreviations());
        HashMap<String, Double> jsonCur = new HashMap<>();

        try
    {
        URL url = new URL("https://xecdapi.xe.com/v1/account_info/");
        HttpRequest get = null;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String userpass = "poodelinc399809255" + ":" + "2crf38oe2900cude699lbg168p";

        conn.setRequestProperty("Authorization", "Basic " + userpass);
        conn.setDoOutput(true);
        get.doHeadRequest(url, new String[]{"Authorization"}, new String[]{"Basic " + userpass});
        conn.setRequestMethod("GET");

        get.doGetRequest(url);
//        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
//        conn.setRequestProperty ("Authorization", basicAuth);
        // read from the URL
        Scanner scan = new Scanner(url.openStream());
        String str = "";
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();
        // build a JSON object
        JSONObject obj = new JSONObject(str);
        // get the result
        JSONArray arr = obj.getJSONArray("to");
        System.out.println(arr.toString());
        System.out.println();

        System.out.println(arr);

//        for (String currency : currencys) {
//            for (int i=0; i<arr.length(); i++){
//
//            }
            //jsonCur.put(currency, new Double(String.valueOf(arr.getDouble(currency))));

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // jsonCur.put(CUR, new Double(String.valueOf(arr.getDouble(CUR))));

//            System.out.println(res);


  //      return jsonCur;

}

public static void main (String d []){
        XecdapiCur xecdapiCur = new XecdapiCur();
        xecdapiCur.getRequiredCoursesFromFixer();
}
}
