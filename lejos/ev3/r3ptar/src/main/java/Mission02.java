import java.io.IOException;
import java.rmi.NotBoundException;

import behaviors.R3ptarAttack;
import behaviors.R3ptarTwist;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;

public class Mission02 {

    private static final int secondsToRunFor = 30;

    public Mission02() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final long millisecondsToRunUntil = System.currentTimeMillis() + (secondsToRunFor * 1000);

        final EV3MediumRegulatedMotor twistMotor = new EV3MediumRegulatedMotor(MotorPort.A);
        final EV3LargeRegulatedMotor jawsMotor = new EV3LargeRegulatedMotor(MotorPort.D);

        final EV3IRSensor eyesSensor = new EV3IRSensor(SensorPort.S4);

        final R3ptarAttack r3ptarAttack = new R3ptarAttack(millisecondsToRunUntil, jawsMotor, eyesSensor);
        final R3ptarTwist r3ptarTwist = new R3ptarTwist(millisecondsToRunUntil, twistMotor);

        r3ptarAttack.start();
        r3ptarTwist.start();
        while (millisecondsToRunUntil > System.currentTimeMillis() || r3ptarTwist.isAlive()|| r3ptarAttack.isAlive()) {
            // wait
        }
        twistMotor.close();
        jawsMotor.close();
        eyesSensor.close();

        System.exit(0);
    }
}