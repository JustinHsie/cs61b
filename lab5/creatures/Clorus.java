package creatures;

import edu.princeton.cs.algs4.StdRandom;
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
        energy += c.energy();
    }

    public void move() {
        energy -= 0.03;
        energy = Math.max(energy, 0);
    }

    public void stay() {
        energy -= 0.01;
        energy = Math.max(energy, 0);
    }

    private Direction randomEntry(Deque<Direction> neighbors) {
        int selection = StdRandom.uniform(neighbors.size());
        Direction[] array = neighbors.toArray(new Direction[neighbors.size()]);
        return array[selection];
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        /**
         * If no empty squares, stay
         */
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        for (Direction key : neighbors.keySet()) {
            Occupant value = neighbors.get(key);
            if (value.name().equals("empty")) {
                emptyNeighbors.addFirst(key);
            }
            if (value.name().equals("plip")) {
                plipNeighbors.addFirst(key);
            }
        }
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        /**
         * Otherwise, if plips seen, attack one randomly
         */
        else if (plipNeighbors.size() > 0) {
            Direction direction = randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, direction);
        }

        /**
         * Otherwise, if energy >= 1, replicate to random empty space
         */
        else if (energy >= 1) {
            Direction direction = randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, direction);
        }

        /**
         * Otherwise, move to random empty space
         */
        Direction direction = randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, direction);
    }

    public Clorus replicate() {
        double babyEnergy = energy * repEnergyGiven;
        energy = energy * repEnergyRetained;
        return new Clorus(babyEnergy);
    }
}
