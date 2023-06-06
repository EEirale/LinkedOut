package com.company;

import com.frame.Frame;
import com.utils.DB.DBManagement;

import java.sql.SQLException;

public class Main {
    public static final String USER_NAME = "";
    public static Frame App;
    public static void main(String[] args) {
        App = new Frame();

        try {
            DBManagement.createConnection();
            DBManagement.closeConnection();

            System.out.println("Conncection established");
        } catch (SQLException e) {
            System.out.println("Conncection failed: ");
            e.printStackTrace();
        }
    }
}