package legomindstorms.ev3.behaviors;

import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;

public class Ev3rstormSpinBlade extends Thread {

    private final long _millisecondsToRunUntil;
    private final RemoteRequestSampleProvider _touchSensor;
    private final RemoteRequestRegulatedMotor _bladeMotor;

    public Ev3rstormSpinBlade(final long millisecondsToRunUntil, final RemoteRequestSampleProvider touchSensor,
            final RemoteRequestRegulatedMotor bladeMotor) {
        super();
        _millisecondsToRunUntil = millisecondsToRunUntil;
        _touchSensor = touchSensor;
        _bladeMotor = bladeMotor;
        _bladeMotor.setSpeed((int) _bladeMotor.getMaxSpeed());
    }

    public void run() {
        final float[] sample = new float[_touchSensor.sampleSize()];
        _touchSensor.fetchSample(sample, 0);

        while (System.currentTimeMillis() < _millisecondsToRunUntil) {
            _touchSensor.fetchSample(sample, 0);
            if (sample[0] == 1 && !_bladeMotor.isMoving()) {
                _bladeMotor.rotate(360 * 6, true);
            }
        }
    }
}