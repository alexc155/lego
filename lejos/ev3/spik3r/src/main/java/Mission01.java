import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Mission01 {

    public Mission01() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final EV3LargeRegulatedMotor motor = new EV3LargeRegulatedMotor(MotorPort.D);

        motor.setSpeed((int) motor.getMaxSpeed());

        motor.rotate(270);
        motor.rotate(-220);
        motor.rotate(-120);

        motor.close();

        System.exit(0);
    }
}