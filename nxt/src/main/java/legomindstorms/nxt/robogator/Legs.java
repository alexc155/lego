package legomindstorms.nxt.robogator;

import legomindstorms.nxt.Base;
import legomindstorms.nxt.behaviors.Bite;
import legomindstorms.nxt.behaviors.Stop;
import legomindstorms.nxt.behaviors.Follow;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Legs extends Base {

    public static void main(String[] args) {

        countdown();

        DifferentialPilot pilot = new DifferentialPilot(3.0f, 14.7f, Motor.B, Motor.C);

        Behavior b1 = new Stop(pilot);
        Behavior b2 = new Follow(pilot, new UltrasonicSensor(SensorPort.S4));
        Behavior b3 = new Bite(100, 80, true);

        Behavior[] bArray = { b1, b2, b3 };
        Arbitrator arby = new Arbitrator(bArray);
        arby.start();

    }
}