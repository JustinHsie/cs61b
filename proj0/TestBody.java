/** 
 *   Tests Body class
 */
public class TestBody {
	/**
	 *   Tests Body
	 */
	public static void main(String[] args) {
		Body b1 = new Body(1, 0, 3, 4, 10, "foo");
		Body b2 = new Body(2, 4, 2, 5, 15, "bar");

		System.out.println("Force on b1 by b2 is " + 
							b1.calcForceExertedBy(b2));
		System.out.println("Force on b2 by b1 is " + 
							b2.calcForceExertedBy(b1));
	}
}