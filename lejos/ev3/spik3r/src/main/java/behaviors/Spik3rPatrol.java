package behaviors;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class Spik3rPatrol implements Behavior {

    private boolean _suppressed;
    private final long _millisecondsToRunUntil;
    private final EV3LargeRegulatedMotor _legsMotor;

    public Spik3rPatrol(final long millisecondsToRunUntil, final EV3LargeRegulatedMotor legsMotor) {
        _millisecondsToRunUntil = millisecondsToRunUntil;
        _legsMotor = legsMotor;
    }

    @Override
    public boolean takeControl() {
        _suppressed = false;
        if (System.currentTimeMillis() < _millisecondsToRunUntil) {
            return true;
        }
        suppress();
        return false;
    }

    @Override
    public void action() {
        while (!_suppressed) {
            try {
                _legsMotor.rotate(3 * 360);
                _legsMotor.rotate(-2 * 360);
            } catch (final Exception e) {
                e.printStackTrace();
                suppress();
            }
            if (System.currentTimeMillis() > _millisecondsToRunUntil) {
                suppress();
            }
        }
    }

    @Override
    public void suppress() {
        _suppressed = true;
    }
}