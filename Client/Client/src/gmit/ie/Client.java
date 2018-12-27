package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

public class Client {
	/**
	 * Attributes to create the connection with server side.
	 */
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

	/**
	 * Create the connection and provide the menu to the user.
	 */
	public void connection() {
		/**
		 * Get the IP Address.
		 */
		System.out.print("Enter the IP Address of the Server : ");
		ipAddress = console.next();

		/**
		 * Get the port number.
		 */
		System.out.print("Enter the TCP Port Number : ");
		portNumber = console.nextInt();

		/**
		 * create the connection
		 */
		try {
			conn = new Socket(ipAddress, portNumber);
			out = new ObjectOutputStream(conn.getOutputStream());
			out.flush();
			in = new ObjectInputStream(conn.getInputStream());

			/**
			 * Menu for the user.
			 */
			do {
				message = (String) in.readObject();
				System.out.println(message);
				message = console.next();
				out.writeObject(message);
				out.flush();
				option = Integer.parseInt(message);
				if (option == 1) {
					/**
					 * if user is successfully logged in then call the main menu for the registered
					 * users/Engineers.
					 */
					loggedIn = new Login().login(in, out, message);
					if (loggedIn) {
						System.out.println("Successfully logged in.");
						new Menu().selectOption(in, out, message);
					}
				} else if (option == 2) {
					/**
					 * Create the object of registration to register the users
					 */
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
			/**
			 * Close the connection.
			 */
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
