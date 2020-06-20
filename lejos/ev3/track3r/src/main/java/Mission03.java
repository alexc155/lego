import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Mission03 {

    public Mission03() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final Wheel leftWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.B)), 3).offset(-6.4);
        final Wheel rightWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.C)), 3).offset(6.4);
        final Chassis chassis = new WheeledChassis(new Wheel[] { leftWheel, rightWheel },
                WheeledChassis.TYPE_DIFFERENTIAL);

        final MovePilot pilot = new MovePilot(chassis);

        final EV3MediumRegulatedMotor motor = new EV3MediumRegulatedMotor(MotorPort.A);

        pilot.setLinearSpeed((0.5 * pilot.getMaxLinearSpeed()));
        motor.setSpeed((int) (0.75 * motor.getMaxSpeed()));
        motor.backward();

        for (int i = 0; i < 2; i++) {
            pilot.travel(2 * 3.0 * 3.14159);
            motor.forward();
            Delay.msDelay(500);
            pilot.travelArc(20, 3 * 3.0 * 3.14159);
            motor.backward();
            Delay.msDelay(500);
            pilot.travelArc(20, -3 * 3.0 * 3.14159);
        }

        motor.forward();
        pilot.travel(-6 * 3.0 * 3.14159);

        motor.close();

        System.exit(0);
    }

}