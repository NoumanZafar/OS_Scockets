package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ViewBugs {

	public ViewBugs() {
		super();
	}

	public void getAllBugs(ObjectInputStream in) throws NumberFormatException, ClassNotFoundException, IOException {
		int size = Integer.parseInt((String) in.readObject());
		if(size>0) {
		for (int i = 0; i < size; i++) {
			System.out.println("Bug Details[\n\tID \t    = " + (String) in.readObject() + "\n\tName \t    = "
					+ (String) in.readObject() + "\n\tTime \t    = " + (String) in.readObject() + "\n\tPlatform    = "
					+ (String) in.readObject() + "\n\tDescription = " + (String) in.readObject() + "\n\tStatus \t    = "
					+ (String) in.readObject() + "\n\t]\n");
		}
		}else {
			System.out.println("There is no bug which is not Assigned.");
		}
	}
}
