package service;

import java.io.*;
import java.util.*;
import global.*;

public class Load_All_Student_Report {

    public Load_All_Student_Report(String[] reports){

        countStudentsAndGradesReceived(reports);

    }

    private  void countStudentsAndGradesReceived(String[] reports){

        ArrayList<Double> studentsCGPA = new ArrayList<>();
        ArrayList<Integer> studentsMarks = new ArrayList<>();

        try{

            BufferedReader br = new BufferedReader(new FileReader(Global_Variables.STUDENT_ID));

            int studentCount = 0;
            int gradesReceivedCount = 0;
            int totalPassed = 0;

            String line;

            while((line=br.readLine())!=null){

                if(line.trim().isEmpty()){
                    continue;
                }

                String[] parts = line.split("\\|");

                if(parts.length>=3){

                    studentCount++;

                    File file = new File(Global_Variables.GRADES_FOLDER + parts[1] + ".txt");

                    try{

                        if(file.exists()){

                            gradesReceivedCount++;

                            BufferedReader br1 = new BufferedReader(new FileReader(file));

                            String line1;

                            while((line1=br1.readLine())!=null){
                                if(line1.trim().isEmpty()){
                                    continue;
                                }
                                String[] parts1 = line1.split("\\|");
                                if(parts1.length>=2){
                                    studentsCGPA.add(Double.valueOf(parts1[1]));
                                    studentsMarks.add(Integer.valueOf(parts1[0]));
                                    Double grade = Double.valueOf(parts1[1]);
                                    if(grade>0.0){
                                        totalPassed++;
                                    }
                                }
                            }

                            br1.close();

                        }

                    } catch(Exception e){
                        Arrays.fill(reports,"0");
                    }

                }
            }

            Collections.sort(studentsCGPA);
            Collections.sort(studentsMarks);

            double sum = 0;
            double sum1 = 0;
            for(double cgpa : studentsCGPA) sum+=cgpa;
            for(double scoring : studentsMarks) sum1+=scoring;
            double averageMarks = studentsMarks.isEmpty() ? 0.0 : sum1 / studentsMarks.size();
            double average = studentsCGPA.isEmpty() ? 0.0 : sum / studentsCGPA.size();

            reports[0] = String.valueOf(studentCount);
            reports[1] = String.valueOf(gradesReceivedCount);
            reports[2] = String.valueOf(totalPassed);
            reports[3] = String.valueOf(gradesReceivedCount-totalPassed);
            reports[4] = String.valueOf(studentsCGPA.isEmpty() ? "0.0" : studentsCGPA.get(studentsCGPA.size()-1));
            reports[5] = String.valueOf(studentsCGPA.isEmpty() ? "0.0" : studentsCGPA.get(0));
            reports[6] = String.format(Locale.US, "%.2f",average);
            reports[7] = String.valueOf(studentsMarks.isEmpty() ? "0" : studentsMarks.get(studentsMarks.size()-1));
            reports[8] = String.valueOf(studentsMarks.isEmpty() ? "0" : studentsMarks.get(0));
            reports[9] = String.format(Locale.US, "%.2f",averageMarks);

            br.close();

        } catch(Exception e){
            Arrays.fill(reports,"0");
        }

    }

}
