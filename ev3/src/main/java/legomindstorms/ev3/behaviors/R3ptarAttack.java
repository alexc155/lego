package legomindstorms.ev3.behaviors;

import java.rmi.RemoteException;

import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RMISampleProvider;
import lejos.robotics.subsumption.Behavior;

public class R3ptarAttack implements Behavior {

    private final RMIRegulatedMotor _jawsMotor;
    private final RMISampleProvider _irSensor;

    public R3ptarAttack(final RMIRegulatedMotor jawsMotor, final RMISampleProvider irSensor) throws RemoteException {
        _jawsMotor = jawsMotor;
        _irSensor = irSensor;

        _jawsMotor.setSpeed((int) _jawsMotor.getMaxSpeed());
    }

    @Override
    public boolean takeControl() {
        try {
            if (_irSensor.fetchSample()[0] < 30) {
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
            _jawsMotor.rotate(50);
            _jawsMotor.rotate(-50);
        } catch (final RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void suppress() {

    }
}