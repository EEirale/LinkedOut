package com.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBJobOffers extends DBManagement{
    public static List<String[]> getOffers(){
        List<String[]> posts = new ArrayList<>();
        try {
            createConnection();

            script="SELECT gece_joboffer.Role, gece_joboffer.PublicationDate, gece_joboffer.ContractType, " +
                    "gece_joboffer.Salary, gece_joboffer.RequiredSkills, gece_companies.Name " +
                    "FROM gece_joboffer JOIN gece_companies ON gece_joboffer.IdCompanies = gece_companies.ID";

            ResultSet output = statement.executeQuery(script);
            while (output.next()){
                posts.add(
                        new String[] {
                                "<html><h2 style='font-size:40px;font-weight:bold;color:black;'>"+ output.getString("Role") +"</h2></html>",
                                "<html><p style='font-weight:bold;color:black;'>"+ output.getString("PublicationDate") +"</p></html>",
                                "<html><h4 style='font-size:20px; font-weight:bold;color:black;'>"+ output.getString("Name") +"</h4></html>",
                                output.getString("ContractType"),
                                "$ " + output.getString("Salary"),
                                output.getString("RequiredSkills"),
                        });
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }
}
