package behaviors;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class SpinBlade extends Thread {

    private long _millisecondsToRunUntil;
    private static BaseSensor _touchSensor;
    private static float[] _touchSensorSample;
    private static BaseRegulatedMotor _bladeMotor;

    public SpinBlade(final long millisecondsToRunUntil, final EV3TouchSensor touchSensor,
            final EV3MediumRegulatedMotor bladeMotor) {
        super();
        _millisecondsToRunUntil = millisecondsToRunUntil;

        _touchSensor = touchSensor;
        final SampleProvider touchSensorSampleProvider = touchSensor.getTouchMode();
        _touchSensorSample = new float[touchSensorSampleProvider.sampleSize()];

        _bladeMotor = bladeMotor;
        _bladeMotor.setSpeed((int) _bladeMotor.getMaxSpeed());

    }

    @Override
    public void run() {
        while (System.currentTimeMillis() < _millisecondsToRunUntil) {
            _touchSensor.fetchSample(_touchSensorSample, 0);

            if (_touchSensorSample[0] == 1 && !_bladeMotor.isMoving()) {
                _bladeMotor.rotate(360 * 6, true);
            }
        }
    }
}