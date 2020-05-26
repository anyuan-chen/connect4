package game;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class myTimer {
    public myTimer(){
        int timerLeft = Options.timerLength;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (timerLeft == -1){
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
}
