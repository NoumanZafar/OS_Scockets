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
	private boolean bugFound=false;

	public UpdateBug() {
		super();
		bugList = new ArrayList<Bug>();
		reader = new ReadFile();
	}

	public void update(ObjectOutputStream out, ObjectInputStream in, String bugFile, Bug bug, String fileName,
			Employee employee) throws NumberFormatException, ClassNotFoundException, IOException {
		do {
			sendMessage(
					"Select one of the following options.\n\t1. Update Status \n\t2. Append to Description\n\t3. Change assigned Engineer\n\t4. Exit",
					out);
			option = Integer.parseInt((String) in.readObject());
			if (option == 1) {
				System.out.println("Update Status");
				updateStatus(in, out, bugFile, bug, fileName, employee);
			} else if (option == 2) {
				System.out.println("Append to description");
				appendDesc(out, in, bugFile, bug);
			} else if (option == 3) {
				System.out.println("Change Assigned Engineer");
				new BugAssignment().assignToEngineer(fileName, employee, bugFile, bug, out, in);
			} else if (option == 4) {
				this.exit = false;
			} else {
				System.out.println("Error -------> Wrong Input. Try Again.");
			}
		} while (exit);
	}

	public void updateStatus(ObjectInputStream in, ObjectOutputStream out, String bugFile, Bug bug, String fileName,
			Employee employee) throws NumberFormatException, ClassNotFoundException, IOException {
		String newStatus = null;
		int index = -1;
		for (int i = 0; i < 1; i++) {
			sendMessage("Choose one of the following Status\n\t1. Open\n\t2. Assigned\n\t3. Closed", out);
			int selection = Integer.parseInt((String) in.readObject());
			if (selection == 1) {
				newStatus = "Open";
			} else if (selection == 2) {
				new BugAssignment().assignToEngineer(fileName, employee, bugFile, bug, out, in);
			} else if (selection == 3) {
				newStatus = "Closed";
			} else {
				System.out.println("Error -------> Wrong Input. Try again.");
				i--;
			}

			if (selection == 1 || selection == 3) {
				bugList = reader.readBugs(bugFile, bug);
				for (int j = 0; j < 1; j++) {
					sendMessage("Enter the Bug Id to perform Action : ", out);
					String bugID = (String) in.readObject();
					System.out.println(bugID);
					for (Bug b : bugList) {
						String bId = b.getId().trim();
						index++;
						if (bId.equalsIgnoreCase(bugID)) {
							this.bugFound = true;
							b.setStatus(newStatus);
							bugList.set(index, b);
							break;
						}
					}
					out.writeBoolean(bugFound);
					out.flush();
					if (bugFound) {
						new WriteFile().writeBug(bugFile, false, bugList);
					} else {
						System.out.println("Bug does not exists.");
						j--;
					}
				}
			}
		}
	}

	public void appendDesc(ObjectOutputStream out, ObjectInputStream in, String bugFile, Bug bug)
			throws ClassNotFoundException, IOException {
		int index = -1;
		sendMessage("Enter the description to append.", out);
		String desc = (String) in.readObject();
		bugList = reader.readBugs(bugFile, bug);
		for (int i = 0; i < 1; i++) {
			sendMessage("Enter the Bug Id to perform Action : ", out);
			String bugID = (String) in.readObject();
			System.out.println(bugID);
			for (Bug b : bugList) {
				String bId = b.getId().trim();
				index++;
				if (bId.equalsIgnoreCase(bugID)) {
					this.found = true;
					b.setProblemDesc(b.getProblemDesc() + desc);
					bugList.set(index, b);
					break;
				}
			}
			out.writeBoolean(found);
			out.flush();
			if (found) {
				new WriteFile().writeBug(bugFile, false, bugList);
			} else {
				System.out.println("Bug does not exists.");
				i--;
			}
		}
	}

	public void sendMessage(String message, ObjectOutputStream out) {
		try {
			out.writeObject(message);
			System.out.println("Server -------> " + message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
