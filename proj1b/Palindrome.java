import java.util.LinkedList;
import static org.junit.Assert.*;

public class Palindrome<Item> {
    public Deque<Character> wordToDeque(String word) {
        Deque d = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
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
        Deque dReversed = reverse(d, new LinkedListDeque());
        String actual = "";
        for (int i = 0; i < word.length(); i++) {
            actual += dReversed.removeFirst();
        }
        if (word.equals(actual)) {
            return true;
        }
        return false;
    }
}
