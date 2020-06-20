import java.io.IOException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Mission01 {

    public static void main(final String[] args) throws IOException {

        final Wheel leftWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.B)), 3).offset(-6.4);
        final Wheel rightWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.C)), 3).offset(6.4);
        final Chassis chassis = new WheeledChassis(new Wheel[] { leftWheel, rightWheel }, WheeledChassis.TYPE_DIFFERENTIAL);
        final MovePilot pilot = new MovePilot(chassis);
        pilot.setLinearSpeed(20); // cm per second

        pilot.travel(50);
        pilot.rotate(180);
        pilot.travel(50);
        pilot.rotate(-180);

        System.exit(0);

    }

}