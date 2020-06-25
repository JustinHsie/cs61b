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
	public double Force;
	public static final double constG = 6.67e-11;
	public double xxForce;
	public double yyForce;

	/** Constructor instantiate class */
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
		Force = (constG * this.mass * b.mass) / 
					(calcDistance(b) * calcDistance(b));
		return Force;
	}

	/** Method to calculate force exerted in x direction */
	public double calcForceExertedByX(Body b) {
		xxForce = (calcForceExertedBy(b) * this.dx) / 
					calcDistance(b);
		return xxForce;
	}

	/** Method to calculate force exerted in y direction */
	public double calcForceExertedByY(Body b) {
		yyForce = (calcForceExertedBy(b) * this.dy) / 
					calcDistance(b);
		return yyForce;
	}

}









