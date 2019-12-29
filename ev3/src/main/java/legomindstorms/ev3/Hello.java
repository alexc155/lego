package legomindstorms.ev3;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.utility.Delay;

public class Hello {

    public static void main(final String args[]) {
        final GraphicsLCD graphicslcd = BrickFinder.getDefault().getGraphicsLCD();
        final int i = graphicslcd.getWidth();
        final int j = graphicslcd.getHeight();
        Button.LEDPattern(4);
        graphicslcd.setFont(Font.getLargeFont());
        graphicslcd.drawString("Hello!", i / 2, j / 2, 65);
        Button.LEDPattern(3);
        Delay.msDelay(4000L);
        Button.LEDPattern(5);
        graphicslcd.clear();
        graphicslcd.refresh();
        Delay.msDelay(500L);
        Button.LEDPattern(0);
    }
}
