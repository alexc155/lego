import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Mission01 {

    private static final int secondsToRunFor = 20;

    public Mission01() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final EV3LargeRegulatedMotor legsMotor = new EV3LargeRegulatedMotor(MotorPort.B);
        final EV3MediumRegulatedMotor twistMotor = new EV3MediumRegulatedMotor(MotorPort.A);

        final long millisecondsToRunUntil = System.currentTimeMillis() + (secondsToRunFor * 1000);

        legsMotor.setSpeed((int) (legsMotor.getMaxSpeed() * 0.75));
        legsMotor.forward();

        twistMotor.setSpeed((int) (twistMotor.getMaxSpeed() * 0.1));
        twistMotor.rotate(30);

        while (millisecondsToRunUntil > System.currentTimeMillis()) {
            try {
                twistMotor.rotate(-60);
                twistMotor.rotate(60);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        legsMotor.stop(true);
        twistMotor.rotate(-30);

        legsMotor.close();
        twistMotor.close();

        System.exit(0);
    }
}