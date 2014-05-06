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

		double mass = 2.0;      //  2.0 [kg]
		double speed = 0.0;     //  0.0 [m/s]
		double radius = 0.1;    // 10.0 [cm]
		double position = 0.0;  //  0.0 [m]
		double gravity = -9.8;   //  9.8 [m/s^2]
		double cof = 0.004;
		
		Block bola0 = new Block(mass, radius, 1.5, 1.0, cof);
		Ball bola1 = new Ball(mass, radius, 2.4, 0.0);
		Spring resorte = new Spring(1.0, 1.0);
		FixedHook gancho = new FixedHook(0.0);
		
		resorte.attachEnd(gancho);
		resorte.attachEnd(bola0);

		MyWorld world = new MyWorld(System.out, gravity);

		world.addElement(resorte);
		world.addElement(gancho);
		world.addElement(bola0);
		world.addElement(bola1);
		world.simulate(deltaTime, endTime, samplingTime); // delta time[s], total simulation time [s]
	}
}