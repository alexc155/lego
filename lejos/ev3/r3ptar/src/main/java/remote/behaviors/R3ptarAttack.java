package remote.behaviors;

import java.rmi.RemoteException;

import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import lejos.robotics.subsumption.Behavior;

public class R3ptarAttack extends Thread implements Behavior {

    private boolean _suppressed;
    private final long _millisecondsToRunUntil;
    private float[] _sample;

    private final RemoteRequestRegulatedMotor _jawsMotor;
    private final RemoteRequestSampleProvider _eyesSensor;

    public R3ptarAttack(final long millisecondsToRunUntil, final RemoteRequestRegulatedMotor jawsMotor,
            final RemoteRequestSampleProvider eyesSensor) throws RemoteException {
        _millisecondsToRunUntil = millisecondsToRunUntil;
        _jawsMotor = jawsMotor;
        _eyesSensor = eyesSensor;

        _jawsMotor.setSpeed((int) _jawsMotor.getMaxSpeed());
        _suppressed = false;
        _sample = new float[_eyesSensor.sampleSize()];
    }

    @Override
    public boolean takeControl() {
        try {
            _eyesSensor.fetchSample(_sample, 0);
            if (_sample[0] < 30) {
                return true;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void action() {
        try {
            _jawsMotor.rotate(60);
            _jawsMotor.rotate(-60);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void suppress() {
        _suppressed = true;
    }

    public void run() {
        while (!_suppressed && System.currentTimeMillis() < _millisecondsToRunUntil) {
            if (takeControl()) {
                action();
            }
        }
    }
}