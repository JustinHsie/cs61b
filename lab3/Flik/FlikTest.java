import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testEquals() {
        boolean actual = Flik.isSameNumber(3, 3);
        assertTrue("Test failed", actual);
    }
}