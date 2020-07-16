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
    public void testGainEnergy() {
        Clorus c = new Clorus(2);
        Plip p = new Plip(2);
        double oldE = c.energy();
        double pipE = p.energy();
        HashMap<Direction, Occupant> eatPlip = new HashMap<Direction, Occupant>();
        eatPlip.put(Direction.TOP, p);
        eatPlip.put(Direction.BOTTOM, new Impassible());
        eatPlip.put(Direction.LEFT, new Impassible());
        eatPlip.put(Direction.RIGHT, new Impassible());

        c.chooseAction(eatPlip);
        assertEquals(oldE + pipE, c.energy(), 0.01);
    }



}
