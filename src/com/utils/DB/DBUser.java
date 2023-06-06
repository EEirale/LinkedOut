package com.utils.DB;

import java.sql.SQLException;

public class DBUser extends DBManagement{
    public static void addUser(){

    }

    public static boolean validateUser(String email, String password) throws SQLException {
        createConnection();

        closeConnection();

        return false;
    }
}
