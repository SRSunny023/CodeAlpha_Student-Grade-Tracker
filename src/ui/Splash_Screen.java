package ui;

import javax.swing.*;

import Global.*;
import service.Session_Check;

public class Splash_Screen extends JFrame {

    public Splash_Screen(){

        ImageIcon welcomeIcon = new ImageIcon(Global_Variables.WELCOME_ICON);
        JLabel welcomeIconLabel = new JLabel(welcomeIcon);
        welcomeIconLabel.setBounds(0, 0, Global_Variables.WINDOW_WIDTH, Global_Variables.WINDOW_HEIGHT);
        add(welcomeIconLabel);

        setLayout(null);
        setUndecorated(true);
        setLocation(Global_Variables.X_POSITION, Global_Variables.Y_POSITION);
        setSize(Global_Variables.WINDOW_WIDTH, Global_Variables.WINDOW_HEIGHT);
        setVisible(true);setSize(Global_Variables.WINDOW_WIDTH, Global_Variables.WINDOW_HEIGHT);

        try{

            Timer timer = new Timer(Global_Variables.SPLASH_TIME, e->{

                setVisible(false);

                dispose();

                new Session_Check();

            });

            timer.setRepeats(false);
            timer.start();

        } catch(Exception e){
            e.printStackTrace();
        }

    }


}
