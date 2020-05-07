package legomindstorms.ev3.behaviors;

import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestSampleProvider;

public class Ev3rstormPatrol extends Thread {

    private long _millisecondsToRunUntil;
    private RemoteRequestSampleProvider _lightSensor;
    private RemoteRequestPilot _pilot;
    private long _startTime;

    public Ev3rstormPatrol(final long millisecondsToRunUntil, final RemoteRequestSampleProvider lightSensor,
            final RemoteRequestPilot pilot, final long startTime) {
        super();
        _millisecondsToRunUntil = millisecondsToRunUntil;
        _lightSensor = lightSensor;
        _pilot = pilot;
        _startTime = startTime;
    }

    public void run() {
        while (System.currentTimeMillis() < _millisecondsToRunUntil) {
            float[] ambientLightSample = new float[_lightSensor.sampleSize()];
            if (ambientLightSample[0] < 25) {
                _pilot.travelArc(100, 100);
                _pilot.rotate(90);
            } else {
                skate();
            }
        }
    }

    private void skate() {
        int secondsSinceStart = (int) ((System.currentTimeMillis() - _startTime) / 1000);
        if (secondsSinceStart % 3 == 0) {
            _pilot.arcForward(180);
        } else {
            _pilot.arcForward(-180);
        }
    }
}