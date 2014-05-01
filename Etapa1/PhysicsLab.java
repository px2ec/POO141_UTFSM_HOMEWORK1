public class PhysicsLab {
	public static void main(String[] args) {
		if (args.length != 3)  {
			System.out.println("usage: java PhysicsLab <delta_time[s]> <end_time[s]> <sampling_time[s]>");
			System.exit(-1);
		}

		double deltaTime = Double.parseDouble(args[0]);    // [s]
		double endTime = Double.parseDouble(args[1]);      // [s]
		double samplingTime = Double.parseDouble(args[2]); // [s]
		MyWorld world = new MyWorld(System.out);

		double mass = 1.0;      //  1.0  [kg]
		double speed = 0.5;     //  0.5 [m/s]
		double radius = 0.1;    // 10.0  [cm]
		double position = 1.0;  //  1.0  [m]

		Ball b0 = new Ball(mass, radius, position, speed);
		Ball b1 = new Ball(mass, radius, 2.56, 0);

		world.addElement(b0);
		world.addElement(b1);
		world.simulate(deltaTime, endTime, samplingTime); // Delta time[s], total simulation time [s].
	}
}