package gmit.ie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadFile {
	/**
	 * This class reads the files and store the data (Objects) in the respective
	 * lists.
	 */
	private Scanner read;
	private List<Employee> list;
	private List<Bug> bugList;

	/**
	 * Read the Employees from file Using scanner.
	 * 
	 * @param fileName employees.txt
	 * @param employee Employee class
	 * @return list of objects
	 */
	public List<Employee> readEmployees(String fileName, Employee employee) {
		list = new ArrayList<Employee>();
		try {
			read = new Scanner(new File(fileName));
			while (read.hasNext()) {
				employee = new Employee(read.nextLine(), read.nextLine(), read.nextLine(), read.nextLine());
				list.add(employee);
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error -------> File Does not EXIST.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Read the bug file using scanner class.
	 * 
	 * @param bugFile bugFile.txt
	 * @param bug     Bug class
	 * @return List of bug objects from file.
	 */
	public List<Bug> readBugs(String bugFile, Bug bug) {
		bugList = new ArrayList<Bug>();
		try {
			read = new Scanner(new File(bugFile));
			while (read.hasNext()) {
				bug = new Bug(read.nextLine(), read.nextLine(), read.nextLine(), read.nextLine(), read.nextLine(),
						read.nextLine());
				bugList.add(bug);
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error -------> File Does not EXIST.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bugList;
	}
}