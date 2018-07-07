package com.poodel.database_manager.table_entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
@DatabaseTable(tableName = "EXPENSES")
public class Expenses {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "ID")
    private Date date;
    @DatabaseField
    private double amount;
    @DatabaseField
    private String currency;
    @DatabaseField
    private String description;

    public Expenses(){

    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getCurrency() {
        return currency;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }





}
