public interface Deque<Item> {
    void addFirst(Item item);
    void addLast(Item item);
    default public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }
    int size();
    void printDeque();
    Item removeFirst();
    Item removeLast();
    Item get(int index);
}