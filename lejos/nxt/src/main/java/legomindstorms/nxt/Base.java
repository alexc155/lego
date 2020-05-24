package legomindstorms.nxt;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.util.Timer;
import lejos.util.TimerListener;

public class Base implements TimerListener {

    static int counter;
    static Timer timer;

    public static void countdown() {
        System.out.println("Hello Dave.");
        System.out.println("Press any key to start...");
        Button.waitForAnyPress();
        LCD.clear();

        counter = 5;

        timer = new Timer(1000, new Base());
        timer.start();

        while (counter > 0) {
            // wait
        }

        LCD.clear();
    }

    public void timedOut() {
        Sound.beep();
        LCD.drawString("Starting in " + counter, 0, 0);

        counter --;
        if (counter == 0)
        {
            timer.stop();
        }
    }    
}