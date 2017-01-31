import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class StudyHelper {
	private MainMenu mm;
	private User currentUser;
	private Database db;
	private JFrame userHud;
	private JLabel userDisplay;
	public StudyHelper(){
		
		db = new Database();
		mm = new MainMenu("Study Helper v1.0", this);
		
		userHud = new JFrame("miniHud");
		userHud.setSize(200, 100);
		userHud.setResizable(false);
		userHud.setLocation(800, 0);
		userHud.setUndecorated(true);
		userHud.setVisible(true);
		userDisplay = new JLabel();
		userHud.add(userDisplay);
		
		
		currentUser = db.getUserByIndex(0);
		userDisplay.setText("Current User: " + currentUser.getName());
	}
	
	public void addNewUser(){
		String name = JOptionPane.showInputDialog("Enter a username: ");
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
		db.addUser(new User(name, password));
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
	
}
