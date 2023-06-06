package com.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.EnumMap;

public class DBUser extends DBManagement{
    public static void insertUser(String email, String password, Date Date){
        try {
            createConnection();

            script="INSERT INTO gece_users (email, DateOfSubscription, Password) " +
                    "VALUES (" +
                    "'"+ email +"', " +
                    "'"+ Date.toString() + "', " +
                    "'"+ password +"')";

            statement.executeUpdate(script);

            closeConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertPerson(String name, String surname, Date Date, String occupation, String location, Integer UserId){
        try {
            createConnection();

            script="INSERT INTO gece_people (name, surname, DateOfBirth, Occupation, Location, IdUser) " +
                    "VALUES (" +
                    "'"+ name +"'," +
                    " '"+ surname +"'," +
                    " '"+ Date.toString() +"'," +
                    " '"+ occupation +"'," +
                    " '"+ location +"'," +
                    " " + UserId + ")";

            statement.executeUpdate(script);

            closeConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertCompany(String name, String Description, Integer EmployeesNumber, String location, Integer UserId){
        try {
            createConnection();

            script="INSERT INTO gece_people (name, Description, EmployeesNumber, Location, IdUser) " +
                    "VALUES (" +
                    "'"+ name +"'," +
                    " '"+ Description +"'," +
                    " "+ EmployeesNumber +"," +
                    " '"+ location +"'," +
                    " " + UserId + ")";

            statement.executeUpdate(script);

            closeConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertCompany(String name, String Description, Integer EmployeesNumber, Integer UserId){
        try {
            createConnection();

            script="INSERT INTO gece_people (name, Description, EmployeesNumber, Location, IdUser) " +
                    "VALUES (" +
                    "'"+ name +"'," +
                    " '"+ Description +"'," +
                    " "+ EmployeesNumber +"," +
                    " " + UserId + ")";

            statement.executeUpdate(script);

            closeConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static String getUserName(Integer ID){
        String _user  = null;
            try {
                createConnection();

                script="SELECT gece_companies.name AS C, gece_people.name AS N, gece_people.surname AS S " +
                        "FROM gece_users " +
                        "LEFT OUTER JOIN gece_companies ON gece_companies.IdUser = gece_users.ID " +
                        "LEFT OUTER JOIN gece_people ON gece_users.ID = gece_people.IdUser " +
                        "WHERE gece_users.ID = " + ID;

                ResultSet output = statement.executeQuery(script);
                output.next();
                 _user = (output.getString("C") != null ? output.getString("C") : "");
                _user += (output.getString("N") != null ? output.getString("N") : "");
                _user += (output.getString("S") != null ? " " + output.getString("S") : "");

                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return _user;
    }

    public static boolean validatePerson(Integer ID){
        Integer check = null;

        try {
            createConnection();

            script="SELECT COUNT(*) FROM gece_people WHERE 1=1 " +
                    "AND IdUser = '"+ ID +"' ";

            ResultSet output = statement.executeQuery(script);
            output.next();
            check = output.getInt("COUNT(*)");

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (check != null && check != 0);
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

            script="SELECT ID FROM gece_users WHERE 1=1 AND Email = '"+ email +"'";

            ResultSet output = statement.executeQuery(script);
            output.next();
            return output.getInt("ID");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
