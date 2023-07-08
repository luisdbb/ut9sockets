package tarintro2e;
/*
 *
 * DatoSocket.java
 * Dato que se va a enviar entre servidores y clientes java y c.
 */

import java.io.*;

/**
 * Dato para enviar por el socket. Sus atributos son simples y una Clase
 * Atributo
 */
public class DatoSocket implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor. Guarda la cadena en el atributo d y calcula su longitud para
	 * guardarla en el atricuto c.
	 */
	public DatoSocket(String cadena) {
		// Si la cadena no es null, se guarda la cadena y su longitud
		if (cadena != null) {
			c = cadena.length();
			d = cadena;
		}
	}

	/** Primer atributo, un int */
	public int c = 0;

	/** Segundo atributo, un String */
	public String d = "";

	/**
	 * M�todo para devolver un String en el que se represente el valor de todos los
	 * atributos.
	 */
	public String toString() {
		String resultado;
		resultado = Integer.toString(c) + " -- " + d;
		return resultado;
	}

	/**
	 * M�todo para escribir los atributos de esta clase en un DataOutputStream de
	 * forma que luego pueda entenderlos un programa en C.
	 */
	public void writeObject(java.io.DataOutputStream out) throws IOException {
		// Se env�a la longitud de la cadena + 1 por el \0 necesario en C
		out.writeInt(c + 1);

		// Se env�a la cadena como bytes.
		out.writeBytes(d);

		// Se env�a el \0 del final
		out.writeByte('\0');
	}

	/**
	 * M�todo que lee los atributos de esta clase de un DataInputStream tal cual nos
	 * los env�a un programa en C. Este m�todo no contempla el caso de que se env�e
	 * una cadena "", es decir, un �nico \0.
	 */
	public void readObject(java.io.DataInputStream in) throws IOException {
		// Se lee la longitud de la cadena y se le resta 1 para eliminar el \0 que
		// nos env�a C.
		c = in.readInt() - 1;

		// Array de bytes auxiliar para la lectura de la cadena.
		byte[] aux = null;

		aux = new byte[c]; // Se le da el tama�o
		in.read(aux, 0, c); // Se leen los bytes
		d = new String(aux); // Se convierten a String
		in.read(aux, 0, 1); // Se lee el \0
	}
}
