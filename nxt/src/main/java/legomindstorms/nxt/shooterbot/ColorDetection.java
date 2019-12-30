package legomindstorms.nxt.shooterbot;

import legomindstorms.nxt.Base;
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Move;

public class ColorDetection extends Base {

    public static void main(final String[] args) {

        countdown();

        final DifferentialPilot pilot = new DifferentialPilot(3.0f, 14.4f, Motor.B, Motor.C);
        pilot.setTravelSpeed(2);
        pilot.forward();

        final ColorSensor colorSensor = new ColorSensor(SensorPort.S3);

        while (Button.readButtons() == 0) {
            final Color color = colorSensor.getColor();
            if (color.getColor() == Color.RED) {
                break;
            }
        }

        final Move movement = pilot.getMovement();
        pilot.rotate(180);
        pilot.travel(movement.getDistanceTraveled());
        pilot.rotate(-180);

        System.out.println("Done!");
        Button.waitForAnyPress();
    }
}
