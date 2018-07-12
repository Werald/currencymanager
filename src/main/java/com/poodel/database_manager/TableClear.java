package com.poodel.database_manager;

import java.sql.*;

/**
 * Реализация команды "clear", инициируемая классом
 * @see com.poodel.commands.commands_implementation.ClearCommand
 * Истанс класса производит удаление запис(и)-ей из БД, связанных одним значением Date
 */
public class TableClear {

    /**
     * Функция, удаляющая запись из БД.
     *
     * @param Date дата расхода, идентификатор по которому удаляются записи.
     */
    public void deleteRecord (String Date) {
        try(Connection c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
        ) {
            Class.forName("org.sqlite.JDBC");
            Statement stmt = c.createStatement();
            stmt.executeUpdate("DELETE FROM EXPENSES WHERE DATE='" + Date + "';");
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
        TableList tableList = new TableList();
        tableList.displayExpenses();
    }
}
