package teacher;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import global.*;

public class Enter_Grades extends JFrame implements ActionListener {

    public static final int COURSE_SIZE = 10;
    String name,id;
    JPanel leftPanel,rightPanel;
    JLabel[] labels = new JLabel[]{
        new JLabel("C Programming"),
        new JLabel("Data Structure"),
        new JLabel("Discrete Mathematics"),
        new JLabel("Digital Logic Design"),
        new JLabel("Artificial Intelligence"),
        new JLabel("Machine Learning"),
        new JLabel("Software Engineering"),
        new JLabel("Object Oriented Programming"),
        new JLabel("Algorithms"),
        new JLabel("Computer Fundamentals")
    };
    JTextField[] fields = new JTextField[]{
        new JTextField(),
        new JTextField(),
        new JTextField(),
        new JTextField(),
        new JTextField(),
        new JTextField(),
        new JTextField(),
        new JTextField(),
        new JTextField(),
        new JTextField()
    };
    JButton[] buttons = new JButton[]{
        new JButton("Exit"),
        new JButton("Submit"),
        new JButton("Back")
    };
    JLabel[] studentDetails = new JLabel[]{
        new JLabel("Name:"),
        new JLabel("ID:"),
        new JLabel(),
        new JLabel()
    };

    public Enter_Grades(String name, String id){

        this.name = name;
        this.id = id;

        createRightPanel();
        createLeftPanel();
        createStudentDetails();
        createLabels();
        createFields();
        createButtons();
        createMenu();

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==buttons[0]){

            System.exit(0);

        }

        else if(e.getSource()==buttons[1]){

            int[] marks = new int[COURSE_SIZE];
            String[] grades = new String[COURSE_SIZE];
            Double[] points = new Double[COURSE_SIZE];

            for(int i=0; i<fields.length; i++){

                try{

                    int score = Integer.parseInt(fields[i].getText().trim());

                    if(score<0 || score>100){
                        JOptionPane.showMessageDialog(this, "Invalid Marks Input (Choose Between 0-100)", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    marks[i] = score;

                    if(score>=80){
                        grades[i] = "A+";
                        points[i] = 4.00;
                    } else if(score>=75){
                        grades[i] = "A";
                        points[i] = 3.75;
                    } else if(score>=70){
                        grades[i] = "A-";
                        points[i] = 3.50;
                    } else if(score>=65){
                        grades[i] = "B+";
                        points[i] = 3.25;
                    } else if(score>=60){
                        grades[i] = "B";
                        points[i] = 3.00;
                    } else if(score>=55){
                        grades[i] = "B-";
                        points[i] = 2.75;
                    } else if(score>=50){
                        grades[i] = "C+";
                        points[i] = 2.50;
                    } else if(score>=45){
                        grades[i] = "C";
                        points[i] = 2.25;
                    } else if(score>=40){
                        grades[i] = "D";
                        points[i] = 2.00;
                    } else{
                        grades[i] = "F";
                        points[i] = 0.00;
                    }


                } catch(Exception ex){
                    JOptionPane.showMessageDialog(this, "Invalid Marks Input (Choose Between 0-100)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }

            int totalMarks = 0;
            Double totalPoints = 0.0;
            for(int i=0; i<COURSE_SIZE; i++){
                totalMarks+=marks[i];
                totalPoints+=points[i];
            }
            double rawCgpa = totalPoints / (double) COURSE_SIZE;
            double cgpa = Math.round(rawCgpa * 100.0) / 100.0;

            File file1 = new File(Global_Variables.MARKSHEET_FOLDER + id + ".txt");
            File file2 = new File(Global_Variables.GRADES_FOLDER + id + ".txt");

            try{

                if(!file1.exists()){
                    file1.createNewFile();
                }

                if(!file2.exists()){
                    file2.createNewFile();
                }

                FileWriter fw = new FileWriter(file1);
                FileWriter fw1 = new FileWriter(file2);

                for(int i=0; i<COURSE_SIZE; i++){
                    String line = labels[i].getText() + "|" + marks[i] + "|" + grades[i] + "|" + points[i] + "\n";
                    fw.write(line);
                }
                fw1.write(totalMarks + "|" + cgpa + "\n");

                fw.close();
                fw1.close();

                JOptionPane.showMessageDialog(this, "Grades Successfully Updated", "Success", JOptionPane.PLAIN_MESSAGE);

                setVisible(false);
                dispose();
                new Show_All_Student("enterGrades");


            } catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Unexpected Error Occurred", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

        else if(e.getSource()==buttons[2]){

            setVisible(false);
            dispose();
            new Show_All_Student("enterGrades");

        }

    }

    private void loadMarks(String[] marks){

        File file = new File(Global_Variables.MARKSHEET_FOLDER + id + ".txt");

        try{

            if(!file.exists()){
                Arrays.fill(marks,"0");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            int i=0;

            while((line=br.readLine())!=null){

                if(line.trim().isEmpty()){
                    continue;
                }

                String[] parts = line.split("\\|");

                if(parts.length>=4){

                    marks[i++] = parts[1];

                }

            }

            br.close();

        } catch(Exception ex){
            Arrays.fill(marks,"0");
        }

    }

    private void createStudentDetails(){

        for(int i=0; i<studentDetails.length; i++){
            if(i==2){
                studentDetails[i].setText(name);
            } else if(i==3){
                studentDetails[i].setText(id);
            }
            studentDetails[i].setFont(new Font("Arial", Font.BOLD, 24));
            studentDetails[i].setForeground(Color.BLACK);
            if(i==0 || i==1){
                studentDetails[i].setBounds(100,0+(30*(i+1)),100,30);
            } else{
                studentDetails[i].setBounds(210,0+(30*(i-1)),300,30);
            }

            leftPanel.add(studentDetails[i]);
        }

    }

    private void createLabels(){

        for(int i=0; i<COURSE_SIZE; i++){
            labels[i].setFont(new Font("Arial",Font.BOLD,16));
            labels[i].setForeground(Color.BLACK);
            labels[i].setBounds(40,130+(40*(i+1)),250,30);
            leftPanel.add(labels[i]);
        }

    }

    private void createFields(){

        String[] marks = new String[COURSE_SIZE];
        loadMarks(marks);

        for(int i=0; i<COURSE_SIZE; i++){
            fields[i].setText(marks[i]);
            fields[i].setFont(new Font("Arial",Font.BOLD,16));
            fields[i].setForeground(Color.WHITE);
            fields[i].setBackground(Color.BLACK);
            fields[i].setBounds(290,130+(40*(i+1)),150,30);
            leftPanel.add(fields[i]);
        }

    }

    private void createButtons(){

        for(int i=0; i<buttons.length; i++){
            buttons[i].setFont(new Font("Arial",Font.BOLD,16));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setFocusPainted(false);
            buttons[i].setBounds(0+(120*i+80),738,120,30);
            buttons[i].addActionListener(this);
            leftPanel.add(buttons[i]);
        }

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
