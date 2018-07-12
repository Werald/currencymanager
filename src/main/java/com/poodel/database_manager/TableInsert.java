package com.poodel.database_manager;

import java.sql.*;

/**
 * Класс-Реализация "add" - добавление в БД данных расходов.
 */
public class TableInsert {


    /**
     * Функция добавляющая записи в БД.
     *
     * @param Date дата расхода
     * @param Ammount сумма расхода
     * @param Currency валюта расхода
     * @param Description описание расхода
     */
    public void addRecord(String Date, String Ammount, String Currency, String Description) {

        try(Connection c = DriverManager.getConnection("jdbc:sqlite:expenses.db")) {
            Class.forName("org.sqlite.JDBC");

            Statement stmt = c.createStatement();
            stmt.executeUpdate("INSERT INTO EXPENSES (DATE, AMOUNT, CURRENCY, DESCRIPTION)" +
                    "VALUES ('" + Date + "', '" + Ammount + "', '" + Currency + "', '" + Description + "')");
            stmt.close();
            c.close();

            TableList tableList = new TableList();
            tableList.displayExpenses();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }
}/* Output:
        2017-04-25
        Jogurt 2 USD
        French Fries 3 EUR
        2017-04-26
        Sweets 2.5 PLN
        2017-04-27
        Beer 4.75 EUR
*///:~
