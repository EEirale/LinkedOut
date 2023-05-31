package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManagement {
    private final static String USER = "Cocito";
    private final static String PASSWORD = "Cocito2022";

    protected static Connection connection;
    protected static Statement statement;
    protected static String script;

    public static void createConnection() throws SQLException {

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_linkedout", USER, PASSWORD);
        statement = connection.createStatement();
    }

    public static void closeConnection() throws SQLException {
        statement.close();
        connection.close();
    }

}
