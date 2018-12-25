package gmit.ie;

import java.io.*;
import java.net.Socket;

public class Server implements Runnable {
	private Socket conn;
	private int clientID;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private int option;
	private boolean exit = true;
	private Employee employee;
	private boolean loggedIn = false;
	private Bug bug;

	public Server(int clientID, Socket conn) {
		super();
		this.clientID = clientID;
		this.conn = conn;
	}

	public void run() {
		try {
			out = new ObjectOutputStream(conn.getOutputStream());
			out.flush();
			in = new ObjectInputStream(conn.getInputStream());
			System.out.println("Client ID -------> " + clientID);

			// Server Logic
			do {
				String message = "Enter one of the Following.\n\t1. Login\n\t2. Register\n\t3. Exit";
				out.writeObject(message);
				System.out.println("Server -------> " + message);
				out.flush();
				option = Integer.parseInt((String) in.readObject());
				if (option == 1) {
					loggedIn = new Login().login("employees.txt", employee, out, in);
					if (loggedIn) {
						System.out.println("Successfully logged in.");
						new Options().selectOption(in, out, "employees.txt", employee,bug,"allBugs.txt");
					}
				} else if (option == 2) {
					new EmployeeRegistration().registration(employee, in, out, "employees.txt");
				} else if (option == 3) {
					exit = false;
					System.out.println("Client with ID -----> " + clientID + " left.");
				} else {
					System.out.println("WRONG INPUT ------> TRY AGAIN.");
				}
			} while (exit);

		} catch (IOException | NumberFormatException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
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
