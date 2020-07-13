import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        /**
         * @source Josh Hug StudentArrayDequeLauncher
         */

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();

        for (int i = 0; i < 500; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform(6);
            Integer expected;
            Integer actual;

            if (numberBetweenZeroAndOne < 1) {
                sad1.addLast(i);
                ads1.addLast(i);;
            } else if (numberBetweenZeroAndOne == 1){
                sad1.addFirst(i);
                ads1.addFirst(i);
            } else if (ads1.size() == 0) {
                i -= 1;
                continue;
            } else if (numberBetweenZeroAndOne == 2) {
                actual = sad1.removeLast();
                expected = ads1.removeLast();
                assertEquals(expected, actual);
            } else if (numberBetweenZeroAndOne == 3) {
                actual = sad1.removeFirst();
                expected = ads1.removeFirst();
                assertEquals(expected, actual);
            } else if (numberBetweenZeroAndOne == 4) {
                actual = sad1.get(i % ads1.size());
                expected = ads1.get(i % ads1.size());
                assertEquals(expected, actual);
            } else if (numberBetweenZeroAndOne == 5) {
                actual = sad1.size();
                expected = ads1.size();
                assertEquals(expected, actual);
            }
        }
    }
}
