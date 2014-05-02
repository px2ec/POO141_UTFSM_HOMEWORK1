import java.util.*;
import java.io.*;

public class MyWorld {
	private PrintStream out;
	// Array to hold everything in my world
	private ArrayList<PhysicsElement> elements;

	public MyWorld() {
		// delta_t= 0.1[ms] and refreshPeriod=200 [ms]
		this(System.out);
	}
	public MyWorld(PrintStream output) {
		out = output;
		elements = new ArrayList<PhysicsElement>();     
	}

	public void addElement(PhysicsElement e) {
		elements.add(e);
	}

	public void printStateDescription() {
		String s = "Time";
		
		for (PhysicsElement e: elements)
			s += ", " + e.getDescription();
		
		out.println(s);
	}

	public void printState(double t) {
		String s = "";
		
		for (PhysicsElement e: elements) {
			if (e instanceof Spring) {
				s += ", " + ((Spring)e).getAendPosition();
				s += ", " + ((Spring)e).getBendPosition();
			} else if (e instanceof SpringAttachable) {
				s += ", " + ((SpringAttachable)e).getPosition();
			}

		}

		out.println(t + s);
	}

	// Simulate passing time
	public void simulate(double delta_t, double endTime, double samplingTime) {
		double t = 0;
		
		printStateDescription();
		printState(t);

		while (t < endTime) {
			for (double nextStop = t + samplingTime; t < nextStop; t += delta_t) {
				// Compute each element's next state based on current global state
				for (PhysicsElement e: elements)
					e.computeNextState(delta_t, this);
				// For each element, update its state
				for (PhysicsElement e: elements)
					e.updateState();
			}

			printState(t);
		}
	}   

	public Ball findCollidingBall(Ball me) {		
		for (PhysicsElement e: elements) {
			if (e instanceof Ball) {
				Ball b = (Ball)e;
				if (b.getDescription().compareTo(me.getDescription()) != 0)
					if (b.collide(me))
						return b;
			}
		}

		return null;
	}  
} 
