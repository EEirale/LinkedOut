package com.frame;

public class Actioner {
    public static void home() {

        Frame.buttons.get("signInButton").addActionListener(l->{
            System.out.println("Clicked Sign IN");
        });

        Frame.buttons.get("signUpButton").addActionListener(l->{
            System.out.println("Clicked Sign UP");
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
}
