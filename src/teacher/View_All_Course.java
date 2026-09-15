package teacher;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import global.*;
import model.*;
import service.*;

public class View_All_Course extends JFrame implements ActionListener {

    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel model;
    JLabel title;
    JButton[] buttons = new JButton[] {
            new JButton(),
            new JButton("Back"),
            new JButton("Exit")
    };
    JPanel[] panels = new JPanel[] {
            new JPanel(),
            new JPanel()
    };

    public View_All_Course() {

        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

            try {

                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a course!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = (String) model.getValueAt(selectedRow, 0);

               new Edit_Course(name, this);

                model.setRowCount(0);
                loadCourses();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Error editing course: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        }

        else if (e.getSource() == buttons[1]) {

            new Global_Functions().clearScreen(this);
            new Teacher_Portal();

        }

        else {

            new Global_Functions().exitApp(this);

        }

    }

    private void loadCourses() {

        try {

            BufferedReader br = new BufferedReader(new FileReader(Global_Variables.COURSE_LIST));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                model.addRow(new Object[]{line});

            }
            br.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void createTable() {
        String[] columns = { "Name" };
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.getTableHeader().setBackground(Color.BLACK);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 90, 430, 400);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.getViewport().setForeground(Color.WHITE);
        panels[1].add(scrollPane);
    }

    private void createButtons() {

        buttons[0].setText("Edit");

        for (int i = 0; i < buttons.length; i++) {

            buttons[i].setFont(new Font("Arial", Font.BOLD, 26));

            if (buttons[i].getText().equals("Exit")) {
                buttons[i].setForeground(Color.BLACK);
                buttons[i].setBackground(Color.ORANGE);
            }

            else {
                buttons[i].setForeground(Color.WHITE);
                buttons[i].setBackground(Color.BLACK);
            }

            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);

            if (buttons[i].getText().equals("Back")) {
                buttons[i].setBounds(40, 520, 200, 50);
            } else if (buttons[i].getText().equals("Exit")) {
                buttons[i].setBounds(0, 718, 512, 50);
            } else {
                buttons[i].setBounds(270, 520, 200, 50);
            }

            panels[1].add(buttons[i]);

        }

    }

    private void createLabels() {

        title = new JLabel("All Courses");
        title.setBounds(170, 20, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        panels[1].add(title);
    }

    private void createMenu() {

        new Global_Functions().createPanels(this,panels, "Teacher Portal");
        createLabels();
        createButtons();
        createTable();
        loadCourses();
        new Global_Functions().createMainFrame(this);

    }

}
