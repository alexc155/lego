package behaviors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class R3ptarTwist extends Thread implements Behavior {

    private boolean _suppressed;
    private final long _millisecondsToRunUntil;
    private final EV3MediumRegulatedMotor _twistMotor;

    public R3ptarTwist(final long millisecondsToRunUntil, final EV3MediumRegulatedMotor twistMotor) {
        _millisecondsToRunUntil = millisecondsToRunUntil;
        _twistMotor = twistMotor;
        _twistMotor.setSpeed((int) (_twistMotor.getMaxSpeed() * 0.2));
        _suppressed = false;
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
        try {
            while (!_suppressed && System.currentTimeMillis() < _millisecondsToRunUntil) {
                _twistMotor.rotate(-80);
                _twistMotor.rotate(80);
            }
        } catch (final Exception e) {
            e.printStackTrace();
            suppress();
        }
        suppress();
    }

    @Override
    public void suppress() {
        try {
        } catch (final Exception e) {
            e.printStackTrace();
        }
        _suppressed = true;
    }

    public void run() {
        action();
    }
}