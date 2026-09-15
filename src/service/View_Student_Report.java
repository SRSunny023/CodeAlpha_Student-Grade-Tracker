package service;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import global.*;
import model.*;

public class View_Student_Report extends JFrame implements ActionListener {

    String name,id,portalType,type;

    JLabel[] titles = new JLabel[]{
        new JLabel("Name:"),
        new JLabel("ID:"),
        new JLabel(),
        new JLabel()
    };

    JLabel[] labels = new JLabel[]{
        new JLabel("CGPA:"),
        new JLabel("Total Marks:"),
        new JLabel("Position:"),
        new JLabel(),
        new JLabel(),
        new JLabel()
    };

    JPanel[] panels = new JPanel[]{
        new JPanel(),
        new JPanel()
    };

    JButton[] buttons = new JButton[]{
        new JButton("Back"),
        new JButton("Exit")
    };

    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel model;

    public View_Student_Report(String name, String id, String portalType, String type){

        this.name = name;
        this.id = id;
        this.portalType = portalType;
        this.type = type;

        createMenu();
        loadCGPA_Marks();

        if(type.equals("Full Result")){
            createTable();
            loadMarksheet();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==buttons[0]){

            new Global_Functions().clearScreen(this);

            if(portalType.equals("Student Portal")){
                new Student_Portal(name,id);
            } else{
                new Teacher_Portal();
            }


        } else{

            new Global_Functions().exitApp(this);

        }

    }

    private void loadCGPA_Marks(){

        File file = new File(Global_Variables.GRADES_FOLDER + id + ".txt");

        try{

            if(!file.exists()){

                JOptionPane.showMessageDialog(this, "Your Result Hasn't Been Published Yet!", "Result Not Published", JOptionPane.INFORMATION_MESSAGE);
                return;

            }

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = br.readLine();

            String[] parts = line.split("\\|");

            if(parts.length>=2){
                labels[3].setText(parts[1]);
                labels[4].setText(parts[0] + " / 1000");
            }

            br.close();

        } catch(Exception e){

            JOptionPane.showMessageDialog(this, "Unexpected Error Occurred", "Error", JOptionPane.ERROR_MESSAGE);
            return;

        }

    }

    private void loadMarksheet(){

        File file = new File(Global_Variables.MARKSHEET_FOLDER + id + ".txt");

        try{

            if(!file.exists()){

                return;

            }

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while((line=br.readLine())!=null){

                if(line.trim().isEmpty()){
                    continue;
                }

                String[] parts = line.split("\\|");

                if(parts.length>=4){

                    String course = parts[0];
                    String mark = parts[1];
                    String grade = parts[2];
                    String point = parts[3];

                    model.addRow(new Object[]{course,mark,grade,point});

                }

            }

            br.close();

        } catch(Exception e){

            JOptionPane.showMessageDialog(this, "Unexpected Error Occurred", "Error", JOptionPane.ERROR_MESSAGE);
            return;

        }

    }

    private void createTable() {
        String[] columns = { "Course", "Mark", "Grade", "Point" };
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setForeground(Color.BLACK);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.getColumnModel().getColumn(0).setPreferredWidth(250);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 200, 430, 400);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getViewport().setForeground(Color.BLACK);

        if(portalType.equals("Student Portal")){
            panels[1].add(scrollPane);
        } else{
            panels[0].add(scrollPane);
        }

    }

    private void createTitles(){

        for(int i=0; i<titles.length; i++){

            titles[i].setFont(new Font("Arial", Font.BOLD, 20));
            titles[i].setForeground(Color.BLACK);

            if(i%2==0){
                titles[i].setBounds((i==0) ? 40 : 250, 80, 200, 30);
            } else{
                titles[i].setBounds((i==1) ? 40 : 250, 140, 200, 30);
            }

            if(i==2){
                titles[i].setText(name);
            } else if(i==3){
                titles[i].setText(id);
            }

            if(portalType.equals("Student Portal")){
                panels[1].add(titles[i]);
            } else{
                panels[0].add(titles[i]);
            }

        }

    }

    private void createLabels(){

        for(int i=0; i<labels.length; i++){

            labels[i].setFont(new Font("Arial", Font.BOLD, 20));
            labels[i].setForeground(Color.BLACK);

            if(i<3){
                labels[i].setBounds(40, (type.equals("Full Result")) ? 620 + (30*i) : 200 + (30*i), 250, 30);
            } else{
                labels[i].setBounds(200, (type.equals("Full Result")) ? 620 + (30*(i-3)) : 200 + (30*(i-3)), 250, 30);
                labels[i].setText("Result Not Published Yet");
            }

            if(portalType.equals("Student Portal")){
                panels[1].add(labels[i]);
            } else{
                panels[0].add(labels[i]);
            }

        }

    }

    private void createButtons(){

        for(int i=0; i<buttons.length; i++){

            buttons[i].setFont(Global_Variables.btnFont);
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setForeground(Color.BLACK);
            buttons[i].addActionListener(this);
            buttons[i].setBounds(0+(360*i),738, 150, 30);

            if(portalType.equals("Student Portal")){
                panels[1].add(buttons[i]);
            } else{
                panels[0].add(buttons[i]);
            }

        }

    }

    private void createMenu(){

        new Global_Functions().createPanels(this, panels, portalType);
        createTitles();
        createLabels();
        createButtons();
        new Global_Functions().createMainFrame(this);

    }

    public static void main(String[] args){

        new View_Student_Report("Napusa", "26-9-10", "Student Portal", "Full Result");

    }

}
