package tarintro2d;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class ServidorCentral {

	public static void main(String[] args) {
		try {
			MulticastSocket ms;
			Scanner teclado = new Scanner(System.in);
			boolean terminar = false;
			do {
				System.out.print("Servidor central preparado. - Dime el mensaje a enviar a todos los clientes: ");
				String mensaje = teclado.nextLine();

				String direccionGrupoMulticast = "225.8.8.8";
				InetAddress grupoMulticast = InetAddress.getByName(direccionGrupoMulticast);
				int puertoGrupoMulticast = 35000;
				ms = new MulticastSocket();
				DatagramPacket datagrama = new DatagramPacket(mensaje.getBytes(), mensaje.length(), grupoMulticast,
						puertoGrupoMulticast);
				ms.send(datagrama);
				System.out.println("¿Desea repetir la operación?");
				terminar = Utilidades.leerBoolean();
			} while (terminar);
			ms.close();
			teclado.close();
		} catch (IOException ex) {
			System.out.println("Se ha producido una IOException: " + ex.getMessage());
			ex.printStackTrace();
		}

	}
}
