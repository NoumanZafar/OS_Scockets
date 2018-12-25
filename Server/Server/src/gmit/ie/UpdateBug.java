package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UpdateBug {
	private boolean exit = true;
	private int option;
	private List<Bug> bugList;
	private ReadFile reader;
	private boolean found = false;
	private String bugID;

	public UpdateBug() {
		super();
		bugList = new ArrayList<Bug>();
		reader = new ReadFile();
	}

	public void update(ObjectOutputStream out, ObjectInputStream in, String bugFile, Bug bug)
			throws NumberFormatException, ClassNotFoundException, IOException {
		do {
			bugList = reader.readBugs(bugFile, bug);
			for (int i = 0; i < 1; i++) {
				sendMessage("Enter the Bug Id to perform Action : ", out);
				bugID = (String) in.readObject();
				for (Bug b : bugList) {
					String bId = b.getId().trim();
					if (bId.equalsIgnoreCase(bugID)) {
						this.found = true;
						out.writeBoolean(found);
						out.flush();
						sendMessage(
								"Select one of the following options.\n\t1. Update Status \n\t2. Append to Description\n\t3. Change assigned Engineer\n\t4. Exit",
								out);
						option = Integer.parseInt((String) in.readObject());
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
						break;
					} else {
						out.writeBoolean(found);
						out.flush();
						System.out.println("There is no bug of this ID.");
						i--;
					}
				}
			}
		} while (exit);
	}

	public void sendMessage(String message, ObjectOutputStream out) {
		try {
			out.writeObject(message);
			System.out.println("Server --------> " + message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
