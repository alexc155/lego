package remote;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;

// This 'sort of' works. Threads don't seem to work properly in lejos Remote - Or at least you can't have mutiple RemoteRequestSampleProviders
// And the skate process isn't great either.

public class Mission03 {

    private static final int _secondsToRunFor = 30;
    private static float[] _bladeSample = new float[1];

    public static void main(final String[] args) throws IOException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final long millisecondsToRunUntil = System.currentTimeMillis() + (_secondsToRunFor * 1000);
        final long startTime = System.currentTimeMillis();

        final RemoteRequestSampleProvider lightSensor = (RemoteRequestSampleProvider) ev3.createSampleProvider("S3",
                "lejos.hardware.sensor.EV3ColorSensor", "Ambient");
        final RemoteRequestPilot pilot = (RemoteRequestPilot) ev3.createPilot(3, 12.8, "B", "C");
        pilot.setLinearSpeed(20); // cm per second

        final RemoteRequestSampleProvider touchSensor = (RemoteRequestSampleProvider) ev3.createSampleProvider("S1",
                "lejos.hardware.sensor.EV3TouchSensor", "Touch");

        final RemoteRequestRegulatedMotor bladeMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');
        bladeMotor.setSpeed((int) bladeMotor.getMaxSpeed());

        final float[] lightSample = new float[1];

        while (System.currentTimeMillis() < millisecondsToRunUntil) {

            lightSensor.fetchSample(lightSample, 0);

            if (lightSample[0] * 100 < 25) {
                pilot.travelArc(60, -10);
                SpinBlade(touchSensor, bladeMotor);

                pilot.rotate(90);
                SpinBlade(touchSensor, bladeMotor);

            } else {
                final int secondsSinceStart = (int) ((System.currentTimeMillis() - startTime) / 1000);
                if (secondsSinceStart % 3 == 0) {
                    pilot.travelArc(20, 10);
                } else {
                    pilot.travelArc(-20, 10);
                }
                SpinBlade(touchSensor, bladeMotor);

            }

        }

        bladeMotor.close();
        touchSensor.close();
        pilot.close();
        lightSensor.close();

        System.exit(0);
    }

    private static void SpinBlade(final RemoteRequestSampleProvider touchSensor,
            final RemoteRequestRegulatedMotor bladeMotor) {

        touchSensor.fetchSample(_bladeSample, 0);

        if (_bladeSample[0] == 1 && !bladeMotor.isMoving()) {
            bladeMotor.rotate(360 * 6, true);
        }
    }
}