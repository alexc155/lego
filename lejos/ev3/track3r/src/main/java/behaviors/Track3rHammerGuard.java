package behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Track3rHammerGuard implements Behavior {

    private final MovePilot _pilot;
    private final EV3MediumRegulatedMotor _motor;
    private boolean _suppressed;
    private final long _millisecondsToRunUntil;

    public Track3rHammerGuard(final MovePilot pilot, final EV3MediumRegulatedMotor motor,
            final long millisecondsToRunUntil) {
        _pilot = pilot;
        _motor = motor;

        _pilot.setLinearSpeed((0.175 * _pilot.getMaxLinearSpeed()));
        _motor.setSpeed((int) (0.175 * _motor.getMaxSpeed()));

        _millisecondsToRunUntil = millisecondsToRunUntil;

    }

    @Override
    public boolean takeControl() {
        _suppressed = false;
        if (System.currentTimeMillis() < _millisecondsToRunUntil) {
            return true;
        }
        return false;
    }

    @Override
    public void action() {
        while (!_suppressed) {
            _pilot.rotate(30, true);
            _motor.rotate(60);
            _motor.rotate(-60, true);
            if (System.currentTimeMillis() > _millisecondsToRunUntil) {
                _motor.rotate(-60, true);
                suppress();
            }
        }
    }

    @Override
    public void suppress() {
        _suppressed = true;
    }
}