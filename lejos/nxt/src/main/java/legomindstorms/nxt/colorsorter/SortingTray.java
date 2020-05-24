package legomindstorms.nxt.colorsorter;

import java.io.File;

import legomindstorms.nxt.Base;
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.util.Delay;

public class SortingTray extends Base {

    public static void main(final String[] args) {

        countdown();

        final NXTRegulatedMotor tableRegulatedMotor = new NXTRegulatedMotor(MotorPort.B);
        final ColorSensor colorSensor = new ColorSensor(SensorPort.S3);
        final NXTRegulatedMotor trayRegulatedMotor = new NXTRegulatedMotor(MotorPort.C);
        final NXTMotor trayMotor = new NXTMotor(MotorPort.C);

        int rotated = -1;

        tableRegulatedMotor.forward();

        trayRegulatedMotor.suspendRegulation();
        trayMotor.setPower(50);

        while (Button.readButtons() == 0) {

            Color color = colorSensor.getColor();
            int colorVal = color.getColor();

            if (rotated < 0) {
                switch (colorVal) {
                case Color.RED:
                    Sound.playSample(new File("Red.wav"));
                    rotated = 0;
                    break;
                case Color.GREEN:
                    Sound.playSample(new File("Green.wav"));
                    trayRegulatedMotor.rotate(90);
                    rotated = 90;
                    break;
                case Color.BLUE:
                    Sound.playSample(new File("Blue.wav"));
                    trayRegulatedMotor.rotate(180);
                    rotated = 180;
                    break;
                case Color.YELLOW:
                    Sound.playSample(new File("Yellow.wav"));
                    trayRegulatedMotor.rotate(270);
                    rotated = 270;
                    break;
                case Color.WHITE:
                    break;
                }
            } else {
                while (colorVal == Color.RED || colorVal == Color.GREEN || colorVal == Color.BLUE || colorVal == Color.YELLOW) {
                    // Ball in the way
                    color = colorSensor.getColor();
                    colorVal = color.getColor();
                }
                Delay.msDelay(1000);
                trayRegulatedMotor.rotate(0 - rotated);
                rotated = -1;
            }
        }
    }
}