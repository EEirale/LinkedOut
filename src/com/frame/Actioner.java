package com.frame;

import com.utils.Pages;

public class Actioner {
    public static void home() {

        Frame.buttons.get("signInButton").addActionListener(l->{
            System.out.println("Clicked Sign IN");
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
