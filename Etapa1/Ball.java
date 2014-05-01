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
	}

	public double getRadius() {
		return radius;
	}
	public double getSpeed() {
		return speed_t;
	}
	public String getState() {
		return "";
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
		/* Elastic collision */
		if ((b = world.findCollidingBall(this)) != null && !in_collision) {
			speed_tPlusDelta  = (speed_t * (this.mass - b.getMass()) + 2 * b.getMass() * b.getSpeed());
			speed_tPlusDelta /= (this.mass + b.getMass());

			in_collision = true;
			pos_tPlusDelta = pos_t;
		} else {
			speed_tPlusDelta = speed_t;
			pos_tPlusDelta = pos_t + speed_t * delta_t;
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
	}

	public String getDescription() {
		return "Ball_" + super.getId();
	}
}
