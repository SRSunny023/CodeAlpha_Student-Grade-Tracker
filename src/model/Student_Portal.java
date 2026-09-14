package model;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import global.*;
import ui.*;

public class Student_Portal extends JFrame implements ActionListener {

    JPanel leftPanel, rightPanel;
    JButton viewResult, viewGradesSW, viewMarksSW, updatePassword, logOut, exit;

    private String studentID;

    public Student_Portal(String studentID) {

        this.studentID = studentID;

        createLeftPanel();
        createRightPanel();
        createButtons();
        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewResult) {

        }

        else if (e.getSource() == viewGradesSW) {

        }

        else if (e.getSource() == viewMarksSW) {

        }

        else if (e.getSource() == updatePassword) {

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

    private void createButtons() {
        viewResult = new JButton("View Result");
        viewGradesSW = new JButton("View Grades (Subject Wise)");
        viewMarksSW = new JButton("View Marks (Subject Wise)");
        updatePassword = new JButton("Update Password");
        logOut = new JButton("Log Out");
        exit = new JButton("Exit");

        viewResult.setBounds(96, 200, 320, 30);
        viewGradesSW.setBounds(96, 200 + (40 * 1), 320, 30);
        viewMarksSW.setBounds(96, 200 + (40 * 2), 320, 30);
        updatePassword.setBounds(96, 200 + (40 * 3), 320, 30);
        logOut.setBounds(96, 200 + (40 * 4), 320, 30);
        exit.setBounds(96, 200 + (40 * 5), 320, 30);

        viewResult.setFocusPainted(false);
        viewGradesSW.setFocusPainted(false);
        viewMarksSW.setFocusPainted(false);
        updatePassword.setFocusPainted(false);
        logOut.setFocusPainted(false);
        exit.setFocusPainted(false);

        viewResult.setFont(Global_Variables.btnFont);
        viewGradesSW.setFont(Global_Variables.btnFont);
        viewMarksSW.setFont(Global_Variables.btnFont);
        updatePassword.setFont(Global_Variables.btnFont);
        logOut.setFont(Global_Variables.btnFont);
        exit.setFont(Global_Variables.btnFont);

        viewResult.setForeground(Color.BLACK);
        viewGradesSW.setForeground(Color.BLACK);
        viewMarksSW.setForeground(Color.BLACK);
        updatePassword.setForeground(Color.BLACK);
        logOut.setForeground(Color.BLACK);
        exit.setForeground(Color.BLACK);

        viewResult.setBackground(Color.WHITE);
        viewGradesSW.setBackground(Color.WHITE);
        viewMarksSW.setBackground(Color.WHITE);
        updatePassword.setBackground(Color.WHITE);
        logOut.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);

        leftPanel.add(viewResult);
        leftPanel.add(viewGradesSW);
        leftPanel.add(viewMarksSW);
        leftPanel.add(updatePassword);
        leftPanel.add(logOut);
        leftPanel.add(exit);

        viewResult.addActionListener(this);
        viewGradesSW.addActionListener(this);
        viewMarksSW.addActionListener(this);
        updatePassword.addActionListener(this);
        logOut.addActionListener(this);
        exit.addActionListener(this);
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(0, 0, 512, Global_Variables.WINDOW_HEIGHT);
        leftPanel.setForeground(Color.WHITE);
        leftPanel.setBackground(Color.BLACK);
        add(leftPanel);
    }

    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(513, 0, 512, Global_Variables.WINDOW_HEIGHT);
        rightPanel.setForeground(Color.BLACK);
        rightPanel.setBackground(Color.WHITE);
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
        new Student_Portal("");
    }
}
