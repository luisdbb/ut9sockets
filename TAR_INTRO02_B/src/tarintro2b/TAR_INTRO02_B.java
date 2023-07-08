package tarintro2b;

/**
 * En esta clase se crean tres hilos (dos hilo cliente y un hilo servidor) y se
 * inician. Cada uno de los hilos cliente establece conexión con el servidor y
 * realiza las siguientes actuaciones: 1. El cliente envía un mensaje al
 * servidor 2. El servidor muestra por consola dicho mensaje 3. El servidor
 * responde con otro mensaje al cliente. 4. El cliente muestra por consola dicho
 * mensaje
 * 
 */
public class TAR_INTRO02_B {

	static int NUMCLIENTES = 100;

	public static void main(String[] args) {
		try {
			Thread servidor = new ServidorTCPConcurrente();
//		Thread servidor = new ServidorTCPSecuencial();
			servidor.start();

			Thread cliente = null;
			for (int cont = 1; cont < NUMCLIENTES; cont++) {
				cliente = new Cliente("CLIENTE" + cont);
				cliente.start();				
			}

			cliente.join();
			// servidor.interrupt();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("FIN TAR_INTRO02_B");
	}

}
