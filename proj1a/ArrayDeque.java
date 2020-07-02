public class ArrayDeque<Item> {
    private Item[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private int begin;

    // Construct empty array size 8
    public ArrayDeque() {
        items = (Item []) new Object[3];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
        begin = 0;
    }

    public ArrayDeque(ArrayDeque other) {

    }

    public int minusOne(int index) {
        if (index == begin) {
            return items.length - 1;
        }
        return index - 1;
    }

    public int plusOne(int index) {
        if (index == items.length - 1) {
            return begin;
        }
        return index + 1;
    }

    private void resize(int capacity) {
        Item[] array = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, array, 0, size);
        items = array;
        begin = size;
        nextFirst = size;
        nextLast = size + 1;
    }

    public void checkFull() {
        if (minusOne(nextLast) == nextFirst ||
                plusOne(nextFirst) == nextLast) {
            System.out.println("full");
            resize(size * 2);
        }
    }

    public void addFirst(Item item) {
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
        checkFull();
    }

    public void addLast(Item item) {
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
        checkFull();
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for(int i = 0, n = items.length; i < n; i += 1) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public Item removeFirst() {
        nextFirst = plusOne(nextFirst);
        Item item = items[nextFirst];
        if (item != null) {
            items[nextFirst] = null;
            size -= 1;
            return item;
        }
        return null;
    }

    public Item removeLast() {
        nextLast = minusOne(nextLast);
        Item item = items[nextLast];
        if (item != null) {
            items[nextLast] = null;
            size -= 1;
            return item;
        }
        return null;
    }

    public Item get(int index) {
        return items[index];
    }
}