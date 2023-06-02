package com.frame;

public class Actioner {
    public static void setActionListeners() {
        Frame.buttons.get("button1").addActionListener(l -> {
            System.out.println("Clicked");
        });
    }
}
