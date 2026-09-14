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
    JButton edit, back, exit;
    JPanel leftPanel,rightPanel;
    String type;

    public Show_All_Student(String type) {

        this.type = type;

        createLeftPanel();
        createRightPanel();
        createMenu();
        createLabels(type);
        createButtons(type);
        createTable();
        loadStudents();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == edit) {

            try {

                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a student!", "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = (String) model.getValueAt(selectedRow, 0);
                String id = (String) model.getValueAt(selectedRow, 1);

                if(type.equals("showAllStudent")){

                    new Edit_Student(name, id);

                    model.setRowCount(0);
                    loadStudents();

                } else{

                    setVisible(false);
                    dispose();
                    new Enter_Grades(name, id);

                }


            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Error editing student: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        }

        else if (e.getSource() == back) {

            setVisible(false);
            dispose();
            new Teacher_Portal();

        }

        else {

            System.exit(0);

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
        rightPanel.add(scrollPane);
    }

    private void createButtons(String type) {

        if(type.equals("enterGrades")){
            edit = new JButton("Enter Grade");
        } else{
            edit = new JButton("Edit");
        }
        edit.setFont(new Font("Arial", Font.BOLD, 26));
        edit.setForeground(Color.WHITE);
        edit.setBackground(Color.BLACK);
        edit.setBounds(270, 520, 200, 50);
        edit.setFocusPainted(false);
        edit.addActionListener(this);
        rightPanel.add(edit);

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 26));
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.setBounds(40, 520, 200, 50);
        back.setFocusPainted(false);
        back.addActionListener(this);
        rightPanel.add(back);

        exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.BOLD, 26));
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.ORANGE);
        exit.setBounds(0, 718, 512, 50);
        exit.setFocusPainted(false);
        exit.addActionListener(this);
        rightPanel.add(exit);
    }

    private void createLabels(String type) {

        if(type.equals("enterGrades")){
            title = new JLabel("Grades Entry");
        } else{
            title = new JLabel("All Students");
        }

        title.setBounds(170, 20, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        rightPanel.add(title);
    }

    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(513, 0, 512, Global_Variables.WINDOW_HEIGHT);
        rightPanel.setForeground(Color.WHITE);
        rightPanel.setBackground(Color.BLACK);
        add(rightPanel);
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(0, 0, 512, Global_Variables.WINDOW_HEIGHT);
        leftPanel.setForeground(Color.BLACK);
        leftPanel.setBackground(Color.WHITE);
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

}
