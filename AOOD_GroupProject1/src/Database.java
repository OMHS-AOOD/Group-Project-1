import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			URL location = StudyHelper.class.getProtectionDomain().getCodeSource().getLocation();
			File f = new File(location.getPath().substring(0,  location.getPath().length()-4) + "src/Users");

		    try {
		    	
		    	FileOutputStream fos = new FileOutputStream(f);
		    	
			    ObjectOutputStream oos = new ObjectOutputStream(fos);
			    
			    User u = new User(n, p);
				users.add(u);
			    oos.writeObject(users);
				oos.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error when writing to users file", "Error" , JOptionPane.INFORMATION_MESSAGE);
			}

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
	    	addUser("Default", "");
	    	FileInputStream fis = new FileInputStream(f);
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    ArrayList<User> u = (ArrayList<User>) ois.readObject();
	        users = u;

			
		}
	    catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(null, "Error when trying to read users file", "Error" , JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace(System.out);
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
