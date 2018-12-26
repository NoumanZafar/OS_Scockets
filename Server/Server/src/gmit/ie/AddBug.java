package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddBug {
	/**
	 * variables and Composition with other classes.
	 */
	private boolean exit = true;
	private int option;
	private String status;
	private WriteFile writer;
	private ReadFile reader;
	private List<Bug> bugList;

	/**
	 * Constructor Instantiate the other classes
	 */
	public AddBug() {
		super();
		writer = new WriteFile();
		reader = new ReadFile();
	}

	/**
	 * This method is synchronized that means only one thread can reach it. In this
	 * method a new bug is being added and written to the file.
	 * 
	 * @param out      ObjectOutputStream
	 * @param in       ObjectInputStream
	 * @param employee Employee class
	 * @param fileName employees.txt to read the Users
	 * @param bug      Bug class
	 * @param bugFile  allBugs.txt to read/write bugs data
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public synchronized void addBug(ObjectOutputStream out, ObjectInputStream in, Employee employee, String fileName,
			Bug bug, String bugFile) throws ClassNotFoundException, IOException {
		do {

			/**
			 * Bug Id is generated
			 */
			String id = generateID(bugFile, bug);

			/**
			 * Bug name send and received.
			 */
			sendMessage("Bug Name : ", out);
			String name = (String) in.readObject();
			System.out.println(name);

			/**
			 * Time and Date
			 */
			sendMessage("Time and Date : ", out);
			String time = (String) in.readObject();
			System.out.println(time);

			/**
			 * Platform
			 */
			sendMessage("Platform-OS : ", out);
			String platform = (String) in.readObject();
			System.out.println(platform);

			/**
			 * Description of the bug.
			 */
			sendMessage("Problem Description : ", out);
			String problemDesc = (String) in.readObject();
			System.out.println(problemDesc);

			/**
			 * Status of the bug. open /closed /assigned to registered engineer
			 */
			for (int i = 0; i < 1; i++) {
				sendMessage("Choose one of the following Status\n\t1. Open\n\t2. Assigned\n\t3. Closed", out);
				String s = (String) in.readObject();
				System.out.println(s);
				option = Integer.parseInt(s);
				if (option == 1) {
					status = "Open";
					System.out.println(status);
				} else if (option == 2) {
					/**
					 * If user choose to set the status of the bug to Assigned then ask for the
					 * Engineer's id because it has to be assigned to an engineer.
					 */
					for (int j = 0; j < 1; j++) {
						sendMessage("Enter the Engineer's ID : ", out);
						String engineerID = (String) in.readObject();
						/**
						 * Check if the engineer is registered or not.
						 */
						boolean found = new EmployeeDetails().findDuplicateID(fileName, employee, engineerID);
						out.writeBoolean(found);
						out.flush();
						/**
						 * if engineer exists then change the status otherwise keep asking for the valid
						 * engineer.
						 */
						if (found) {
							status = "Assigned [ Engineer's ID : " + engineerID + " ]";
							System.out.println(status);
						} else {
							System.out.println("No Engineer Exists of ID : " + engineerID + ". Try Again");
							j--;
						}
					}
				} else if (option == 3) {
					status = "Closed";
					System.out.println(status);
				} else {
					System.out.println("Wrong input. Try Again.");
					i--;
				}
			}

			/**
			 * Create an object of new Bug on the bases of the information is taken above.
			 */
			bug = new Bug(id, name, time, platform, problemDesc, status);
			bugList = new ArrayList<Bug>();
			bugList.add(bug);

			/**
			 * Exist the loop
			 */
			this.exit = false;
		} while (exit);

		/**
		 * Write the object of the bug to the file.
		 */
		writer.writeBug(bugFile, true, bugList);
	}

	/**
	 * Generate the random and unique id for the bug.
	 * 
	 * @param bugFile where bugs are stored.
	 * @param bug     Bug class
	 * @return 6 digit Hexadecimal ID.
	 */
	public String generateID(String bugFile, Bug bug) {
		boolean found = false;
		String id = null;
		bugList = reader.readBugs(bugFile, bug);
		for (int i = 0; i < 1; i++) {
			String z = "000000";
			Random rand = new Random();
			/**
			 * Create Hex.
			 */
			id = Integer.toString(rand.nextInt(0X1000000), 16);
			id = z.substring(id.length()) + id;
			System.out.println(id);

			/**
			 * Check whether the id is unique or not if not then create a new one
			 */
			for (Bug b : bugList) {
				String bID = b.getId().trim();
				if (bID.equalsIgnoreCase(id)) {
					found = true;
					break;
				}
			}
			if (found) {
				i--;
			}
		}
		return id;
	}

	public void sendMessage(String message, ObjectOutputStream out) {
		try {
			out.writeObject(message);
			out.flush();
			System.out.println("Server Message -------> " + message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
