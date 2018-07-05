package com.poodel.database_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TableClear {

    Connection c = null;
    Statement stmt = null;
    public void deleteRecord (String Date){

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expenses1.db");
            c.setAutoCommit(false);
            System.out.println("db opened successfurry in void Clear");

            stmt = c.createStatement();
            String sql = "DELETE FROM EXPENSES WHERE DATE='"+Date+"';";
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }

        TableList tableList = new TableList();
        tableList.displayExpenses();

        System.out.println("records deleted succesfurry");
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
//    public static void main(String []sd){
//        TableClear da = new TableClear();
//        da.deleteRecord("2017-04-25");
//    }
}
