package com.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser extends DBManagement{
    public static void addUser(){

    }

    public static boolean validateUser(String email, String password) throws SQLException {
        Integer check = null;
        try {
            createConnection();

            script="SELECT COUNT(*) FROM gece_users WHERE 1=1 " +
                    "AND Email = '"+ email +"' " +
                    "AND Password = '"+ password +"'";

            ResultSet output = statement.executeQuery(script);
            output.next();
            check = output.getInt("COUNT(*)");

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (check != null && check != 0);
    }

    public static Integer getId(String email) throws SQLException {
        try {
            createConnection();

            script="SELECT ID FROM gece_users WHERE 1=1 " +
                    "AND Email = '"+ email +"' ";

            ResultSet output = statement.executeQuery(script);
            output.next();
            return output.getInt("ID");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
