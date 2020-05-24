package legomindstorms.ev3.behaviors;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.robotics.RangeFinder;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.subsumption.Behavior;

public class Track3rHammerAttack implements Behavior {

    private final RemoteRequestPilot _pilot;
    private final RemoteRequestRegulatedMotor _motor;
    private final RangeFinder _sonar;

    public Track3rHammerAttack(final RemoteRequestPilot pilot, final RemoteRequestRegulatedMotor motor,
            final EV3IRSensor irSensor) {
        _pilot = pilot;
        _motor = motor;
        _sonar = new RangeFinderAdapter(irSensor);

        _pilot.setLinearSpeed((0.175 * _pilot.getMaxLinearSpeed()));
        _motor.setSpeed((int) (1 * _motor.getMaxSpeed()));
    }

    @Override
    public boolean takeControl() {
        final float distance = _sonar.getRange();
        if (!Float.isInfinite(distance)) {
            return distance / 100 < 25.0;
        }
        return false;
    }

    @Override
    public void action() {
        _pilot.rotate(180);
        _motor.rotate(180);
        _motor.rotate(-180);
    }

    @Override
    public void suppress() {
    }
}