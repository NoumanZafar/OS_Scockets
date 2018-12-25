package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Options {
	private int option;
	private boolean exit = true;
	private EmployeeDetails empDetails;
	private AddBug addBug;
	private BugAssignment bugAsi;
	private ViewBugs viewBugs;
	private UpdateBug updateBug;

	public Options() {
		super();
		empDetails = new EmployeeDetails();
		addBug = new AddBug();
		bugAsi = new BugAssignment();
		viewBugs = new ViewBugs();
		updateBug=new UpdateBug();
	}

	public void selectOption(ObjectInputStream in, ObjectOutputStream out, String fileName, Employee employee, Bug bug,
			String bugFile) throws NumberFormatException, ClassNotFoundException, IOException {
		do {
			sendMessage(
					"Select one of the following options.\n\t1. All Employees\n\t2. Add a bug\n\t3. Assign a bug\n\t4. View All bugs (Not Assigned to Developer)\n\t5. View All bugs\n\t6. Update a bug\n\t7. Exit",
					out);
			option = Integer.parseInt((String) in.readObject());
			if (option == 1) {
				empDetails.getEmployees(fileName, employee, out);
			} else if (option == 2) {
				addBug.addBug(out, in, employee, fileName, bug, bugFile);
			} else if (option == 3) {
				System.out.println("Assign a bug");
				bugAsi.assignToEngineer(fileName, employee, bugFile, bug, out, in);
			} else if (option == 4) {
				System.out.println("All bugs (Not Assigned)");
				viewBugs.getBugsNotAssigned(bugFile, bug, in, out);
			} else if (option == 5) {
				System.out.println("All bugs");
				viewBugs.getAllBugs(bugFile, bug, in, out);
			} else if (option == 6) {
				System.out.println("Update a bug");
				updateBug.update(out, in, bugFile, bug,fileName,employee);
			} else if (option == 7) {
				this.exit = false;
			} else {
				System.out.println("WRONG INPUT ------> TRY AGAIN.");
			}
		} while (exit);
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