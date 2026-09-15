package model;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import auth.Update_Password;
import global.*;
import service.View_Student_Report;
import ui.*;

public class Student_Portal extends JFrame implements ActionListener {

    JPanel[] panels = new JPanel[]{
        new JPanel(),
        new JPanel()
    };

    JButton[] buttons = new JButton[]{
        new JButton("View Result"),
        new JButton("View Full Result"),
        new JButton("Update Password"),
        new JButton("Log Out"),
        new JButton("Exit")
    };

    private String studentID;
    private String studentName;

    public Student_Portal(String studentName, String studentID) {

        this.studentID = studentID;
        this.studentName = studentName;
        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

            new Global_Functions().clearScreen(this);
            new View_Student_Report(studentName, studentID, "Student Portal", "Short Result");

        }

        else if (e.getSource() == buttons[1]) {

            new Global_Functions().clearScreen(this);
            new View_Student_Report(studentName, studentID, "Student Portal", "Full Result");

        }

        else if (e.getSource() == buttons[2]) {

            new Global_Functions().clearScreen(this);
            new Update_Password(studentName, studentID);

        }

        else if (e.getSource() == buttons[3]) {

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
        new Global_Functions().createPanels(this,panels,"Student Portal");
        createButtons();
        new Global_Functions().createMainFrame(this);
    }

    public static void main(String[] args) {
        new Student_Portal("","");
    }
}
