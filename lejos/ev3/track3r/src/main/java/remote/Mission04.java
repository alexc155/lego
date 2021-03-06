package remote;

import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.BrickFinder;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import remote.behaviors.Track3rHammerAttack;
import remote.behaviors.Track3rHammerGuard;

public class Mission04 {

    public Mission04() throws IOException, NotBoundException {
        super();
    }

    private static final int secondsToRunFor = 30;

    public static void main(final String[] args) throws NotBoundException, IOException, InterruptedException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestPilot pilot = (RemoteRequestPilot) ev3.createPilot(3.0, 18.0, "B", "C");
        final RemoteRequestRegulatedMotor motor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');

        final EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S4);

        final Behavior b1 = new Track3rHammerGuard(pilot, motor, System.currentTimeMillis() + (secondsToRunFor * 1000));
        final Behavior b2 = new Track3rHammerAttack(pilot, motor, irSensor);

        final Behavior[] bArray = { b1, b2 };
        final Arbitrator arby = new Arbitrator(bArray, true);

        arby.go(); // Carries on (blocking thread) until there are no more actions to take control

        arby.stop();
        irSensor.close();
        motor.close();
        pilot.close();

        System.exit(0);
    }
}