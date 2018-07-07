package com.poodel.database_manager;

import sun.util.resources.el.CalendarData_el;

import java.lang.ref.SoftReference;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TableList {

    Connection c = null;
    Statement stmt = null;

    public void displayExpenses(){

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            c.setAutoCommit(true);
//            System.out.println("Opened database successfully");
            ArrayList<String> dates = new ArrayList();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EXPENSES GROUP BY DATE; ");

            while (rs.next()){
               //System.out.println(new SimpleDateFormat ("yyyy-MM-dd").parse(rs.getString("DATE")));
                    dates.add(rs.getString("DATE"));
//                System.out.println(rs.getString("DATE") + " ");
//                System.out.print( rs.getString("DESCRIPTION") + " "
//                                + rs.getDouble("AMOUNT") + " "
//                                + rs.getString("CURRENCY") + "\n\n");
            }
            rs.close();

            for (String date: dates) {
                System.out.println(date );
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM EXPENSES  WHERE DATE='"+date+"'ORDER BY DATE;");
                while (rs1.next()) {
                    System.out.print(rs1.getString("DESCRIPTION") + " "
                            + rs1.getDouble("AMOUNT") + " "
                            + rs1.getString("CURRENCY") + "\n");
                }
                rs1.close();
                System.out.println("\n");
            }

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
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

    }
//    public static void main(String sd[]){
//        TableList tableList = new TableList();
//        tableList.displayExpenses();
//    }

}


