package tarintro2c;

/**
 * En esta clase se crean dos hilos (un hilo cliente y un hilo servidor) y se
 * inician. El hilo cliente establece conexión (UDP y TCP respectivamente) con
 * el servidor (correspondiente) y realiza las siguientes actuaciones: 1. El
 * cliente construye un objeto Coche y lo envía al servidor 2. El servidor
 * muestra por consola el objeto recibido 3. El servidor responde al cliente con
 * el mismo objeto recibido incrementando en 1 el número de puertas, el modelo,
 * la marca y la matrícula 4. El cliente muestra por consola dicho objeto al
 * recibirlo de vuelta
 * 
 */
public class TAR_INTRO02_C {

	/**
	 * Método principal de la clase
	 * 
	 * @param args No recibe parámetros de entrada
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Intercambio de un objeto Coche mediante UDP");
			Thread servidorUDP = new ServidorUDP();
			Thread clienteUDP = new ClienteUDP("PEPE");

			servidorUDP.start();
			clienteUDP.start();
			servidorUDP.join();
			clienteUDP.join();
			System.out.println("----------------------------------");

			System.out.println("Intercambio de un objeto Coche mediante TCP");
			Thread servidorTCP = new ServidorTCP();
			Thread clienteTCP = new ClienteTCP("ROSA");

			servidorTCP.start();
			clienteTCP.start();
			servidorUDP.join();
			clienteUDP.join();

		} catch (Exception e) {
			System.out.println("Se ha producido una Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
