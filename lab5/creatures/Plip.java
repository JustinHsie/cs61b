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

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;
    /**
     * probability of taking a move when empty space available
     */
    private double moveProbability = 0.5;
    /**
     * fraction of energy to retain when replicating.
     */
    private double repEnergyRetained = 0.5;
    /**
     * fraction of energy to bestow upon offspring.
     */
    private double repEnergyGiven = 0.5;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 99;
        g = 80;
        b = 76;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = 96 * (int) energy + 63;
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.15;
        energy = Math.max(energy, 0);
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy += 0.2;
        energy = Math.min(energy, 2);
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        double babyEnergy = energy * repEnergyGiven;
        energy = energy * repEnergyRetained;
        return new Plip(babyEnergy);
    }

    private Direction randomEntry(Deque<Direction> neighbors) {
        int selection = StdRandom.uniform(neighbors.size());
        Direction[] array = neighbors.toArray(new Direction[neighbors.size()]);
        return array[selection];
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        /**
         * If no empty spaces, stay
         */
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;

        for(Direction key : neighbors.keySet()) {
            Occupant value = neighbors.get(key);
            if (value.name().equals("empty")) {
                emptyNeighbors.addFirst(key);
            }
            if (value.name().equals("clorus")) {
                anyClorus = true;
            }
        }

        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        /**
         * Otherwise, if energy >= 1.0, replicate to random available space
         */
        else if (energy >= 1) {
            Direction direction = randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, direction);
        }

        /**
         * Otherwise, if neighbor clorus, move to empty space with prob 50%
         */
        else if (anyClorus == true) {
            if (Math.random() < moveProbability) {
                Direction direction = randomEntry(emptyNeighbors);
                return new Action(Action.ActionType.MOVE, direction);
            }
        }

        /**
         * Otherwise, stay
         */
        return new Action(Action.ActionType.STAY);
    }
}
