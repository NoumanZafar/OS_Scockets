package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class UpdateBug {
	private boolean exit = true;
	private Scanner console;
	private int option;

	public UpdateBug() {
		super();
		console = new Scanner(System.in);
	}

	public void update(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {
		do {
			for (int i = 0; i < 1; i++) {
				receiveAndSendMessage(in, out, message);
				boolean found = in.readBoolean();
				if (found) {
					message = (String) in.readObject();
					System.out.println(message);
					message = console.nextLine();
					sendMessage(message, out);
					option = Integer.parseInt(message);
					if (option == 1) {
						System.out.println("Update Status");
					} else if (option == 2) {
						System.out.println("Append to description");
					} else if (option == 3) {
						System.out.println("Change Assigned Engineer");
					} else if (option == 4) {
						this.exit = false;
					} else {
						System.out.println("Error -------> Wrong Input. Try Again.");
					}
				} else if (found == false) {
					System.out.println("There is no bug of this ID.");
					i--;
				}
			}
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
