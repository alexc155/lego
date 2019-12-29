package legomindstorms.ev3.spik3r;

import java.io.IOException;
import java.rmi.NotBoundException;

import legomindstorms.ev3.Base;
import legomindstorms.ev3.behaviors.Spik3rPatrol;
import legomindstorms.ev3.behaviors.Spik3rAttack;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RMISampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Mission04 extends Base {

    private static final int secondsToRunFor = 20;

    public Mission04() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RMIRegulatedMotor pincerMotor = (RMIRegulatedMotor) ev3.createRegulatedMotor("A", 'M');
        final RMIRegulatedMotor legsMotor = (RMIRegulatedMotor) ev3.createRegulatedMotor("B", 'L');
        final RMIRegulatedMotor stingMotor = (RMIRegulatedMotor) ev3.createRegulatedMotor("D", 'L');
        final RMISampleProvider irSensor = (RMISampleProvider) ev3.createSampleProvider("S4",
                "lejos.hardware.sensor.EV3UltrasonicSensor", "distance");

        pincerMotor.setSpeed((int) (pincerMotor.getMaxSpeed() / 2));
        legsMotor.setSpeed((int) legsMotor.getMaxSpeed());
        stingMotor.setSpeed((int) stingMotor.getMaxSpeed());

        final Behavior b1 = new Spik3rPatrol(System.currentTimeMillis() + (secondsToRunFor * 1000), legsMotor);
        final Behavior b2 = new Spik3rAttack(legsMotor, pincerMotor, stingMotor, irSensor);

        final Behavior[] bArray = { b1, b2 };
        final Arbitrator arby = new Arbitrator(bArray, true);

        arby.go(); // Carries on (blocking thread) until there are no more actions to take control

        arby.stop();
        irSensor.close();

        pincerMotor.close();
        legsMotor.close();
        stingMotor.close();
    }
}