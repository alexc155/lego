package legomindstorms.ev3.behaviors;

import java.rmi.RemoteException;

import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RMISampleProvider;
import lejos.robotics.subsumption.Behavior;

public class Spik3rAttack implements Behavior {

    private final RMIRegulatedMotor _legsMotor;
    private final RMIRegulatedMotor _pincerMotor;
    private final RMIRegulatedMotor _stingMotor;
    private final RMISampleProvider _irSensor;

    public Spik3rAttack(final RMIRegulatedMotor legsMotor, final RMIRegulatedMotor pincerMotor,
            final RMIRegulatedMotor stingMotor, final RMISampleProvider irSensor) {
        _legsMotor = legsMotor;
        _pincerMotor = pincerMotor;
        _stingMotor = stingMotor;
        _irSensor = irSensor;
    }

    @Override
    public boolean takeControl() {
        try {
            if (_irSensor.fetchSample()[0] < 40) {
                return true;
            }
        } catch (final RemoteException e) {
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
        } catch (final RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void suppress() {

    }

}