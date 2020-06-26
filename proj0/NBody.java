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

		double radius = readRadius(filename);
		Body[] bodies = readBodies(filename);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Body b : bodies) {
			b.draw();
		}

		int arrayLength = bodies.length;
		double time = 0;
		while (time <= T) {
			double[] xForces = new double[arrayLength];
			double[] yForces = new double[arrayLength];
			for (int i = 0; i < arrayLength; i += 1) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for (int j = 0; j < arrayLength; j += 1) {
				bodies[j].update(dt, xForces[j], yForces[j]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Body b : bodies) {
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time = time + dt;
		}

		

	}
}











