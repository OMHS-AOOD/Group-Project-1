import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOException;
import javax.swing.JOptionPane;

public class Database {
	private ArrayList<User> users;
	public Database(){
		users = new ArrayList<User>();
		users.add(new User("Default", ""));
		getUsersFromFile();
	}
	public void addUser(String n){
		if(!checkForUserName(n)){
			users.add(new User(n));
		}
		else{
			JOptionPane.showMessageDialog(null, "User already exists", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	public void addUser(String n, String p){
		if(!checkForUserName(n)){
			users.add(new User(n, p));
		}
		else{
			JOptionPane.showMessageDialog(null, "User already exists", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	public void remove(User u){
		//Fill later
	}
	public void getUsersFromFile(){

		URL location = StudyHelper.class.getProtectionDomain().getCodeSource().getLocation();
		File f = new File(location.getPath().substring(0,  location.getPath().length()-4) + "src/Users");
		try {
			List<String> fileLines = Files.readAllLines(f.toPath(), Charset.defaultCharset());
			int x = 0;
			while(!fileLines.get(x).equals("START")){
				x++;
			}
			String username = "";
			for(int i = x; i < fileLines.size(); i++){
				String line = fileLines.get(i);
				String prefix = line.substring(0, 2);
				String data = line.substring(3);
				
				if(prefix.equals("UN")){
					addUser(data);
					username = data;
				}
				else if(prefix.equals("PS")){
					User u = getUserByName(username);
					u.setPassword(data);
				}
			}
		}
		 catch (IOException e) {
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
	public int getUserIndexByName(String n){
		for(int i = 0; i < users.size(); i++){
			User u = users.get(i);
			if(u.getName().equals(n)){
				return i;
			}
		}
		return -1;
	}
}
