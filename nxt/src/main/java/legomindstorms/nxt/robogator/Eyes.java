package legomindstorms.nxt.robogator;

import legomindstorms.nxt.Base;
import legomindstorms.nxt.behaviors.Bite;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Eyes extends Base {

    public static void main(final String[] args) {

        countdown();

        final Behavior b1 = new Bite(100, 80, true);

        final Behavior[] bArray = { b1 };
        final Arbitrator arby = new Arbitrator(bArray);
        arby.start();

    }
}