/** Import packages */
import java.lang.Math;

/** Class that determines properties of the planet body
 * @author Justin Hsie
 */

public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public double dx;
	public double dy;
	public double r;
	public static final double constG = 6.67e-11;
	public Body[] allBodys;

	/** Constructor instantiate class 
	 *
	 *  @param  xP   x Position
	 *  @param  yP   y Position
	 *  @param  xV   x Velocity
	 *  @param  yV   y Velocity
	 *  @param  m    Mass
	 *  @param  img  Image file
	 */
	public Body(double xP, double yP, double xV,
              	double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** Constructor deep copy of body */
	public Body(Body b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	/** Method to calculate distance between 
	this body and given body */
	public double calcDistance(Body b) {
		dx = b.xxPos - this.xxPos;
		dy = b.yyPos - this.yyPos;
		r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}

	/** Method to calculate force exerted on 
	this body by given body */
	public double calcForceExertedBy(Body b) {
		double force = (constG * this.mass * b.mass) / 
					(calcDistance(b) * calcDistance(b));
		return force;
	}

	/** Method to calculate force exerted in x direction */
	public double calcForceExertedByX(Body b) {
		double xxForce = (calcForceExertedBy(b) * this.dx) / 
					calcDistance(b);
		return xxForce;
	}

	/** Method to calculate force exerted in y direction */
	public double calcForceExertedByY(Body b) {
		double yyForce = (calcForceExertedBy(b) * this.dy) / 
					calcDistance(b);
		return yyForce;
	}

	/** Method to calculate net force in x by all bodies */
	public double calcNetForceExertedByX(Body[] allBodys) {
		this.allBodys = allBodys;
		double netForceX = 0;
		for (Body b : allBodys) {
			if (b.equals(this)) {
				continue;
			}
			else {
				netForceX = netForceX + calcForceExertedByX(b);
			}
		}
		return netForceX;
	}

	/** Method to calculate net force in y by all bodies */
	public double calcNetForceExertedByY(Body[] allBodys) {
		this.allBodys = allBodys;
		double netForceY = 0;
		for (Body b : allBodys) {
			if (b.equals(this)) {
				continue;
			}
			else {
				netForceY = netForceY + calcForceExertedByY(b);
			}
		}
		return netForceY;
	}

	/** Method to update position of body */
	public void update(double dt, double fX, double fY) {
		double xxAccel = fX / mass;
		double yyAccel = fY / mass; 

		xxVel = xxVel + dt * xxAccel;
		yyVel = yyVel + dt * yyAccel;

		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

	/** Method to draw */
	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}

}









