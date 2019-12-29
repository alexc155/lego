package legomindstorms.ev3.r3ptar;

import java.io.IOException;
import java.rmi.NotBoundException;

import legomindstorms.ev3.Base;
import legomindstorms.ev3.behaviors.R3ptarSlither;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Mission01 extends Base {

    private static final int secondsToRunFor = 20;

    public Mission01() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RMIRegulatedMotor legsMotor = (RMIRegulatedMotor) ev3.createRegulatedMotor("B", 'L');
        final RMIRegulatedMotor twistMotor = (RMIRegulatedMotor) ev3.createRegulatedMotor("A", 'M');

        final Behavior b1 = new R3ptarSlither(System.currentTimeMillis() + (secondsToRunFor * 1000), legsMotor, twistMotor);

        final Behavior[] bArray = { b1 };
        final Arbitrator arby = new Arbitrator(bArray, true);

        arby.go(); // Carries on (blocking thread) until there are no more actions to take control

        arby.stop();

        legsMotor.close();
        twistMotor.close();
    }
}