package behaviors;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;

public class Spik3rAttack implements Behavior {

    private final EV3LargeRegulatedMotor _legsMotor;
    private final EV3MediumRegulatedMotor _pincerMotor;
    private final EV3LargeRegulatedMotor _stingMotor;
    private final EV3UltrasonicSensor _irSensor;
    private final float[] _sample;

    public Spik3rAttack(final EV3LargeRegulatedMotor legsMotor, final EV3MediumRegulatedMotor pincerMotor,
            final EV3LargeRegulatedMotor stingMotor, final EV3UltrasonicSensor irSensor) {
        _legsMotor = legsMotor;
        _pincerMotor = pincerMotor;
        _stingMotor = stingMotor;
        _irSensor = irSensor;
        _sample = new float[_irSensor.sampleSize()];
    }

    @Override
    public boolean takeControl() {
        try {
            _irSensor.fetchSample(_sample, 0);
            if (_sample[0] < 40) {
                return true;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void action() {
        try {
            _legsMotor.setSpeed((int) (_legsMotor.getMaxSpeed() / 4));
            _legsMotor.forward();

            _stingMotor.rotate(-210);
            _stingMotor.rotate(-60);

            _pincerMotor.rotate(170);
            _pincerMotor.rotate(-170);

            _legsMotor.setSpeed((int) _legsMotor.getMaxSpeed());
            _legsMotor.backward();

            _stingMotor.rotate(270);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void suppress() {

    }

}