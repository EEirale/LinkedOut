package com.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class DBJobOffers extends DBManagement{
    public static List<Dictionary<String, String>> getOffers(){
        List<Dictionary<String, String>> offers = new ArrayList<>();
        try {
            createConnection();

            script="SELECT gece_joboffer.IdOffer, gece_joboffer.Role, gece_joboffer.PublicationDate, gece_joboffer.ContractType, " +
                    "gece_joboffer.Salary, gece_joboffer.RequiredSkills, gece_companies.Name " +
                    "FROM gece_joboffer JOIN gece_companies ON gece_joboffer.IdCompanies = gece_companies.ID";

            ResultSet output = statement.executeQuery(script);
            while (output.next()){
                Dictionary<String, String> dict = new Hashtable<>();

                dict.put("roleLabel", "<html><h2 style='font-size:40px;font-weight:bold;color:black;'>"+ output.getString("Role") +"</h2></html>");
                dict.put("dateLabel", "<html><p style='font-weight:bold;color:black;'>"+ output.getString("PublicationDate") +"</p></html>");
                dict.put("companyLabel", "<html><h4 style='font-size:20px; font-weight:bold;color:black;'>"+ output.getString("Name") +"</h4></html>");
                dict.put("contractLabel", output.getString("ContractType"));
                dict.put("salaryLabel", "$ " + output.getString("Salary"));
                dict.put("requiredSkillsLabel", output.getString("RequiredSkills"));
                dict.put("offerIDLabel", output.getString("IdOffer"));

                offers.add(dict);

            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return offers;
    }
}
