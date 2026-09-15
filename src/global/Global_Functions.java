package global;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Global_Functions {

    public void exitApp(JFrame parentFrame){

        int result = JOptionPane.showConfirmDialog(parentFrame, "Do you really want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);

        if(result == JOptionPane.YES_OPTION){
            System.exit(0);
        }

    }

    public void clearScreen(JFrame parentFrame){
        parentFrame.setVisible(false);
        parentFrame.dispose();
    }

    public void createPanels(JFrame parentFrame, JPanel[] panels, String type){

        for (int i = 0; i < 2; i++) {
            panels[i].setLayout(null);
            if (i == 0) {
                panels[i].setBounds(0, 0, 512, Global_Variables.WINDOW_HEIGHT);
                panels[i].setForeground((type.equals("Student Portal")) ? Color.WHITE : Color.BLACK);
                panels[i].setBackground((type.equals("Student Portal")) ? Color.BLACK : Color.WHITE);
            } else {
                panels[i].setBounds(513, 0, 512, Global_Variables.WINDOW_HEIGHT);
                panels[i].setForeground((type.equals("Student Portal")) ? Color.BLACK : Color.WHITE);
                panels[i].setBackground((type.equals("Student Portal")) ? Color.WHITE : Color.BLACK);
            }
            parentFrame.add(panels[i]);
        }

    }

    public void createMainFrame(JFrame parentFrame){

        parentFrame.getContentPane().setBackground(Color.BLACK);
        parentFrame.setLayout(null);
        parentFrame.setUndecorated(true);
        parentFrame.setLocation(Global_Variables.X_POSITION, Global_Variables.Y_POSITION);
        parentFrame.setSize(Global_Variables.WINDOW_WIDTH, Global_Variables.WINDOW_HEIGHT);
        parentFrame.setVisible(true);

    }

    public String countTotalMarks(){

        File file = new File(Global_Variables.COURSE_LIST);

        try{

            if(!file.exists()){
                return "0";
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int count = 0;
            while((line=br.readLine())!=null){
                if(line.trim().isEmpty()){
                    continue;
                }
                count++;
            }
            br.close();
            return String.valueOf(count*100);


        } catch(Exception e){
            return "0";
        }

    }

}
