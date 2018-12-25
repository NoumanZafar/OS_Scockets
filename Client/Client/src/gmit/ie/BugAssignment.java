package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class BugAssignment {
	private Scanner console;

	public BugAssignment() {
		super();
		console = new Scanner(System.in);
	}

	public void assignToEngineer(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {

		for (int i = 0; i < 1; i++) {
			receiveAndSendMessage(in, out, message);
			boolean eFound = in.readBoolean();
			if (eFound == false) {
				System.out.println("Engineer does not exist.");
				i--;
			}
		}

		for (int j = 0; j < 1; j++) {
			receiveAndSendMessage(in, out, message);
			boolean bFound = in.readBoolean();
			if (bFound==false) {
				System.out.println("Bug does not exists.");
				j--;
			}
		}
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
