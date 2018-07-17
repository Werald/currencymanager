package com.poodel.database_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Класс, создающий БД и таблицу с полями в момент запуска main()-нити,
 * если на момент старта БД/таблицы не существует.
 */
public class TableCreate {

    /**
     * Метод, создающий и размечающий БД
     */
    public static void createTable() {

        try ( Connection c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
              Statement stmt = c.createStatement()
        ) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS EXPENSES" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                    "DATE DATE NOT NULL, " +
                    "AMOUNT DOUBLE NOT NULL," +
                    "CURRENCY VARCHAR(3) NOT NULL," +
                    "DESCRIPTION VARCHAR NOT NULL)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
