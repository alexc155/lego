package legomindstorms.nxt.behaviors;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Follow implements Behavior {
    private DifferentialPilot _pilot;
    private UltrasonicSensor _ultrasonicSensor;
    private boolean _supressed;
    
    public Follow(DifferentialPilot pilot, UltrasonicSensor ultrasonicSensor) {
        _pilot = pilot;
        _ultrasonicSensor = ultrasonicSensor;
        _ultrasonicSensor.capture();
        _pilot.setTravelSpeed(15);
        _pilot.stop();
    }

	public boolean takeControl() {
        _ultrasonicSensor.ping();
        Delay.msDelay(100);
        return _ultrasonicSensor.getDistance() < 60;
    }

    public void suppress() {
        _supressed = true;
    }

    public void action() {
        _supressed = false;
        _pilot.backward();
        while (!_supressed) {
            Thread.yield();
        }
        _pilot.stop();
    }
}