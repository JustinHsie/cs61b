public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> ad1 = new ArrayDeque();
        ad1.addFirst(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addFirst(3);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addLast(9);
        ad1.addFirst(2);
        System.out.println(ad1.size());
        System.out.println(ad1.get(4));
        ad1.printDeque();

    }
}