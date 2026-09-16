package service;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import global.*;

public class Edit_Student implements ActionListener {

    JTextField nameField, idField;
    JPanel panel;
    JButton okButton, cancelButton;
    JDialog dialog;

    String name, id;
    JFrame parentFrame;

    public Edit_Student(String name, String id, JFrame parentFrame) {

        this.name = name;
        this.id = id;
        this.parentFrame = parentFrame;

        createFields(name, id);
        createButtons();
        createPanel();
        createDialog();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == okButton) {

            String newName = nameField.getText();
            String newId = idField.getText();

            if (newName.isEmpty() || newId.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Name and ID cannot be empty!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (checkDuplicatedID(newId)) {
                JOptionPane.showMessageDialog(dialog, "ID " + newId + " already exists!", "Duplicate ID",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newId.matches("\\d{2}-(?:[1-9]|1[0-2])-\\d+")) {
                JOptionPane.showMessageDialog(dialog, "ID must be in the format yy-M(M)-id!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {

                BufferedReader br = new BufferedReader(new FileReader(Global_Variables.STUDENT_ID));
                StringBuilder content = new StringBuilder();

                String line;
                boolean found = false;

                while ((line = br.readLine()) != null) {

                    if (line.trim().isEmpty()) {
                        continue;
                    }

                    String[] parts = line.split("\\|");
                    if (parts.length >= 3 && parts[0].equals(name) && parts[1].equals(id)) {

                        line = newName + "|" + newId + "|" + parts[2];
                        found = true;

                    }
                    content.append(line);
                    content.append(System.lineSeparator());

                }

                br.close();

                if (!found) {
                    JOptionPane.showMessageDialog(dialog, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                FileWriter fw = new FileWriter(Global_Variables.STUDENT_ID);
                fw.write(content.toString());
                fw.close();
                JOptionPane.showMessageDialog(dialog, "Student updated successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error updating student: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

        else {

            dialog.dispose();

        }

    }

    private boolean checkDuplicatedID(String newId) {

        try {

            BufferedReader br = new BufferedReader(new FileReader(Global_Variables.STUDENT_ID));
            String line;
            boolean duplicatedId = false;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String existingId = parts[1];
                    if (!existingId.equals(id) && existingId.equals(newId)) {
                        duplicatedId = true;
                        break;
                    }
                }
            }

            br.close();

            return duplicatedId;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(dialog, "Error checking ID: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    private void createFields(String name, String id) {
        nameField = new JTextField(name, 9);
        nameField.setForeground(Color.WHITE);
        nameField.setBackground(Color.BLACK);
        nameField.setFont(new Font("Arial", Font.BOLD, 20));

        idField = new JTextField(id, 9);
        idField.setForeground(Color.WHITE);
        idField.setBackground(Color.BLACK);
        idField.setFont(new Font("Arial", Font.BOLD, 20));
    }

    private void createButtons() {
        okButton = new JButton("OK");
        okButton.setBackground(Color.BLACK);
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.setFont(new Font("Arial", Font.BOLD, 20));
        okButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 20));
        cancelButton.addActionListener(this);
    }

    private void createPanel() {
        panel = new JPanel(new GridLayout(3, 1, 5, 5));

        panel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel idLabel = new JLabel("ID:");
        idLabel.setForeground(Color.WHITE);
        idLabel.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(idLabel);
        panel.add(idField);

        panel.add(okButton);
        panel.add(cancelButton);
    }

    private void createDialog() {
        dialog = new JDialog(parentFrame, true);
        dialog.setUndecorated(true);

        dialog.add(panel);
        dialog.pack();
        dialog.setLocation(Global_Variables.X_POSITION + 100, Global_Variables.Y_POSITION + 300);

        dialog.setVisible(true);
    }

}
