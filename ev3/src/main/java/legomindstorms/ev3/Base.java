package legomindstorms.ev3;

import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.utility.Timer;
import lejos.utility.TimerListener;

public class Base implements TimerListener {

    static int counter;
    static Timer timer;
    private static TextLCD _graphicsLCD;
    protected static RemoteRequestEV3 ev3;

    public Base() throws NotBoundException, IOException {
        ev3 = new RemoteRequestEV3("192.168.0.34");
    }

    public static void countdown(final TextLCD graphicsLCD)
            throws NotBoundException, IOException {
        _graphicsLCD = graphicsLCD;
        _graphicsLCD.clear();
        _graphicsLCD.drawString("Hello Dave.", 0, 0);
        _graphicsLCD.drawString("Press any key to start...", 0, 1);
        Button.waitForAnyPress();
        _graphicsLCD.clear();

        counter = 5;

        timer = new Timer(1000, new Base());
        timer.start();

        while (counter > 0) {
            // wait
        }

        _graphicsLCD.clear();
    }

    public void timedOut() {
        Sound.beep();
        _graphicsLCD.drawString("Starting in " + counter, 0, 0);

        counter--;
        if (counter <= 0) {
            timer.stop();
        }
    }
}