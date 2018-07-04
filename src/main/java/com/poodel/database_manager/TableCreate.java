package com.poodel.database_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TableCreate {

    public static void createTable(){
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS EXPENSES" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                    "DATE DATE NOT NULL, " +
                    "AMOUNT DOUBLE NOT NULL," +
                    "CURRENCY CHAR NOT NULL," +
                    "DESCRIPTION TEXT NOT NULL)";

            stmt.execute(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
            System.out.println("db was created successfurry");
    }
}
