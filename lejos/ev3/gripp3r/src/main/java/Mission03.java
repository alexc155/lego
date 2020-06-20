import java.io.IOException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Mission03 {

    public static void main(String[] args) throws IOException {

        final EV3MediumRegulatedMotor gripMotor = new EV3MediumRegulatedMotor(MotorPort.A);
        gripMotor.setSpeed((int) (gripMotor.getMaxSpeed() * 0.75));

        final Wheel leftWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.B)), 3).offset(-6.4);
        final Wheel rightWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.C)), 3).offset(6.4);
        final Chassis chassis = new WheeledChassis(new Wheel[] { leftWheel, rightWheel },
                WheeledChassis.TYPE_DIFFERENTIAL);
        final MovePilot pilot = new MovePilot(chassis);

        pilot.setLinearSpeed(50); // cm per second

        final EV3IRSensor eyesSensor = new EV3IRSensor(SensorPort.S4);
        eyesSensor.getDistanceMode();

        float[] sample = new float[eyesSensor.sampleSize()];
        eyesSensor.fetchSample(sample, 0);

        gripMotor.rotate(-720);
        pilot.forward();
        while (sample[0] >= 25) {
            eyesSensor.fetchSample(sample, 0);
        }
        pilot.stop();
        gripMotor.rotate(720);
        pilot.rotate(180);
        pilot.forward();
        eyesSensor.fetchSample(sample, 0);
        while (sample[0] >= 25) {
            eyesSensor.fetchSample(sample, 0);
        }
        pilot.stop();

        eyesSensor.close();
        gripMotor.close();

        System.exit(0);
    }
}