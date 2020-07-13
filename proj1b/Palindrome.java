import java.util.LinkedList;
import static org.junit.Assert.*;

public class Palindrome<Item> {
    /* // Linked List
    public Deque<Character> wordToDeque(String word) {
        Deque d = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

     */

    // Array
    public Deque<Character> wordToDeque(String word) {
        Deque d = new ArrayDeque();
        for (int i = 0; i < word.length(); i++) {
            if (i == 0) {
                d.addFirst(word.charAt(i));
                continue;
            }
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public static Deque<Character> reverse(Deque<Character> original, Deque<Character> d) {
        if (original.size() == 0) {
            return d;
        }
        d.addLast(original.removeLast());
        return reverse(original, d);
    }

    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        Deque dReversed = reverse(d, new ArrayDeque());
        String actual = "";
        for (int i = 0; i < word.length(); i++) {
            actual += dReversed.removeFirst();
        }
        if (word.equals(actual)) {
            return true;
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque d = wordToDeque(word);
        Deque dReversed = reverse(d, new LinkedListDeque());
        String dRev = "";
        for (int i = 0; i < word.length(); i++) {
            dRev += dReversed.removeFirst();
        }

        boolean result = true;
        for (int i = 0; i < word.length(); i++) {
            if (i == word.length() / 2) {
                continue;
            }
            if (!cc.equalChars(word.charAt(i), dRev.charAt(i))) {
                result = false;
            }
        }
        return result;
    }
}
