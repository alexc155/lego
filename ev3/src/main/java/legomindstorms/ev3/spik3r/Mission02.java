package legomindstorms.ev3.spik3r;

import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestEV3;

public class Mission02 {

    public Mission02() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestRegulatedMotor stingMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("D", 'L');
        final RemoteRequestRegulatedMotor legsMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("B", 'L');

        stingMotor.setSpeed((int) stingMotor.getMaxSpeed());
        legsMotor.setSpeed((int) legsMotor.getMaxSpeed());

        stingMotor.rotate(270);

        legsMotor.rotate(3 * 360);
        legsMotor.rotate(-2 * 360);

        stingMotor.rotate(-210);
        stingMotor.rotate(-60);

        stingMotor.close();
        legsMotor.close();
    }
}