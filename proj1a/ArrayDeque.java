public class ArrayDeque<Item> {
    private Item[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    // Construct empty array size 8
    public ArrayDeque() {
        items = (Item []) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    public int minusOne(int index) {
        if (index == nextLast) {
            //resize();
        }
        else if (index - 1 < 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    public ArrayDeque(ArrayDeque other) {

    }

    public void addFirst(Item item) {
        items[nextFirst] = item;
        size += 1;
        minusOne(nextFirst);
    }

    public void addLast(Item item) {

    }

    public boolean isEmpty() {
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
        return null;
    }

    public Item removeLast() {
        return null;
    }

    public Item get(int index) {
        return items[index];
    }
}