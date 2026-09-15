package auth;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;
import global.*;
import model.*;

public class Update_Password extends JFrame implements ActionListener {

    String id, name;

    JPanel[] panels = new JPanel[]{
        new JPanel(),
        new JPanel()
    };

    JLabel[] labels = new JLabel[]{
        new JLabel("Enter New Password:"),
        new JLabel("Confirm New Password:")
    };

    JPasswordField[] fields = new JPasswordField[]{
        new JPasswordField(),
        new JPasswordField()
    };

    JButton[] buttons = new JButton[]{
        new JButton("Update"),
        new JButton("Back"),
        new JButton("Exit")
    };

    public Update_Password(String name, String id){

        this.id = id;
        this.name = name;
        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==buttons[0]){

            String newPass = new String(fields[0].getPassword());
            String confirmPass = new String(fields[1].getPassword());

            if(newPass.isEmpty() || confirmPass.isEmpty()){

                JOptionPane.showMessageDialog(this, "Password Can Not Be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }

            if(newPass.length()<8 || confirmPass.length()<8){

                JOptionPane.showMessageDialog(this, "Password Must Be 8 Character Long", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }

            if(!newPass.equals(confirmPass)){

                JOptionPane.showMessageDialog(this, "Password Didn't Matched!", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }

            File file = new File(Global_Variables.STUDENT_ID);

            boolean updated = false;

            try{

                if(!file.exists()){

                    JOptionPane.showMessageDialog(this, "Database Student_ID.txt Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                BufferedReader br = new BufferedReader(new FileReader(file));
                StringBuilder content = new StringBuilder();
                String line;

                while((line=br.readLine())!=null){

                    if(line.trim().isEmpty()){
                        continue;
                    }

                    String[] parts = line.split("\\|");

                    if(parts.length>=3 && parts[0].equals(name)){

                        line = parts[0] + "|" + parts[1] + "|" + newPass;
                        updated = true;

                    }

                    content.append(line);
                    content.append(System.lineSeparator());

                }

                br.close();

                if(updated){

                    FileWriter fw = new FileWriter(file);
                    fw.write(content.toString());
                    fw.close();

                }

                JOptionPane.showMessageDialog(this, "Password Updated Successfully!", "Password Updated", JOptionPane.PLAIN_MESSAGE);

                new Global_Functions().clearScreen(this);
                new Student_Portal(name, id);

            } catch(Exception ex){

                JOptionPane.showMessageDialog(this, "Unexpected Error Occurred", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }



        }

        else if(e.getSource()==buttons[1]){

            new Global_Functions().clearScreen(this);
            new Student_Portal(name, id);

        } else{

            new Global_Functions().exitApp(this);

        }

    }

    private void createButtons(){

        for(int i=0; i<buttons.length; i++){

            buttons[i].setFont(new Font("Arial",Font.BOLD, 20));
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setForeground(Color.BLACK);
            buttons[i].addActionListener(this);
            buttons[i].setFocusPainted(false);

            if(buttons[i].getText().equals("Update")){
                buttons[i].setBounds(150, 420, 200, 50);
            } else{
                buttons[i].setBounds(0 + (312*(i-1)), 718, 200, 50);
            }

            panels[1].add(buttons[i]);

        }

    }

    private void createFields(){

        for(int i=0; i<fields.length; i++){

            fields[i].setFont(new Font("Arial",Font.BOLD, 16));
            fields[i].setBackground(Color.BLACK);
            fields[i].setForeground(Color.WHITE);
            fields[i].setBounds(250, 200 + (80*i), 200, 30);
            panels[1].add(fields[i]);

        }

    }

    private void createLabels(){

        for(int i=0; i<labels.length; i++){

            labels[i].setFont(new Font("Arial",Font.BOLD, 16));
            labels[i].setForeground(Color.BLACK);
            labels[i].setBounds(40, 200 + (80*i), 200, 30);
            panels[1].add(labels[i]);

        }

    }

    private void createMenu(){

        new Global_Functions().createPanels(this, panels, "Student Portal");
        createLabels();
        createFields();
        createButtons();
        new Global_Functions().createMainFrame(this);

    }

    public static void main(String[] args){
        new Update_Password("Napusa", "26-9-9");
    }

}
