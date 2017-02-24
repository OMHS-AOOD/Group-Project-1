
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class StudyHelper {
	private MainMenu mm;
	private User currentUser;
	private ProblemSet currentDomain;
	private Database db;
	private DomainSelect ds;
	private DomainEditor de;
	private QuestionWindow qw;
	private miniHUD mh;
	private ProblemStorage ps;
	private UserSelect us;
	

	
	public StudyHelper(){
		
		
		mm = new MainMenu("Study Helper v1.0", this);
		ps = new ProblemStorage();
		ds = new DomainSelect("Select a domain", ps, this);
		db = new Database(ps);
		ps.loadDb(db);
		de = new DomainEditor(ps, this, db);
		mh = new miniHUD();
		qw = new QuestionWindow(this, mh, ps, db);
		us = new UserSelect("Select a user", db, this);
		
		currentUser = db.getUserByIndex(0);
		currentDomain = null;
		
		mh.setUser(currentUser.getName());
		mh.setDomain("None");
		mh.setRand(currentUser.getRandomize());
		
	}
	
	public void addNewUser(){
		String name = JOptionPane.showInputDialog("Enter a username: ");
		if(name == null){
			JOptionPane.showMessageDialog(null, "No name entered", "User Select" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		name = name.trim();
		if(db.checkForUserName(name)){
			JOptionPane.showMessageDialog(null, "Username already taken", "New User" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		String password = JOptionPane.showInputDialog("Enter a password: ");
		String passCheck = JOptionPane.showInputDialog("Re-enter the password: ");
		if(!password.equals(passCheck)){
			JOptionPane.showMessageDialog(null, "Passwords do not match", "New User" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		db.addUser(name, password);
		us.updateList();
		
	}
	
	public void showUsersWindow(){
		us.setLocation(0, 0);
		us.setVisible(true);
	}
	public void selectUser(int i){
		User u = db.getUserByIndex(i);
		if(i == 0){
			currentUser = u;
			mh.setUser(currentUser.getName());
			us.setVisible(false);
			return;
		}
		String password = JOptionPane.showInputDialog("Enter your password: ");
		if(password == null || !password.equals(u.getPassword())){
			JOptionPane.showMessageDialog(null, "Incorrect Password", "User Select" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		currentUser = u;
		mh.setUser(currentUser.getName());
		us.setVisible(false);
	}
	public void selectDomain(){
		ds.setLocation(0, 0);
		ds.setVisible(true);
	}
	
	public void setDomain(int i){
		currentDomain = ps.getPSByIndex(i);
		mh.setDomain(currentDomain.getName());
		ds.setVisible(false);
	}
	
	public void deleteUsers(){
		String check = JOptionPane.showInputDialog("Are you sure?(Y/N)");
		if(check == null){
			return;
		}
		check = check.toUpperCase();
		if(check.equals("Y")){
			db.resetUsers();
			currentUser = db.getUserByIndex(0);
			mh.setUser(currentUser.getName());
			us.updateList();
		}
	}
	public void startProblems(){
		if(currentDomain != null){
			mm.setVisible(false);
			us.setVisible(false);
			ds.setVisible(false);
			mh.setVisible(false);
			qw.loadWindow(currentDomain, currentUser.getRandomize(), currentUser);
		}
		else{
			JOptionPane.showMessageDialog(null, "No problem set loaded", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void toggleMiniHUD(){
		mh.setVisible(!mh.isVisible());

	}
	public void reload(ProblemSet cS){
		currentDomain = cS;
		mm.setVisible(true);
		us.setVisible(false);
		ds.setVisible(false);
		mh.setVisible(false);
		de.setVisible(false);
		qw.setVisible(false);
		ds.updateList();
		us.updateList();
		mm.setLocation(0, 0);
		mh.setUser(currentUser.getName());
		mh.setDomain(currentDomain.getName());
		mh.setLocation(800, 0);
		mh.setVisible(true);

	}
	
	public void deleteDomain(int i){
		String check = JOptionPane.showInputDialog("Are you sure?(Y/N)");	
		if(check == null){
			return;
		}
		check = check.toUpperCase();
		if(check.equals("Y")){
			ps.removeDomain(i);
			currentDomain = null;
			mh.setDomain("None");
		}
	}
	public void deleteUser(int i){
		if(i == 0){
			JOptionPane.showMessageDialog(null, "Can't delete default user", "Error" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String check = JOptionPane.showInputDialog("Are you sure? Please enter your password.");
		if(check == null){
			return;
		}
		if(check.equals(db.getUserByIndex(i).getPassword())){
			db.deleteUser(i);
			currentUser = db.getUserByIndex(0);
			mh.setUser(currentUser.getName());
		}
	}
	public void startEditor(){
		if(currentDomain != null){
			mm.setVisible(false);
			us.setVisible(false);
			ds.setVisible(false);
			mh.setVisible(false);
			de.loadWindow(currentDomain);
			
		}
		else{
			JOptionPane.showMessageDialog(null, "No problem set loaded", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void newDomain(){
		String name = JOptionPane.showInputDialog("Enter a domain name: ");
		if(name == null){
			JOptionPane.showMessageDialog(null, "No name entered", "Error" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		name = name.trim();
		ps.addProblemSet(name, currentUser.getName());
		ds.updateList();
		de.loadWindow(ps.getPSByIndex(ps.getLength()-1));
		de.updateWindow();
		
	}
	
	public void importProblemSet(){
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select a problem set");
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Problem Set Files(.shps)", "shps");
		jfc.setFileFilter(filter);
		int check = jfc.showOpenDialog(new JFrame());

		if(check == JFileChooser.APPROVE_OPTION) {
			File f = jfc.getSelectedFile();
			try {
		    	FileInputStream fis = new FileInputStream(f);
			    ObjectInputStream ois = new ObjectInputStream(fis);
			    ProblemSet p = (ProblemSet) ois.readObject();
		        ps.addProblemSet(p);
		        for(User u: db.getUserArray()){
		        	db.getData().get(u.getName()).addSet(p);
		        }
		        ds.updateList();
		        ois.close();
			}
		    catch (ClassNotFoundException | IOException e) {
				JOptionPane.showMessageDialog(null, "Error when trying to read problems file", "Error" , JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
	}

	public void toggleUserRandomization() {
		currentUser.toggleRandomize();
		mh.setRand(currentUser.getRandomize());
		db.updateFile();
	}

	public void changeUsername() {
		if(currentUser.getName().equals("Default")){
			JOptionPane.showMessageDialog(null, "Can't edit default user", "Change Password" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String newName = JOptionPane.showInputDialog("Enter a new username: ");
		if(newName == null ||db.checkForUserName(newName)){
			JOptionPane.showMessageDialog(null, "Username is invalid/already taken", "Change Username" , JOptionPane.INFORMATION_MESSAGE);
		}
		UserProblemStorage temp = db.getData().get(currentUser.getName());
		db.getData().remove(currentUser.getName());
		currentUser.setName(newName);
		db.getData().put(newName, temp);
		db.updateFileData();
		mh.setUser(currentUser.getName());
	}
	
	public void changePassword(){
		if(currentUser.getName().equals("Default")){
			JOptionPane.showMessageDialog(null, "Can't edit default user", "Change Password" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String oldPass = JOptionPane.showInputDialog("Enter your current password: ");
		if(oldPass == null || !oldPass.equals(currentUser.getPassword())){
			JOptionPane.showMessageDialog(null, "Incorrect Password", "Change Password" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String password = JOptionPane.showInputDialog("Enter a password: ");
		String passCheck = JOptionPane.showInputDialog("Re-enter the password: ");
		if(!password.equals(passCheck)){
			JOptionPane.showMessageDialog(null, "Passwords do not match", "Change Password" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		currentUser.setPassword(password);
	}

	public User getCurrentUser(){
		return currentUser;
	}
	
}
