import java.io.IOException;
import java.rmi.NotBoundException;

import behaviors.Track3rHammerAttack;
import behaviors.Track3rHammerGuard;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Mission04 {

    public Mission04() throws IOException, NotBoundException {
        super();
    }

    private static final int secondsToRunFor = 30;

    public static void main(final String[] args) throws NotBoundException, IOException, InterruptedException {

        final Wheel leftWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.B)), 3).offset(-6.4);
        final Wheel rightWheel = WheeledChassis.modelWheel((new EV3LargeRegulatedMotor(MotorPort.C)), 3).offset(6.4);
        final Chassis chassis = new WheeledChassis(new Wheel[] { leftWheel, rightWheel },
                WheeledChassis.TYPE_DIFFERENTIAL);

        final MovePilot pilot = new MovePilot(chassis);

        final EV3MediumRegulatedMotor motor = new EV3MediumRegulatedMotor(MotorPort.A);

        final EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S4);

        final Behavior b1 = new Track3rHammerGuard(pilot, motor, System.currentTimeMillis() + (secondsToRunFor * 1000));
        final Behavior b2 = new Track3rHammerAttack(pilot, motor, irSensor);

        final Behavior[] bArray = { b1, b2 };
        final Arbitrator arby = new Arbitrator(bArray, true);

        arby.go(); // Carries on (blocking thread) until there are no more actions to take control

        arby.stop();
        irSensor.close();
        motor.close();

        System.exit(0);
    }
}