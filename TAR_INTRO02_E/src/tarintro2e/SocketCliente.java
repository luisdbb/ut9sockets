package tarintro2e;
/*
 *
 * SocketCliente.java
 *
 * Ejemplo de cliente en java que se conecta con un servidor en C.
 */

import java.net.*;
import java.io.*;

/**
 * Clase que crea un socket cliente, establece la conexi�n y lee los datos del
 * servidor, escribi�ndolos en pantalla.
 */
public class SocketCliente {
	/** Programa principal, crea el socket cliente */
	public static void main(String[] args) {
		new SocketCliente();
	}

	/**
	 * Crea el socket cliente y lee los datos
	 */
	public SocketCliente() {
		try {
			/* Se crea el socket cliente */
			Socket socket = new Socket("localhost", 35557);
			System.out.println("conectado");

			/*
			 * Se hace que el cierre espere a la recogida de todos los datos desde el otro
			 * lado
			 */
			socket.setSoLinger(true, 10);

			/* Se obtiene un stream de lectura para leer objetos */
			DataInputStream bufferEntrada = new DataInputStream(socket.getInputStream());

			/*
			 * Se lee un Datosocket que nos env�a el servidor y se muestra en pantalla
			 */
			DatoSocket dato = new DatoSocket("");
			dato.readObject(bufferEntrada);
			System.out.println("Cliente Java: Recibido " + dato.toString());

			/* Se obtiene un flujo de envio de datos para enviar un dato al servidor */
			DataOutputStream bufferSalida = new DataOutputStream(socket.getOutputStream());

			/* Se crea el dato y se escribe en el flujo de salida */
			DatoSocket aux = new DatoSocket("Cliente, me despido.Adios");
			aux.writeObject(bufferSalida);

			System.out.println("Cliente Java: Enviado " + aux.toString());

			/*
			 * La llamada a setSoLinger() har� que el cierre espere a que el otro lado
			 * retire los datos que hemos enviado
			 */
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
