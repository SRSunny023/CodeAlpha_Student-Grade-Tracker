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

    String idPrefix = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-M-"));

    JButton[] buttons = new JButton[] {
            new JButton("Add Student"),
            new JButton("Back"),
            new JButton("Exit")
    };

    JLabel[] labels = new JLabel[] {
            new JLabel("Enter Name:"),
            new JLabel("Enter ID:")
    };

    JTextField[] fields = new JTextField[] {
            new JTextField(),
            new JTextField(idPrefix)
    };

    JPanel[] panels = new JPanel[] {
            new JPanel(),
            new JPanel()
    };

    public Add_Student() {

        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

            String name = fields[0].getText();
            String id = fields[1].getText();

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

            fields[0].setText("");
            fields[1].setText(idPrefix);

        } else if (e.getSource() == buttons[1]) {

            new Global_Functions().clearScreen(this);
            new Teacher_Portal();

        } else {

            new Global_Functions().exitApp(this);

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

        for (int i = 0; i < buttons.length; i++) {

            buttons[i].setFont(new Font("Arial", Font.BOLD, 26));

            if (buttons[i].getText().equals("Exit")) {
                buttons[i].setForeground(Color.BLACK);
                buttons[i].setBackground(Color.ORANGE);
            } else {
                buttons[i].setForeground(Color.WHITE);
                buttons[i].setBackground(Color.BLACK);
            }

            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);

            if (buttons[i].getText().equals("Exit")) {
                buttons[i].setBounds(0, 718, 512, 50);
            } else {
                buttons[i].setBounds(50 + (210 * i), 344, 200, 50);
            }

            panels[1].add(buttons[i]);

        }

    }

    private void createFields() {

        for (int i = 0; i < fields.length; i++) {

            fields[i].setFont(new Font("Arial", Font.BOLD, 26));
            fields[i].setForeground(Color.BLACK);
            fields[i].setBounds(260, 224 + (60 * i), 200, 50);
            panels[1].add(fields[i]);

        }

    }

    private void createLabels() {

        for (int i = 0; i < labels.length; i++) {

            labels[i].setFont(new Font("Arial", Font.BOLD, 26));
            labels[i].setForeground(Color.WHITE);
            labels[i].setBounds(50, 224 + (60 * i), 200, 50);
            panels[1].add(labels[i]);

        }

    }

    private void createMenu() {

        new Global_Functions().createPanels(this,panels, "Teacher Portal");
        createLabels();
        createFields();
        createButtons();
        new Global_Functions().createMainFrame(this);

    }

}
