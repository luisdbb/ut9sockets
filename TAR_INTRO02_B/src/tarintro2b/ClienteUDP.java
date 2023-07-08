package tarintro2b;

import java.net.*;
import java.io.*;

public class ClienteUDP {

	// Los argumentos proporcionan el mensaje y el nombre del servidor
	public static void main(String args[]) {

		String men = "Hola Soy un cliente UDP";

		try {
			DatagramSocket socketUDP = new DatagramSocket();
			byte[] mensaje = men.getBytes();
			InetAddress hostServidor = InetAddress.getByName("localhost");
			int puertoServidor = 36789;

			// Construimos un datagrama para enviar el mensaje al servidor
			DatagramPacket peticion = new DatagramPacket(mensaje, men.length(), hostServidor, puertoServidor);
			
			// Enviamos el datagrama
			socketUDP.send(peticion);

			// Construimos el DatagramPacket que contendrá la respuesta
			byte[] bufer = new byte[1000];
			DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
			socketUDP.receive(respuesta);
			men = new String(respuesta.getData()).trim();
			// Enviamos la respuesta del servidor a la salida estandar
			System.out.println("Respuesta: " + men);

			// Cerramos el socket
			socketUDP.close();

		} catch (SocketException e) {
			System.out.println("Se ha producido una SocketException: "+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException: "+ e.getMessage());
			e.printStackTrace();
		}
        System.out.println("FIN ClienteUDP");
	}
}
