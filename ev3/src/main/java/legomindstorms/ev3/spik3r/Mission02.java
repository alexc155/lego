package legomindstorms.ev3.spik3r;

import java.io.IOException;
import java.rmi.NotBoundException;

import legomindstorms.ev3.Base;
import lejos.remote.ev3.RMIRegulatedMotor;

public class Mission02 extends Base {

    public Mission02() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final RMIRegulatedMotor stingMotor = (RMIRegulatedMotor) ev3.createRegulatedMotor("D", 'L');
        final RMIRegulatedMotor legsMotor = (RMIRegulatedMotor) ev3.createRegulatedMotor("B", 'L');

        stingMotor.setSpeed((int) stingMotor.getMaxSpeed());
        legsMotor.setSpeed((int) legsMotor.getMaxSpeed());

        stingMotor.rotate(270);

        legsMotor.rotate(3 * 360);
        legsMotor.rotate(-2 * 360);

        stingMotor.rotate(-210);
        stingMotor.rotate(-60);

        stingMotor.close();
        legsMotor.close();
    }
}