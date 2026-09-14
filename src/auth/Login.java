package auth;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;
import global.*;
import model.*;
import ui.*;

public class Login extends JFrame implements ActionListener {

    JPanel leftPanel,rightPanel;
    JLabel idLabel,passLabel;
    JPasswordField passField;
    JTextField idField;
    JButton login,back,exit;

    private int portalType;

    public Login(int portalType){

        this.portalType = portalType;

        leftPanel = new JPanel();
        rightPanel = new JPanel();
        idLabel = new JLabel("Enter ID:");
        passLabel = new JLabel("Enter Password:");
        idField = new JTextField();
        passField = new JPasswordField();
        login = new JButton("Login");
        back = new JButton("Back");
        exit = new JButton("Exit");

        leftPanel.setLayout(null);
        rightPanel.setLayout(null);

        leftPanel.setBounds(0,0,512,Global_Variables.WINDOW_HEIGHT);
        rightPanel.setBounds(513,0,512,Global_Variables.WINDOW_HEIGHT);

        leftPanel.setForeground(Color.WHITE);
        rightPanel.setForeground(Color.WHITE);

        leftPanel.setBackground(Color.BLACK);
        rightPanel.setBackground(Color.WHITE);

        add(leftPanel);
        add(rightPanel);

        idLabel.setFont(new Font("Arial",Font.BOLD,32));
        passLabel.setFont(new Font("Arial",Font.BOLD,32));
        idField.setFont(new Font("Arial",Font.BOLD,32));
        passField.setFont(new Font("Arial",Font.BOLD,32));
        login.setFont(new Font("Arial",Font.BOLD,16));
        back.setFont(new Font("Arial",Font.BOLD,16));
        exit.setFont(new Font("Arial",Font.BOLD,16));

        idLabel.setForeground(Color.WHITE);
        passLabel.setForeground(Color.WHITE);
        idField.setForeground(Color.WHITE);
        passField.setForeground(Color.WHITE);
        login.setForeground(Color.WHITE);
        back.setForeground(Color.WHITE);
        exit.setForeground(Color.WHITE);

        idField.setBackground(Color.BLACK);
        passField.setBackground(Color.BLACK);
        login.setBackground(Color.BLACK);
        back.setBackground(Color.BLACK);
        exit.setBackground(Color.BLACK);

        idLabel.setBounds(130,239,300,50);
        passLabel.setBounds(130,239+(100*1),300,50);
        idField.setBounds(101,239,310,50);
        passField.setBounds(101,239+(100*1),310,50);
        login.setBounds(101,239+(100*2),100,50);
        back.setBounds(206,239+(100*2),100,50);
        exit.setBounds(311,239+(100*2),100,50);

        login.setFocusPainted(false);
        back.setFocusPainted(false);
        exit.setFocusPainted(false);

        leftPanel.add(idLabel);
        leftPanel.add(passLabel);
        rightPanel.add(idField);
        rightPanel.add(passField);
        rightPanel.add(login);
        rightPanel.add(back);
        rightPanel.add(exit);

        login.addActionListener(this);
        back.addActionListener(this);
        exit.addActionListener(this);

        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setUndecorated(true);
        setLocation(Global_Variables.X_POSITION, Global_Variables.Y_POSITION);
        setSize(Global_Variables.WINDOW_WIDTH, Global_Variables.WINDOW_HEIGHT);
        setVisible(true);setSize(Global_Variables.WINDOW_WIDTH, Global_Variables.WINDOW_HEIGHT);

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==login){

            String id = idField.getText();
            String pass = new String(passField.getPassword());

            if(id.equals(Global_Variables.TEACHER_PORTAL_ID) && pass.equals(Global_Variables.TEACHER_PORTAL_PASS) && portalType==1){

                try{

                    FileWriter fw = new FileWriter(Global_Variables.CURRENT_SESSION);
                    String line1 = "Admin" + "|" + Global_Variables.TEACHER_PORTAL_ID;
                    fw.write(line1);
                    fw.close();

                } catch(Exception ex){
                    ex.printStackTrace();
                }

                setVisible(false);
                dispose();

                new Teacher_Portal();

            }

            else {

                try{

                    BufferedReader br = new BufferedReader(new FileReader(Global_Variables.STUDENT_ID));

                    String line;

                    while((line = br.readLine()) != null){

                        String[] parts = line.split("\\|");

                        if(parts[1].equals(id) && parts[2].equals(pass) && portalType==0){

                            FileWriter fw = new FileWriter(Global_Variables.CURRENT_SESSION);
                            String line1 = parts[0] + "|" + parts[1];
                            fw.write(line1);
                            fw.close();

                            setVisible(false);
                            dispose();

                            new Student_Portal(id);

                            break;

                        } else{

                            JOptionPane.showMessageDialog(this,"Invalid ID or Password!","Error",JOptionPane.ERROR_MESSAGE);

                            break;

                        }

                    }

                    br.close();

                } catch(Exception ex){
                    ex.printStackTrace();
                }

            }

        }

        else if(e.getSource()==back){

            setVisible(false);
            dispose();

            new Main_Menu();

        }

        else{

            System.exit(0);

        }

    }

    public static void main(String[] args){
        new Login(-1);
    }

}
