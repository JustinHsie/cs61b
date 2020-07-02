public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> ad1 = new ArrayDeque();
        ad1.addFirst(0);
        ad1.addLast(1);
        System.out.println("Removed " + ad1.removeFirst());
        System.out.println("Removed " + ad1.removeLast());
        System.out.println("Size is " + ad1.size());
        System.out.println("Is empty: " + ad1.isEmpty());
        ad1.printDeque();

    }
}