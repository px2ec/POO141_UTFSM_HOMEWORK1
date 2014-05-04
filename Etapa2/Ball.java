import java.util.*;
import java.lang.Math.*;

public class Ball extends PhysicsElement {
	private static int id = 0;         // Ball identification number
	private final double mass;
	private final double radius;
	private double pos_t;              // Current position at time t
	private double pos_tPlusDelta;     // Next position in delta time in future
	private double speed_t;            // Speed at time t
	private double speed_tPlusDelta;   // Speed in delta time in future
	private double a_t;                // Acceleration at time t
	private double a_tMinusDelta;      // Acceleration delta time ago
	private ArrayList<Spring> springs; // ArrayList can grow, arrays cannot. 

	private boolean in_collision = false;

	private Ball() {
		// Nobody can create a block without state
		this(1.0, 0.1, 0, 0);
	}
	public Ball(double mass, double radius, double position, double speed) {
		super(id++);
		pos_t = position;
		speed_t = speed;
		this.mass = mass;
		this.radius = radius;

		a_t = 0;
		a_tMinusDelta = 0;
		springs = new ArrayList<Spring>();
	}

	public double getRadius() {
		return radius;
	}
	public double getSpeed() {
		return speed_t;
	}
	private double getNetForce() {
		double extForce = 0;
		
		for (Spring s: springs) {
			extForce += s.getForce(this);
		}

		return extForce;
	}
	public String getState() {
		if (speed_t != 0)
			return "Moviendose";

		return "Detenida";
	}
	public double getPosition() {
		return pos_t;
	}
	public double getMass() {
		return this.mass;
	}

	public void computeNextState(double delta_t, MyWorld world) {
		// Assumption: On collision we only change speed
		Ball b;
		a_t = getNetForce() / mass;

		/* Elastic collision */
		if ((b = world.findCollidingBall(this)) != null && !in_collision) {
			speed_tPlusDelta  = (speed_t * (this.mass - b.getMass()) + 2 * b.getMass() * b.getSpeed());
			speed_tPlusDelta /= (this.mass + b.getMass());
			pos_tPlusDelta = pos_t + speed_tPlusDelta * delta_t;

			in_collision = true;
		} else {
			speed_tPlusDelta = speed_t + 0.5 * (3 * a_t - a_tMinusDelta) * delta_t;
			//pos_tPlusDelta = pos_t + speed_t * delta_t + 0.5 * a_t * delta_t * delta_t;
			pos_tPlusDelta = pos_t + speed_t * delta_t + (4 * a_t - a_tMinusDelta) * delta_t * delta_t / 6;

			in_collision = false;
		}
	}

	public boolean collide(Ball b) {
		if (b == null)
			return false;

		return (Math.abs(b.getPosition() - this.pos_t) <= (b.getRadius() + this.radius));
	}

	public void updateState() {
		pos_t = pos_tPlusDelta;
		speed_t = speed_tPlusDelta;
		a_tMinusDelta = a_t;
	}

	public String getDescription() {
		return "Ball_" + super.getId();
	}

	public void attachSpring(Spring spring) {
		if (spring == null)
			return;

		springs.add(spring);
	}
}
