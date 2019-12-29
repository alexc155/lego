package legomindstorms.nxt.colorsorter;

import legomindstorms.nxt.Base;
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.util.Delay;

public class Catapult extends Base {

    public static void main(String[] args) {

        countdown();

        NXTRegulatedMotor tableRegulatedMotor = new NXTRegulatedMotor(MotorPort.B);
        ColorSensor colorSensor = new ColorSensor(SensorPort.S3);

        NXTRegulatedMotor trayRegulatedMotor = new NXTRegulatedMotor(MotorPort.C);
        NXTMotor trayMotor = new NXTMotor(MotorPort.C);

        NXTRegulatedMotor catapultRegulatedMotor = new NXTRegulatedMotor(MotorPort.A);
        NXTMotor catapultMotor = new NXTMotor(MotorPort.A);

        catapultRegulatedMotor.suspendRegulation();
        catapultMotor.setPower(100);

        int rotated = -1;

        tableRegulatedMotor.forward();

        trayRegulatedMotor.suspendRegulation();
        trayMotor.setPower(50);

        while (Button.readButtons() == 0) {

            Color color = colorSensor.getColor();
            int colorVal = color.getColor();

            System.out.println(colorVal);

            if (rotated == -1) {
                switch (colorVal) {
                case Color.RED:
                    Delay.msDelay(1000);
                    trayRegulatedMotor.rotate(-60);
                    rotated = -60;
                    break;
                case Color.GREEN:
                    Delay.msDelay(1000);
                    trayRegulatedMotor.rotate(-30);
                    rotated = -30;
                    break;
                case Color.BLUE:
                    Delay.msDelay(1000);
                    trayRegulatedMotor.rotate(30);
                    rotated = 30;
                    break;
                case Color.YELLOW:
                    Delay.msDelay(1000);
                    trayRegulatedMotor.rotate(60);
                    rotated = 60;
                    break;
                case Color.WHITE:
                    break;
                }
                if (rotated != -1) {
                    catapultRegulatedMotor.rotate(120);
                    catapultRegulatedMotor.rotate(-120);
                }
            } else {
                while (colorVal == Color.RED || colorVal == Color.GREEN || colorVal == Color.BLUE
                        || colorVal == Color.YELLOW) {
                    // Ball in the way
                    color = colorSensor.getColor();
                    colorVal = color.getColor();
                }
                Delay.msDelay(1000);
                if (rotated != -1) {
                    trayRegulatedMotor.rotate(0 - rotated);
                }
                rotated = -1;
            }
        }
    }
}