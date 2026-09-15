package model;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import global.*;
import teacher.*;
import ui.*;

public class Teacher_Portal extends JFrame implements ActionListener {

    JPanel[] panels = new JPanel[] {
            new JPanel(),
            new JPanel()
    };

    JButton[] buttons = new JButton[]{
        new JButton("Add Student"),
        new JButton("Search Student"),
        new JButton("Show All Student"),
        new JButton("Enter Grades"),
        new JButton("View Student Report"),
        new JButton("View All Student Report"),
        new JButton("Log Out"),
        new JButton("Exit")
    };

    public Teacher_Portal() {

        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

            new Global_Functions().clearScreen(this);
            new Add_Student();

        }

        else if (e.getSource() == buttons[1]) {

        }

        else if (e.getSource() == buttons[2]) {

            new Global_Functions().clearScreen(this);
            new Show_All_Student("showAllStudent");

        }

        else if (e.getSource() == buttons[3]) {

            new Global_Functions().clearScreen(this);
            new Show_All_Student("enterGrades");

        }

        else if (e.getSource() == buttons[4]) {

        }

        else if (e.getSource() == buttons[5]) {

            new Global_Functions().clearScreen(this);
            new View_All_Student_Report();

        }

        else if (e.getSource() == buttons[6]) {

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

            buttons[i].setBounds(106, 200 + (40*i), 300, 30);
            buttons[i].setFocusPainted(false);
            buttons[i].setFont(Global_Variables.btnFont);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBackground(Color.BLACK);
            buttons[i].addActionListener(this);
            panels[0].add(buttons[i]);

        }

    }

    private void createMenu() {

        new Global_Functions().createPanels(this,panels);
        createButtons();
        new Global_Functions().createMainFrame(this);

    }

    public static void main(String[] args) {
        new Teacher_Portal();
    }

}
