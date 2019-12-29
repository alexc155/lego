package legomindstorms.ev3.spik3r;

import java.io.IOException;
import java.rmi.NotBoundException;

import legomindstorms.ev3.Base;
import lejos.remote.ev3.RMIRegulatedMotor;

public class Mission01 extends Base {

    public Mission01() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RMIRegulatedMotor motor = (RMIRegulatedMotor) ev3.createRegulatedMotor("D", 'L');
        motor.setSpeed((int) motor.getMaxSpeed());

        motor.rotate(270);
        motor.rotate(-220);
        motor.rotate(-120);

        motor.close();
    }
}