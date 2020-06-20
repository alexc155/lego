import java.io.IOException;

import behaviors.Patrol;
import behaviors.SpinBlade;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Mission03 {

    private static final int _secondsToRunFor = 30;

    public static void main(final String[] args) throws IOException {

        final long millisecondsToRunUntil = System.currentTimeMillis() + (_secondsToRunFor * 1000);

        final Wheel leftWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.B)), 3).offset(-6.4);
        final Wheel rightWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.C)), 3).offset(6.4);
        final Chassis chassis = new WheeledChassis(new Wheel[] { leftWheel, rightWheel },
                WheeledChassis.TYPE_DIFFERENTIAL);
        final MovePilot pilot = new MovePilot(chassis);

        final EV3ColorSensor lightSensor = new EV3ColorSensor(SensorPort.S3);

        final Patrol patrol = new Patrol(millisecondsToRunUntil, System.currentTimeMillis(), lightSensor, pilot);

        final EV3MediumRegulatedMotor bladeMotor = new EV3MediumRegulatedMotor(MotorPort.A);

        final EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S1);
        final SpinBlade spinBlade = new SpinBlade(millisecondsToRunUntil, touchSensor, bladeMotor);

        spinBlade.start();
        patrol.start();

        while (spinBlade.isAlive() || patrol.isAlive()) {

            //

        }

        bladeMotor.close();
        touchSensor.close();
        lightSensor.close();

        System.exit(0);
    }
}