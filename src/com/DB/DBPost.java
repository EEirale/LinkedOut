package com.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                dict.put("IDLabel", output.getString("ID"));

                posts.add(dict);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public static Integer getPostId(String text, String tags){
        Integer id = null;
        try {
            createConnection();

            script= "SELECT ID FROM gece_posts WHERE gece_posts.Description = '" + text + "' AND gece_posts.tags = '" + tags +"'";

            ResultSet output = statement.executeQuery(script);
            output.next();
            id =  output.getInt("ID");
            closeConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return id;
    }

    public static void insertPost(String text, String tags){
        try {
            createConnection();

            script= "INSERT INTO gece_posts (description, tags) " +
                    "VALUES ('"+ text +"', '"+ tags +"'); ";
            statement.executeUpdate(script);

            closeConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertUserPost(String text, String tags, Date date,Integer post, Integer id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String _date = sdf.format(date);
        try {
            createConnection();

            script = "INSERT INTO gece_usersposts (IdPost, IdUser, PublicationDate) " +
                    "VALUES( " + post +", " + id +", " +
                    "'"+ _date +"' " + ");";
            statement.executeUpdate(script);

            closeConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
