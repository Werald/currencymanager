package com.poodel.database_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TableInsert {
    Connection c = null;
    Statement stmt = null;
    public void addRecord(String Date, String Ammount, String Currency, String Description){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            c.setAutoCommit(true);
            System.out.println("db opened successfurry in void addDAte");

            stmt = c.createStatement();
            String sql = "INSERT INTO EXPENSES (DATE, AMOUNT, CURRENCY, DESCRIPTION)"+
                    "VALUES ('" +Date + "', '" +Ammount+ "', '" +Currency+ "', '" +Description+"')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
        System.out.println("records created succesfully");
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
//    public static void main(String []sd){
//        TableInsert da = new TableInsert();
//        da.addRecord("2014-04-25", "1442","USD", "Jeeogurt");
//
//    }
}
