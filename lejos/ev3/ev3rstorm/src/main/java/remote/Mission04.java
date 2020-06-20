package remote;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;

public class Mission04 {

    private static final int _secondsToRunFor = 30;
    private static long _startTime;
    private static RemoteRequestPilot _pilot;

    private static void skate() {
        final int secondsSinceStart = (int) ((System.currentTimeMillis() - _startTime) / 1000);
        if (secondsSinceStart % 3 == 0) {
            _pilot.travelArc(20, 10);
        } else {
            _pilot.travelArc(-20, 10);
        }
    }

    public static void main(final String[] args) throws IOException {

        _startTime = System.currentTimeMillis();

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final long millisecondsToRunUntil = System.currentTimeMillis() + (_secondsToRunFor * 1000);

        _pilot = (RemoteRequestPilot) ev3.createPilot(3, 12.8, "B", "C");
        _pilot.setLinearSpeed(20); // cm per second

        final RemoteRequestSampleProvider eyesSensor = (RemoteRequestSampleProvider) ev3.createSampleProvider("S4",
                "lejos.hardware.sensor.EV3IRSensor", "Distance");
        final float[] sample = new float[eyesSensor.sampleSize()];
        
        final RemoteRequestRegulatedMotor bladeMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');
        bladeMotor.setSpeed((int)bladeMotor.getMaxSpeed());

        while (millisecondsToRunUntil > System.currentTimeMillis()) {
            eyesSensor.fetchSample(sample, 0);
            if (sample[0] < 25) {
                _pilot.stop();
                bladeMotor.forward();
                _pilot.travelArc(20, 20);
                bladeMotor.stop();
                bladeMotor.backward();
                _pilot.travelArc(-20, -20);
                bladeMotor.stop();
                _pilot.travelArc(1, 40);
                _pilot.travelArc(40, 20);
            }
            else {
                skate();
            }
        }

        bladeMotor.close();
        eyesSensor.close();
        _pilot.close();

        System.exit(0);
    }
}