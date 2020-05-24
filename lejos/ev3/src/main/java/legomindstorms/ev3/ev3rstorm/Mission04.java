package legomindstorms.ev3.ev3rstorm;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;

public class Mission04 {

    private static final int secondsToRunFor = 30;
    private static long startTime;
    private static RemoteRequestPilot pilot;

    private static void skate() {
        int secondsSinceStart = (int) ((System.currentTimeMillis() - startTime) / 1000);
        if (secondsSinceStart % 3 == 0) {
            pilot.travelArc(20, 10);
        } else {
            pilot.travelArc(-20, 10);
        }
    }

    public static void main(String[] args) throws IOException {

        startTime = System.currentTimeMillis();

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final long millisecondsToRunUntil = System.currentTimeMillis() + (secondsToRunFor * 1000);

        pilot = (RemoteRequestPilot) ev3.createPilot(3, 12.8, "B", "C");
        pilot.setLinearSpeed(20); // cm per second

        final RemoteRequestSampleProvider eyesSensor = (RemoteRequestSampleProvider) ev3.createSampleProvider("S4",
                "lejos.hardware.sensor.EV3IRSensor", "Distance");
        float[] sample = new float[eyesSensor.sampleSize()];
        
        final RemoteRequestRegulatedMotor bladeMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');
        bladeMotor.setSpeed((int)bladeMotor.getMaxSpeed());

        while (millisecondsToRunUntil > System.currentTimeMillis()) {
            eyesSensor.fetchSample(sample, 0);
            if (sample[0] < 25) {
                pilot.stop();
                bladeMotor.forward();
                pilot.travelArc(20, 20);
                bladeMotor.stop();
                bladeMotor.backward();
                pilot.travelArc(-20, -20);
                bladeMotor.stop();
                pilot.travelArc(1, 40);
                pilot.travelArc(40, 20);
            }
            else {
                skate();
            }
        }

        bladeMotor.close();
        eyesSensor.close();
        pilot.close();

        System.exit(0);
    }
}