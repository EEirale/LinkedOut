package com.frame;

import com.DB.DBPost;
import com.DB.DBUser;
import com.company.Main;
import com.frame.pages.Pages;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Actioner {
    public static void refresh(JPanel panel){
        panel.revalidate();
        panel.repaint();
    }
    public static void home() {

        Frame.buttons.get("signUpButton").addActionListener(l->{
            System.out.println("Clicked Sign UP");

            signUp();
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
        Frame.buttons.get("accountButton").addActionListener(l->{
            System.out.println("Clicked account");
        });

        Frame.buttons.get("homeButton").addActionListener(l->{
            Frame.window.remove(Frame.centerPanel);
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
            Frame.window.remove(Frame.centerPanel);
            Frame.centerPanel.removeAll();

            List<String[]> posts = DBPost.getPosts();
            for (String[] post : posts){
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
        });

        Frame.buttons.get("jobOffersButton").addActionListener(l->{
            Frame.window.remove(Frame.centerPanel);
            Frame.centerPanel.removeAll();

            // update centerPanel

            refresh(Frame.centerPanel);
            Frame.scrollPane.add(Frame.centerPanel);
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
}
