package remote;

import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.utility.Delay;

public class Mission03 {

    public Mission03() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestPilot pilot = (RemoteRequestPilot) ev3.createPilot(3.0, 18.0, "B", "C");
        final RemoteRequestRegulatedMotor motor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');

        pilot.setLinearSpeed((0.5 * pilot.getMaxLinearSpeed()));
        motor.setSpeed((int) (0.75 * motor.getMaxSpeed()));
        motor.backward();

        for (int i = 0; i < 2; i++) {
            pilot.travel(2 * 3.0 * 3.14159);
            motor.forward();
            Delay.msDelay(500);
            pilot.travelArc(20, 3 * 3.0 * 3.14159);
            motor.backward();
            Delay.msDelay(500);
            pilot.travelArc(20, -3 * 3.0 * 3.14159);
        }

        motor.forward();
        pilot.travel(-6 * 3.0 * 3.14159);

        motor.close();
        pilot.close();

        System.exit(0);
    }

}