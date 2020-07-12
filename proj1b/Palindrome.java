public class Palindrome<Item> {
    public Deque<Character> wordToDeque(String word) {
        Deque d = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }
}
