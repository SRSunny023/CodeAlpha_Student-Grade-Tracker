package service;

import java.io.BufferedReader;
import java.io.FileReader;

import Global.Global_Variables;
import ui.Main_Menu;

public class Session_Check {

    public Session_Check(){

        try{

            BufferedReader br = new BufferedReader(new FileReader(Global_Variables.CURRENT_SESSION));

            String line = br.readLine();
            if(line==null){

                new Main_Menu();

            }

            String[] parts = line.split("\\|");
            String userRole = parts[1];
            if (userRole.equals("Student")) {
                //
            } else {
                //
            }

            br.close();

        } catch(Exception e){
            return;
        }

    }

}
