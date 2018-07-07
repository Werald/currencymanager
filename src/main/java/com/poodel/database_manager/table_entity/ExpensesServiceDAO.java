package com.poodel.database_manager.table_entity;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.poodel.database_manager.TableClear;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;

public class ExpensesServiceDAO {
    private final String url = "jdbc:sqlite:expenses2.db";
    private ConnectionSource source;
    private Dao<Expenses, String> expensesDAO ;
    
    public ExpensesServiceDAO() throws SQLException{
        source = new JdbcConnectionSource(url);
        expensesDAO = DaoManager.createDao(source, Expenses.class);
        TableUtils.clearTable(source, Expenses.class);




    }

    public List<Expenses> getAll() throws SQLException{
        return expensesDAO.queryForAll();
    }

    public static void main(String[] args) throws Exception {
        final String url = "jdbc:sqlite:expenses2.db";
        ConnectionSource source;
        Dao<Expenses, String> expensesDAO ;

        source = new JdbcConnectionSource(url);
        expensesDAO = DaoManager.createDao(source, Expenses.class);
       // TableUtils.clearTable(source, Expenses.class);

        Expenses expenses = new Expenses();


        expenses.setAmount(1.0);
        expenses.setCurrency("EUR");
        expenses.setDescription("BEEEER");
        expensesDAO.create(expenses);


        source.close();
    }
}


