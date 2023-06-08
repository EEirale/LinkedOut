package com.frame;

import com.DB.DBJobOffers;
import com.DB.DBPost;
import com.DB.DBUser;
import com.company.Main;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.List;
import java.util.Objects;
import java.util.Date;

public class Actioner {
    public static void refresh(JPanel panel){
        panel.revalidate();
        panel.repaint();
    }

    public static void gotoPosts(){
        Frame.window.remove(Frame.centerPanel);
        Frame.window.remove(Frame.scrollPane);
        Frame.centerPanel.removeAll();

        Frame.centerPanel.add(Frame.XMLreader("src/com/frame/pages/components/add-post-div.xml", null));

        List<Dictionary<String, String>> posts = DBPost.getPosts();
        for (Dictionary<String, String> post : posts){
            Frame.centerPanel.add(
                    Frame.XMLreader(
                            Pages.POST,
                            post
                    )
            );
        }

        Frame.centerPanel.setLayout(new BoxLayout(Frame.centerPanel, BoxLayout.Y_AXIS));
        Frame.scrollPane.setViewportView(Frame.centerPanel);
        Frame.window.add(Frame.scrollPane);
        refresh(Frame.window);

        Actioner.posts();
    }
    public static void home() {

        Frame.buttons.get("signUpButton").addActionListener(l->{
            System.out.println("Clicked Sign UP");

            signUp();
        });

        Frame.buttons.get("signOutButton").addActionListener(l->{
            Main.USER_ID = null;
            Main.PERSON = false;
            Main.USERNAME = null;

            Frame.labels.get("username").setText(Main.USERNAME);
        });

        Frame.buttons.get("signInButton").addActionListener(l->{
            Frame.window.remove(Frame.centerPanel);
            Frame.centerPanel.removeAll();

            Frame.centerPanel = Frame.XMLreader(Pages.SIGN_IN, null);

            refresh(Frame.centerPanel);

            Frame.window.add(Frame.centerPanel);

            refresh(Frame.window);

            signIn();
        });
    }

    public static void menu(){
        Frame.buttons.get("accountButton").addActionListener(l-> System.out.println("Clicked account"));

        Frame.buttons.get("homeButton").addActionListener(l->{
            Frame.window.remove(Frame.centerPanel);
            Frame.window.remove(Frame.scrollPane);
            Frame.centerPanel.removeAll();
            Frame.centerPanel = Frame.XMLreader(
                    Pages.HOME,
                    null);
            refresh(Frame.centerPanel);
            Frame.window.add(Frame.centerPanel);
            refresh(Frame.window);

            Actioner.home();
        });

        Frame.buttons.get("feedButton").addActionListener(l->{
            Actioner.gotoPosts();
        });

        Frame.buttons.get("jobOffersButton").addActionListener(l->{
            Frame.window.remove(Frame.centerPanel);
            Frame.window.remove(Frame.scrollPane);
            Frame.centerPanel.removeAll();

            List<Dictionary<String, String>> offers = DBJobOffers.getOffers();
            for (Dictionary<String, String> offer : offers){
                Frame.centerPanel.add(
                        Frame.XMLreader(
                                Pages.JOB_OFFER,
                                offer
                        )
                );
            }

            Frame.centerPanel.setLayout(new BoxLayout(Frame.centerPanel, BoxLayout.Y_AXIS));
            Frame.scrollPane.setViewportView(Frame.centerPanel);
            Frame.window.add(Frame.scrollPane);
            refresh(Frame.window);
        });
    }

    public static void signIn(){
        Frame.buttons.get("confirmButton").addActionListener(l->{
            String _password = null;
            String _email = null;

            if(!Objects.equals(Frame.textFields.get("emailField").getText(), "") && Frame.textFields.get("emailField") != null){
                _email = Frame.textFields.get("emailField").getText();
            } else {
                JOptionPane.showMessageDialog(null,
                        "All fields must be filled",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if(!Objects.equals(Frame.textFields.get("passwordField").getText(), "") && Frame.textFields.get("passwordField") != null){
                _password = Frame.textFields.get("passwordField").getText();
            } else {
                JOptionPane.showMessageDialog(null,
                        "All fields must be filled",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            try {
                if(DBUser.validateUser(_email, _password)){
                    JOptionPane.showMessageDialog(null,
                            "Log in successful",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    Main.USER_ID = DBUser.getId(_email);
                    Main.PERSON = DBUser.validatePerson(Main.USER_ID);
                    Main.USERNAME = DBUser.getUserName(Main.USER_ID);

                    Frame.labels.get("username").setText(Main.USERNAME);

                    Frame.textFields.get("passwordField").setText("");
                    Frame.textFields.get("emailField").setText("");
                } else{
                    JOptionPane.showMessageDialog(null,
                            "Email and password do not match, retry",
                            "Error",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        Frame.buttons.get("cancelButton").addActionListener(l->{
            Frame.textFields.get("passwordField").setText("");
            Frame.textFields.get("emailField").setText("");
        });
    }

    public static void signUp(){

    }

    public static void posts(){
        Frame.buttons.get("addPostButton").addActionListener(l->{
            if(Main.USER_ID == null){
                JOptionPane.showMessageDialog(null,
                        "You must be signed in to submit a post",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                Frame.window.remove(Frame.centerPanel);
                Frame.window.remove(Frame.scrollPane);
                Frame.centerPanel.removeAll();
                Frame.centerPanel = Frame.XMLreader(
                        Pages.CREATE_POST,
                        null);
                refresh(Frame.centerPanel);
                Frame.window.add(Frame.centerPanel);
                refresh(Frame.window);

                Actioner.createPost();
            }
        });
    }

    public static void createPost(){
        Frame.buttons.get("backButton").addActionListener(l-> Actioner.gotoPosts());

        Frame.buttons.get("publishButton").addActionListener(l->{
            String _text= null;
            String _tags= null;

            if(!Objects.equals(Frame.textAreas.get("postField").getText(), "") && Frame.textAreas.get("postField") != null){
                _text = Frame.textAreas.get("postField").getText();
            } else {
                JOptionPane.showMessageDialog(null,
                        "All fields must be filled",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            if(!Objects.equals(Frame.textFields.get("tagsField").getText(), "") && Frame.textFields.get("tagsField") != null){
                _tags = Frame.textFields.get("tagsField").getText();
            } else {
                JOptionPane.showMessageDialog(null,
                        "All fields must be filled",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            DBPost.insertPost(_text, _tags);
            DBPost.insertUserPost(_text, _tags, new Date(), DBPost.getPostId(_text, _tags), Main.USER_ID);
            Actioner.gotoPosts();
        });
    }
}
