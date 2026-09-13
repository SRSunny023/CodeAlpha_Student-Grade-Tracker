package ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Global.*;

public class Main_Menu extends JFrame implements ActionListener {

    JButton sP,tP;

    public Main_Menu(){

        sP = new JButton("Student Portal");
        tP = new JButton("Teacher Portal");

        sP.setBounds(412,269,200,30);
        tP.setBounds(412,469,200,30);

        sP.setFont(Global_Variables.btnFont);
        tP.setFont(Global_Variables.btnFont);

        sP.setForeground(Color.WHITE);
        tP.setForeground(Color.WHITE);

        sP.setBackground(Color.BLACK);
        tP.setBackground(Color.BLACK);

        sP.setFocusPainted(false);
        tP.setFocusPainted(false);

        sP.addActionListener(this);
        tP.addActionListener(this);

        add(sP);
        add(tP);


        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setUndecorated(true);
        setLocation(Global_Variables.X_POSITION, Global_Variables.Y_POSITION);
        setSize(Global_Variables.WINDOW_WIDTH, Global_Variables.WINDOW_HEIGHT);
        setVisible(true);setSize(Global_Variables.WINDOW_WIDTH, Global_Variables.WINDOW_HEIGHT);

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==sP){

            setVisible(false);
            dispose();

            //

        }

        else if(e.getSource()==tP){

            setVisible(false);
            dispose();

            //

        }
    }

    public static void main(String[] args){
        new Main_Menu();
    }

}
