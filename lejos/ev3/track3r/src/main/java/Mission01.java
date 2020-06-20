import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Mission01 {

    public Mission01() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final Wheel leftWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.B)), 3).offset(-6.4);
        final Wheel rightWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.C)), 3).offset(6.4);
        final Chassis chassis = new WheeledChassis(new Wheel[] { leftWheel, rightWheel }, WheeledChassis.TYPE_DIFFERENTIAL);

        final MovePilot pilot = new MovePilot(chassis);
        
        final EV3MediumRegulatedMotor motor = new EV3MediumRegulatedMotor(MotorPort.A);

        motor.setSpeed((int) (0.75 * motor.getMaxSpeed()));
        pilot.setLinearSpeed(30); // cm per second

        pilot.travel(2 * 3.0f * 3.14159); // cm
        motor.rotate(3 * 360);
        pilot.travel(-2 * 3.0f * 3.14159); // move backward for 2 revolutions

        pilot.stop();

        motor.close();

        System.exit(0);
    }

}