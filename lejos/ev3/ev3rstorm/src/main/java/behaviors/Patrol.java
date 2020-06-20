package behaviors;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;

public class Patrol extends Thread {

    private static long _startTime;
    private long _millisecondsToRunUntil;
    private EV3ColorSensor _lightSensor;
    private float[] _lightSensorSample;
    private MovePilot _pilot;

    public Patrol(final long millisecondsToRunUntil, final long startTime, final EV3ColorSensor lightSensor,
            MovePilot pilot) {
        super();
        _millisecondsToRunUntil = millisecondsToRunUntil;
        _startTime = startTime;

        _lightSensor = lightSensor;
        final SampleProvider lightSensorSampleProvider = _lightSensor.getAmbientMode();
        _lightSensorSample = new float[lightSensorSampleProvider.sampleSize()];

        _pilot = pilot;
        _pilot.setLinearSpeed(20); // cm per second
    }

    @Override
    public void run() {
        while (System.currentTimeMillis() < _millisecondsToRunUntil) {

            _lightSensor.fetchSample(_lightSensorSample, 0);
            System.out.println(_lightSensorSample[0]);
            if (_lightSensorSample[0] < 25 && _lightSensorSample[0] >= 0) {
                _pilot.travelArc(60, -10);

                _pilot.rotate(90);

            } else {
                skate();
            }
        }
    }

    private void skate() {
        final int secondsSinceStart = (int) ((System.currentTimeMillis() - _startTime) / 1000);
        if (secondsSinceStart % 3 == 0) {
            _pilot.travelArc(50, 70);
        } else {
            _pilot.travelArc(-50, 70);
        }
    }
}