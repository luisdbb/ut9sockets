package tarintro2f;

import java.net.*;
import java.io.*;

public class TCP2_ConjuntoClientes { // Send and receive

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
		new TCP2_ConjuntoClientes("127.0.0.1", Integer.parseInt("53135"), Integer.parseInt("40000"), Integer.parseInt("100"));
	}

	public TCP2_ConjuntoClientes(String ip, int port, int size, int repeat) throws Exception {
		this.RECEIVER_IP = ip;
		this.PORT = port;
		this.SIZE = size;
		this.REPEAT = repeat;

		for (int i = 1; i <= REPEAT; i++) {
			send(i);
			Thread.sleep(10);
		}
	}

	private void send(int id) throws Exception {
		new Thread(() -> {
			try {
				Socket socket = new Socket(RECEIVER_IP, PORT);

				byte[] buffer = createData();
				OutputStream os = socket.getOutputStream();
				os.write(buffer);
				os.flush();

				System.out.println(id + " messages sent");

				receive(socket);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void receive(Socket socket) throws Exception {
		byte[] buffer = new byte[SIZE];
		InputStream is = socket.getInputStream();
		int left = SIZE;
		while (left > 0) {
			int read = is.read(buffer, SIZE - left, left);
			left -= read;
		}

		long sendTime = validateData(buffer);
		long taken = System.currentTimeMillis() - sendTime;
		COUNT();
		if (sendTime == -1) {
			WRONG_COUNT();
			return;
		}
		TIME_SUM((int) taken);

		System.out.println("TCP:: "+COUNT + " messages received (including " + WRONG_COUNT + " wrong message) / Time taken "
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