package com.poodel.database_manager;

import java.sql.*;

/**
 * Реализация команды "clear", инициируемая классом commands_implementation.ClearCommand
 * Инстанс класса производит удаление запис(и)-ей из БД, связанных одним значением Date
 */
public class TableClear {

    private Connection c = null;
    private Statement stmt = null;

    /**
     * Функция, удаляющая запись из БД.
     *
     * @param Date - дата расхода, идентификатор по которому удаляются записи.
     */
    public void deleteRecord (String Date) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
            stmt = c.createStatement();
            stmt.executeUpdate("DELETE FROM EXPENSES WHERE DATE='" + Date + "';");
            stmt.close();
            c.close();
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
        TableList tableList = new TableList();
        tableList.displayExpenses();
    }
}
