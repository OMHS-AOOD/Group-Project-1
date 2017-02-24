
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Database {
	private URL location;
	private File f, f2;
	private ArrayList<User> users;
	private ProblemStorage ps;
	private HashMap<String, UserProblemStorage> userData;

	public Database(ProblemStorage p) {
		location = StudyHelper.class.getProtectionDomain().getCodeSource().getLocation();
		f = new File(location.getPath().substring(0, location.getPath().length() - 4) + "src/Users");
		f2 = new File(location.getPath().substring(0, location.getPath().length() - 4) + "src/UserData");
		ps = p;
		users = new ArrayList<User>();
		userData = new HashMap<String, UserProblemStorage>();
		getUsersFromFile();
		getUserDataFromFile();
	}

	public void getUsersFromFile() {

		try {

			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<User> u = (ArrayList<User>) ois.readObject();
			users = u;
			ois.close();

		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(null, "Error when trying to read users file", "Error",
					JOptionPane.INFORMATION_MESSAGE);
			this.addUser("Default");

		}

	}

	private void getUserDataFromFile() {
		try {

			FileInputStream fis = new FileInputStream(f2);
			ObjectInputStream ois = new ObjectInputStream(fis);
			HashMap<String, UserProblemStorage> u = (HashMap<String, UserProblemStorage>) ois.readObject();
			userData = u;
			ois.close();

		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(null, "Error when trying to read user data file", "Error",
					JOptionPane.INFORMATION_MESSAGE);
			if (users.size() == 1) {
				userData.put("Default", new UserProblemStorage(ps));
				updateFileData();
			}
		}

	}

	public void addUser(String n) {
		if (!checkForUserName(n)) {
			try {

				FileOutputStream fos = new FileOutputStream(f);

				ObjectOutputStream oos = new ObjectOutputStream(fos);

				User u = new User(n, ps);
				users.add(u);
				userData.put(n, new UserProblemStorage(ps));
				oos.writeObject(users);
				oos.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error when writing to users file", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(null, "User already exists", "Error", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void addUser(String n, String p) {
		if (!checkForUserName(n)) {

			try {

				FileOutputStream fos = new FileOutputStream(f);

				ObjectOutputStream oos = new ObjectOutputStream(fos);

				User u = new User(n, p, ps);
				users.add(u);
				userData.put(n, new UserProblemStorage(ps));
				oos.writeObject(users);
				oos.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error when writing to users file", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(null, "User already exists", "Error", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public User getUserByIndex(int i) {
		return users.get(i);
	}

	public boolean checkForUserName(String name) {
		for (User u : users) {
			if (u.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<User> getUserArray() {
		return users;
	}

	public User getUserByName(String n) {
		for (User u : users) {
			if (u.getName().equals(n)) {
				return u;
			}
		}
		return new User("Error", "", ps);
	}

	public int getUserIndexByName(String n) {
		for (int i = 0; i < users.size(); i++) {
			User u = users.get(i);
			if (u.getName().equals(n)) {
				return i;
			}
		}
		return -1;
	}

	public void resetUsers() {

		users = new ArrayList<User>();
		userData = new HashMap<String, UserProblemStorage>();
		addUser("Default", "");
		userData.put("Default", new UserProblemStorage(ps));
		this.updateFile();
		this.updateFileData();

	}

	public void deleteUser(int i) {

		User u = users.remove(i);
		userData.remove(u.getName());

		this.updateFile();
		this.updateFileData();

	}

	public void updateFile() {
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error when writing to user file", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void updateFileData() {
		try {
			FileOutputStream fos = new FileOutputStream(f2);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userData);
			oos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error when writing to user data file", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public int size() {

		return users.size();
	}

	public HashMap<String, UserProblemStorage> getData() {
		return userData;
	}

}
