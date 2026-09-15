package service;

import java.io.*;
import global.*;
import model.*;
import ui.*;

public class Session_Check {

    public Session_Check() {

        try {

            BufferedReader br = new BufferedReader(new FileReader(Global_Variables.CURRENT_SESSION));

            String line = br.readLine();

            if (line == null) {

                new Main_Menu();

            }

            String[] parts = line.split("\\|");
            String id = parts[1];
            if (id.equals(Global_Variables.TEACHER_PORTAL_ID)) {

                new Teacher_Portal();

            } else {

                new Student_Portal(parts[0],id);

            }

            br.close();

        } catch (Exception e) {
            return;
        }

    }

}
