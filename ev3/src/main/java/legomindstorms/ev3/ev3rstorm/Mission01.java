package legomindstorms.ev3.ev3rstorm;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;

public class Mission01 {

    public static void main(String[] args) throws IOException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestPilot pilot = (RemoteRequestPilot) ev3.createPilot(3, 18, "B", "C");
        pilot.setLinearSpeed(30); // cm per second

        pilot.rotate(5 * 360);
        pilot.travelArc(50, 5 * 100);
        pilot.rotate(5 * 360);
        pilot.travelArc(-50, 5 * 100);

    }

}