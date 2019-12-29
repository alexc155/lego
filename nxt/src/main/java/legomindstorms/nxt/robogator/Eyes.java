package legomindstorms.nxt.robogator;

import legomindstorms.nxt.Base;
import legomindstorms.nxt.behaviors.Bite;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Eyes extends Base {

    public static void main(String[] args) {

        countdown();

        Behavior b1 = new Bite(100, 80, true);

        Behavior[] bArray = { b1 };
        Arbitrator arby = new Arbitrator(bArray);
        arby.start();

    }
}