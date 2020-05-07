package legomindstorms.ev3.ev3rstorm;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;

public class Mission01 {

    public static void main(String[] args) throws IOException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestPilot pilot = (RemoteRequestPilot) ev3.createPilot(3, 12.8, "B", "C");
        pilot.setLinearSpeed(20); // cm per second

        pilot.travel(50);
        pilot.rotate(180);
        pilot.travel(50);
        pilot.rotate(-180);

        pilot.close();
        System.exit(0);

    }

}