package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import auth.*;
import global.*;

public class Main_Menu extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[] {
            new JButton("Student Portal"),
            new JButton("Teacher Portal"),
            new JButton("Exit")
    };
    JPanel[] panels = new JPanel[] {
            new JPanel(),
            new JPanel()
    };

    public Main_Menu() {

        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

            new Global_Functions().clearScreen(this);
            new Login(0);

        }

        else if (e.getSource() == buttons[1]) {

            new Global_Functions().clearScreen(this);
            new Login(1);

        }

        else {

            new Global_Functions().exitApp(this);

        }

    }

    private void createButtons() {

        for (int i = 0; i < buttons.length; i++) {

            if (buttons[i].getText().equals("Teacher Portal")) {
                buttons[i].setForeground(Color.BLACK);
                buttons[i].setBackground(Color.WHITE);
            } else if (buttons[i].getText().equals("Student Portal")) {
                buttons[i].setForeground(Color.WHITE);
                buttons[i].setBackground(Color.BLACK);
            } else {
                buttons[i].setForeground(Color.BLACK);
                buttons[i].setBackground(Color.ORANGE);
            }

            buttons[i].setFont(Global_Variables.btnFont);
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);

            if (buttons[i].getText().equals("Exit")) {
                buttons[i].setBounds(312, 738, 200, 30);
            } else {
                buttons[i].setBounds(156, 369, 200, 30);
            }

            if (buttons[i].getText().equals("Student Portal")) {
                panels[0].add(buttons[i]);
            } else {
                panels[1].add(buttons[i]);
            }

        }
    }

    private void createMenu() {

        new Global_Functions().createPanels(this,panels);
        createButtons();
        new Global_Functions().createMainFrame(this);

    }

    public static void main(String[] args) {
        new Main_Menu();
    }

}
