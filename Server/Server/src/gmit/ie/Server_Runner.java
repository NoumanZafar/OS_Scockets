package gmit.ie;

import java.io.IOException;
import java.net.*;

public class Server_Runner {
	public static void main(String[] args) {
		ServerSocket listner;
		int portNumber = 10000;
		int backLog = 10;
		Socket conn;
		int connectionNumber = 0;

		try {
			listner = new ServerSocket(portNumber, backLog);
			System.out.println("Waiting for the Connection....");
			while (true) {
				conn = listner.accept();
				System.out.println("Connection Received.");
				new Thread(new Server(++connectionNumber, conn)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
