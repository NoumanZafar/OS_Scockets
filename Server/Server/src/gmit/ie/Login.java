package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Login {
	private ReadFile emp;
	private List<Employee> list;
	private boolean found = false;

	public Login() {
		super();
		emp = new ReadFile();
		list = new ArrayList<Employee>();
	}

	public boolean login(String fileName, Employee employee, ObjectOutputStream out, ObjectInputStream in)
			throws ClassNotFoundException, IOException {
		list = emp.readEmployees(fileName, employee);

		sendMessage("Employee ID : ", out);
		String id = (String) in.readObject();
		System.out.println(id);

		sendMessage("Employee Email : ", out);
		String email = (String) in.readObject();
		System.out.println(email);

		for (Employee e : list) {
			String eEmail = e.getEmail().trim();
			String eID = e.getEmployeeID().trim();
			if (email.equalsIgnoreCase(eEmail) && id.equalsIgnoreCase(eID)) {
				this.found = true;
				break;
			}
		}

		out.writeBoolean(found);
		out.flush();
		if (found == false) {
			System.out.println("Wrong ID or Email. Try Again.");
		}
		return found;
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