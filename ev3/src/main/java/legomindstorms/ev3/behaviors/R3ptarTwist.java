package legomindstorms.ev3.behaviors;

import java.rmi.RemoteException;

import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class R3ptarTwist implements Behavior {

    private boolean _suppressed;
    private final long _millisecondsToRunUntil;
    private final RMIRegulatedMotor _twistMotor;

    public R3ptarTwist(final long millisecondsToRunUntil, final RMIRegulatedMotor twistMotor) {
        _millisecondsToRunUntil = millisecondsToRunUntil;
        _twistMotor = twistMotor;
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
            _twistMotor.setSpeed((int) (_twistMotor.getMaxSpeed() * 0.1));
            _twistMotor.rotate(30);

            while (!_suppressed && System.currentTimeMillis() < _millisecondsToRunUntil) {
                _twistMotor.rotate(-60);
                _twistMotor.rotate(60);
            }
        } catch (final RemoteException e) {
            e.printStackTrace();
            suppress();
        }
        suppress();
    }

    @Override
    public void suppress() {
        try {
            _twistMotor.rotate(-30);
        } catch (final RemoteException e) {
            e.printStackTrace();
        }
        _suppressed = true;
    }
}