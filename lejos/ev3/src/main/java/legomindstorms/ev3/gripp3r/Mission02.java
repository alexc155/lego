package legomindstorms.ev3.gripp3r;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;

public class Mission02 {

    public static void main(String[] args) throws IOException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestRegulatedMotor gripMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');
        gripMotor.setSpeed((int) (gripMotor.getMaxSpeed() * 0.75));

        final RemoteRequestPilot pilot = (RemoteRequestPilot) ev3.createPilot(3, 14, "B", "C");
        pilot.setLinearSpeed(50); // cm per second

        gripMotor.rotate(-720);
        pilot.travel(2 * 3.0f * 3.14159); // cm
        gripMotor.rotate(720);
        pilot.rotate(180);
        pilot.travel(2 * 3.0f * 3.14159); // cm
        gripMotor.rotate(-720);

        gripMotor.close();
        pilot.close();

        System.exit(0);
    }
}