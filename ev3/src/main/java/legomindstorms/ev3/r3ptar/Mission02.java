package legomindstorms.ev3.r3ptar;

import java.io.IOException;
import java.rmi.NotBoundException;

import legomindstorms.ev3.Base;
import legomindstorms.ev3.behaviors.R3ptarAttack;
import legomindstorms.ev3.behaviors.R3ptarTwist;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RMISampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Mission02 extends Base {

    private static final int secondsToRunFor = 20;

    public Mission02() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RMIRegulatedMotor twistMotor = (RMIRegulatedMotor) ev3.createRegulatedMotor("A", 'M');
        final RMIRegulatedMotor jawsMotor = (RMIRegulatedMotor) ev3.createRegulatedMotor("D", 'L');
        final RMISampleProvider eyesSensor = (RMISampleProvider) ev3.createSampleProvider("S4",
                "lejos.hardware.sensor.EV3UltrasonicSensor", "distance");

        final Behavior b1 = new R3ptarTwist(System.currentTimeMillis() + (secondsToRunFor * 1000), twistMotor);
        final Behavior b2 = new R3ptarAttack(jawsMotor, eyesSensor);

        final Behavior[] bArray = { b1, b2 };
        final Arbitrator arby = new Arbitrator(bArray, true);

        arby.go(); // Carries on (blocking thread) until there are no more actions to take control

        arby.stop();

        twistMotor.close();
        jawsMotor.close();
    }
}