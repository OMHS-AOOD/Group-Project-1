import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		
		db = new Database();
		mm = new MainMenu("Study Helper v1.0", this);
		ps = new ProblemStorage();
		ds = new DomainSelect("Select a domain", ps, this);
		de = new DomainEditor(ps);
		mh = new miniHUD();
		qw = new QuestionWindow(this, mh);
		us = new UserSelect("Select a user", db, this);
		
		currentUser = db.getUserByIndex(0);
		currentDomain = null;
		
		mh.setUser(currentUser.getName());
		mh.setDomain("None");
	}
	
	public void addNewUser(){
		String name = JOptionPane.showInputDialog("Enter a username: ");
		if(db.checkForUserName(name)){
			JOptionPane.showMessageDialog(null, "Username already taken", "New User" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(name == null){
			JOptionPane.showMessageDialog(null, "No name entered", "User Select" , JOptionPane.INFORMATION_MESSAGE);
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
		ds.setVisible(true);
	}
	
	public void setDomain(int i){
		currentDomain = ps.getPSByIndex(i);
		mh.setDomain(currentDomain.getName());
		ds.setVisible(false);
	}
	
	public void deleteUsers(){
		String check = JOptionPane.showInputDialog("Are you sure?(Y/N)").toUpperCase();
		if(check == null){
			return;
		}
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
			qw.loadWindow(currentDomain);
		}
		else{
			JOptionPane.showMessageDialog(null, "No problem set loaded", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void toggleMiniHUD(){
		mh.setVisible(!mh.isVisible());

	}
	public void reload(){
		qw.setVisible(false);
		mm.setVisible(true);

	}
	
}
