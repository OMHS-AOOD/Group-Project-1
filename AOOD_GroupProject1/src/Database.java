
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import java.util.ArrayList;



import javax.swing.JOptionPane;

public class Database {
	private URL location;
	private File f;
	private ArrayList<User> users;

	public Database() {
		location = StudyHelper.class.getProtectionDomain().getCodeSource().getLocation();
		f = new File(location.getPath().substring(0, location.getPath().length() - 4) + "src/Users");

		users = new ArrayList<User>();
		getUsersFromFile();
	}

	public void addUser(String n) {
		if (!checkForUserName(n)) {			
			try {

				FileOutputStream fos = new FileOutputStream(f);

				ObjectOutputStream oos = new ObjectOutputStream(fos);

				User u = new User(n);
				users.add(u);
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

				User u = new User(n, p);
				users.add(u);
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

	public void remove(User u) {
		// Fill later
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
		return new User("Error", "");
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
		try {
			users = new ArrayList<User>();
			addUser("Default", "");
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error when writing to users file", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void deleteUser(int i) {
		try {
			users.remove(i);
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error when writing to users file", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}
}
