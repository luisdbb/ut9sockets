package tarintro2a;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * En esta clase se muestra cómo obtener información de un equipo local o remoto
 * a partir de un objeto InetAddress que lo representa
 */
public class TAR_INTRO02_A1 {

	public static void main(String[] args) {
		try {
			// Se obtienen los datos de localhost (IP local)
			System.out.println("Obteniendo los datos del equipo local con InetAddress.getLocalHost():");
			InetAddress dirLocalhost = InetAddress.getLocalHost();
			obtenerDatosDireccion(dirLocalhost);
			System.out.println("------------------------------------------");
			InetAddress address2 = InetAddress.getByName("45.22.30.39");
			System.out.println("Obteniendo los datos de la IP: " + address2 + " con InetAddress.getByName():");
			obtenerDatosDireccion(address2);
			System.out.println("------------------------------------------");
			// Se obtienen los datos de la url www.javatpoint.com
			// El servidor DNS traduce la url por su dirección IP
			String dirjavatpoint = "www.javatpoint.com";
			System.out.println("Obteniendo todas las IP de " + dirjavatpoint +" con InetAddress.getAllByName():");
			InetAddress[] dirsJavaTPoint = InetAddress.getAllByName(dirjavatpoint);
			int cont = 0;
			System.out.println("Hay " + dirsJavaTPoint.length + " IP(s) para la direccion \"" + dirjavatpoint + "\"");
			while (cont < dirsJavaTPoint.length) {
				System.out.println("\t Dirección " + cont + ": " + dirsJavaTPoint[cont].getHostAddress());
				InetAddress dir = dirsJavaTPoint[cont];
				obtenerDatosDireccion(dir);
				cont++;
			}
		} catch (UnknownHostException ex) {
			System.out.println("Se ha producido una UnknownHostException:" + ex.getMessage());
		}
		System.out.println("------------------------------------------");
		// Se pregunta al usuario por una direccion y, si es válida y alcanzable,
		// muestra sus datos
		// Este proceso se repite hasta que el usuario lo decida explícitamente
		boolean valido = false;
		String dir = "";
		Scanner in = new Scanner(System.in);
		int cont2 = 0;
		do {
			try {
				System.out.println("Introduzca una direccion para obtener sus datos.");
				System.out.println("Puede hacerlo mediante su URL (www.direccion.xx) o por su IP (xx.xx.xx.xx)");
				dir = in.nextLine();
				InetAddress[] dirs = InetAddress.getAllByName(dir);
				System.out.println("Obteniendo todas las IP de \"" + dir + "\"");
				cont2 = 0;
				System.out.println("Hay " + dirs.length + " IP(s) para la direccion " + dir);
				while (cont2 < dirs.length) {
					System.out.println("\t Dirección " + cont2 + ": " + dirs[cont2].getHostAddress());
					obtenerDatosDireccion(dirs[cont2]);
					cont2++;
				}
			} catch (UnknownHostException ue) {
				System.out.println("La direccion \"" + dir + "\" no puede ser alcanzada. ");
			} catch (NoSuchElementException nse) {
				System.out.println("Se ha producido una NoSuchElementException:" + nse.getMessage());
			} finally {
				System.out.println("¿Quiere repetir el proceso otra vez? (s/n)");
				valido = !(Utilidades.leerBoolean());
				in = new Scanner(System.in);
			}
		} while (!valido);

		in.close();
		System.out.println("FIN");
	}

	/**
	 * Función que se le pasa un objeto InetAddress y muestra por pantalla sus datos
	 * a través de los métodos de la clase InetAddress. En caso de que la direccion
	 * no sea alcanzable lanza una excepcion UnknownHostException
	 * 
	 * @param dir direccion (url)
	 * @throws UnknownHostException si la direccion no es alcanzable
	 */
	private static void obtenerDatosDireccion(InetAddress dir) throws UnknownHostException {
		System.out.println("Obteniendo los datos de: " + InetAddress.getByName(dir.getHostAddress()));
		System.out.println("\t toString de un objeto InetAddress: " + dir.toString());
		System.out.println("\t getCanonicalHostName de un objeto InetAddress: " + dir.getCanonicalHostName());
		System.out.println("\t getHostAddress de un objeto InetAddress: " + dir.getHostAddress());
		System.out.println("\t getHostName de un objeto InetAddress: " + dir.getHostName());
		System.out.println("\t getAddress de un objeto InetAddress: " + dir.getAddress()[0] + "-" + dir.getAddress()[1]
				+ "-" + dir.getAddress()[2] + "-" + dir.getAddress()[3]);
		System.out.println("\t ¿InetAddress.isMulticastAddress()? " + dir.isMulticastAddress());
	}

}
