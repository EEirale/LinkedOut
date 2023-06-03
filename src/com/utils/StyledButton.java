package com.utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class StyledButton extends JButton {
    private final  Color ON_HOVER = new Color(67, 170, 248);

    public StyledButton(String text){
        this.setText(text);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
        this.setSize();
    }
    public StyledButton(String text, Icon icon){
        this.setText(text);
        this.setIcon(icon);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
        this.setSize();

    }
    public StyledButton(String text, Color background, Color foreground){
        this.setText(text);
        this.setBackground(background);
        this.setForeground(foreground);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
        this.setSize();

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                changeBackground(ON_HOVER);
            }


            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                changeBackground(background);
            }
        });
    }

    public StyledButton(String text, Icon icon, Color background, Color foreground){
        this.setText(text);
        this.setIcon(icon);
        this.setBackground(background);
        this.setForeground(foreground);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
        this.setSize();

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                changeBackground(ON_HOVER);
            }


            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                changeBackground(background);
            }
        });
    }

    public StyledButton(String text, Color background, Color foreground, Integer margin){
        this.setText(text);
        this.setBackground(background);
        this.setForeground(foreground);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
        this.setMargin(margin);
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                changeBackground(ON_HOVER);
            }


            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                changeBackground(background);
            }
        });
        //this.setSize();
    }

    public StyledButton(String text, Icon icon, Color background, Color foreground, Integer margin){
        this.setText(text);
        this.setIcon(icon);
        this.setBackground(background);
        this.setForeground(foreground);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
        this.setMargin(margin);
        //this.setSize();
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                changeBackground(ON_HOVER);
            }


            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                changeBackground(background);
            }
        });
    }

    public void setMargin(Integer px){
        this.setBorder(new EmptyBorder(px, px, px, px));
    }

    public void setSize(){
        this.setPreferredSize(new Dimension(100, 30));
    }

    public void changeBackground(Color color){
        this.setBackground(color);
    }

}
