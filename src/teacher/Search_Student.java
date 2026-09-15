package teacher;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import global.Global_Functions;
import global.Global_Variables;
import model.Teacher_Portal;
import service.Edit_Student;

public class Search_Student extends JFrame implements ActionListener {

    JPanel[] panels = new JPanel[]{
        new JPanel(),
        new JPanel()
    };

    JButton[] buttons = new JButton[] {
            new JButton("Search By Name"),
            new JButton("Search By ID"),
            new JButton("Search"),
            new JButton("Back"),
            new JButton("Exit")
    };

    JTextField field;
    JButton edit;

    boolean searchByName = true;

    String name, id;

    public Search_Student(){

        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource() == buttons[0] || e.getSource() == buttons[1]) {

            createFields();
            buttons[2].setVisible(true);
            buttons[1].setVisible(false);
            buttons[0].setVisible(false);

            if (e.getSource() == buttons[1]) {
                searchByName = false;
            }

        }

        else if (e.getSource() == buttons[2]) {

            if (searchByName) {

                name = field.getText();
                id = findId("Id", name);

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                else if (id.equals("")) {
                    JOptionPane.showMessageDialog(this, "Student Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
                    buttons[2].setVisible(false);
                    buttons[1].setVisible(true);
                    buttons[0].setVisible(true);
                    field.setVisible(false);
                    return;
                }

            } else {

                id = field.getText();
                name = findId("Name", id);

                if (!id.matches("\\d{2}-\\d{1}-\\d+")) {
                    JOptionPane.showMessageDialog(this, "ID must be in the format yy-M-id!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (name.equals("") || id.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Student Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
                    buttons[2].setVisible(false);
                    buttons[1].setVisible(true);
                    buttons[0].setVisible(true);
                    field.setVisible(false);
                    searchByName = true;
                    return;
                }

            }

            buttons[2].setVisible(false);
            field.setVisible(false);

            JLabel nameLabel = new JLabel("Name: " + name);
            JLabel idLabel = new JLabel("ID: " + id);

            nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
            idLabel.setFont(new Font("Arial", Font.BOLD, 30));

            nameLabel.setForeground(Color.WHITE);
            idLabel.setForeground(Color.WHITE);

            nameLabel.setBounds(156, 280, 200, 30);
            idLabel.setBounds(156, 310, 200, 30);

            panels[1].add(nameLabel);
            panels[1].add(idLabel);

            edit = new JButton("Edit");
            edit.setFocusPainted(false);
            edit.setFont(new Font("Arial", Font.BOLD, 30));
            edit.setBackground(Color.BLACK);
            edit.setForeground(Color.WHITE);
            edit.addActionListener(this);
            edit.setBounds(156, 480, 200, 30);
            panels[1].add(edit);


        }

        else if (e.getSource() == buttons[3]) {

            new Global_Functions().clearScreen(this);
            new Teacher_Portal();

        }

        else if(e.getSource() == edit){

            new Edit_Student(name, id, this);

        }

        else {

            new Global_Functions().exitApp(this);

        }

    }

    private String findId(String type, String name) {

        File file = new File(Global_Variables.STUDENT_ID);

        try {

            if (!file.exists()) {
                JOptionPane.showMessageDialog(this, "Database Student_Id.txt not found", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return "";
            }

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");

                if (parts.length >= 3) {

                    if (type.equals("Id")) {
                        if (parts[0].equals(name)) {
                            br.close();
                            return parts[1];
                        }
                    } else {
                        if (parts[1].equals(name)) {
                            br.close();
                            return parts[0];
                        }
                    }

                }

            }

            br.close();

            return "";

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unexpected Error Occurred", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }

    }

    private void createFields() {
        field = new JTextField();
        field.setFont(new Font("Arial", Font.BOLD, 20));
        field.setForeground(Color.BLACK);
        field.setBackground(Color.WHITE);
        field.setBounds(156, 280, 200, 30);
        panels[1].add(field);
        field.setVisible(true);
    }

    private void createButtons() {

        for (int i = 0; i < buttons.length; i++) {

            buttons[i].setFocusPainted(false);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].addActionListener(this);

            if (i < 2) {
                buttons[i].setBounds(40 + (250 * i), 100, 200, 30);
            } else if (i == 2) {
                buttons[i].setBounds(156, 480, 200, 30);
            } else {
                buttons[i].setBounds(0 + (310 * (i - 3)), 718, 200, 50);
            }

            panels[1].add(buttons[i]);
            buttons[2].setVisible(false);

        }

    }

    private void createMenu(){

        new Global_Functions().createPanels(this, panels, "Teacher Portal");
        createButtons();
        new Global_Functions().createMainFrame(this);

    }

}
