package legomindstorms.ev3.track3r;

import java.io.IOException;
import java.rmi.NotBoundException;

import legomindstorms.ev3.Base;
import legomindstorms.ev3.behaviors.Track3rHammerAttack;
import legomindstorms.ev3.behaviors.Track3rHammerGuard;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Mission04 extends Base {

    public Mission04() throws IOException, NotBoundException {
        super();
    }

    private static final int secondsToRunFor = 30;

    public static void main(final String[] args) throws NotBoundException, IOException, InterruptedException {

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
    }
}