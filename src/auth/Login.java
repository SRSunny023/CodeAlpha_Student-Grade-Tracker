package auth;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import global.*;
import model.*;
import ui.*;

public class Login extends JFrame implements ActionListener {

    JPanel[] panels = new JPanel[]{
        new JPanel(),
        new JPanel()
    };

    JLabel[] labels = new JLabel[]{
        new JLabel("Enter ID:"),
        new JLabel("Enter Password:")
    };

    JButton[] buttons = new JButton[]{
        new JButton("Login"),
        new JButton("Back"),
        new JButton("Exit")
    };

    JPasswordField passField;
    JTextField idField;

    private int portalType;

    public Login(int portalType) {

        this.portalType = portalType;
        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

            String id = idField.getText();
            String pass = new String(passField.getPassword());

            if (id.equals(Global_Variables.TEACHER_PORTAL_ID) && pass.equals(Global_Variables.TEACHER_PORTAL_PASS) && portalType == 1) {

                try {

                    FileWriter fw = new FileWriter(Global_Variables.CURRENT_SESSION);
                    String line1 = "Admin" + "|" + Global_Variables.TEACHER_PORTAL_ID;
                    fw.write(line1);
                    fw.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                new Global_Functions().clearScreen(this);
                new Teacher_Portal();

            }

            else {

                try {

                    BufferedReader br = new BufferedReader(new FileReader(Global_Variables.STUDENT_ID));

                    String line;

                    while ((line = br.readLine()) != null) {

                        String[] parts = line.split("\\|");

                        if (parts[1].equals(id) && parts[2].equals(pass) && portalType == 0) {

                            FileWriter fw = new FileWriter(Global_Variables.CURRENT_SESSION);
                            String line1 = parts[0] + "|" + parts[1];
                            fw.write(line1);
                            fw.close();

                            new Global_Functions().clearScreen(this);
                            new Student_Portal(id);

                            break;

                        } else {

                            JOptionPane.showMessageDialog(this, "Invalid ID or Password!", "Error",
                                    JOptionPane.ERROR_MESSAGE);

                            break;

                        }

                    }

                    br.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        }

        else if (e.getSource() == buttons[1]) {

            new Global_Functions().clearScreen(this);
            new Main_Menu();

        }

        else {

            new Global_Functions().exitApp(this);

        }

    }

    private void createButtons() {

        for(int i=0; i<buttons.length; i++){

            buttons[i].setFont(new Font("Arial", Font.BOLD, 16));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            buttons[i].setBounds(101 + (105*i), 239 + (100 * 2), 100, 50);
            panels[1].add(buttons[i]);

        }

    }

    private void createFields() {

        idField = new JTextField();
        idField.setFont(new Font("Arial", Font.BOLD, 32));
        idField.setForeground(Color.WHITE);
        idField.setBackground(Color.BLACK);
        idField.setBounds(101, 239, 310, 50);
        panels[1].add(idField);

        passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.BOLD, 32));
        passField.setForeground(Color.WHITE);
        passField.setBackground(Color.BLACK);
        passField.setBounds(101, 239 + (100 * 1), 310, 50);
        panels[1].add(passField);

    }

    private void createLabels() {
        for(int i=0; i<labels.length; i++){
            labels[i].setFont(new Font("Arial", Font.BOLD, 32));
            labels[i].setForeground(Color.BLACK);
            labels[i].setBounds(130, 239 + (100*i), 300, 50);
            panels[0].add(labels[i]);
        }
    }

    private void createMenu() {
        new Global_Functions().createPanels(this, panels);
        createLabels();
        createFields();
        createButtons();
        new Global_Functions().createMainFrame(this);
    }

    public static void main(String[] args) {
        new Login(-1);
    }

}
