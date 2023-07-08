package tarintro2e;
/*
 *
 * SocketServidor.java
 * Ejemplo de un socket servidor en java que se conecta con un cliente C.
 */

import java.net.*;
import java.io.*;

/**
 * Clase principal que instancia un socket servidor, acepta una conexi�n de un
 * cliente y le env�a un entero y una cadena de caracteres.
 */
public class SocketServidor {
	public static void main(String[] args) {
		// Se instancia la clase principal para que haga todo lo que tiene que
		// hacer el ejemplo
		new SocketServidor();
	}

	/**
	 * Constructor por defecto. Hace todo lo que hace el ejemplo.
	 */
	public SocketServidor() {
		try {
			// Se crea un socket servidor atendiendo a un determinado puerto.
			// Por ejemplo, el 25557.
			ServerSocket socket = new ServerSocket(35557);

			// Se acepata una conexi�n con un cliente. Esta llamada se queda
			// bloqueada hasta que se arranque el cliente.
			System.out.println("Esperando cliente");
			Socket cliente = socket.accept();
			System.out.println("Conectado con cliente de " + cliente.getInetAddress());

			// Se hace que el cierre del socket sea "gracioso". Esta llamada s�lo
			// es necesaria si cerramos el socket inmediatamente despu�s de
			// enviar los datos (como en este caso).
			// setSoLinger() a true hace que el cierre del socket espere a que
			// el cliente lea los datos, hasta un m�ximo de 10 segundos de espera.
			// Si no ponemos esto, el socket se cierra inmediatamente y si el
			// cliente no ha tenido tiempo de leerlos, los datos se pierden.
			cliente.setSoLinger(true, 10);

			// Se prepara un dato para enviar.
			DatoSocket dato = new DatoSocket("HolaDesdeServerJava");

			// Se prepara un flujo de salida de datos, es decir, la clase encargada
			// de escribir datos en el socket.
			DataOutputStream bufferSalida = new DataOutputStream(cliente.getOutputStream());

			// Se env�a el dato.
			dato.writeObject(bufferSalida);
			System.out.println("Servidor Java: Enviado " + dato.toString());

			// Se prepara el flujo de entrada de datos, es decir, la clase encargada
			// de leer datos del socket.
			DataInputStream bufferEntrada = new DataInputStream(cliente.getInputStream());

			// Se crea un dato a leer y se le dice que se rellene con el flujo de
			// entrada de datos.
			DatoSocket aux = new DatoSocket("");
			aux.readObject(bufferEntrada);
			System.out.println("Servidor java: Recibido " + aux.toString());

			// Se cierra el socket con el cliente.
			// La llamada anterior a setSoLinger() har�
			// que estos cierres esperen a que el cliente retire los datos.
			cliente.close();

			// Se cierra el socket encargado de aceptar clientes. Ya no
			// queremos m�s.
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
