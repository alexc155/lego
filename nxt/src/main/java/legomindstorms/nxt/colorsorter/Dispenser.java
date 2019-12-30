package legomindstorms.nxt.colorsorter;

import legomindstorms.nxt.Base;
import legomindstorms.nxt.behaviors.TiltTable;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Dispenser extends Base {

    public static void main(final String[] args) {

        countdown();

        final Behavior b1 = new TiltTable(MotorPort.B, new TouchSensor(SensorPort.S1));

        final Behavior[] bArray = { b1 };
        final Arbitrator arby = new Arbitrator(bArray);
        arby.start();

    }
}