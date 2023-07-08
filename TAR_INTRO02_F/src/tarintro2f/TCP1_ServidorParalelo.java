package tarintro2f;

import java.net.*;
import java.io.*;

class TCP1_ServidorParalelo { // Receive and send

	private final ServerSocket serverSocket;
	private final int SIZE;

	public static void main(String[] args) throws Exception {
		new TCP1_ServidorParalelo(Integer.parseInt("53135"), Integer.parseInt( "40000"));
	}

	public TCP1_ServidorParalelo(int port, int size) throws Exception {
		this.SIZE = size;
		this.serverSocket = new ServerSocket(port);
		System.out.println("Servidor TCP listo para escuchar en puerto " + port);
		while (true) {
			Socket socket = serverSocket.accept();

			new Thread(() -> {
				byte[] data = new byte[SIZE];
				try {
					InputStream is = socket.getInputStream();

					int left = SIZE;
					while (left > 0) {
						int read = is.read(data, SIZE - left, left);
						left -= read;
					}
					OutputStream os = socket.getOutputStream();
					os.write(data);
					os.flush();

				//	System.out.println("Received message and send back");
				} catch (IOException e) {
					return;
				}
			}).start();
		}
	}

}