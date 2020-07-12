public class LinkedListDeque<Spiff> {
    private class StuffNode {
        private StuffNode prev;
        private Spiff item;
        private StuffNode next;

        private StuffNode(Spiff item, StuffNode prev, StuffNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private StuffNode sentinel;
    private StuffNode current;
    private StuffNode entry;
    private int size;


    // Empty deque constructor
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        current = sentinel;
        size = 0;
    }

    public void add(Spiff item) {
        entry = new StuffNode(item, current, current.next);
        current.next.prev = entry;
        current.next = entry;
    }
    public void addFirst(Spiff item) {
        current = sentinel;
        add(item);
        size += 1;

    }

    public void addLast(Spiff item) {
        current = sentinel.prev;
        add(item);
        size += 1;
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
        StuffNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    public Spiff removeFirst() {
        if (sentinel.next != sentinel) {
            Spiff item = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return item;
        }
        return null;
    }


    public Spiff removeLast() {
        if (sentinel.prev != sentinel) {
            Spiff item = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return item;
        }
        return null;
    }

    public Spiff get(int index) {
        StuffNode ptr = sentinel.next;
        for (int i = 0; i < index; i += 1) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    /**
     * @source Josh Hug walkthrough vid
     */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        current = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i += 1) {
            addLast((Spiff) other.get(i));
        }
    }

    public Spiff recursive(int index, StuffNode pointer) {
        pointer = pointer.next;
        if (pointer == sentinel) {
            return null;
        }
        if (index == 0) {
            return pointer.item;
        }
        return recursive(index - 1, current);
    }
    public Spiff getRecursive(int index) {
        current = sentinel;
        return recursive(index, current);
    }


}
