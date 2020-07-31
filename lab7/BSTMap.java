import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size;

        private Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;

        }
    }

    public BSTMap() {}

    @Override
    public Iterator<K> iterator() {throw new UnsupportedOperationException();}

    @Override
    public void clear() {}

    @Override
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {return false;}

    @Override
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (key == null) throw new IllegalArgumentException();
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        }
        else if (cmp > 0) {
            return get(x.right, key);
        }
        else {
            return x.val;
        }
    }

    @Override
    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    @Override
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {}

    @Override
    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {throw new UnsupportedOperationException();}

    @Override
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {throw new UnsupportedOperationException();}

    @Override
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {throw new UnsupportedOperationException();}

    public void printInOrder() {}


}
