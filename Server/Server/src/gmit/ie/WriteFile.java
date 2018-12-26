package gmit.ie;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.*;

public class WriteFile {
	private Formatter formatter;
	private FileWriter fileWriter;

	public void writeEmployees(String fileName, Employee employee) {
		try {
			//comment added for test.
			fileWriter = new FileWriter(fileName, true);
			formatter = new Formatter(fileWriter);
			formatter.format("%s\n%s\n%s\n%s\n", employee.getName(), employee.getEmployeeID(),
					employee.getEmail(), employee.getDepartment());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			formatter.close();
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeBug(String bugFile, boolean append, List<Bug> list) {
		try {
			fileWriter = new FileWriter(bugFile, append);
			formatter = new Formatter(fileWriter);
			for (Bug bug : list) {
				formatter.format("%s\n%s\n%s\n%s\n%s\n%s\n", bug.getId(), bug.getName(),
						bug.getDateAndTime(), bug.getPlatform(), bug.getProblemDesc(), bug.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			formatter.close();
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
