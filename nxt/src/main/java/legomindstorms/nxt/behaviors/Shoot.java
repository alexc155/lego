package legomindstorms.nxt.behaviors;

import lejos.nxt.ColorSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.Color;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Shoot implements Behavior {
    private boolean _suppressed = false;
    private final UltrasonicSensor _ultrasonicSensor;
    private final ColorSensor _attackLamp;
    private final DifferentialPilot _pilot;
    private final NXTRegulatedMotor _shootRegulatedMotor;
    private final NXTMotor _shootMotor;

    public Shoot(final UltrasonicSensor ultrasonicSensor, final ColorSensor attackLamp, final DifferentialPilot pilot,
            final MotorPort shooterPort) {
        _ultrasonicSensor = ultrasonicSensor;
        _attackLamp = attackLamp;
        _pilot = pilot;

        _shootRegulatedMotor = new NXTRegulatedMotor(shooterPort);
        _shootMotor = new NXTMotor(shooterPort);
    }

    public boolean takeControl() {
        return _ultrasonicSensor.getDistance() < 40;
    }

    public void suppress() {
        _suppressed = true;
    }

    public void action() {
        _suppressed = false;

        _attackLamp.setFloodlight(Color.BLUE);

        _pilot.stop();

        Delay.msDelay(500);

        Thread.yield();

        if (takeControl() & !_suppressed) {
            _attackLamp.setFloodlight(Color.RED);

            Delay.msDelay(500);

            _shootRegulatedMotor.suspendRegulation();
            _shootMotor.setPower(100);
            _shootRegulatedMotor.rotate(360 * 2);
            _shootRegulatedMotor.stop();
        }

        _attackLamp.setFloodlight(Color.NONE);
    }
}