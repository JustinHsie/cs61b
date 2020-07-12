public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> ad1 = new ArrayDeque();
        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.printDeque();
        ad1.addFirst(3);
        ad1.addFirst(4);
        ad1.addFirst(5);
        ad1.addFirst(6);
        ad1.printDeque();
        System.out.println(ad1.get(1));
        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.printDeque();
        ad1.removeLast();
        ad1.printDeque();

        ArrayDeque<Integer> ad2 = new ArrayDeque<>(ad1);
        ad2.printDeque();



    }
}