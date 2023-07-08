package tarintro2b;

import java.io.*;
import java.net.*;

public class ServidorTCPSecuencial extends Thread {

	@Override
	public void run() {

		// Definimos los Sockets
		ServerSocket servidor; // Socket de escucha del servidor
		Socket cliente; // Socket para atender a un cliente
		int numCliente = 0; // Contador de clientes
		int PUERTO = 30500; // Puerto para esuchar

		System.out.println("Soy el servidor y empiezo a escuchar peticiones por el puerto: " + PUERTO);

		try {
			// Apertura de socket para escuhar a través de un puerto
			servidor = new ServerSocket(PUERTO);
			// Atendemos a los clientes
			do {
				numCliente++;
				// Aceptamos la conexión
				cliente = servidor.accept();
				// se procesa la petición del cliente y se le da una respuesta
				InputStream is = cliente.getInputStream();
	            DataInputStream dis = new DataInputStream(is);
				System.out.println("\t Llega el cliente: " + numCliente + "->"+ dis.readUTF());
				DataOutputStream ps = new DataOutputStream(cliente.getOutputStream());
				ps.writeUTF("Usted es mi cliente: " + numCliente);

				// Y cerramos la conexión porque ya no vamos a oprrar más con él
				cliente.close();
				System.out.println("\t Se ha cerrado la conexión con el cliente: " + numCliente);
			} while (numCliente < TAR_INTRO02_B.NUMCLIENTES-1);
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception: "+ e.getMessage());
			e.printStackTrace();
		}
        System.out.println("FIN ServidorTCPSecuencial");
	}

}
