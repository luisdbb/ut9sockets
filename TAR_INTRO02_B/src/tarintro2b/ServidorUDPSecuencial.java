package tarintro2b;

import java.net.*;
import java.io.*;

public class ServidorUDPSecuencial {

	public static void main(String args[]) {

		int numclientes =0;
		try {
			DatagramSocket socketUDP = new DatagramSocket(36789);
			byte[] bufer = new byte[1000];

			while (true) {
				System.out.println("El servidor UDP está listo para conexiones entrantes...");
				// Construimos el DatagramPacket para recibir peticiones
				DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);

				// Leemos una petición del DatagramSocket
				socketUDP.receive(peticion);
				numclientes++;
				System.out.println("Se ha conectado el cliente " + numclientes);
				System.out.print("Datagrama recibido del host: " + peticion.getAddress());
				System.out.println(" desde el puerto remoto: " + peticion.getPort());
				System.out.println("Haciendo ECHO del mensaje recibido: \""+ new String(peticion.getData()).trim()+"\".");

				// Construimos el DatagramPacket para enviar la respuesta
				DatagramPacket respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(),
						peticion.getAddress(), peticion.getPort());

				// Enviamos la respuesta, que es un eco
				socketUDP.send(respuesta);
				System.out.println("--------------");
				socketUDP.close();
			}

		} catch (SocketException e) {
			System.out.println("Se ha producido una SocketException: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException: " + e.getMessage());
			e.printStackTrace();
		}
		 System.out.println("FIN ServidorUDPSecuencial");
	}

}
