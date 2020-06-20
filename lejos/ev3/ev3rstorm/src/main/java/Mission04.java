import java.io.IOException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Mission04 {

    private static final int _secondsToRunFor = 30;
    private static long _startTime;
    private static MovePilot _pilot;

    private static void skate() {
        final int secondsSinceStart = (int) ((System.currentTimeMillis() - _startTime) / 1000);
        if (secondsSinceStart % 3 == 0) {
            _pilot.travelArc(50, 70);
        } else {
            _pilot.travelArc(-50, 70);
        }
    }

    public static void main(final String[] args) throws IOException {

        _startTime = System.currentTimeMillis();

        final long millisecondsToRunUntil = System.currentTimeMillis() + (_secondsToRunFor * 1000);

        final Wheel leftWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.B)), 3).offset(-6.4);
        final Wheel rightWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.C)), 3).offset(6.4);
        final Chassis chassis = new WheeledChassis(new Wheel[] { leftWheel, rightWheel },
                WheeledChassis.TYPE_DIFFERENTIAL);

        _pilot = new MovePilot(chassis);
        _pilot.setLinearSpeed(20); // cm per second

        final EV3IRSensor eyesSensor = new EV3IRSensor(SensorPort.S4);
        final SampleProvider sp = eyesSensor.getDistanceMode();
        final float[] sample = new float[sp.sampleSize()];

        final EV3MediumRegulatedMotor bladeMotor = new EV3MediumRegulatedMotor(MotorPort.A);
        bladeMotor.setSpeed((int) bladeMotor.getMaxSpeed());

        while (millisecondsToRunUntil > System.currentTimeMillis()) {
            eyesSensor.fetchSample(sample, 0);
            if (sample[0] < 25) {
                _pilot.stop();
                bladeMotor.forward();
                _pilot.travelArc(20, 70);
                bladeMotor.stop();
                bladeMotor.backward();
                _pilot.travelArc(-20, -70);
                bladeMotor.stop();
                _pilot.rotate(150);
            } else {
                skate();
            }
        }

        bladeMotor.close();
        eyesSensor.close();

        System.exit(0);
    }
}