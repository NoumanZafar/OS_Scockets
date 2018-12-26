package gmit.ie;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.*;

public class WriteFile {
	private Formatter formatter;
	private FileWriter fileWriter;

	/**
	 * Write the data to the text file. Data is written to the file in appending
	 * fashion. that means every single time file is not created but data is written
	 * to the same file.
	 * 
	 * @param fileName name of the file
	 * @param employee (Object) data needs to be stored
	 */
	public synchronized void writeEmployees(String fileName, Employee employee) {
		try {
			fileWriter = new FileWriter(fileName, true);
			formatter = new Formatter(fileWriter);
			formatter.format("%s\n%s\n%s\n%s\n", employee.getName(), employee.getEmployeeID(), employee.getEmail(),
					employee.getDepartment());
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

	/**
	 * This method write the bugs to the file.
	 * 
	 * @param bugFile text file where needs to be stored.
	 * @param append  whether create a new file or write to the existing file and
	 *                append the new data at the end.
	 * @param list    List of bug (Objects).
	 */
	public synchronized void writeBug(String bugFile, boolean append, List<Bug> list) {
		try {
			fileWriter = new FileWriter(bugFile, append);
			formatter = new Formatter(fileWriter);
			for (Bug bug : list) {
				formatter.format("%s\n%s\n%s\n%s\n%s\n%s\n", bug.getId(), bug.getName(), bug.getDateAndTime(),
						bug.getPlatform(), bug.getProblemDesc(), bug.getStatus());
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
