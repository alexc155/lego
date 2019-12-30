package legomindstorms.nxt;

import java.io.File;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;

public class Test extends Base {

    public static void main(final String[] args) {

        // countdown();

        // DifferentialPilot pilot;

        // System.out.println("11.6f");
        // pilot = new DifferentialPilot(3.0f, 11.6f, Motor.B, Motor.C);
        // pilot.setTravelSpeed(2);
        // pilot.rotate(180);

        // Button.waitForAnyPress();

        // pilot.rotate(90);

        // Button.waitForAnyPress();

        // System.out.println("14.4f");
        // pilot = new DifferentialPilot(3.0f, 14.4f, Motor.B, Motor.C);
        // pilot.setTravelSpeed(2);
        // pilot.rotate(180);

        // Button.waitForAnyPress();

        // pilot.rotate(90);

        // Button.waitForAnyPress();

        // System.out.println("11.8f");
        // pilot = new DifferentialPilot(3.0f, 11.8f, Motor.B, Motor.C);
        // pilot.setTravelSpeed(2);
        // pilot.rotate(180);

        // Button.waitForAnyPress();

        // pilot.rotate(90);

        final ColorSensor _colorSensor = new ColorSensor(SensorPort.S3);
        while (Button.readButtons() == 0) {
            final Color _color = _colorSensor.getColor();
            final int _colorVal = _color.getColor();

            if (_colorVal == Color.RED || _colorVal == Color.GREEN || _colorVal == Color.BLUE
                    || _colorVal == Color.YELLOW) {
                        System.out.println(_colorVal);
                        Sound.playSample(new File("Red.wav"));
            }
        }

        System.out.println("Done!");
        Button.waitForAnyPress();
    }
}
