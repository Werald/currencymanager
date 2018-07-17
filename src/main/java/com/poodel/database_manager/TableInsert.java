package com.poodel.database_manager;

import java.sql.*;

/**
 * Класс-Реализация "add" - добавление в БД данных расходов.
 */
public class TableInsert {

    /**
     * Функция добавляющая записи в БД.
     *
     * @param Date - дата расхода
     * @param Ammount - сумма расхода
     * @param Currency - валюта расхода
     * @param Description - описание расхода
     */
    public void addRecord(String Date, String Ammount, String Currency, String Description) {

        try( Connection c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
             Statement stmt = c.createStatement()
        ) {
             stmt.executeUpdate("INSERT INTO EXPENSES (DATE, AMOUNT, CURRENCY, DESCRIPTION)" +
                    "VALUES ('" + Date + "', '" + Ammount + "', '" + Currency + "', '" + Description + "')");
            TableList tableList = new TableList();
            tableList.displayExpenses();

        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }
}
