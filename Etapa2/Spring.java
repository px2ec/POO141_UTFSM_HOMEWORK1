import java.lang.Math.*;

public class Spring extends PhysicsElement {
	private static int id = 0;  // Spring identification
	protected final double restLength;
	private final double stiffness;
	protected Ball a_end, b_end;
/*
	private double currentLength;
	private double delta_x;
*/
	private Spring() {
		// Nobody can create a block without state
		this(0, 0);
	}
	
	public Spring(double restLength, double stiffness) {
		super(id++);
		this.restLength = restLength;
		this.stiffness = stiffness;
		a_end = b_end = null;
	}
	
	public void attachEnd(Ball sa) {
		/*
		 * Note: We attach a spring to a ball,
		 * not the other way around.
		 */
		if (a_end == null) {
			a_end = sa;
		} else if (b_end == null) {
			if (a_end.getPosition() > sa.getPosition()) {
				Ball tmp = a_end;
				a_end = sa;
				b_end = tmp;
			} else {
				b_end = sa;
			}
		} else {
			return;
		}

		sa.attachSpring(this);
	}

	public double getAendPosition() {
		if (a_end != null)
			return a_end.getPosition();
		if (b_end != null)
			return b_end.getPosition() - restLength;
		return 0;
	}

	public double getBendPosition() {
		if (a_end != null)
			return a_end.getPosition() + restLength;
		if (b_end != null)
			return b_end.getPosition();
		return 0;
	}

	public double getForce(Ball ball) {
		double force = 0;
		
		if ((a_end == null) || (b_end == null))
			return force;
		if ((ball != a_end) && (ball != b_end))
			return force;

		currentLength = Math.abs(b_end.getPosition() - a_end.getPosition());
		delta_x = Math.abs(currentLength - restLength);

		if (currentLength > restLength) {
			if (ball == a_end)
				force = stiffness * delta_x;
			else if (ball == b_end)
				force = stiffness * delta_x * -1;
		} else if (currentLength == restLength) {
			force = 0;
		} else {
			if (ball == a_end)
				force = stiffness * delta_x * - 1;
			else if (ball == b_end)
				force = stiffness * delta_x;
		}

		return force;
	}
	
	public void computeNextState(double delta_t, MyWorld w) {
	}
	
	public void updateState() {
	}

	public String getDescription() {
		if (a_end != null && b_end != null)
			return "Spring_" + super.getId() + ": " +
				a_end.getDescription() + "-" + b_end.getDescription();

		return "Spring_" + super.getId();
	}
	
	public String getState() {
		// To be coded by you
		return "";
	}
}