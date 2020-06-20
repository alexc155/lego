package remote;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.utility.Delay;

public class Mission01 {

    public static void main(final String[] args) throws IOException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestRegulatedMotor gripMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');
        gripMotor.setSpeed((int) (gripMotor.getMaxSpeed() * 0.75));

        gripMotor.rotate(-720);
        gripMotor.rotate(720);
        Delay.msDelay(1000);
        gripMotor.rotate(-720);

        gripMotor.close();

        System.exit(0);
    }
}