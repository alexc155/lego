package legomindstorms.nxt.shooterbot;

import legomindstorms.nxt.Base;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class DrivingBase extends Base {

    public static void main(final String[] args) {

        countdown();

        final DifferentialPilot pilot = new DifferentialPilot(3.0f, 14.4f, Motor.B, Motor.C);

        pilot.setTravelSpeed(5);
        pilot.travel(3.0f * 3.14f * 5);
        pilot.stop();
        pilot.travel(-3.0f * 3.14f * 5);
        pilot.stop();

        System.out.println("Done!");
        Button.waitForAnyPress();
    }
}
