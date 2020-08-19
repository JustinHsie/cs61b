import edu.princeton.cs.algs4.Queue;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> stringQueue = new Queue<String>();
        stringQueue.enqueue("Joe");
        stringQueue.enqueue("Omar");
        stringQueue.enqueue("Itai");
        stringQueue.enqueue("Abby");
        stringQueue.enqueue("Messi");

        Queue<String> sortedString = QuickSort.quickSort(stringQueue);
        assertTrue(isSorted(sortedString));
        assertEquals(stringQueue.size(), sortedString.size());

        Queue<Integer> intQueue = new Queue<Integer>();
        Random rand = new Random(123);
        for (int i = 0; i < 100; i++) {
            intQueue.enqueue(rand.nextInt(50));
        }
        Queue<Integer> sortedInt = QuickSort.quickSort(intQueue);
        assertTrue(isSorted(sortedInt));
        assertEquals(intQueue.size(), sortedInt.size());
    }

    @Test
    public void testMergeSort() {
        Queue<String> stringQueue = new Queue<String>();
        stringQueue.enqueue("Joe");
        stringQueue.enqueue("Omar");
        stringQueue.enqueue("Itai");
        stringQueue.enqueue("Abby");
        stringQueue.enqueue("Messi");

        Queue<String> sortedString = MergeSort.mergeSort(stringQueue);
        assertTrue(isSorted(sortedString));
        assertEquals(stringQueue.size(), sortedString.size());

        Queue<Integer> intQueue = new Queue<Integer>();
        Random rand = new Random(123);
        for (int i = 0; i < 100; i++) {
            intQueue.enqueue(rand.nextInt(50));
        }
        Queue<Integer> sortedInt = MergeSort.mergeSort(intQueue);
        assertTrue(isSorted(sortedInt));
        assertEquals(intQueue.size(), sortedInt.size());


    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Queue<Item> copy = new Queue<>();
        for (Item item : items) {
            copy.enqueue(item);
        }

        Item curr = copy.dequeue();
        Item prev = curr;
        while (!copy.isEmpty()) {
            prev = curr;
            curr = copy.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
