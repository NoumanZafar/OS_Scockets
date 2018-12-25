package gmit.ie;

import java.io.*;
import java.util.Scanner;

public class EmployeeRegistration {
	private boolean exit = true;
	private Scanner console;

	public EmployeeRegistration() {
		super();
		console = new Scanner(System.in);
	}

	public void registration(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {
		do {
			receiveAndSendMessage(in, out, message);
			for (int i = 0; i < 1; i++) {
				receiveAndSendMessage(in, out, message);
				boolean found = in.readBoolean();
				if (found) {
					System.out.println("Error ------> ID Already Exists. Try Again.");
					i--;
				}
			}

			for (int i = 0; i < 1; i++) {
				receiveAndSendMessage(in, out, message);
				boolean found = in.readBoolean();
				if (found) {
					System.out.println("Error ------> Email Already Exists. Try Again.");
					i--;
				}
			}

			receiveAndSendMessage(in, out, message);

			this.exit = false;
		} while (exit);
	}

	public void receiveAndSendMessage(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {
		message = (String) in.readObject();
		System.out.println(message);
		message = console.nextLine();
		sendMessage(message, out);
	}

	public void sendMessage(String message, ObjectOutputStream out) {
		try {
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
