package teacher;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import javax.swing.*;
import global.*;
import model.*;

public class Add_Student extends JFrame implements ActionListener {

    JLabel nameLabel, idLabel;
    JTextField nameField, idField;
    JButton addStudent, back, exit;
    JPanel leftPanel,rightPanel;

    String idPrefix = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-M-"));

    public Add_Student() {

        createLeftPanel();
        createRightPanel();
        createLabels();
        createFields();
        createButtons();
        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addStudent) {

            String name = nameField.getText();
            String id = idField.getText();

            if (name.isEmpty() || id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!id.matches("\\d{2}-\\d{1}-\\d+")) {
                JOptionPane.showMessageDialog(this, "ID must be in the format yy-M-id!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (id.equals(matchId(id))) {
                JOptionPane.showMessageDialog(this, "ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {

                FileWriter fw = new FileWriter(Global_Variables.STUDENT_ID, true);
                String line = name + "|" + id + "|" + id + "\n";
                fw.write(line);
                fw.close();
                JOptionPane.showMessageDialog(this, "Student added successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding student!", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

            nameField.setText("");
            idField.setText(idPrefix);

        } else if (e.getSource() == back) {

            setVisible(false);
            dispose();
            new Teacher_Portal();

        } else {

            System.exit(0);

        }

    }

    public String matchId(String id) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(Global_Variables.STUDENT_ID));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[1].equals(id)) {
                    br.close();
                    return parts[1];
                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    private void createButtons() {
        addStudent = new JButton("Add Student");
        addStudent.setFont(new Font("Arial", Font.BOLD, 26));
        addStudent.setForeground(Color.WHITE);
        addStudent.setBackground(Color.BLACK);
        addStudent.setBounds(50, 344, 200, 50);
        addStudent.setFocusPainted(false);
        addStudent.addActionListener(this);
        rightPanel.add(addStudent);

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 26));
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.setBounds(260, 344, 200, 50);
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

    private void createFields() {
        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.BOLD, 26));
        nameField.setBounds(260, 224, 200, 50);
        nameField.setForeground(Color.BLACK);
        rightPanel.add(nameField);

        idField = new JTextField(idPrefix);
        idField.setFont(new Font("Arial", Font.BOLD, 26));
        idField.setBounds(260, 284, 200, 50);
        idField.setForeground(Color.BLACK);
        rightPanel.add(idField);
    }

    private void createLabels() {
        nameLabel = new JLabel("Enter Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 26));
        nameLabel.setBounds(50, 224, 200, 50);
        nameLabel.setForeground(Color.WHITE);
        rightPanel.add(nameLabel);

        idLabel = new JLabel("Enter ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 26));
        idLabel.setBounds(50, 284, 200, 50);
        idLabel.setForeground(Color.WHITE);
        rightPanel.add(idLabel);
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
