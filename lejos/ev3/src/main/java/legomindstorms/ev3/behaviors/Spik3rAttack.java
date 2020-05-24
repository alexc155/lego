package legomindstorms.ev3.behaviors;

import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import lejos.robotics.subsumption.Behavior;

public class Spik3rAttack implements Behavior {

    private final RemoteRequestRegulatedMotor _legsMotor;
    private final RemoteRequestRegulatedMotor _pincerMotor;
    private final RemoteRequestRegulatedMotor _stingMotor;
    private final RemoteRequestSampleProvider _irSensor;
    private float[] _sample;

    public Spik3rAttack(final RemoteRequestRegulatedMotor legsMotor, final RemoteRequestRegulatedMotor pincerMotor,
            final RemoteRequestRegulatedMotor stingMotor, final RemoteRequestSampleProvider irSensor) {
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