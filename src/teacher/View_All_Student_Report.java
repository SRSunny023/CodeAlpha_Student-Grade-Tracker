package teacher;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import global.*;
import model.Teacher_Portal;
import service.*;

public class View_All_Student_Report extends JFrame implements ActionListener {

    JPanel[] panels = new JPanel[] {
            new JPanel(),
            new JPanel()
    };
    JLabel title;
    JLabel[] labels = new JLabel[] {
            new JLabel("Total Student:"),
            new JLabel("Students Received Grade:"),
            new JLabel("Total Passed:"),
            new JLabel("Total Failed:"),
            new JLabel("Highest CGPA:"),
            new JLabel("Lowest CGPA:"),
            new JLabel("Average CGPA:"),
            new JLabel("Highest Mark:"),
            new JLabel("Lowest Mark:"),
            new JLabel("Average Mark:"),
            new JLabel("0"),
            new JLabel("0"),
            new JLabel("0"),
            new JLabel("0"),
            new JLabel("0"),
            new JLabel("0"),
            new JLabel("0"),
            new JLabel("0"),
            new JLabel("0"),
            new JLabel("0")
    };
    JButton[] buttons = new JButton[] {
            new JButton("Back"),
            new JButton("Exit")
    };

    public View_All_Student_Report() {

        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

            new Global_Functions().clearScreen(this);
            new Teacher_Portal();

        } else {

            new Global_Functions().exitApp(this);

        }

    }

    private void createButtons() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setFont(new Font("Arial", Font.BOLD, 26));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            buttons[i].setBounds((231 * i + 30 * i), 738, 241, 30);
            panels[1].add(buttons[i]);
        }
    }

    private void createLabels() {

        String[] reports = new String[10];

        new Load_All_Student_Report(reports);
        int j = 0;

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(new Font("Arial", Font.BOLD, 16));
            labels[i].setForeground(Color.WHITE);

            if (i < 10) {
                labels[i].setBounds(40, 40 + (50 * (i + 1)), 220, 30);
            }

            else {
                labels[i].setBounds(260, 40 + (50 * (i - 9)), 200, 30);
                labels[i].setText(reports[j++]);
            }

            panels[1].add(labels[i]);
        }
    }

    private void createTitle() {
        title = new JLabel("All Student Report");
        title.setBounds(170, 20, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        panels[1].add(title);
    }

    private void createMenu() {

        new Global_Functions().createPanels(this,panels, "Teacher Portal");
        createTitle();
        createLabels();
        createButtons();
        new Global_Functions().createMainFrame(this);

    }

}
