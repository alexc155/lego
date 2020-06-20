import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Mission03 {

    public Mission03() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final EV3MediumRegulatedMotor pincerMotor = new EV3MediumRegulatedMotor(MotorPort.A);

        final EV3LargeRegulatedMotor legsMotor = new EV3LargeRegulatedMotor(MotorPort.B);

        pincerMotor.setSpeed((int) (pincerMotor.getMaxSpeed() / 3));
        legsMotor.setSpeed((int) legsMotor.getMaxSpeed());

        pincerMotor.rotate(170);
        pincerMotor.rotate(-170);

        pincerMotor.rotate(170);
        pincerMotor.rotate(-170);

        pincerMotor.setSpeed((int) pincerMotor.getMaxSpeed());

        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 3; j++) {
                pincerMotor.rotate(170);
                pincerMotor.rotate(-170);
            }

            legsMotor.rotate(3 * 360);
            legsMotor.rotate(-2 * 360);
        }

        pincerMotor.close();
        legsMotor.close();

        System.exit(0);
    }
}