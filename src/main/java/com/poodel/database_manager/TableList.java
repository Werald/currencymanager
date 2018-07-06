package com.poodel.database_manager;

import sun.util.resources.el.CalendarData_el;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class TableList {

    Connection c = null;
    Statement stmt = null;

    public void displayExpenses(){

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EXPENSES GROUP BY DATE; ");

            while (rs.next()){
               //System.out.println(new SimpleDateFormat ("yyyy-MM-dd").parse(rs.getString("DATE")));
                System.out.println(rs.getString("DATE") + " ");
                System.out.print( rs.getString("DESCRIPTION") + " "
                                + rs.getDouble("AMOUNT") + " "
                                + rs.getString("CURRENCY") + "\n\n");
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("List void done successfurry");

    }
//    public static void main(String sd[]){
//        TableList tableList = new TableList();
//        tableList.displayExpenses();
//    }

}


