package service;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.JOptionPane;
import global.*;

public class Ranking {

    public int[] getRanking(String studentID){

        File folder = new File(Global_Variables.GRADES_FOLDER);

        File[] files = folder.listFiles();

        Pair[] pairs = new Pair[files.length];

        int index = 0;

        for(File file : files){

            if(file.isFile() && file.getName().endsWith(".txt")){

                try{

                    String line = Files.readString(file.toPath());
                    String[] parts = line.split("\\|");
                    double cgpa = Double.parseDouble(parts[1]);
                    String id = file.getName().replace(".txt", "");
                    pairs[index++] = new Pair(id,cgpa);

                } catch(Exception e){
                    JOptionPane.showMessageDialog(null, "e.printStackTrace()", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        }

        Arrays.sort(pairs, 0, index, (p1,p2) -> Double.compare(p2.getCGPA(), p1.getCGPA()));

        for(int i=0; i<index; i++){

            if(pairs[i].getId().equals(studentID)){
                int[] arr = {i+1,index};
                return arr;
            }

        }

        int[] arr = {0,0};

        return arr;

    }

}
