package behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RangeFinder;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Track3rHammerAttack implements Behavior {

    private final MovePilot _pilot;
    private final EV3MediumRegulatedMotor _motor;
    private final RangeFinder _sonar;

    public Track3rHammerAttack(final MovePilot pilot, final EV3MediumRegulatedMotor motor,
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