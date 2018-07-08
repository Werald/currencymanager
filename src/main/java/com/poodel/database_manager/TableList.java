package com.poodel.database_manager;

import java.sql.*;
import java.util.ArrayList;

/**
 * Класс-Реализация "list" - запрашивание и отображение в консоль всех записей расходов из БД.
 */
public class TableList {

    private Connection c = null;
    private Statement stmt = null;


    public void displayExpenses(){

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
            stmt = c.createStatement();

            ArrayList<String> dates = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EXPENSES GROUP BY DATE; ");
            while (rs.next()){
                    dates.add(rs.getString("DATE"));
            }
            rs.close();

            for (String date: dates) {
                System.out.println(date);
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM EXPENSES  WHERE DATE='"+date+"'ORDER BY DATE;");
                while (rs1.next()) {
                    System.out.print( rs1.getString("DESCRIPTION").replace("\"", "") + " "
                                    + rs1.getDouble("AMOUNT") + " "
                                    + rs1.getString("CURRENCY") + "\n");
                }
                rs1.close();
                System.out.println();
            }
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
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
    }
}


