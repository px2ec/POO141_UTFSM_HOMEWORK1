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

		double mass = 1.0;      //  1.0  [kg]
		double speed = 0.5;     //  0.5  [m/s]
		double radius = 0.1;    // 10.0  [cm]
		double position = 1.0;  //  1.0  [m]

		Ball b0 = new Ball(mass, radius, position, speed);
		Ball b1 = new Ball(mass, radius, 2.56, 0);

		world.addElement(b0);
		world.addElement(b1);
		world.simulate(deltaTime, endTime, samplingTime); // Delta time[s], total simulation time [s]
	}
}