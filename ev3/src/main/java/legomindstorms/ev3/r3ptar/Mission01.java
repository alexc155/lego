package legomindstorms.ev3.r3ptar;

import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestEV3;

public class Mission01 {

    private static final int secondsToRunFor = 20;

    public Mission01() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestRegulatedMotor legsMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("B", 'L');
        final RemoteRequestRegulatedMotor twistMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');

        final long millisecondsToRunUntil = System.currentTimeMillis() + (secondsToRunFor * 1000);

        legsMotor.setSpeed((int) (legsMotor.getMaxSpeed() * 0.75));
        legsMotor.forward();

        twistMotor.setSpeed((int) (twistMotor.getMaxSpeed() * 0.1));
        twistMotor.rotate(30);

        while (millisecondsToRunUntil > System.currentTimeMillis()) {
            try {
                twistMotor.rotate(-60);
                twistMotor.rotate(60);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        legsMotor.stop(true);
        twistMotor.rotate(-30);

        legsMotor.close();
        twistMotor.close();
    }
}