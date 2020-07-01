public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> ad1= new ArrayDeque();
        ad1.addFirst(10);
        System.out.println(ad1.size());
        System.out.println(ad1.get(4));
        ad1.printDeque();

    }
}