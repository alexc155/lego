import java.io.IOException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Mission02 {

    public static void main(String[] args) throws IOException {

        final EV3MediumRegulatedMotor gripMotor = new EV3MediumRegulatedMotor(MotorPort.A);
        gripMotor.setSpeed((int) (gripMotor.getMaxSpeed() * 0.75));

        final Wheel leftWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.B)), 3).offset(-6.4);
        final Wheel rightWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.C)), 3).offset(6.4);
        final Chassis chassis = new WheeledChassis(new Wheel[] { leftWheel, rightWheel },
                WheeledChassis.TYPE_DIFFERENTIAL);
        final MovePilot pilot = new MovePilot(chassis);

        pilot.setLinearSpeed(50); // cm per second

        gripMotor.rotate(-720);
        pilot.travel(2 * 3.0f * 3.14159); // cm
        gripMotor.rotate(720);
        pilot.rotate(180);
        pilot.travel(2 * 3.0f * 3.14159); // cm
        gripMotor.rotate(-720);

        gripMotor.close();

        System.exit(0);
    }
}