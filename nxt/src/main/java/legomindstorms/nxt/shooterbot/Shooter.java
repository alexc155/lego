package legomindstorms.nxt.shooterbot;

import legomindstorms.nxt.Base;
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Shooter extends Base {

    public static void main(String[] args) throws InterruptedException {

        countdown();

        while (Button.readButtons() == 0) {
            
            ColorSensor colorSensor = new ColorSensor(SensorPort.S3);
            colorSensor.setFloodlight(Color.GREEN);

            DifferentialPilot pilot = new DifferentialPilot(3.0f, 14.7f, Motor.B, Motor.C);
            pilot.setTravelSpeed(15);
            pilot.travel(3.0f * 3.14 * 3);
            pilot.stop();

            colorSensor.setFloodlight(Color.NONE);
            colorSensor.setFloodlight(Color.RED);

            Delay.msDelay(500);

            NXTRegulatedMotor shootRegulatedMotor = new NXTRegulatedMotor(MotorPort.A);
            NXTMotor shootMotor = new NXTMotor(MotorPort.A);

            shootRegulatedMotor.suspendRegulation();
            shootMotor.setPower(100);
            shootRegulatedMotor.rotate(360);
            shootRegulatedMotor.stop();

            colorSensor.setFloodlight(Color.NONE);
            colorSensor.setFloodlight(Color.BLUE);

            pilot.travel(-3.0f * 3.14 * 0.5);
            pilot.stop();

            pilot.rotate(90);

        }

        System.out.println("Done!");
    }
}
