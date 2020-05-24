package legomindstorms.ev3.spik3r;

import java.io.IOException;
import java.rmi.NotBoundException;

import legomindstorms.ev3.behaviors.Spik3rPatrol;
import legomindstorms.ev3.behaviors.Spik3rAttack;
import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Mission04 {

    private static final int secondsToRunFor = 20;

    public Mission04() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestRegulatedMotor pincerMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A",
                'M');
        final RemoteRequestRegulatedMotor legsMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("B", 'L');
        final RemoteRequestRegulatedMotor stingMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("D", 'L');
        final RemoteRequestSampleProvider irSensor = (RemoteRequestSampleProvider) ev3.createSampleProvider("S4",
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

        System.exit(0);
    }
}