package gmit.ie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadFile {
	private Scanner read;
	private List<Employee> list;
	private List<Bug> bugList;

	public List<Employee> readEmployees(String fileName, Employee employee) {
		list=new ArrayList<Employee>();
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
	
	public List<Bug> readBugs(String bugFile,Bug bug){
		bugList=new ArrayList<Bug>();
		try {
			read = new Scanner(new File(bugFile));
			while (read.hasNext()) {
				bug=new Bug(read.nextLine(), read.nextLine(), read.nextLine(), read.nextLine(), read.nextLine(), read.nextLine());
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