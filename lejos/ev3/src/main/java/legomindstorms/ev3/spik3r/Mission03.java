package legomindstorms.ev3.spik3r;

import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestEV3;

public class Mission03 {

    public Mission03() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestRegulatedMotor pincerMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A",
                'M');
        final RemoteRequestRegulatedMotor legsMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("B", 'L');

        pincerMotor.setSpeed((int) (pincerMotor.getMaxSpeed() / 3));
        legsMotor.setSpeed((int) legsMotor.getMaxSpeed());

        pincerMotor.rotate(170);
        pincerMotor.rotate(-170);

        pincerMotor.rotate(170);
        pincerMotor.rotate(-170);

        pincerMotor.setSpeed((int) pincerMotor.getMaxSpeed());

        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 3; j++) {
                pincerMotor.rotate(170);
                pincerMotor.rotate(-170);
            }

            legsMotor.rotate(3 * 360);
            legsMotor.rotate(-2 * 360);
        }

        pincerMotor.close();
        legsMotor.close();

        System.exit(0);
    }
}