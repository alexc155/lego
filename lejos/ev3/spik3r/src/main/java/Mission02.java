import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Mission02 {

    public Mission02() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final EV3LargeRegulatedMotor stingMotor = new EV3LargeRegulatedMotor(MotorPort.D);
        final EV3LargeRegulatedMotor legsMotor = new EV3LargeRegulatedMotor(MotorPort.B);

        stingMotor.setSpeed((int) stingMotor.getMaxSpeed());
        legsMotor.setSpeed((int) legsMotor.getMaxSpeed());

        stingMotor.rotate(270);

        legsMotor.rotate(3 * 360);
        legsMotor.rotate(-2 * 360);

        stingMotor.rotate(-210);
        stingMotor.rotate(-60);

        stingMotor.close();
        legsMotor.close();

        System.exit(0);
    }
}