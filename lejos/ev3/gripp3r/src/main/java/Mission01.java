import java.io.IOException;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Mission01 {

    public static void main(final String[] args) throws IOException {

        final EV3MediumRegulatedMotor gripMotor = new EV3MediumRegulatedMotor(MotorPort.A);

        gripMotor.setSpeed((int) (gripMotor.getMaxSpeed() * 0.75));

        gripMotor.rotate(-720);
        gripMotor.rotate(720);
        Delay.msDelay(1000);
        gripMotor.rotate(-720);

        gripMotor.close();

        System.exit(0);
    }
}