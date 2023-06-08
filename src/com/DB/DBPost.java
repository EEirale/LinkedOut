package com.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class DBPost extends DBManagement{
    public static List<Dictionary<String, String>> getPosts(){
        List<Dictionary<String, String>> posts = new ArrayList<>();
        try {
            createConnection();

            script="SELECT  gece_posts.ID, gece_people.name AS N, gece_people.surname AS S, gece_companies.name AS C,  gece_usersposts.PublicationDate, gece_posts.description, gece_posts.tags\n" +
                    "FROM gece_posts \n" +
                    "JOIN gece_usersposts ON gece_posts.ID = gece_usersposts.IdPost \n" +
                    "JOIN gece_users ON gece_usersposts.IdUser = gece_users.ID \n" +
                    "LEFT OUTER JOIN gece_companies ON gece_companies.IdUser = gece_users.ID\n" +
                    "LEFT OUTER JOIN gece_people ON gece_users.ID = gece_people.IdUser ";

            ResultSet output = statement.executeQuery(script);
            while (output.next()){
                String _user = (output.getString("C") != null ? output.getString("C") : "");
                _user += (output.getString("N") != null ? output.getString("N") : "");
                _user += (output.getString("S") != null ? " " + output.getString("S") : "");

                Dictionary<String, String> dict = new Hashtable<>();

                dict.put("userLabel", _user);
                dict.put("dateLabel", output.getString("PublicationDate"));
                dict.put("postLabel", output.getString("description"));
                dict.put("tagLabel", output.getString("tags"));
                dict.put("IDlabel", output.getString("ID"));

                posts.add(dict);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

//    public static void insertPost(String text, String tags, Date date, Integer id){
//        try {
//            createConnection();
//
//            script="INSERT INTO gece_posts (description, tags, Password) " +
//                    "VALUES (" +
//                    "'"+ email +"', " +
//                    "'"+ Date.toString() + "', " +
//                    "'"+ password +"')";
//
//            statement.executeUpdate(script);
//
//            closeConnection();
//        } catch(SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
}
