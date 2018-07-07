package com.poodel.database_manager;

import java.sql.*;

public class TableClear {

    private Connection c = null;
    private Statement stmt = null;
    public void deleteRecord (String Date) {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "DELETE FROM EXPENSES WHERE DATE='" + Date + "';";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
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

        TableList tableList = new TableList();
        tableList.displayExpenses();
    }
}
