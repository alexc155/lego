package legomindstorms.nxt.behaviors;

import java.io.File;

import lejos.nxt.ColorSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Sound;
import lejos.nxt.ColorSensor.Color;
import lejos.robotics.subsumption.Behavior;

public class ManageBallColor implements Behavior {
    private final ColorSensor _colorSensor;
    private Color _color;
    private int _colorVal;
    private boolean _announce;
    private NXTRegulatedMotor _trayRegulatedMotor;
    private NXTMotor _trayMotor;
    private int _rotated;

    public ManageBallColor(final ColorSensor colorSensor, final MotorPort trayMotorPort) {
        _colorSensor = colorSensor;
        _announce = false;
        if (trayMotorPort != null) {
            _trayRegulatedMotor = new NXTRegulatedMotor(trayMotorPort);
            _trayMotor = new NXTMotor(trayMotorPort);

            _trayRegulatedMotor.suspendRegulation();
            _trayMotor.setPower(50);
        }
    }

    public boolean takeControl() {

        _color = _colorSensor.getColor();
        _colorVal = _color.getColor();

        if (_announce == false && (_colorVal != Color.WHITE)) {
            _announce = true;
            return true;
        }
        _announce = false;
        return false;
    }

    public void suppress() {
    }

    public void action() {
        if (_announce == true) {
            switch (_colorVal) {
            case Color.RED:
                Sound.playSample(new File("Red.wav"));
                break;
            case Color.GREEN:
                Sound.playSample(new File("Green.wav"));
                if (_trayMotor != null) {
                    _trayRegulatedMotor.rotate(0 - _rotated);
                    _trayRegulatedMotor.rotate(90);
                    _rotated = 90;
                }
                break;
            case Color.BLUE:
                Sound.playSample(new File("Blue.wav"));
                if (_trayMotor != null) {
                    _trayRegulatedMotor.rotate(0 - _rotated);
                    _trayRegulatedMotor.rotate(180);
                    _rotated = 180;
                }
                break;
            case Color.YELLOW:
                Sound.playSample(new File("Yellow.wav"));
                if (_trayMotor != null) {
                    _trayRegulatedMotor.rotate(0 - _rotated);
                    _trayRegulatedMotor.rotate(270);
                    _rotated = 270;
                }
                break;
            default:
                Sound.playSample(new File("Error.wav"));
            }
        }
    }
}