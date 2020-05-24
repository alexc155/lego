package legomindstorms.ev3.track3r;

import java.io.IOException;
import java.rmi.NotBoundException;

import lejos.hardware.BrickFinder;
import lejos.hardware.Sound;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.remote.ev3.RemoteRequestEV3;

public class Test {

    public static void main(final String[] args) throws NotBoundException, IOException {

        final RemoteRequestEV3 ev3 = new RemoteRequestEV3(BrickFinder.find("EV3")[0].getIPAddress());
        final GraphicsLCD graphicsLCD = ev3.getGraphicsLCD();
        Sound.beep();
        graphicsLCD.drawString("str", 0, 0, 0);
        // RemoteRequestPilot pilot = (RemoteRequestPilot) ev3.createPilot(3.0, 18.0,
        // "B", "C");
        // RemoteRequestRegulatedMotor motor = (RemoteRequestRegulatedMotor)ev3.createRegulatedMotor("D", 'L');
        // motor.rotate(-270);
        // EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S4);
        // RangeFinder sonar;
        // sonar = new RangeFinderAdapter(irSensor);
        // pilot.setLinearSpeed((0.175 * pilot.getMaxLinearSpeed()));
        // motor.setSpeed((int) (0.175 * motor.getMaxSpeed()));

        // pilot.rotate(180);

        System.out.println("Running");

        // long end = System.currentTimeMillis() + 15000;
        // while (System.currentTimeMillis() < end) {
        //     // Need to check that the distance probe works
        //     float distance = sonar.getRange();
        //     if (!Float.isInfinite(distance)) {
        //         System.out.println(distance / 100);
        //     }
        // }

        // irSensor.close();

        System.out.println("Done");
    }
}