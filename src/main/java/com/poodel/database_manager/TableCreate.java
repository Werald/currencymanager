package com.poodel.database_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс, создающий БД и таблицу с полями в момент запуска main()-нити,
 * если на момент старта экземпляр БД/таблицы не существует.
 */
public class TableCreate {

    /**
     * Метод, создающий и размечающий БД
     */
    public static void createTable(){
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
            stmt = c.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS EXPENSES" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                    "DATE DATE NOT NULL, " +
                    "AMOUNT DOUBLE NOT NULL," +
                    "CURRENCY VARCHAR(3) NOT NULL," +
                    "DESCRIPTION VARCHAR NOT NULL)");
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        finally {
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
