package gmit.ie;

public class Employee {

	/**
	 * Employee /Engineer /User attributes.
	 */
	private String name;
	private String employeeID;
	private String email;
	private String department;

	public Employee() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param employeeID
	 * @param email
	 * @param department
	 */
	public Employee(String name, String employeeID, String email, String department) {
		super();
		this.name = name;
		this.employeeID = employeeID;
		this.email = email;
		this.department = department;
	}

	/**
	 * Getters and Setters for the attributes.
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
