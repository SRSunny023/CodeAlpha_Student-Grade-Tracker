package global;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Inactivity_Manager {

    private static Timer timer;
    private static final int TIMEOUT_MS = 1800000;

    public static void startWatching() {

        timer = new Timer(TIMEOUT_MS, e -> {
            System.exit(0);
        });
        timer.setRepeats(false);
        timer.start();

        long eventMask = AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.KEY_EVENT_MASK;

        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                timer.restart();
            }
        }, eventMask);
    }

}