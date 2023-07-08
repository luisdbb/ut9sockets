package tarintro2a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * En esta clase se muestra cómo obtener información dirección URL a partir de
 * un objeto URL que lo representa
 */
public class TAR_INTRO02_A2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String cadenaurl = "https://www.educastur.es/estudiantes/fp/oferta-pdf";
		System.out.println("Obteniendo los datos la URL: \"" + cadenaurl + "\"");
		URL url;
		try {
			url = new URL(cadenaurl);
			obtenerDatosURL(url);
		} catch (MalformedURLException e) {
			System.out.println("Se ha producido una MalformedURLException: " + e.getMessage());
		}
		System.out.println("------------------------------------------");

		cadenaurl = "https://crunchify.com/wp-content/uploads/code/json.sample.txt";
		System.out.println("Obteniendo los datos la URL: \"" + cadenaurl + "\"");
		try {
			url = new URL(cadenaurl);
			obtenerDatosURL(url);
			System.out.println("El volcado de la petición a la url " + url.toString() + " es el siguiente:");
			volcarPeticionURLPorPantalla(url);
		} catch (MalformedURLException e) {
			System.out.println("Se ha producido una MalformedURLException: " + e.getMessage());
		}
		System.out.println("------------------------------------------");

		boolean valido = false;
		do {
			System.out.println("Introduzca la url completa (https://www.aaaaaa.xxx)");
			cadenaurl = in.nextLine();
			try {
				System.out.println("Obteniendo los datos la URL: \"" + cadenaurl + "\"");
				url = new URL(cadenaurl);
				/*
				 * NOTA sobre el constructor de la clase URL: De acuerdo a la norma descrita en
				 * RFC2396, se crea un nuevo objeto URL a partir de una cadena si ésta viene
				 * dada de acuerdo a la "Uniform Resource Identifiers : Generic * Syntax", que
				 * sigue esta plantilla :
				 * 
				 * <scheme>://<authority><path>?<query>#<fragment>
				 * 
				 * <scheme> representa el protocolo, típicamente http o https <path> representa
				 * la URL (Ej: www.educastur.es/estudiantes/fp/ <query> representa los datos
				 * pasados la peticion por POST <fragment> representa un fragmento concreto de
				 * la direccion
				 * 
				 * Ejemplo:
				 * https://www.europedia.es/politica-de-la-ue/normas-de-libre-competencia-dentro
				 * -de-la-ue/#respond
				 * 
				 */
				obtenerDatosURL(url);
				System.out.println("¿Desea volcar la petición de la URL por pantalla?");
				boolean respuesta = Utilidades.leerBoolean();
				if (respuesta) {
					volcarPeticionURLPorPantalla(url);
				}
				System.out.println("------------------------------------------");
			} catch (MalformedURLException ex) {
				System.out.println("Se ha producido una MalformedURLException: " + ex.getMessage());
				System.out.println("La url \"" + cadenaurl + "\" no puede ser alcanzada. ");
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
	 * Función que se le pasa un objeto URL y muestra por pantalla el volcado de la
	 * petición a la misma. En caso de de error con la E/S captura una IOException
	 * 
	 * @param url
	 */
	private static void volcarPeticionURLPorPantalla(URL url) {
		try {
			System.out.println("URL: " + url.toString());
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String linea = br.readLine();
			while (linea != null) {
				System.out.println(linea);
				linea = br.readLine();
			}
		} catch (IOException ex) {
			System.out.println("Se ha producido un error de E/S al volcar la petición de la URL " + url.toString());
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Función que se le pasa un objeto URL y muestra por pantalla sus datos a
	 * través de los métodos de la clase URL. En caso de que la url no sea correcta
	 * o alcanzable lanza una Exception
	 * 
	 * @param url
	 */
	private static void obtenerDatosURL(URL url) {
		try {
			System.out.println("\t getProtocol: " + url.getProtocol());
			System.out.println("\t getHost: " + url.getHost());
			System.out.println("\t getPath: " + url.getPath());
			System.out.println("\t getFile: " + url.getFile());
			System.out.println("\t getAuthority: " + url.getAuthority());
			System.out.println("\t getUserInfo: " + url.getUserInfo());
			System.out.println("\t getRef: " + url.getRef());
			System.out.println("\t getQuery: " + url.getQuery());
			System.out.println("\t toExternalForm: " + url.toExternalForm());
			System.out.println("\t toString: " + url.toString());
			System.out.println("\t getDefaultPort: " + url.getDefaultPort());
			System.out.println("\t getPort: " + url.getPort() + "\n");
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
		}
	}

}
