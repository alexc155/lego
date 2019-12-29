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

    public static void main(String[] args) {

        countdown();

        DifferentialPilot pilot = new DifferentialPilot(3.0f, 14.7f, Motor.B, Motor.C);

        ColorSensor indicatorLamp = new ColorSensor(SensorPort.S3);

        Behavior b1 = new Spin(pilot, indicatorLamp);
        Behavior b2 = new Shoot(new UltrasonicSensor(SensorPort.S4), indicatorLamp, pilot, MotorPort.A);

        Behavior[] bArray = { b1, b2 };
        Arbitrator arby = new Arbitrator(bArray);
        arby.start();
    }
}
