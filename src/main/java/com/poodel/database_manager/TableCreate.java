package com.poodel.database_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TableCreate {

    public static void main(String atgs[]) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url = "jdbc:oracle:thin:@localhost:1521:javaDemo";
        String username = "root";
        String password = "root";

        String sql = "CREATE TABLE expenses (id NUMBER(11), amount DOUBLE, currency VARCHAR(3), exp_target VARCHAR(100) )";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();
    }

}
