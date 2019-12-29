package legomindstorms.nxt.behaviors;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TouchSensor;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class TiltTable implements Behavior {
    private boolean _suppressed = false;
    private NXTRegulatedMotor _tableRegulatedMotor;
    private NXTMotor _tableMotor;
    private TouchSensor _touchSensor;

    public TiltTable(MotorPort motorPort, TouchSensor touchSensor) {

        _touchSensor = touchSensor;
        _tableRegulatedMotor = new NXTRegulatedMotor(motorPort);
        _tableMotor = new NXTMotor(motorPort);

        _tableRegulatedMotor.suspendRegulation();
        _tableMotor.setPower(50);
    }

    public boolean takeControl() {
        return true;
    }

    public void suppress() {
        _suppressed = true;
    }

    public void action() {

        _suppressed = false;

        _tableRegulatedMotor.forward();

        while (!_touchSensor.isPressed() && !_suppressed) {
            Thread.yield();
        }

        while (_touchSensor.isPressed() && !_suppressed) {
            Thread.yield();
        }

        _tableRegulatedMotor.stop();

        _tableRegulatedMotor.rotate(2 * 360);

        Delay.msDelay(1000);
    }
}