package legomindstorms.ev3.r3ptar;

import java.io.IOException;
import java.rmi.NotBoundException;

import legomindstorms.ev3.behaviors.R3ptarAttack;
import legomindstorms.ev3.behaviors.R3ptarTwist;
import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;

public class Mission02 {

    private static final int secondsToRunFor = 30;

    public Mission02() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final long millisecondsToRunUntil = System.currentTimeMillis() + (secondsToRunFor * 1000);

        final RemoteRequestRegulatedMotor twistMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');
        final RemoteRequestRegulatedMotor jawsMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("D", 'L');
        final RemoteRequestSampleProvider eyesSensor = (RemoteRequestSampleProvider) ev3.createSampleProvider("S4",
                "lejos.hardware.sensor.EV3IRSensor", "Distance");

        final R3ptarAttack r3ptarAttack = new R3ptarAttack(millisecondsToRunUntil, jawsMotor, eyesSensor);
        final R3ptarTwist r3ptarTwist = new R3ptarTwist(millisecondsToRunUntil, twistMotor);

        r3ptarAttack.start();
        r3ptarTwist.start();
        while (millisecondsToRunUntil > System.currentTimeMillis()) {
            // wait
        }
        twistMotor.close();
        jawsMotor.close();
        eyesSensor.close();
        System.exit(0);
    }
}