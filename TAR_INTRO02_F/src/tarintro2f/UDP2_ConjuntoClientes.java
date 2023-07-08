package tarintro2f;

import java.net.*;

public class UDP2_ConjuntoClientes { // Send and receive

	private final DatagramSocket socket;

	private final String RECEIVER_IP;
	private final int PORT;
	private final int SIZE;
	private final int REPEAT;

	private int COUNT = 0;
	private int WRONG_COUNT = 0;
	private int TIME_SUM = 0;

	private synchronized void COUNT() {
		COUNT++;
	}

	private synchronized void WRONG_COUNT() {
		WRONG_COUNT++;
	}

	private synchronized void TIME_SUM(int time) {
		TIME_SUM += time;
	}

	public static void main(String[] args) throws Exception {
		new UDP2_ConjuntoClientes("127.0.0.1", Integer.parseInt("53135"), Integer.parseInt("40000"),
				Integer.parseInt("100"));
	}

	public UDP2_ConjuntoClientes(String ip, int port, int size, int repeat) throws Exception {
		this.RECEIVER_IP = ip;
		this.PORT = port;
		this.SIZE = size;
		this.REPEAT = repeat;

		this.socket = new DatagramSocket();

		for (int i = 1; i <= REPEAT; i++) {
			send(i);
			Thread.sleep(10);
		}
	}

	private void send(int id) throws Exception {
		new Thread(() -> {
			try {
				byte[] buffer = createData();
				InetAddress address = InetAddress.getByName(RECEIVER_IP);
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);

				socket.send(packet);
				System.out.println(id + " messages sent");

				receive(socket);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void receive(DatagramSocket socket) throws Exception {
		byte[] buffer = new byte[SIZE];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

		socket.receive(packet);

		long sendTime = validateData(packet.getData());
		long taken = System.currentTimeMillis() - sendTime;
		COUNT();

		if (sendTime == -1) {
			WRONG_COUNT();
			return;
		}

		TIME_SUM((int) taken);

		System.out.println(
				"UDP:: " + COUNT + " messages received (including " + WRONG_COUNT + " wrong message) / Time taken "
						+ taken + "ms (Average " + String.format("%.3f", (double) TIME_SUM / COUNT) + "ms)");
	}

	private byte[] createData() {
		long currentTime = System.currentTimeMillis();
		byte[] buffer = new byte[SIZE];

		for (int i = 0; i < SIZE; i += 8) {
			for (int j = 0; j < 8; j++) {
				buffer[i + j] = (byte) (currentTime >> (56 - (8 * j)));
			}
		}

		return buffer;
	}

	private long validateData(byte[] data) {
		long sendTime = -1;

		for (int i = 0; i < SIZE; i += 8) {
			long time = 0;
			for (int j = 0; j < 8; j++) {
				time |= ((long) data[i + j] & 0xff) << (56 - (8 * j));
			}
			if (sendTime != -1 && time != sendTime) {
				return -1;
			}
			sendTime = time;
		}

		return sendTime;
	}

}