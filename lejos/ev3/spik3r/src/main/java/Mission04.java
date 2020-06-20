import java.io.IOException;
import java.rmi.NotBoundException;

import behaviors.Spik3rAttack;
import behaviors.Spik3rPatrol;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Mission04 {

    private static final int _secondsToRunFor = 20;

    public Mission04() throws IOException, NotBoundException {
        super();
    }

    public static void main(final String[] args) throws IOException, NotBoundException {

        final EV3MediumRegulatedMotor pincerMotor = new EV3MediumRegulatedMotor(MotorPort.A);

        final EV3LargeRegulatedMotor legsMotor = new EV3LargeRegulatedMotor(MotorPort.B);

        final EV3LargeRegulatedMotor stingMotor = new EV3LargeRegulatedMotor(MotorPort.D);
        
        final EV3UltrasonicSensor irSensor = new EV3UltrasonicSensor(SensorPort.S4);

        pincerMotor.setSpeed((int) (pincerMotor.getMaxSpeed() / 2));
        legsMotor.setSpeed((int) legsMotor.getMaxSpeed());
        stingMotor.setSpeed((int) stingMotor.getMaxSpeed());

        final Behavior b1 = new Spik3rPatrol(System.currentTimeMillis() + (_secondsToRunFor * 1000), legsMotor);
        final Behavior b2 = new Spik3rAttack(legsMotor, pincerMotor, stingMotor, irSensor);

        final Behavior[] bArray = { b1, b2 };
        final Arbitrator arby = new Arbitrator(bArray, true);

        arby.go(); // Carries on (blocking thread) until there are no more actions to take control

        arby.stop();
        irSensor.close();

        pincerMotor.close();
        legsMotor.close();
        stingMotor.close();

        System.exit(0);
    }
}