interface SpringAttachable {
	void attachSpring(Spring s);
	String getDescription();
	double getPosition();
	boolean collide(SpringAttachable b);
	double getRadius();
	double getMass();
	double getSpeed();
}