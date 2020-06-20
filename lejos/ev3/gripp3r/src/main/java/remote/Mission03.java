package remote;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;

public class Mission03 {

    public static void main(final String[] args) throws IOException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestRegulatedMotor gripMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');
        gripMotor.setSpeed((int) (gripMotor.getMaxSpeed() * 0.75));

        final RemoteRequestPilot pilot = (RemoteRequestPilot) ev3.createPilot(3, 14, "B", "C");
        pilot.setLinearSpeed(50); // cm per second

        final RemoteRequestSampleProvider eyesSensor = (RemoteRequestSampleProvider) ev3.createSampleProvider("S4",
                "lejos.hardware.sensor.EV3IRSensor", "Distance");
        final float[] sample = new float[eyesSensor.sampleSize()];
        eyesSensor.fetchSample(sample, 0);

        gripMotor.rotate(-720);
        pilot.forward();
        while (sample[0] >= 25) {
            eyesSensor.fetchSample(sample, 0);
        }
        pilot.stop();
        gripMotor.rotate(720);
        pilot.rotate(180);
        pilot.forward();
        eyesSensor.fetchSample(sample, 0);
        while (sample[0] >= 25) {
            eyesSensor.fetchSample(sample, 0);
        }
        pilot.stop();

        eyesSensor.close();
        pilot.close();
        gripMotor.close();

        System.exit(0);
    }
}