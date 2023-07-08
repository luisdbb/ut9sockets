package tarintro2b;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Esta clase implementa un hilo que permite ilimitadas conexiones de cliente.
 * Por cada conexión se crea un hilo que gestiona la comunicación con dicho
 * cliente
 */
public class ServidorTCPConcurrente extends Thread {

	/**
	 * Método que implementa el comportamiento del hilo
	 */
	@Override
	public void run() {

		try {
			ServerSocket socketServidor; // Socket de escucha del servidor
			Socket clienteConectado; // Socket para atender a un cliente
			int numCliente = 0; // Contador de clientes
			int PUERTO = 30500; // Puerto para esuchar

			System.out.println("Soy el servidor y empiezo a escuchar peticiones por el puerto: " + PUERTO);
			socketServidor = new ServerSocket(PUERTO);
			System.out.println(
					"Servidor.Consola - Se crea un ArrayList para almacenar los manejadores de sockets de los clientes");
			ArrayList<SesionServidor> sesiones = new ArrayList<SesionServidor>();
			System.out.println(
					"Servidor.Consola - El servidor queda a la espera indefinidamente de todas las conexiones de cleinte que se produzcan");
			SesionServidor sesion;
			// Atendemos a los clientes
			do {

				clienteConectado = socketServidor.accept();
				numCliente++;
				System.out.println("\t Llega el cliente: " + numCliente);
				sesion = new SesionServidor(clienteConectado, numCliente);
				sesiones.add(sesion);
				sesion.start();
			} while (numCliente < TAR_INTRO02_B.NUMCLIENTES - 1);

			socketServidor.close();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception: " + e.getMessage());
			;
			e.printStackTrace();
		}
		System.out.println("FIN ServidorTCPConcurrente");
	}
}
