package com.poodel.database_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreate {

    public static void createTable(){
        Connection c = null;
        Statement stmt = null;

        try {
            // Register JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Open a connection
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            // Execute a query
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS EXPENSES" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                    "DATE DATE NOT NULL, " +
                    "AMOUNT DOUBLE NOT NULL," +
                    "CURRENCY VARCHAR(3) NOT NULL," +
                    "DESCRIPTION VARCHAR NOT NULL)";

            stmt.executeUpdate(sql);
            // Clean-up environment
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }   finally {
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

            System.out.println("db was created successfurry");
    }
}
