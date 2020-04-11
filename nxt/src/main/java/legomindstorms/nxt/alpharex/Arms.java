package legomindstorms.nxt.alpharex;

import legomindstorms.nxt.Base;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Arms extends Base {

    public static void main(final String[] args) {

        countdown();

        final NXTRegulatedMotor armsRegulatedMotor = new NXTRegulatedMotor(MotorPort.A);
        final NXTMotor armsMotor = new NXTMotor(MotorPort.A);

        final NXTRegulatedMotor leftLegRegulatedMotor = new NXTRegulatedMotor(MotorPort.B);
        final NXTMotor leftLegMotor = new NXTMotor(MotorPort.B);

        final NXTRegulatedMotor rightLegRegulatedMotor = new NXTRegulatedMotor(MotorPort.C);
        final NXTMotor rightLegMotor = new NXTMotor(MotorPort.C);

        final TouchSensor leftLegTouchSensor = new TouchSensor(SensorPort.S1);
        final TouchSensor rightLegTouchSensor = new TouchSensor(SensorPort.S2);

        armsMotor.setPower(40);
        armsRegulatedMotor.rotate(5 * 360);

        leftLegMotor.setPower(45);
        leftLegRegulatedMotor.forward();
        while (!leftLegTouchSensor.isPressed()) {
            Thread.yield();
        }
        leftLegRegulatedMotor.stop();

        rightLegMotor.setPower(45);
        rightLegRegulatedMotor.forward();
        while (!rightLegTouchSensor.isPressed()) {
            Thread.yield();
        }
        rightLegRegulatedMotor.stop();
        leftLegRegulatedMotor.rotate(180);

        leftLegMotor.setPower(45);
        rightLegMotor.setPower(45);
        armsMotor.setPower(45);

        leftLegRegulatedMotor.rotate(7 * 360, true);
        rightLegRegulatedMotor.rotate(7 * 360, true);
        armsRegulatedMotor.rotate(7 * 360, false);

        armsRegulatedMotor.rotate(5 * 360);

        leftLegRegulatedMotor.rotate(-7 * 360, true);
        rightLegRegulatedMotor.rotate(-7 * 360, true);
        armsRegulatedMotor.rotate(-7 * 360, false);
    }
}