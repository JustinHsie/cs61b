public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    // Construct empty array size 8
    public ArrayDeque() {
        items = (Item []) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (Item []) new Object[other.items.length];
        nextFirst = 0;
        nextLast = 1;
        size = 0;

        for (int i = 0; i < other.items.length; i += 1) {
            items[i] = (Item) other.get(i);
        }
    }

    public int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    public int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    private void resize(int capacity) {
        Item[] array = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, array, 0, size);
        items = array;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void checkFull() {
        if (minusOne(nextLast) == nextFirst
                || plusOne(nextFirst) == nextLast) {
            System.out.println("full");
            resize(size * 2);
        }
    }

    @Override
    public void addFirst(Item item) {
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
        checkFull();
    }

    @Override
    public void addLast(Item item) {
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
        checkFull();
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0, n = items.length; i < n; i += 1) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    private void shrink() {
        Item[] array = (Item []) new Object[items.length / 2];
        System.arraycopy(items, nextFirst + 1, array, 0, size);
        items = array;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private void usageRatio() {
        if ((float) size / (float) items.length < 0.25) {
            shrink();
        }
    }

    @Override
    public Item removeFirst() {
        nextFirst = plusOne(nextFirst);
        Item item = items[nextFirst];
        if (item != null) {
            items[nextFirst] = null;
            size -= 1;
            usageRatio();
            return item;
        }
        return null;
    }

    @Override
    public Item removeLast() {
        nextLast = minusOne(nextLast);
        Item item = items[nextLast];
        if (item != null) {
            items[nextLast] = null;
            size -= 1;
            usageRatio();
            return item;
        }
        return null;
    }

    @Override
    public Item get(int index) {
        return items[index];
    }
}
