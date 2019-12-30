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

    public static void main(final String[] args) {

        countdown();

        final DifferentialPilot pilot = new DifferentialPilot(3.0f, 14.7f, Motor.B, Motor.C);

        final Behavior b1 = new Stop(pilot);
        final Behavior b2 = new Follow(pilot, new UltrasonicSensor(SensorPort.S4));
        final Behavior b3 = new Bite(100, 80, true);

        final Behavior[] bArray = { b1, b2, b3 };
        final Arbitrator arby = new Arbitrator(bArray);
        arby.start();

    }
}