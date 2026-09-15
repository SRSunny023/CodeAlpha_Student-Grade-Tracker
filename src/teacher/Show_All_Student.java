package teacher;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import global.*;
import model.*;
import service.*;

public class Show_All_Student extends JFrame implements ActionListener {

    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel model;
    JLabel title;
    String type;
    JButton[] buttons = new JButton[] {
            new JButton(),
            new JButton("Back"),
            new JButton("Exit")
    };
    JPanel[] panels = new JPanel[] {
            new JPanel(),
            new JPanel()
    };

    public Show_All_Student(String type) {

        this.type = type;
        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

            try {

                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a student!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = (String) model.getValueAt(selectedRow, 0);
                String id = (String) model.getValueAt(selectedRow, 1);

                if (type.equals("showAllStudent")) {

                    new Edit_Student(name, id);

                    model.setRowCount(0);
                    loadStudents();

                } else {

                    new Global_Functions().clearScreen(this);
                    new Enter_Grades(name, id);

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Error editing student: " + ex.getMessage(), "Error",
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

    private void loadStudents() {

        try {

            BufferedReader br = new BufferedReader(new FileReader(Global_Variables.STUDENT_ID));
            String line;
            ArrayList<String[]> students = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    students.add(parts);
                }
            }
            br.close();

            students.sort((a, b) -> {
                return compareId(a[1], b[1]);
            });

            for (String[] student : students) {
                model.addRow(new Object[] { student[0], student[1] });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private int compareId(String id1, String id2) {

        String[] parts1 = id1.split("-");
        String[] parts2 = id2.split("-");

        for (int i = 0; i < parts1.length; i++) {

            int num1 = Integer.parseInt(parts1[i]);
            int num2 = Integer.parseInt(parts2[i]);

            if (num1 != num2) {
                return Integer.compare(num1, num2);
            }
        }

        return 0;

    }

    private void createTable() {
        String[] columns = { "Name", "ID" };
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

    private void createButtons(String type) {

        if (type.equals("enterGrades")) {
            buttons[0].setText("Grade Entry");
        } else {
            buttons[0].setText("Edit");
        }

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

    private void createLabels(String type) {

        if (type.equals("enterGrades")) {
            title = new JLabel("Grades Entry");
        } else {
            title = new JLabel("All Students");
        }

        title.setBounds(170, 20, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        panels[1].add(title);
    }

    private void createMenu() {

        new Global_Functions().createPanels(this,panels, "Teacher Portal");
        createLabels(type);
        createButtons(type);
        createTable();
        loadStudents();
        new Global_Functions().createMainFrame(this);

    }

}
