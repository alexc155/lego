package legomindstorms.nxt.alpharex;

import legomindstorms.nxt.Base;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.util.Delay;

public class Legs extends Base {

    public static void main(final String[] args) {

        countdown();

        final NXTRegulatedMotor leftLegRegulatedMotor = new NXTRegulatedMotor(MotorPort.B);
        final NXTMotor leftLegMotor = new NXTMotor(MotorPort.B);

        final NXTRegulatedMotor rightLegRegulatedMotor = new NXTRegulatedMotor(MotorPort.C);
        final NXTMotor rightLegMotor = new NXTMotor(MotorPort.C);

        final TouchSensor leftLegTouchSensor = new TouchSensor(SensorPort.S1);
        final TouchSensor rightLegTouchSensor = new TouchSensor(SensorPort.S2);

        leftLegMotor.setPower(45);
        rightLegMotor.setPower(45);

        leftLegRegulatedMotor.forward();
        while (!leftLegTouchSensor.isPressed()) {
            Thread.yield();
        }
        leftLegRegulatedMotor.stop();

        rightLegRegulatedMotor.forward();
        while (!rightLegTouchSensor.isPressed()) {
            Thread.yield();
        }
        rightLegRegulatedMotor.stop();

        leftLegRegulatedMotor.rotate(54);
        rightLegRegulatedMotor.rotate(5 * 360);
        leftLegRegulatedMotor.rotate(-54);

        rightLegRegulatedMotor.rotate(54);
        leftLegRegulatedMotor.rotate(5 * 360);
        rightLegRegulatedMotor.rotate(-54);

        leftLegRegulatedMotor.rotate(180);

        leftLegRegulatedMotor.rotate(7 * 360, true);
        rightLegRegulatedMotor.rotate(7 * 360, true);

        Delay.msDelay(2000);

        leftLegRegulatedMotor.rotate(-7 * 360, true);
        rightLegRegulatedMotor.rotate(-7 * 360, true);

    }
}