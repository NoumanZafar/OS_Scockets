package gmit.ie;

import java.io.*;
import java.net.Socket;

public class Server implements Runnable {
	/**
	 * Main class where threads are created for each client.
	 */
	private Socket conn;
	private int clientID;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private int option;
	private boolean exit = true;
	private Employee employee;
	private boolean loggedIn = false;
	private Bug bug;

	/**
	 * Constructor takes the client Id and the Socket
	 * 
	 * @param clientID
	 * @param conn
	 */
	public Server(int clientID, Socket conn) {
		super();
		this.clientID = clientID;
		this.conn = conn;
	}

	/**
	 * run() method from Runnable Interface.
	 */
	public void run() {
		/**
		 * Create Object Input/Output Streams
		 */
		try {
			out = new ObjectOutputStream(conn.getOutputStream());
			out.flush();
			in = new ObjectInputStream(conn.getInputStream());
			System.out.println("Client ID -------> " + clientID);

			/**
			 * Give client a little menu to login or register.
			 */
			do {
				String message = "Enter one of the Following.\n\t1. Login\n\t2. Register\n\t3. Exit";
				out.writeObject(message);
				System.out.println("Server -------> " + message);
				out.flush();
				option = Integer.parseInt((String) in.readObject());
				if (option == 1) {

					/**
					 * If user choose to login then call the login class and Authenticate the user.
					 */
					loggedIn = new Login().login("employees.txt", employee, out, in);
					if (loggedIn) {
						/**
						 * If login details were right then call the menu class to perform some action.
						 */
						System.out.println("Successfully logged in.");
						new Menu().selectOption(in, out, "employees.txt", employee, bug, "allBugs.txt");
					}
				} else if (option == 2) {
					/**
					 * Registration class
					 */
					new EmployeeRegistration().registration(employee, in, out, "employees.txt");
				} else if (option == 3) {
					/**
					 * When client exists.
					 */
					exit = false;
					System.out.println("Client with ID -----> " + clientID + " left.");
				} else {
					System.out.println("WRONG INPUT ------> TRY AGAIN.");
				}
			} while (exit);

		} catch (IOException | NumberFormatException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			/**
			 * Close the connection and Streams.
			 */
			try {
				out.close();
				in.close();
				conn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
