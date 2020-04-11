package legomindstorms.ev3.r3ptar;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.Random;

import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import lejos.utility.Delay;

public class Mission03 {

    private static final int secondsToRunFor = 60;

    public Mission03() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestSampleProvider eyesSensor = (RemoteRequestSampleProvider) ev3.createSampleProvider("S4",
                "lejos.hardware.sensor.EV3IRSensor", "Distance");
        final RemoteRequestRegulatedMotor twistMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');
        final RemoteRequestRegulatedMotor slitherMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("B",
                'L');
        final RemoteRequestRegulatedMotor jawsMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("D", 'L');

        final float[] sample = new float[eyesSensor.sampleSize()];

        slitherMotor.setSpeed((int) (slitherMotor.getMaxSpeed() * 0.75));
        jawsMotor.setSpeed((int) jawsMotor.getMaxSpeed());
        jawsMotor.rotate(-10);

        final long millisecondsToRunUntil = System.currentTimeMillis() + (secondsToRunFor * 1000);

        while (System.currentTimeMillis() < millisecondsToRunUntil) {
            eyesSensor.fetchSample(sample, 0);
            if (sample[0] < 30) {
                twistMotor.stop(false);
                slitherMotor.stop(false);
                jawsMotor.setSpeed((int) jawsMotor.getMaxSpeed());
                jawsMotor.rotate(50);
                twistMotor.setSpeed((int) (twistMotor.getMaxSpeed() * 0.75));
                twistMotor.forward();
                slitherMotor.backward();
                jawsMotor.setSpeed((int) (jawsMotor.getMaxSpeed() * 0.3));
                jawsMotor.rotate(-50);
                Delay.msDelay(2000);
                twistMotor.rotate(-50);
                Delay.msDelay(1000);
            } else {
                slitherMotor.forward();
                final float power = (new Random()).nextInt(30) / 100;
                twistMotor.setSpeed((int) (twistMotor.getMaxSpeed() * power));
                int angle = 10;
                if ((new Random()).nextInt(1) == 1) {
                    angle = -10;
                }
                twistMotor.rotate(angle);
            }
        }

        twistMotor.close();
        jawsMotor.close();
        slitherMotor.close();
        eyesSensor.close();
        System.exit(0);
    }
}