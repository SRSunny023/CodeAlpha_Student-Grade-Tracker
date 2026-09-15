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

    String portalType;

    public Login(String portalType) {

        this.portalType = portalType;
        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

            String id = idField.getText();
            String pass = new String(passField.getPassword());

            if (id.equals(Global_Variables.TEACHER_PORTAL_ID) && pass.equals(Global_Variables.TEACHER_PORTAL_PASS) && portalType.equals("Teacher Portal")) {

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

                    Boolean loginSuccess = false;

                    while ((line = br.readLine()) != null) {

                        if(line.trim().isEmpty()){
                            continue;
                        }

                        String[] parts = line.split("\\|");

                        if(parts.length>=3){

                            if (parts[1].equals(id) && parts[2].equals(pass) && portalType.equals("Student Portal")) {

                                FileWriter fw = new FileWriter(Global_Variables.CURRENT_SESSION);
                                String line1 = parts[0] + "|" + parts[1];
                                fw.write(line1);
                                fw.close();

                                loginSuccess = true;

                                new Global_Functions().clearScreen(this);
                                new Student_Portal(parts[0],id);

                                break;

                            }

                        }


                    }

                    br.close();

                    if(!loginSuccess){
                        JOptionPane.showMessageDialog(this, "Invalid ID or Password!", "Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

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
            buttons[i].setForeground((portalType.equals("Student Portal")) ? Color.BLACK : Color.WHITE);
            buttons[i].setBackground((portalType.equals("Student Portal")) ? Color.WHITE : Color.BLACK);
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            buttons[i].setBounds(101 + (105*i), 239 + (100 * 2), 100, 50);
            panels[1].add(buttons[i]);

        }

    }

    private void createFields() {

        idField = new JTextField();
        idField.setFont(new Font("Arial", Font.BOLD, 32));
        idField.setForeground((portalType.equals("Student Portal")) ? Color.BLACK : Color.WHITE);
        idField.setBackground((portalType.equals("Student Portal")) ? Color.WHITE : Color.BLACK);
        idField.setBounds(101, 239, 310, 50);
        panels[1].add(idField);

        passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.BOLD, 32));
        passField.setForeground((portalType.equals("Student Portal")) ? Color.BLACK : Color.WHITE);
        passField.setBackground((portalType.equals("Student Portal")) ? Color.WHITE : Color.BLACK);
        passField.setBounds(101, 239 + (100 * 1), 310, 50);
        panels[1].add(passField);

    }

    private void createLabels() {
        for(int i=0; i<labels.length; i++){
            labels[i].setFont(new Font("Arial", Font.BOLD, 32));
            labels[i].setForeground((portalType.equals("Student Portal")) ? Color.WHITE : Color.BLACK);
            labels[i].setBounds(130, 239 + (100*i), 300, 50);
            panels[0].add(labels[i]);
        }
    }

    private void createMenu() {
        new Global_Functions().createPanels(this, panels, portalType);
        createLabels();
        createFields();
        createButtons();
        new Global_Functions().createMainFrame(this);
    }

}
