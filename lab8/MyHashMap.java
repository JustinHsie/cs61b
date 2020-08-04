import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Code from textbook
public class MyHashMap<K, V> implements Map61B<K, V> {

    private int numBuckets;
    private double loadFactor;
    private int size;
    private HashSet<K> keySet;
    private ArrayList<Entry<K, V>> bins;

    public MyHashMap() {
        numBuckets = 16;
        loadFactor = 0.75;
        size = 0;
        keySet = null;
        bins = new ArrayList<Entry<K, V>>(numBuckets);
    }
    public MyHashMap(int initialSize) {
        numBuckets = initialSize;
        loadFactor = 0.75;
        size = 0;
        keySet = null;
        bins= new ArrayList<Entry<K, V>>(numBuckets);
    }
    public MyHashMap(int initialSize, double loadFactor) {
        numBuckets = initialSize;
        this.loadFactor = loadFactor;
        size = 0;
        keySet = null;
        bins = new ArrayList<Entry<K, V>>(numBuckets);
    }

    private class Entry<Key, Val> {
        Key key;
        Val value;
        Entry<Key, Val> next;

        Entry(Key k, Val v, Entry<Key, Val> n) {
            this.key = k;
            this.value = v;
            this.next = n;
        }
        public Key getKey() {
            return key;
        }
        public Val getValue() {
            return value;
        }
        public void setValue(Val x) {
            value = x;
        }
    }
    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        size = 0;
        keySet.clear();
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Entry<K, V> e = find(key, bins.get(key.hashCode() % numBuckets));
        return (e == null) ? null : e.value;
    }

    // Returns entry in its bin list whose key matches
    private Entry<K, V> find (K key, Entry<K, V> bin) {
        for (Entry<K, V> e = bin; e != null; e = e.next) {
            if (key.equals(e.key)) {
                return e;
            }
        }
        return null;
    }

    private void resize() {
        MyHashMap<K, V> temp = new MyHashMap<>(bins.size() * 2);
        for (int i = 0; i < numBuckets; i++) {
            for (Entry<K, V> e = bins.get(0); e != null; e = e.next) {
                temp.put(e.key, e.value);
            }
        }
        this.numBuckets = temp.numBuckets;
        this.size = temp.size;
        this.bins = temp.bins;
    }
    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        int h = key.hashCode() % numBuckets;
        Entry<K, V> e = find(key, bins.get(h));
        if (e == null) {
            bins.set(h, new Entry<K, V> (key, value, bins.get(h)));
            keySet.add(key);
            size += 1;
            if (size > bins.size() * loadFactor) {
                resize();
            }
        }
        else {
            e.setValue(value);
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {return keySet;}

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
