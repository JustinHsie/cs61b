public class ArrayDeque<Item> {
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

    }

    public void addFirst(Item item) {

    }

    public void addLast(Item item) {

    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public void printDeque() {

    }

    public Item removeFirst() {
        return null;
    }

    public Item removeLast() {
        return null;
    }

    public Item get(int index) {
        return null;
    }
}