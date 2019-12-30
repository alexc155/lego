package legomindstorms.nxt.shooterbot;

import legomindstorms.nxt.Base;
import legomindstorms.nxt.behaviors.Shoot;
import legomindstorms.nxt.behaviors.Spin;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class LocateObjects extends Base {

    public static void main(final String[] args) {

        countdown();

        final DifferentialPilot pilot = new DifferentialPilot(3.0f, 14.7f, Motor.B, Motor.C);

        final ColorSensor indicatorLamp = new ColorSensor(SensorPort.S3);

        final Behavior b1 = new Spin(pilot, indicatorLamp);
        final Behavior b2 = new Shoot(new UltrasonicSensor(SensorPort.S4), indicatorLamp, pilot, MotorPort.A);

        final Behavior[] bArray = { b1, b2 };
        final Arbitrator arby = new Arbitrator(bArray);
        arby.start();
    }
}
