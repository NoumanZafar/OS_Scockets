package gmit.ie;

import java.io.IOException;
import java.net.*;

public class Server_Runner {
	public static void main(String[] args) {
		ServerSocket listner;

		/**
		 * Port number and Backlog.
		 */
		int portNumber = 10000;
		int backLog = 10;
		Socket conn;
		int connectionNumber = 0;

		/**
		 * Accept a new connection from client and each connection has their own thread
		 * running.
		 */
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
