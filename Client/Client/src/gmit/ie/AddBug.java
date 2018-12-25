package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class AddBug {
	private boolean exit = true;
	private Scanner console;
	private int option;

	public AddBug() {
		super();
		console = new Scanner(System.in);
	}

	public void addBug(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {
		do {
			receiveAndSendMessage(in, out, message);

			receiveAndSendMessage(in, out, message);

			receiveAndSendMessage(in, out, message);

			receiveAndSendMessage(in, out, message);

			for (int i = 0; i < 1; i++) {
				message = (String) in.readObject();
				System.out.println(message);
				message = console.nextLine();
				sendMessage(message, out);
				option = Integer.parseInt(message);
				if (option == 2) {
					for (int j = 0; j < 1; j++) {
						receiveAndSendMessage(in, out, message);
						boolean found = in.readBoolean();
						if (found == false) {
							System.out.println("No Engineer Exists. Try Again.");
							j--;
						}
					}
				} else if (!(option == 1 || option == 2 || option == 3)) {
					System.out.println("Wrong input. Try Again.");
					i--;
				}
			}
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
