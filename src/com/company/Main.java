package com.company;

import com.frame.Frame;
import com.DB.DBManagement;

import java.sql.SQLException;

public class Main {
    public static Integer USER_ID = null;
    public static String USERNAME = null;
    public static boolean PERSON;
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