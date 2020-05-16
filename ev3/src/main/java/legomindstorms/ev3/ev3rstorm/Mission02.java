package legomindstorms.ev3.ev3rstorm;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestSampleProvider;

public class Mission02 {

    private static final int secondsToRunFor = 30;

    public static void main(String[] args) throws IOException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final long millisecondsToRunUntil = System.currentTimeMillis() + (secondsToRunFor * 1000);

        final RemoteRequestPilot pilot = (RemoteRequestPilot) ev3.createPilot(3, 12.8, "B", "C");
        pilot.setLinearSpeed(20); // cm per second

        final RemoteRequestSampleProvider touchSensor = (RemoteRequestSampleProvider) ev3.createSampleProvider("S1",
                "lejos.hardware.sensor.EV3TouchSensor", "Touch");
        float[] sample = new float[touchSensor.sampleSize()];
        touchSensor.fetchSample(sample, 0);

        while (millisecondsToRunUntil > System.currentTimeMillis()) {
            while (sample[0] != 1 && millisecondsToRunUntil > System.currentTimeMillis()) {
                touchSensor.fetchSample(sample, 0);
            }
            while (sample[0] != 0 && millisecondsToRunUntil > System.currentTimeMillis()) {
                touchSensor.fetchSample(sample, 0);
            }
            pilot.rotate(100);
            pilot.rotate(-100);
        }

        touchSensor.close();
        pilot.close();

        System.exit(0);
    }
}