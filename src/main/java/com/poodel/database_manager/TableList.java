package com.poodel.database_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Класс-Реализация "list" - запрашивание и отображение в консоль всех записей расходов из БД.
 */
public class TableList {

    /**
     * Функция, отображающая все записи в БД
     */
    public static void displayExpenses(){

        try(Connection c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
            Statement stmt = c.createStatement()
        ) {
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
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}/*2017-04-25
        Jogurt 2 USD
        French Fries 3 EUR
        2017-04-26
        Sweets 2.5 PLN
        2017-04-27
        Beer 4.75 EUR
        *///:~


