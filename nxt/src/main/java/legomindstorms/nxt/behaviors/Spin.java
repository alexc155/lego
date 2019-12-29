package legomindstorms.nxt.behaviors;

import lejos.nxt.ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class Spin implements Behavior {
    private boolean _suppressed = false;
    private DifferentialPilot _pilot;
    private ColorSensor _guardLamp;

    public Spin(DifferentialPilot pilot, ColorSensor guardLamp) {
        _pilot = pilot;
        _guardLamp = guardLamp;
        _pilot.setRotateSpeed(3);
    }

    public boolean takeControl() {
        return true;
    }

    public void suppress() {
        _suppressed = true;
    }

    public void action() {

        _suppressed = false;

        _guardLamp.setFloodlight(Color.GREEN);

        _pilot.rotateLeft();

        while (!_suppressed) {
            Thread.yield();
        }

        _pilot.stop();

        _guardLamp.setFloodlight(Color.NONE);
    }
}