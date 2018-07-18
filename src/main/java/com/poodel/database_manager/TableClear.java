package com.poodel.database_manager;

import java.sql.*;

/**
 * Реализация команды "clear", инициируемая классом commands_implementation.ClearCommand
 * Истанс класса производит удаление запис(и)-ей из БД, связанных одним значением Date
 */
public class TableClear {

    /**
     * Функция, удаляющая запись из БД.
     *
     * @param Date - дата расхода, идентификатор по которому удаляются записи.
     */
    public void deleteRecord (String Date) {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:expenses.db");
             Statement stmt = c.createStatement()
        ) {
            stmt.executeUpdate("DELETE FROM EXPENSES WHERE DATE='" + Date + "';");

        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
        TableList.displayExpenses();
    }
}
