import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		nameSubmit = new JButton("Enter");
		promptSubmit = new JButton("Enter");
		extraSubmit = new JButton("Enter");
		answerSubmit = new JButton("Enter");
		newQu = new JButton("New Question");
		deleteQu = new JButton("Delete Question");
		
		this.add(panel);
		
		panel.add(nameEntry);
		panel.add(promptEntry);
		panel.add(extraEntry);
		panel.add(answerEntry);
		panel.add(nameSubmit);
		panel.add(promptSubmit);
		panel.add(extraSubmit);
		panel.add(answerSubmit);
		panel.add(newQu);
		panel.add(deleteQu);
		
		nameSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		promptSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		extraSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		answerSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		newQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		deleteQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
	}
	
	public void loadWindow(ProblemSet ps){
		currentSet = ps;
		setTitle("Editing: " + currentSet.getName());
		setVisible(true);
		
		
	}

}
