package gmit.ie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDetails {
	private ReadFile emp;
	private List<Employee> list;

	public EmployeeDetails() {
		super();
		emp = new ReadFile();
		list = new ArrayList<Employee>();
	}

	public void getEmployees(String fileName, Employee employee, ObjectOutputStream out) throws IOException {
		list = emp.readEmployees(fileName, employee);
		String message = Integer.toString(list.size());
		out.writeObject(message);
		out.flush();
		for(Employee e : list) {
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
	
	public boolean findDuplicateEmail(String fileName,Employee employee,String email) {
		list = emp.readEmployees(fileName, employee);
		for(Employee e : list) {
			String mail=e.getEmail().trim();
			if(mail.equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean findDuplicateID(String fileName,Employee employee,String id) {
		list = emp.readEmployees(fileName, employee);
		for(Employee e : list) {
			String eID=e.getEmployeeID().trim();
			if(eID.equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}
}
