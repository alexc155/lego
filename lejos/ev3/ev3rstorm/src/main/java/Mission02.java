import java.io.IOException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Mission02 {

    private static final int _secondsToRunFor = 30;

    public static void main(final String[] args) throws IOException {

        final long millisecondsToRunUntil = System.currentTimeMillis() + (_secondsToRunFor * 1000);

        final Wheel leftWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.B)), 3).offset(-6.4);
        final Wheel rightWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.C)), 3).offset(6.4);
        final Chassis chassis = new WheeledChassis(new Wheel[] { leftWheel, rightWheel },
                WheeledChassis.TYPE_DIFFERENTIAL);
        final MovePilot pilot = new MovePilot(chassis);
        pilot.setLinearSpeed(20); // cm per second

        final EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S1);

        final float[] sample = new float[touchSensor.sampleSize()];
        touchSensor.fetchSample(sample, 0);

        while (millisecondsToRunUntil > System.currentTimeMillis()) {
            while (sample[0] != 1 && millisecondsToRunUntil > System.currentTimeMillis()) {
                touchSensor.fetchSample(sample, 0);
            }
            while (sample[0] != 0 && millisecondsToRunUntil > System.currentTimeMillis()) {
                touchSensor.fetchSample(sample, 0);
            }
            pilot.rotate(100);
            pilot.rotate(-100);
        }

        touchSensor.close();

        System.exit(0);
    }
}