package tarintro2f;

import java.net.*;

class UDP1_Servidor { // Receive and send

	private final DatagramSocket socket;
	private final int SIZE;

	public static void main(String[] args) throws Exception {
		new UDP1_Servidor(Integer.parseInt("53135"), Integer.parseInt("40000" ));
	}

	public UDP1_Servidor(int port, int size) throws Exception {
		this.SIZE = size;
		this.socket = new DatagramSocket(port);
		System.out.println("Servidor UDP preparado.");
		while (true) {
			byte[] buffer = new byte[SIZE];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);

			new Thread(() -> {
				DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, packet.getAddress(),
						packet.getPort());
				try {
					socket.send(sendPacket);
				//	System.out.println("Received message and send back");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}

}