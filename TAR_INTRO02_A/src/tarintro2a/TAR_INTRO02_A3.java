package tarintro2a;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * En esta clase se muestra cómo capturar la respuesta de una petición de URL
 * usando las clases URLConnection y HttpURLConnection
 */
public class TAR_INTRO02_A3 {

	public static void main(String[] args) {
		String urlstr = "";
		String filePath = "";

		try {
			// Primer ejemplo con la URL del buscador Google, para que se guarde en el
			// fichero google_inicio.html
			urlstr = "https://google.com";
			filePath = "google_inicio.html";

			System.out.println("Obteniendo la respuesta de una Conexión a la URL " + urlstr + "\n");
			URL urlObj = new URL(urlstr);
			URLConnection urlCon = urlObj.openConnection();
			if (urlCon != null) {
				InputStream is = urlCon.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String linea = br.readLine();
				while (linea != null) {
					System.out.println(linea);
					linea = br.readLine();
				}
				System.out.println("\n¿Desea guardar el resultado sobre el fichero " + filePath + "?");
				boolean resp = Utilidades.leerBoolean();
				if (resp) {
					InputStream inputStream = urlCon.getInputStream();
					BufferedInputStream reader = new BufferedInputStream(inputStream);
					BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(filePath));
					byte[] buffer = new byte[4096];
					int bytesRead = -1;
					while ((bytesRead = reader.read(buffer)) != -1) {
						writer.write(buffer, 0, bytesRead);
					}
					writer.close();
					reader.close();
				}
			}
		} catch (UnknownHostException uhe) {
			// Controla que la URL sea alcanzable (exista)
			// Ej: urlstr = "https://googlooooe.com";
			System.out.println("Se ha producido una UnknownHostException. La url " + uhe.getMessage()
					+ " no es alcanzable o no existe.");
		} catch (MalformedURLException mue) {
			// Controla que la URL esté bien formada Ej: urlstr = "htps://google.com";
			// producirá el mensaje "unknown protocol: htps"
			System.out.println("Se ha producido una MalformedURLException: " + mue.getMessage());
		} catch (IOException ex) {
			System.out.println("Se ha producido una IOException: " + ex.getMessage());
		}
		System.out.println("------------------------------------------");
		try {
			// Otro ejemplo pasando valores a Google directamente y que se guarde en el
			// fichero resbusquedagoogle.html
			filePath = "resbusquedagoogle.html";
			urlstr = "https://www.google.com/search?q=URLConnection+java";
			URL urlObj = new URL(urlstr);
			System.out.println("Obteniendo la respuesta de una Conexión a la URL:" + urlstr);
			/*
			 * El bloque siguiente producirá una IOException con el codigo de respuesta HTTP
			 * 403 (forbidden), al intentar obtener el InputStream de la conexion a la url
			 * 
			 * Server returned HTTP response code: 403 for URL:
			 * https://www.google.com/search?q=URLConnection+java
			 * 
			 */
			URLConnection urlCon = urlObj.openConnection();
			if (urlCon != null) {
				InputStream inputStream = urlCon.getInputStream();
				BufferedInputStream reader = new BufferedInputStream(inputStream);
				BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(filePath));
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				while ((bytesRead = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, bytesRead);
				}
				writer.close();
				reader.close();
			}
		} catch (IOException ex) {
			System.out.println("Se ha producido una IOException: " + ex.getMessage());
			System.out.println();
		}
		System.out.println("------------------------------------------");
		try {
			urlstr = "https://google.com";
			URL urlObj = new URL(urlstr);
			URLConnection conn = urlObj.openConnection();

			System.out.println("Mostrando todas las propiedas de la peticion para la URL: " + urlObj.toString() + "\n");
			Map<String, List<String>> mapPropiedadesPeticion = conn.getRequestProperties();
			for (Map.Entry<String, List<String>> entry : mapPropiedadesPeticion.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
			System.out.println("------------------------------------------");

			System.out.println("Mostrando todos los campos de las cabeceras de la URL: " + urlObj.toString() + "\n");
			Map<String, List<String>> mapCabeceras = conn.getHeaderFields();
			for (String key : mapCabeceras.keySet()) {
				System.out.print(key + ":");
				List<String> values = mapCabeceras.get(key);
				for (String aValue : values) {
					System.out.println("\t" + aValue);
				}
			}
			System.out.println("------------------------------------------");

			System.out.println("Utilizando un objeto HttpURLConnection para la url " + urlstr);
			HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
			int responseCode = httpCon.getResponseCode();
			String responseMessage = httpCon.getResponseMessage();
			String contentType = httpCon.getContentType();
			String contentEncoding = httpCon.getContentEncoding();
			int contentLength = httpCon.getContentLength();
			long date = httpCon.getDate();
			long expiration = httpCon.getExpiration();
			long lastModified = httpCon.getLastModified();

			System.out.println("Response Code: " + responseCode);
			System.out.println("Response Message: " + responseMessage);
			System.out.println("Content Type: " + contentType);
			System.out.println("Content Encoding: " + contentEncoding);
			System.out.println("Content Length: " + contentLength);
			System.out.println("Date: " + new Date(date));
			System.out.println("Expiration: " + new Date(expiration));
			System.out.println("Last Modified: " + new Date(lastModified));
			System.out.println("------------------------------------------");
		} catch (IOException ioe) {

		}
		System.out.println("FIN");
	}

}
