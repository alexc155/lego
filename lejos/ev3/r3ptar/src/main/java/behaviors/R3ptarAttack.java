package behaviors;

import java.rmi.RemoteException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class R3ptarAttack extends Thread implements Behavior {

    private boolean _suppressed;
    private final long _millisecondsToRunUntil;
    private final float[] _sample;

    private final EV3LargeRegulatedMotor _jawsMotor;
    private final EV3IRSensor _eyesSensor;

    public R3ptarAttack(final long millisecondsToRunUntil, final EV3LargeRegulatedMotor jawsMotor,
            final EV3IRSensor eyesSensor) throws RemoteException {
        _millisecondsToRunUntil = millisecondsToRunUntil;
        _jawsMotor = jawsMotor;
        _jawsMotor.setSpeed((int) _jawsMotor.getMaxSpeed());

        _eyesSensor = eyesSensor;
        final SampleProvider sp = eyesSensor.getDistanceMode();
        _sample = new float[sp.sampleSize()];

        _suppressed = false;
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