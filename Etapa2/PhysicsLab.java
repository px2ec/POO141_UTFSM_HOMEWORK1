import java.util.Scanner;

public class PhysicsLab {
	public static void main(String[] args) {
		double deltaTime = 0;    // [s]
		double endTime = 0;      // [s]
		double samplingTime = 0; // [s]

		if (args.length != 3)  {
			Scanner scan = new Scanner(System.in);
			boolean bError = true;
			do {
				try {
					System.out.print("Ingrese delta: ");
					deltaTime = scan.nextDouble();
					bError = false;
				} catch (Exception e) {
					System.out.println("Intente nuevamente");
					scan.next();
				}
			} while (bError);
			bError = true;
			do {
				try {
					System.out.print("Ingrese tiempo a simular: ");
					endTime = scan.nextDouble();
					bError = false;
				} catch (Exception e) {
					System.out.println("Intente nuevamente");
					scan.next();
				}
			} while (bError);
			bError = true;
			do {
				try {
					System.out.print("Ingrese tiempo de muestreo: ");
					samplingTime = scan.nextDouble();
					bError = false;
				} catch (Exception e) {
					System.out.println("Intente nuevamente");
					scan.next();
				}
			} while (bError);
		} else {
			deltaTime = Double.parseDouble(args[0]);
			endTime = Double.parseDouble(args[1]);
			samplingTime = Double.parseDouble(args[2]);
		}

		MyWorld world = new MyWorld(System.out);

		double mass = 1.0;      //  1.0 [kg]
		double radius = 0.1;    // 10.0 [cm]
		
		Ball b0 = new Ball(mass, radius, 0.0, 0.0);
		Ball b1 = new Ball(mass, radius, 1.5, 0.0);
		Spring s0 = new Spring(1.0, 1.0);
		
		s0.attachEnd(b0);
		s0.attachEnd(b1);

		world.addElement(s0);
		world.addElement(b0);
		world.addElement(b1);
		world.simulate(deltaTime, endTime, samplingTime); // delta time[s], total simulation time [s]
	}
}