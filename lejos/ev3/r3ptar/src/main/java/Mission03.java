import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.Random;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Mission03 {

    private static final int secondsToRunFor = 60;

    public Mission03() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final EV3IRSensor eyesSensor = new EV3IRSensor(SensorPort.S4);

        final SampleProvider sp = eyesSensor.getDistanceMode();
        float[] sample = new float[sp.sampleSize()];
        
        final EV3MediumRegulatedMotor twistMotor = new EV3MediumRegulatedMotor(MotorPort.A);
        final EV3LargeRegulatedMotor slitherMotor = new EV3LargeRegulatedMotor(MotorPort.B);
        final EV3LargeRegulatedMotor jawsMotor = new EV3LargeRegulatedMotor(MotorPort.D);

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