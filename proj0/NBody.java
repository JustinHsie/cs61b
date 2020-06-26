/** Runs simulation */
public class NBody {

	/** Method reads in radius */
	public static double readRadius(String file) {
		In in = new In(file);
		int numPlanets = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	/** Method reads in planet bodies */
	public static Body[] readBodies(String file) {
		In in = new In(file);

		// Read in n and radius
		int numPlanets = in.readInt();
		double radius = in.readDouble();

		Body[] bodies = new Body[numPlanets];

		for (int i = 0; i < numPlanets; i += 1) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();

			Body body = new Body(xxPos, yyPos, xxVel,
								 yyVel, mass, img);
			bodies[i] = body;
		}
		return bodies;
	}

	/** Main */
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		readRadius(filename);
		readBodies(filename);
	}
}