package service;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import global.*;

public class Edit_Course implements ActionListener {

    JTextField nameField;
    JPanel panel;
    JButton okButton, cancelButton;
    JDialog dialog;

    String name;
    JFrame parentFrame;

    public Edit_Course(String name, JFrame parentFrame) {

        this.name = name;
        this.parentFrame = parentFrame;

        createFields(name);
        createButtons();
        createPanel();
        createDialog();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == okButton) {

            String newName = nameField.getText();

            if (newName.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Name cannot be empty!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isNameExist(newName)) {
                JOptionPane.showMessageDialog(dialog, "Name: " + newName + " already exists!", "Duplicate Name",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {

                BufferedReader br = new BufferedReader(new FileReader(Global_Variables.COURSE_LIST));
                StringBuilder content = new StringBuilder();

                String line;
                boolean found = false;

                while ((line = br.readLine()) != null) {

                    if (line.trim().isEmpty()) {
                        continue;
                    }

                    if (line.equals(name)) {

                        line = newName;
                        found = true;

                    }
                    content.append(line);
                    content.append(System.lineSeparator());

                }

                br.close();

                if (!found) {
                    JOptionPane.showMessageDialog(dialog, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                FileWriter fw = new FileWriter(Global_Variables.COURSE_LIST);
                fw.write(content.toString());
                fw.close();
                JOptionPane.showMessageDialog(dialog, "Course name updated successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error updating course: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

        else {

            dialog.dispose();

        }

    }

    private boolean isNameExist(String name){

        File file = new File(Global_Variables.COURSE_LIST);

        try{

            if(!file.exists()){
                return false;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line=br.readLine())!=null){
                if(line.trim().isEmpty()){
                    continue;
                }
                if(line.equals(name)){
                    br.close();
                    return true;
                }
            }
            br.close();
            return false;

        } catch(Exception e){
            return true;
        }

    }

    private void createFields(String name) {
        nameField = new JTextField(name, 9);
        nameField.setForeground(Color.WHITE);
        nameField.setBackground(Color.BLACK);
        nameField.setFont(new Font("Arial", Font.BOLD, 20));
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

        panel.add(nameLabel);
        panel.add(nameField);

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
