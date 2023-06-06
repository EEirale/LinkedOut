package com.frame.pages;

import com.frame.Actioner;
import com.frame.Frame;

import javax.swing.*;

public class Pages {
    public static final String MENU = "src/com/frame/pages/menu.xml";
    public static final String HOME = "src/com/frame/pages/page-home.xml";
    public static final String SIGN_UP = "src/com/frame/pages/page-sign-in.xml";
    public static final String POST = "src/com/frame/pages/post.xml";

    public static void buildPostPage(){

        Frame.centerPanel.setLayout(new BoxLayout(Frame.centerPanel, BoxLayout.Y_AXIS));
        Actioner.refresh(Frame.centerPanel);
    }
}
