package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BugAssignment {
	private List<Employee> eList;
	private List<Bug> bugList;
	private ReadFile reader;
	private boolean eFound = false;
	private boolean bFound = false;
	private String id;
	private int index=-1;

	public BugAssignment() {
		super();
		eList = new ArrayList<Employee>();
		bugList = new ArrayList<Bug>();
		reader = new ReadFile();
	}

	public void assignToEngineer(String fileName, Employee employee, String bugFile, Bug bug, ObjectOutputStream out,
			ObjectInputStream in) throws ClassNotFoundException, IOException {
		eList = reader.readEmployees(fileName, employee);
		bugList = reader.readBugs(bugFile, bug);

		for (int i = 0; i < 1; i++) {
			sendMessage("Enter Engineer ID : ", out);
			String eID = (String) in.readObject();
			System.out.println(eID);

			for (Employee e : eList) {
				id = e.getEmployeeID().trim();
				if (id.equalsIgnoreCase(eID)) {
					this.eFound = true;
					break;
				}
			}

			out.writeBoolean(eFound);
			out.flush();

			if (eFound == false) {
				System.out.println("Engineer does not exist.");
				i--;
			}
		}

		for (int j = 0; j < 1; j++) {
			sendMessage("Enter Bug ID : ", out);
			String bugID = (String) in.readObject();
			System.out.println(bugID);

			for (Bug b : bugList) {
				String bID = b.getId().trim();
				this.index++;
				if (bID.equalsIgnoreCase(bugID)) {
					this.bFound = true;
					b.setStatus("Assigned [ Engineer's ID : " + id + " ]");
					bugList.set(this.index, b);
					break;
				}
			}

			out.writeBoolean(bFound);
			out.flush();
			if (bFound) {
				new WriteFile().writeBug(bugFile, false, bugList);
			} else {
				System.out.println("Bug does not exists.");
				j--;
			}
		}
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
