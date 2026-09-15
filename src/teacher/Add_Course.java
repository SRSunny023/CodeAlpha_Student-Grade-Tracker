package teacher;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import global.*;
import model.*;

public class Add_Course extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[] {
            new JButton("Add Course"),
            new JButton("Back"),
            new JButton("Exit")
    };

    JLabel courseLabel;
    JTextField courseField;

    JPanel[] panels = new JPanel[] {
            new JPanel(),
            new JPanel()
    };

    public Add_Course() {

        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttons[0]) {

            String name = courseField.getText();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isNameExist(name)){
                JOptionPane.showMessageDialog(this, "Duplicate Course Not Allowed", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File file = new File(Global_Variables.COURSE_LIST);

            try {

                if(!file.exists()){
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file, true);
                fw.write(name);
                fw.write(System.lineSeparator());
                fw.close();
                JOptionPane.showMessageDialog(this, "Course added successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding course!", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

            courseField.setText("");

        } else if (e.getSource() == buttons[1]) {

            new Global_Functions().clearScreen(this);
            new Teacher_Portal();

        } else {

            new Global_Functions().exitApp(this);

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
                if(line.equalsIgnoreCase(name)){
                    br.close();
                    return true;
                }
                if(line.trim().equalsIgnoreCase(name.trim())){
                    br.close();
                    return true;
                }
                String[] parts = line.split("\\ ");
                String newLine = "";
                for(int i=0; i<parts.length; i++){
                    newLine = newLine + parts[i];
                }
                if(newLine.trim().equalsIgnoreCase(name.trim())){
                    br.close();
                    return true;
                }
                String tempName = name;
                String[] tempParts = tempName.split("\\ ");
                String tempLine = "";
                for(int i=0; i<tempParts.length; i++){
                    tempLine = tempLine + tempParts[i];
                }
                if(newLine.trim().equalsIgnoreCase(tempLine.trim())){
                    br.close();
                    return true;
                }
                if(line.trim().equalsIgnoreCase(tempLine.trim())){
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

        courseField = new JTextField();
        courseField.setFont(new Font("Arial", Font.BOLD, 20));
        courseField.setForeground(Color.BLACK);
        courseField.setBounds(260, 224, 200, 50);
        panels[1].add(courseField);

    }

    private void createLabels() {

        courseLabel = new JLabel("Enter Course Name:");
        courseLabel.setFont(new Font("Arial", Font.BOLD, 20));
        courseLabel.setForeground(Color.WHITE);
        courseLabel.setBounds(50, 224, 200, 50);
        panels[1].add(courseLabel);

    }

    private void createMenu() {

        new Global_Functions().createPanels(this, panels, "Teacher Portal");
        createLabels();
        createFields();
        createButtons();
        new Global_Functions().createMainFrame(this);

    }

}
