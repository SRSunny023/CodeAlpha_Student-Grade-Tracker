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
    Show_All_Student show_All_Student;
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

    public Enter_Grades(String name, String id, Show_All_Student show_All_Student){

        this.name = name;
        this.id = id;
        this.show_All_Student = show_All_Student;

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
            String[] points = new String[COURSE_SIZE];

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
                        points[i] = "4.00";
                    } else if(score>=75){
                        grades[i] = "A";
                        points[i] = "3.75";
                    } else if(score>=70){
                        grades[i] = "A-";
                        points[i] = "3.50";
                    } else if(score>=65){
                        grades[i] = "B+";
                        points[i] = "3.25";
                    } else if(score>=60){
                        grades[i] = "B";
                        points[i] = "3.00";
                    } else if(score>=55){
                        grades[i] = "B-";
                        points[i] = "2.75";
                    } else if(score>=50){
                        grades[i] = "C+";
                        points[i] = "2.50";
                    } else if(score>=45){
                        grades[i] = "C";
                        points[i] = "2.25";
                    } else if(score>=40){
                        grades[i] = "D";
                        points[i] = "2.00";
                    } else{
                        grades[i] = "F";
                        points[i] = "0.00";
                    }


                } catch(Exception ex){
                    JOptionPane.showMessageDialog(this, "Invalid Marks Input (Choose Between 0-100)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }

            File file = new File(Global_Variables.GRADES_FOLDER + id + ".txt");

            try{

                if(!file.exists()){
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file);

                for(int i=0; i<COURSE_SIZE; i++){
                    String line = labels[i].getText() + "|" + marks[i] + "|" + grades[i] + "|" + points[i] + "\n";
                    fw.write(line);
                }

                fw.close();

                JOptionPane.showMessageDialog(this, "Grades Successfully Updated", "Success", JOptionPane.PLAIN_MESSAGE);

                setVisible(false);
                dispose();
                show_All_Student.setEnabled(true);


            } catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Unexpected Error Occurred", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

        else if(e.getSource()==buttons[2]){

            setVisible(false);
            dispose();
            show_All_Student.setEnabled(true);

        }

    }

    private void loadMarks(String[] marks){

        File file = new File(Global_Variables.GRADES_FOLDER + id + ".txt");

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

            add(studentDetails[i]);
        }

    }

    private void createLabels(){

        for(int i=0; i<COURSE_SIZE; i++){
            labels[i].setFont(new Font("Arial",Font.BOLD,16));
            labels[i].setForeground(Color.BLACK);
            labels[i].setBounds(40,130+(40*(i+1)),250,30);
            add(labels[i]);
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
            add(fields[i]);
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
            add(buttons[i]);
        }

    }

    private void createMenu(){
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setForeground(Color.BLACK);
        setLayout(null);
        setUndecorated(true);
        setLocation(Global_Variables.X_POSITION, Global_Variables.Y_POSITION);
        setSize(512, Global_Variables.WINDOW_HEIGHT);
        setVisible(true);
    }

}
