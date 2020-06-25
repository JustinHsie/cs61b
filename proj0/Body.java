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
	public double netForce;
	public static final double constG = 6.67e-11;

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
		dx = this.xxPos - b.xxPos;
		dy = this.yyPos - b.yyPos;
		r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}

	/** Method to calculate force exerted on 
	this body by given body */
	public double calcForceExertedBy(Body b) {
		netForce = (constG * this.mass * b.mass) / 
					(calcDistance(b) * calcDistance(b));
		return netForce;
	}
}









