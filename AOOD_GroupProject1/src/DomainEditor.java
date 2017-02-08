import javax.swing.JFrame;

public class DomainEditor extends JFrame {
	private ProblemSet currentSet;
	public DomainEditor(){
		setSize(800, 450);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void loadWindow(ProblemSet ps){
		currentSet = ps;
		setTitle("Editing: " + currentSet.getName());
		setVisible(true);
		
		
	}

}
