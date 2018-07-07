package com.poodel.database_manager;

import java.sql.*;

public class TableInsert {

    private Connection c = null;
    private Statement stmt = null;

    public void addRecord(String Date, String Ammount, String Currency, String Description) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            c.setAutoCommit(true);

            stmt = c.createStatement();
            String sql = "INSERT INTO EXPENSES (DATE, AMOUNT, CURRENCY, DESCRIPTION)" +
                    "VALUES ('" + Date + "', '" + Ammount + "', '" + Currency + "', '" + Description + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

            TableList tableList = new TableList();
            tableList.displayExpenses();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        } finally {
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
}
