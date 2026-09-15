package teacher;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import global.*;

public class Enter_Grades extends JFrame implements ActionListener {

    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel model;
    String name, id;
    JPanel[] panels = new JPanel[] {
            new JPanel(),
            new JPanel()
    };

    JButton[] buttons = new JButton[] {
            new JButton("Exit"),
            new JButton("Submit"),
            new JButton("Back")
    };
    JLabel[] studentDetails = new JLabel[] {
            new JLabel("Name:"),
            new JLabel("ID:"),
            new JLabel(),
            new JLabel()
    };

    public Enter_Grades(String name, String id) {

        this.name = name;
        this.id = id;

        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

            new Global_Functions().exitApp(this);

        }

        else if (e.getSource() == buttons[1]) {

            ArrayList<Integer> marks = new ArrayList<>();
            ArrayList<String> grades = new ArrayList<>();
            ArrayList<Double> points = new ArrayList<>();

            for (int i = 0; i < table.getRowCount(); i++) {

                try {

                    String selectedRow = (String) model.getValueAt(i, 1);
                    int score = Integer.parseInt(selectedRow);

                    if (score < 0 || score > 100) {
                        JOptionPane.showMessageDialog(this, "Invalid Marks Input (Choose Between 0-100)", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    marks.add(score);

                    if (score >= 80) {
                        grades.add("A+");
                        points.add(4.00);
                    } else if (score >= 75) {
                        grades.add("A");
                        points.add(3.75);
                    } else if (score >= 70) {
                        grades.add("A-");
                        points.add(3.50);
                    } else if (score >= 65) {
                        grades.add("B+");
                        points.add(3.25);
                    } else if (score >= 60) {
                        grades.add("B");
                        points.add(3.00);
                    } else if (score >= 55) {
                        grades.add("B-");
                        points.add(2.75);
                    } else if (score >= 50) {
                        grades.add("C+");
                        points.add(2.50);
                    } else if (score >= 45) {
                        grades.add("C");
                        points.add(2.25);
                    } else if (score >= 40) {
                        grades.add("D");
                        points.add(2.00);
                    } else {
                        grades.add("F");
                        points.add(0.00);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid Marks Input (Choose Between 0-100)", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }

            int totalMarks = 0;
            Double totalPoints = 0.0;
            for (int i = 0; i < marks.size(); i++) {
                totalMarks += marks.get(i);
                totalPoints += points.get(i);
            }
            double rawCgpa = totalPoints / (double) marks.size();
            double cgpa = Math.round(rawCgpa * 100.0) / 100.0;

            File file1 = new File(Global_Variables.MARKSHEET_FOLDER + id + ".txt");
            File file2 = new File(Global_Variables.GRADES_FOLDER + id + ".txt");

            try {

                if (!file1.exists()) {
                    file1.createNewFile();
                }

                if (!file2.exists()) {
                    file2.createNewFile();
                }

                FileWriter fw = new FileWriter(file1);
                FileWriter fw1 = new FileWriter(file2);

                for (int i = 0; i < marks.size(); i++) {
                    String courseName = (String) model.getValueAt(i, 0);
                    String line = courseName + "|" + marks.get(i) + "|" + grades.get(i) + "|" + points.get(i) + "\n";
                    fw.write(line);
                }
                fw1.write(totalMarks + "|" + cgpa + "\n");

                fw.close();
                fw1.close();

                JOptionPane.showMessageDialog(this, "Grades Successfully Updated", "Success",
                        JOptionPane.PLAIN_MESSAGE);

                new Global_Functions().clearScreen(this);
                new Show_All_Student("enterGrades");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unexpected Error Occurred", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

        else if (e.getSource() == buttons[2]) {

            new Global_Functions().clearScreen(this);
            new Show_All_Student("enterGrades");

        }

    }

    private void createStudentDetails() {

        for (int i = 0; i < studentDetails.length; i++) {
            if (i == 2) {
                studentDetails[i].setText(name);
            } else if (i == 3) {
                studentDetails[i].setText(id);
            }
            studentDetails[i].setFont(new Font("Arial", Font.BOLD, 24));
            studentDetails[i].setForeground(Color.BLACK);
            if (i == 0 || i == 1) {
                studentDetails[i].setBounds(100, 0 + (30 * (i + 1)), 100, 30);
            } else {
                studentDetails[i].setBounds(210, 0 + (30 * (i - 1)), 300, 30);
            }

            panels[0].add(studentDetails[i]);
        }

    }

    private void loadCourses() {

        File file = new File(Global_Variables.MARKSHEET_FOLDER + id + ".txt");

        try {

            if(!file.exists()){

                BufferedReader br = new BufferedReader(new FileReader(Global_Variables.COURSE_LIST));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }

                    model.addRow(new Object[]{line, 0});

                }
                br.close();
            } else{

                BufferedReader br = new BufferedReader(new FileReader(file));

                String line;

                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    String[] parts = line.split("\\|");
                    if(parts.length>=4){
                        model.addRow(new Object[]{parts[0],parts[1]});
                    }

                }
                br.close();

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading courses: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void createTable() {
        String[] columns = { "Course", "Mark" };
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==1) return true;
                else return false;
            }
        };

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setForeground(Color.BLACK);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.getColumnModel().getColumn(0).setPreferredWidth(250);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 200, 430, 400);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getViewport().setForeground(Color.BLACK);

        panels[0].add(scrollPane);

    }


    private void createButtons() {

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setFont(new Font("Arial", Font.BOLD, 16));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setFocusPainted(false);
            buttons[i].setBounds(0 + (120 * i + 80), 738, 120, 30);
            buttons[i].addActionListener(this);
            panels[0].add(buttons[i]);
        }

    }

    private void createMenu() {

        new Global_Functions().createPanels(this,panels, "Teacher Portal");
        createStudentDetails();
        createButtons();
        createTable();
        loadCourses();
        new Global_Functions().createMainFrame(this);

    }

}
