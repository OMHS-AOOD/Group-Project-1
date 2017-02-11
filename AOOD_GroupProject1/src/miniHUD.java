import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

public class miniHUD extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6325884092961918654L;
	private JPanel userPanel;
	private JLabel userDisplay, userDisplay2, quDisplay, quDisplay2;
	public miniHUD(){
		super("miniHUD");
		this.setSize(200, 100);
		this.setResizable(false);
		this.setLocation(800, 0);
		this.setVisible(true);
		userPanel = new JPanel();
		this.add(userPanel);
		userDisplay = new JLabel();
		userDisplay2 = new JLabel();
		quDisplay = new JLabel();
		quDisplay2 = new JLabel();
		userPanel.add(userDisplay);
		userPanel.add(userDisplay2);
		userPanel.add(quDisplay);
		userPanel.add(quDisplay2);
		userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
		
		
		
	}
	
	public void setUser(String user){
		userDisplay.setText("Current User: " + user);
	}
	public void setDomain(String dom){
		userDisplay2.setText("Current Problem set: " + dom);
	}
	
	public void setRight(int c){
		quDisplay.setText("# of Correct Answers: " + c);
	}
	public void setWrong(int w){
		quDisplay2.setText("# of Incorrect Answers: " + w);
	}
	public void emptyRight(){
		quDisplay.setText("");
	}
	public void emptyLeft(){
		quDisplay2.setText("");
	}
	
}
