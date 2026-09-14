package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import auth.*;
import global.*;

public class Main_Menu extends JFrame implements ActionListener {

    JButton sP, tP, exit;
    JPanel leftPanel, rightPanel;

    public Main_Menu() {

        createRightPanel();
        createLeftPanel();
        createButtons();
        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == sP) {

            setVisible(false);
            dispose();

            new Login(0);

        }

        else if (e.getSource() == tP) {

            setVisible(false);
            dispose();

            new Login(1);

        }

        else {

            System.exit(0);

        }

    }

    private void createButtons() {
        sP = new JButton("Student Portal");
        tP = new JButton("Teacher Portal");
        exit = new JButton("Exit");

        sP.setBounds(156, 369, 200, 30);
        tP.setBounds(156, 369, 200, 30);
        exit.setBounds(312, 738, 200, 30);

        sP.setForeground(Color.BLACK);
        tP.setForeground(Color.WHITE);
        exit.setForeground(Color.BLACK);

        sP.setBackground(Color.WHITE);
        tP.setBackground(Color.BLACK);
        exit.setBackground(Color.ORANGE);

        sP.setFont(Global_Variables.btnFont);
        tP.setFont(Global_Variables.btnFont);
        exit.setFont(Global_Variables.btnFont);

        sP.setFocusPainted(false);
        tP.setFocusPainted(false);
        exit.setFocusPainted(false);

        sP.addActionListener(this);
        tP.addActionListener(this);
        exit.addActionListener(this);

        leftPanel.add(sP);
        rightPanel.add(tP);
        rightPanel.add(exit);
    }

    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(513, 0, 512, Global_Variables.WINDOW_HEIGHT);
        rightPanel.setForeground(Color.WHITE);
        rightPanel.setBackground(Color.WHITE);
        add(rightPanel);
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(0, 0, 512, Global_Variables.WINDOW_HEIGHT);
        leftPanel.setForeground(Color.WHITE);
        leftPanel.setBackground(Color.BLACK);
        add(leftPanel);
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
        new Main_Menu();
    }

}
