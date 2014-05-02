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

		double mass = 2.0;      //  2.0 [kg]
		double speed = 0.0;     //  0.0 [m/s]
		double radius = 0.1;    // 10.0 [cm]
		double position = 0.0;  //  0.0 [m] 
		
		Ball bola0 = new Ball(mass, radius, 1.5, 1.0);
		Ball bola1 = new Ball(mass, radius, 2.5, 0.0);
		Spring resorte = new Spring(1.0, 1.0);
		FixedHook gancho = new FixedHook(0.0);
		
		resorte.attachEnd(gancho);
		resorte.attachEnd(bola0);

		world.addElement(resorte);
		world.addElement(gancho);
		world.addElement(bola0);
		world.addElement(bola1);
		world.simulate(deltaTime, endTime, samplingTime); // delta time[s], total simulation time [s]
	}
}