import javax.swing.*;

public class DomainEditor extends JFrame {
	private ProblemSet currentSet;
	private JPanel panel;
	private JTextField nameEntry, promptEntry, extraEntry, answerEntry;
	private JButton nameSubmit, promptSubmit, extraSubmit, answerSubmit, newQu, deleteQu; 
	public DomainEditor(){
		setSize(800, 450);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		panel = new JPanel();
		nameEntry = new JTextField();
		promptEntry = new JTextField();
		extraEntry = new JTextField();
		answerEntry = new JTextField();
		nameSubmit = new JButton();
		promptSubmit = new JButton("Enter");
		extraSubmit = new JButton("Enter");
		answerSubmit = new JButton("Enter");
		newQu = new JButton("New Question");
		deleteQu = new JButton("Delete Question");
		
		this.add(panel);
		
		
	}
	
	public void loadWindow(ProblemSet ps){
		currentSet = ps;
		setTitle("Editing: " + currentSet.getName());
		setVisible(true);
		
		
	}

}
