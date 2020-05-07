package legomindstorms.ev3.ev3rstorm;

import java.io.IOException;

import legomindstorms.ev3.behaviors.Ev3rstormPatrol;
import legomindstorms.ev3.behaviors.Ev3rstormSpinBlade;
import lejos.hardware.BrickFinder;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;

public class Mission03 {

    private static final int secondsToRunFor = 30;

    public static void main(final String[] args) throws IOException {

        final long millisecondsToRunUntil = System.currentTimeMillis() + (secondsToRunFor * 1000);
        final long startTime = System.currentTimeMillis();

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());

        final RemoteRequestPilot pilot = (RemoteRequestPilot) ev3.createPilot(3, 12.8, "B", "C");
        pilot.setLinearSpeed(20); // cm per second

        final RemoteRequestSampleProvider touchSensor = (RemoteRequestSampleProvider) ev3.createSampleProvider("S1",
                "lejos.hardware.sensor.EV3TouchSensor", "Touch");

        final RemoteRequestSampleProvider colorSensor = (RemoteRequestSampleProvider) ev3.createSampleProvider("S2",
                "lejos.hardware.sensor.EV3ColorSensor", "Ambient");

        final RemoteRequestRegulatedMotor bladeMotor = (RemoteRequestRegulatedMotor) ev3.createRegulatedMotor("A", 'M');

        final Ev3rstormPatrol ev3rstormPatrol = new Ev3rstormPatrol(millisecondsToRunUntil, colorSensor, pilot,
                startTime);

        final Ev3rstormSpinBlade ev3rstormSpinBlade = new Ev3rstormSpinBlade(millisecondsToRunUntil, touchSensor,
                bladeMotor);

        ev3rstormPatrol.start();
        ev3rstormSpinBlade.start();

        while (millisecondsToRunUntil > System.currentTimeMillis()) {
            // wait
        }

        bladeMotor.close();
        colorSensor.close();
        touchSensor.close();
        pilot.close();

        System.exit(0);
    }
}