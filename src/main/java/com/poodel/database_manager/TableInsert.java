package com.poodel.database_manager;

import java.sql.*;

/**
 * Класс-Реализация "add" - добавление в БД данных расходов.
 */
public class TableInsert {

    private Connection c = null;
    private Statement stmt = null;

    /**
     * Функция добавляющая записи в БД.
     *
     * @param Date - дата расхода
     * @param Ammount - сумма расхода
     * @param Currency - валюта расхода
     * @param Description - описание расхода
     */
    public void addRecord(String Date, String Ammount, String Currency, String Description) {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
            stmt = c.createStatement();
            stmt.executeUpdate("INSERT INTO EXPENSES (DATE, AMOUNT, CURRENCY, DESCRIPTION)" +
                    "VALUES ('" + Date + "', '" + Ammount + "', '" + Currency + "', '" + Description + "')");
            stmt.close();
            c.close();

            TableList tableList = new TableList();
            tableList.displayExpenses();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
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
