import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    public MyTrieSet() {

    }
    private static class Node {
        private char c;
        private boolean isKey;
        private HashMap<Character, Node> map;

        private Node(char c, boolean isKey) {
            this.c = c;
            this.isKey = isKey;
            map = new HashMap<>();
        }
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        //TODO
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        //TODO
        return false;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        //TODO
        return null;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
