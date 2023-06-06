package com.frame;

import com.DB.DBUser;
import com.company.Main;
import com.utils.Pages;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;

public class Actioner {
    public static void home() {

        Frame.buttons.get("signInButton").addActionListener(l->{
            System.out.println("Clicked Sign IN");

            signIn();
        });

        Frame.buttons.get("signUpButton").addActionListener(l->{
            Frame.window.remove(Frame.centerPanel);
            Frame.centerPanel.removeAll();
            Frame.centerPanel = Frame.XMLreader(Pages.SIGN_UP, null);

            Frame.centerPanel.revalidate();
            Frame.centerPanel.repaint();

            Frame.window.add(Frame.centerPanel);

            Frame.window.revalidate();
            Frame.window.repaint();

            singUp();
        });
    }

    public static void menu(){
        Frame.buttons.get("accountButton").addActionListener(l->{
            System.out.println("Clicked account");
        });
        Frame.buttons.get("feedButton").addActionListener(l->{
            System.out.println("Clicked feed");
        });
        Frame.buttons.get("jobOffersButton").addActionListener(l->{
            System.out.println("Clicked job offers");
        });
    }

    public static void singUp(){
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

    public static void signIn(){

    }
}
