import java.util.*;

public class Ball extends PhysicsElement {
	private static int id=0;  // Ball identification number
	private final double mass;
	private final double radius;
	private double pos_t;     // current position at time t
	private double pos_tPlusDelta;  // next position in delta time in future
	private double speed_t;   // speed at time t
	private double speed_tPlusDelta;   // speed in delta time in future
	private double a_t;    // acceleration at time t
	private double a_tMinusDelta;  // acceleration delta time ago;
	private ArrayList<Spring> springs;  // ArrayList can grow, arrays cannot. 

	private Ball(){   // nobody can create a block without state
		this(1.0, 0.1, 0, 0);
	}
	public Ball(double mass, double radius, double position, double speed){
		super(id++);
		this.mass = mass;
		pos_t = position;
		speed_t = speed;
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}
	public double getSpeed() {
		return speed_t;
	}
	private double getNetForce() {
		return this.mass*a_tMinusDelta;
	}
	public String getState() {
		//  to be coded by you
		return "";
	}
	public double getPosition() {
		return pos_t;
	}
	public double getMass() {
		return this.mass;
	}

	public void computeNextState(double delta_t, MyWorld world) {
		Ball b;  // Assumption: on collision we only change speed.   
		if ((b = world.findCollidingBall(this)) != null){ /* elastic collision */
			speed_tPlusDelta = (speed_t*(mass - b.getMass()) + 2*b.getMass()*b.getSpeed())/(mass + b.getMass());
			//pos_tPlusDelta = pos_t;
			a_t = getNetForce()/mass;
			pos_tPlusDelta = pos_t;
		} else {
			// to be coded by you        
		}
	}
	public boolean collide(Ball b) {
		if (b == null) return false;
		return (Math.abs(b.getPosition() - this.pos_t) <= (b.getRadius() + this.radius));
	}
	public void updateState(){
		pos_t = pos_tPlusDelta;
		speed_t = speed_tPlusDelta;
	}

	public String getDescription() {
		return "Ball " + super.getId();
	}

	public void attachSpring(Spring das_spring) {
		if (das_spring == null) return;
		springs.add(das_spring);
	}
}
