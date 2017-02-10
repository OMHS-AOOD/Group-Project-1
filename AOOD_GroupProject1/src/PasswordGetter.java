import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PasswordGetter extends JFrame {
	private JPanel panel;
	private JPasswordField passField;
	private JLabel prompt;
	private JButton enter;
	private JButton cancel;
	private StudyHelper sh;
	public PasswordGetter(StudyHelper s){
		sh = s;
		setVisible(true);
		setResizable(false);
		setSize(300, 100);
		enter = new JButton("OK");
		cancel = new JButton("Cancel");
		setLocation(800-150, 450-50);
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		panel.add(prompt);
		panel.add(passField);
		panel.add(enter);
		panel.add(cancel);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				
			}
		});
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				
				
			}
		});
	}
	
	public String getPass(){
		char[] out = passField.getPassword();
		passField.setText("");
		String pass = String.copyValueOf(out);
		out = null;
		
		return pass;
	}
	
}
