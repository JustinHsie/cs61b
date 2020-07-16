package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature{

    private int r = 34;
    private int g = 0;
    private int b = 231;

    private double repEnergyRetained = 0.5;
    private double repEnergyGiven = 0.5;

    /**
     * Constructor
     */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    /**
     * Creates default Clorus
     */
    public Clorus() {
        this(1);
    }

    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
        // ToDo
    }

    public void move() {
        // ToDo
    }

    public void stay() {
        // ToDo
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // ToDo
        return new Action(Action.ActionType.STAY);
    }

    public Clorus replicate() {
        // ToDo
        return new Clorus();
    }
}
