package model;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import global.*;
import teacher.*;
import ui.*;

public class Teacher_Portal extends JFrame implements ActionListener {

    JPanel leftPanel, rightPanel;
    JButton addStudent, searchStudent, showAllStudent, enterGrades, viewStudentReport, viewAllReport, logOut, exit;

    public Teacher_Portal() {

        createLeftPanel();
        createRightPanel();
        createButtons();
        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addStudent) {

            setVisible(false);
            dispose();
            new Add_Student();

        }

        else if (e.getSource() == searchStudent) {

        }

        else if (e.getSource() == showAllStudent) {

            setVisible(false);
            dispose();
            new Show_All_Student("showAllStudent");

        }

        else if (e.getSource() == enterGrades) {

            setVisible(false);
            dispose();
            new Show_All_Student("enterGrades");

        }

        else if (e.getSource() == viewStudentReport) {

        }

        else if (e.getSource() == viewAllReport) {

            setVisible(false);
            dispose();
            new View_All_Student_Report();

        }

        else if (e.getSource() == logOut) {

            try {

                FileWriter fw = new FileWriter(Global_Variables.CURRENT_SESSION);
                fw.write("");
                fw.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            setVisible(false);
            dispose();

            new Main_Menu();

        }

        else {

            System.exit(0);

        }

    }

    public void showButtonsVisible(boolean show) {
        addStudent.setVisible(show);
        searchStudent.setVisible(show);
        showAllStudent.setVisible(show);
        enterGrades.setVisible(show);
        viewStudentReport.setVisible(show);
        viewAllReport.setVisible(show);
        logOut.setVisible(show);
        exit.setVisible(show);
    }

    private void createButtons() {
        addStudent = new JButton("Add Student");
        searchStudent = new JButton("Search Student");
        showAllStudent = new JButton("Show All Student");
        enterGrades = new JButton("Enter Grades");
        viewStudentReport = new JButton("View Student Report");
        viewAllReport = new JButton("View All Student Report");
        logOut = new JButton("Log Out");
        exit = new JButton("Exit");

        addStudent.setBounds(106, 200, 300, 30);
        searchStudent.setBounds(106, 200 + (40 * 1), 300, 30);
        showAllStudent.setBounds(106, 200 + (40 * 2), 300, 30);
        enterGrades.setBounds(106, 200 + (40 * 3), 300, 30);
        viewStudentReport.setBounds(106, 200 + (40 * 4), 300, 30);
        viewAllReport.setBounds(106, 200 + (40 * 5), 300, 30);
        logOut.setBounds(106, 200 + (40 * 6), 300, 30);
        exit.setBounds(106, 200 + (40 * 7), 300, 30);

        addStudent.setFocusPainted(false);
        searchStudent.setFocusPainted(false);
        showAllStudent.setFocusPainted(false);
        enterGrades.setFocusPainted(false);
        viewStudentReport.setFocusPainted(false);
        viewAllReport.setFocusPainted(false);
        logOut.setFocusPainted(false);
        exit.setFocusPainted(false);

        addStudent.setFont(Global_Variables.btnFont);
        searchStudent.setFont(Global_Variables.btnFont);
        showAllStudent.setFont(Global_Variables.btnFont);
        enterGrades.setFont(Global_Variables.btnFont);
        viewStudentReport.setFont(Global_Variables.btnFont);
        viewAllReport.setFont(Global_Variables.btnFont);
        logOut.setFont(Global_Variables.btnFont);
        exit.setFont(Global_Variables.btnFont);

        addStudent.setForeground(Color.WHITE);
        searchStudent.setForeground(Color.WHITE);
        showAllStudent.setForeground(Color.WHITE);
        enterGrades.setForeground(Color.WHITE);
        viewStudentReport.setForeground(Color.WHITE);
        viewAllReport.setForeground(Color.WHITE);
        logOut.setForeground(Color.WHITE);
        exit.setForeground(Color.WHITE);

        addStudent.setBackground(Color.BLACK);
        searchStudent.setBackground(Color.BLACK);
        showAllStudent.setBackground(Color.BLACK);
        enterGrades.setBackground(Color.BLACK);
        viewStudentReport.setBackground(Color.BLACK);
        viewAllReport.setBackground(Color.BLACK);
        logOut.setBackground(Color.BLACK);
        exit.setBackground(Color.BLACK);

        leftPanel.add(addStudent);
        leftPanel.add(searchStudent);
        leftPanel.add(showAllStudent);
        leftPanel.add(enterGrades);
        leftPanel.add(viewStudentReport);
        leftPanel.add(viewAllReport);
        leftPanel.add(logOut);
        leftPanel.add(exit);

        addStudent.addActionListener(this);
        searchStudent.addActionListener(this);
        showAllStudent.addActionListener(this);
        enterGrades.addActionListener(this);
        viewStudentReport.addActionListener(this);
        viewAllReport.addActionListener(this);
        logOut.addActionListener(this);
        exit.addActionListener(this);
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(0, 0, 512, Global_Variables.WINDOW_HEIGHT);
        leftPanel.setForeground(Color.BLACK);
        leftPanel.setBackground(Color.WHITE);
        add(leftPanel);
    }

    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(513, 0, 512, Global_Variables.WINDOW_HEIGHT);
        rightPanel.setForeground(Color.WHITE);
        rightPanel.setBackground(Color.BLACK);
        add(rightPanel);
    }

    private void createMenu() {
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setUndecorated(true);
        setLocation(Global_Variables.X_POSITION, Global_Variables.Y_POSITION);
        setSize(Global_Variables.WINDOW_WIDTH, Global_Variables.WINDOW_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Teacher_Portal();
    }

}
