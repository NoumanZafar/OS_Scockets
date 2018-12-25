package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

public class Client {
	private Socket conn;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String message;
	private String ipAddress;
	private int portNumber;
	private Scanner console;
	private boolean exit = true;
	private int option;
	private boolean loggedIn = false;

	public Client() {
		super();
		console = new Scanner(System.in);
	}

	public void connection() {
		System.out.print("Enter the IP Address of the Server : ");
		ipAddress = console.next();

		System.out.print("Enter the TCP Port Number : ");
		portNumber = console.nextInt();

		try {
			conn = new Socket(ipAddress, portNumber);
			out = new ObjectOutputStream(conn.getOutputStream());
			out.flush();
			in = new ObjectInputStream(conn.getInputStream());

			// Client logic
			do {
				message = (String) in.readObject();
				System.out.println(message);
				message = console.next();
				out.writeObject(message);
				out.flush();
				option = Integer.parseInt(message);
				if (option == 1) {
					loggedIn = new Login().login(in, out, message);
					if (loggedIn) {
						System.out.println("Successfully logged in.");
						new Options().selectOption(in, out, message);
					}
				} else if (option == 2) {
					new EmployeeRegistration().registration(in, out, message);
				} else if (option == 3) {
					exit = false;
					System.out.println("Connection Ended.");
				} else {
					System.out.println("WRONG INPUT ------> TRY AGAIN.");
				}
			} while (exit);
		} catch (IOException | NumberFormatException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				out.close();
				in.close();
				console.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
