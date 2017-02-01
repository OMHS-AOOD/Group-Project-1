import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Database {
	private ArrayList<User> users;
	public Database(){
		users = new ArrayList<User>();
		users.add(new User("Default", ""));
		getUsersFromFile();
	}
	public void addUser(User u){
		users.add(u);
	}
	public void remove(User u){
		//Fill later
	}
	public void getUsersFromFile(){
		URL location = StudyHelper.class.getProtectionDomain().getCodeSource().getLocation();
		File f = new File(location.getPath().substring(0,  location.getPath().length()-4) + "src/Users");
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "User database file not found", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	public User getUserByIndex(int i){
		return users.get(i);
	}
	public boolean checkForUserName(String name){
		for(User u: users){
			if(u.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	public ArrayList<User> getUserArray(){
		return users;
	}
	
	public User getUserByName(String n){
		for(User u: users){
			if(u.getName().equals(n)){
				return u;
			}
		}
		return new User("Error", "");
	}
}
