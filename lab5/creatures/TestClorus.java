package creatures;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Creature;
import huglife.Empty;

public class TestClorus {

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        double oldE = c.energy();
        Clorus baby = c.replicate();

        assertEquals(oldE / 2, c.energy(), 0.01);
        assertEquals(c.energy(), baby.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        // No adjacent empty squares, stay
        Clorus c = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // Attack plip
        c = new Clorus(2);
        Plip p = new Plip(2);
        HashMap<Direction, Occupant> eatPlip = new HashMap<Direction, Occupant>();
        eatPlip.put(Direction.TOP, p);
        eatPlip.put(Direction.BOTTOM, new Empty());
        eatPlip.put(Direction.LEFT, new Impassible());
        eatPlip.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(eatPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);

        // Energy >= 1, replicate to empty space
        c = new Clorus(2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);

        // Energy >= 1, replicate to empty space
        c = new Clorus(2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);

        // Energy < 1, move to empty space
        c = new Clorus(.99);

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);
        assertEquals(expected, actual);

        // Energy < 1, move to empty space
        c = new Clorus(.99);

        actual = c.chooseAction(allEmpty);
        unexpected = new Action(Action.ActionType.STAY);
        assertNotEquals(unexpected, actual);
    }

    @Test
    public void testGainEnergy() {
        Clorus c = new Clorus(2);
        Plip p = new Plip(2);
        double oldE = c.energy();
        double pipE = p.energy();

        c.attack(p);
        assertEquals(oldE + pipE, c.energy(), 0.01);
    }
}
