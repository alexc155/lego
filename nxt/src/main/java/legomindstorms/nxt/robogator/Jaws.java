package legomindstorms.nxt.robogator;

import legomindstorms.nxt.Base;
import legomindstorms.nxt.behaviors.Bite;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Jaws extends Base {

    public static void main(String[] args) {

        countdown();

        Behavior b1 = new Bite(75, 100, false);

        Behavior[] bArray = { b1 };
        Arbitrator arby = new Arbitrator(bArray);
        arby.start();
    }
}