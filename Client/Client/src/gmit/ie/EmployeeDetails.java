package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;

public class EmployeeDetails {

	public EmployeeDetails() {
		super();
	}

	public void getEmployees(ObjectInputStream in) throws NumberFormatException, ClassNotFoundException, IOException {
		int size = Integer.parseInt((String) in.readObject());
		for (int i = 0; i < size; i++) {
			System.out.println("Employee[\n\tName \t   = " + (String) in.readObject() + ",\n\tID \t   = " + (String) in.readObject()
					+ ",\n\tEmail \t   = " + (String) in.readObject() + ",\n\tDepartment = " + (String) in.readObject() + "\n\t]\n");
		}
	}
}
