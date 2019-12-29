package legomindstorms.nxt.behaviors;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class Stop implements Behavior {
    private DifferentialPilot _pilot;

    public Stop(DifferentialPilot pilot) {
        _pilot = pilot;
    }

    public boolean takeControl() {
        return true;
    }

    public void suppress() {
    }

    public void action() {
        _pilot.stop();
    }
}