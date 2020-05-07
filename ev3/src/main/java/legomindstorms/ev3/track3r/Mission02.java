package legomindstorms.ev3.track3r;

import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;

public class Mission02 {

    public Mission02() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestPilot pilot = (RemoteRequestPilot) ev3.createPilot(3, 18, "B", "C");
        final RemoteRequestRegulatedMotor motor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');
        motor.setSpeed((int) (0.75 * motor.getMaxSpeed()));
        pilot.setLinearSpeed(50);

        pilot.rotate(-50);
        motor.rotate(3 * 360);
        pilot.rotate(100);
        motor.rotate(6 * 360);
        pilot.rotate(-50);

        motor.close();
        pilot.close();

        System.exit(0);
    }

}