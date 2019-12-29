package legomindstorms.nxt.behaviors;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Bite implements Behavior {

    boolean _suppressed = false;
    NXTMotor _motor;
    int _biteDelay;
    boolean _takeControlWithSensor;
    UltrasonicSensor _ultrasonicSensor;

    public Bite(int bitePower, int biteDelay, boolean takeControlWithSensor) {
        _motor = new NXTMotor(MotorPort.A);
        _motor.setPower(bitePower);
        _motor.stop();
        _biteDelay = biteDelay;
        _takeControlWithSensor = takeControlWithSensor;

        if (takeControlWithSensor) {
            _ultrasonicSensor = new UltrasonicSensor(SensorPort.S4);
        }
    }

    public void action() {
        _suppressed = false;

        for (int i = 0; i < 5; i++) {
            _motor.forward();
            Delay.msDelay(_biteDelay);
            _motor.stop();
            Delay.msDelay(200);
            _motor.backward();
            Delay.msDelay(_biteDelay);
            _motor.stop();

            Thread.yield();
            if (_suppressed) {
                break;
            }
        }
    }

    public void suppress() {
        _suppressed = true;
    }

    public boolean takeControl() {
        if (_takeControlWithSensor) {
            return _ultrasonicSensor.getDistance() < 30;
        }
        return true;
    }

}