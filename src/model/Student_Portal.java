package model;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import global.*;
import ui.*;

public class Student_Portal extends JFrame implements ActionListener {

    JPanel[] panels = new JPanel[]{
        new JPanel(),
        new JPanel()
    };

    JButton[] buttons = new JButton[]{
        new JButton("View Result"),
        new JButton("View Grades (Subject Wise)"),
        new JButton("View Marks (Subject Wise)"),
        new JButton("Update Password"),
        new JButton("Log Out"),
        new JButton("Exit")
    };

    private String studentID;

    public Student_Portal(String studentID) {

        this.studentID = studentID;
        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

        }

        else if (e.getSource() == buttons[1]) {

        }

        else if (e.getSource() == buttons[2]) {

        }

        else if (e.getSource() == buttons[3]) {

        }

        else if (e.getSource() == buttons[4]) {

            try {

                FileWriter fw = new FileWriter(Global_Variables.CURRENT_SESSION);
                fw.write("");
                fw.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            new Global_Functions().clearScreen(this);
            new Main_Menu();

        }

        else {

            new Global_Functions().exitApp(this);

        }

    }

    private void createButtons() {

        for(int i=0; i<buttons.length; i++){

            buttons[i].setFocusPainted(false);
            buttons[i].setFont(Global_Variables.btnFont);
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].addActionListener(this);
            buttons[i].setBounds(96, 200+(40*i), 320, 30);
            panels[0].add(buttons[i]);

        }

    }

    private void createMenu() {
        new Global_Functions().createPanels(this,panels);
        createButtons();
        new Global_Functions().createMainFrame(this);
    }

    public static void main(String[] args) {
        new Student_Portal("");
    }
}
