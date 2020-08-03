import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private int numBuckets;
    private double loadFactor;
    private HashSet keys;
    private LinkedList<V>[] ll;

    public MyHashMap() {
        numBuckets = 16;
        loadFactor = 0.75;
        ll = (LinkedList<V>[]) new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            ll[i] = new LinkedList<V>();
        }
    }
    public MyHashMap(int initialSize) {
        numBuckets = initialSize;
        loadFactor = 0.75;
        ll = (LinkedList<V>[]) new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            ll[i] = new LinkedList<V>();
        }
    }
    public MyHashMap(int initialSize, double loadFactor) {
        numBuckets = initialSize;
        this.loadFactor = loadFactor;
        ll = (LinkedList<V>[]) new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            ll[i] = new LinkedList<V>();
        }
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {}

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {return null;}

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {return 0;}

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {}

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {return null;}

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {throw new UnsupportedOperationException();}

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {throw new UnsupportedOperationException();}
}
