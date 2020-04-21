package legomindstorms.nxt.alpharex;

import legomindstorms.nxt.Base;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;

public class Head extends Base {

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

        final UltrasonicSensor eyesUltrasonicSensor = new UltrasonicSensor(SensorPort.S4);

        Delay.msDelay(500);

        while (eyesUltrasonicSensor.getDistance() > 25) {
            // wait
        }

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
        rightLegRegulatedMotor.rotate(180);

        leftLegMotor.setPower(45);
        rightLegMotor.setPower(45);
        armsMotor.setPower(45);

        leftLegRegulatedMotor.forward();
        rightLegRegulatedMotor.forward();
        armsRegulatedMotor.forward();

        while (eyesUltrasonicSensor.getDistance() > 25) {
            // wait
        }

        leftLegRegulatedMotor.stop();
        rightLegRegulatedMotor.stop();
        armsMotor.stop();

        leftLegMotor.setPower(45);
        leftLegRegulatedMotor.forward();
        while (!leftLegTouchSensor.isPressed()) {
            Thread.yield();
        }
        leftLegRegulatedMotor.stop();
        leftLegRegulatedMotor.rotate(216);

        rightLegMotor.setPower(45);
        rightLegRegulatedMotor.forward();
        while (!rightLegTouchSensor.isPressed()) {
            Thread.yield();
        }
        rightLegRegulatedMotor.stop();

        rightLegMotor.setPower(50);
        rightLegRegulatedMotor.rotate(360 * 20);

        leftLegMotor.setPower(45);
        rightLegMotor.setPower(45);
        armsMotor.setPower(45);

        leftLegRegulatedMotor.rotate(360 * 7);
        rightLegRegulatedMotor.rotate(360 * 7);
        armsRegulatedMotor.rotate(360 * 7);
    }
}