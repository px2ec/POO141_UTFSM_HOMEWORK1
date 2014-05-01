public abstract class PhysicsElement {
	// To identify each element within its category
	private final int myId;

	protected PhysicsElement(int id) {
		myId = id;
	}
	
	protected int getId() {
		return myId;
	}

	public abstract String getDescription();
	public abstract String getState();
	public abstract void computeNextState(double delta_t, MyWorld world);
	public abstract void updateState();
}