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
	private JFrame userHud;
	private JPanel userPanel;
	private JLabel userDisplay, userDisplay2;
	private DomainSelect ds;
	private DomainEditor de;

	private ProblemStorage ps;
	public StudyHelper(){
		
		db = new Database();
		mm = new MainMenu("Study Helper v1.0", this);
		ps = new ProblemStorage();
		ds = new DomainSelect("Select a domain", ps);
		de = new DomainEditor(ps);
		
		
		userHud = new JFrame("miniHud");
		userHud.setSize(200, 100);
		userHud.setResizable(false);
		userHud.setLocation(800, 0);
		userHud.setVisible(true);
		userPanel = new JPanel();
		userHud.add(userPanel);
		userDisplay = new JLabel();
		userDisplay2 = new JLabel();
		userPanel.add(userDisplay);
		userPanel.add(userDisplay2);
		userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
		
		
		
		
		currentUser = db.getUserByIndex(0);
		currentDomain = null;
		
		userDisplay.setText("Current User: " + currentUser.getName());
		userDisplay2.setText("Current Problem set: " + "None");
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
	}
	
	public void showUsers(){
		String output = "The current users are: \n" ;
		for(User u: db.getUserArray()){
			output += u.getName() + "\n";
		}
		JOptionPane.showMessageDialog(null, output, "User List" , JOptionPane.INFORMATION_MESSAGE);
	}
	public void selectUser(){
		String name = JOptionPane.showInputDialog("Enter a username: ");
		if(!db.checkForUserName(name)){
			JOptionPane.showMessageDialog(null, "No user with that name", "User Select" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		User u = db.getUserByName(name);
		String password = JOptionPane.showInputDialog("Enter your password: ");
		if(!password.equals(u.getPassword())){
			JOptionPane.showMessageDialog(null, "Incorrect Password", "User Select" , JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		currentUser = u;
		userDisplay.setText("Current User: " + currentUser.getName());
	}
	public void selectDomain(){
		ds.setVisible(true);
	}
}
