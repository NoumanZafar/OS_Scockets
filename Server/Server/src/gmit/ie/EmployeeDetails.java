package gmit.ie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDetails {
	/**
	 * Class attributes.
	 */
	private ReadFile emp;
	private List<Employee> list;

	/**
	 * Constructor Instantiate the objects.
	 */
	public EmployeeDetails() {
		super();
		emp = new ReadFile();
		list = new ArrayList<Employee>();
	}

	/**
	 * Get the data from file and send it over to client.
	 * 
	 * @param fileName text file where data was stored
	 * @param employee user/employee/engineer
	 * @param out      ObjectOutputStream.
	 * @throws IOException
	 */
	public void getEmployees(String fileName, Employee employee, ObjectOutputStream out) throws IOException {
		list = emp.readEmployees(fileName, employee);
		String message = Integer.toString(list.size());
		out.writeObject(message);
		out.flush();
		for (Employee e : list) {
			out.writeObject(e.getName());
			out.flush();

			out.writeObject(e.getEmployeeID());
			out.flush();

			out.writeObject(e.getEmail());
			out.flush();

			out.writeObject(e.getDepartment());
			out.flush();
		}
	}

	/**
	 * Find if there is email in existence already
	 * 
	 * @param fileName
	 * @param employee
	 * @param email
	 * @return True/false
	 */
	public boolean findDuplicateEmail(String fileName, Employee employee, String email) {
		list = emp.readEmployees(fileName, employee);
		for (Employee e : list) {
			String mail = e.getEmail().trim();
			if (mail.equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * find out if id is already there in the file or not.
	 * 
	 * @param fileName
	 * @param employee
	 * @param id
	 * @return true/false
	 */
	public boolean findDuplicateID(String fileName, Employee employee, String id) {
		list = emp.readEmployees(fileName, employee);
		for (Employee e : list) {
			String eID = e.getEmployeeID().trim();
			if (eID.equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}
}
