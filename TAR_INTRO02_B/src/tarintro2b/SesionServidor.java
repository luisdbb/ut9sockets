package tarintro2b;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Clase que implementa un hilo que gestiona la recepción del mensaje de un
 * cliente, el envío del mensaje a dicho cliente y la muestra del mensaje
 * recibido por consola
 * 
 */
public class SesionServidor extends Thread {

	Socket clienteConectado;
	int ncliente;

	public SesionServidor(Socket clienteConectado, int ncliente) {
		this.clienteConectado = clienteConectado;
		this.ncliente = ncliente;
	}

	/**
	 * Método que implementa el comportamiento del hilo
	 */
	@Override
	public void run() {
		try {
			System.out.println("Servidor.Consola - El servidor ha recibido un mensaje de algún cliente y procede a mostrarlo por consola");
			InputStream is;
			is = clienteConectado.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			//El servidor responde al cliente
			OutputStream os = clienteConectado.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF("Usted es mi cliente: " + ncliente);
			is.close();
			dis.close();
			os.close();
			dos.close();
			clienteConectado.close();
		} catch (IOException e) {
			System.out.println("Se ha producido una Exception: "+ e.getMessage());
		}
		 System.out.println("FIN Hilo SesionServidor de cliente " + ncliente);
	}

}
