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
		nameEntry.setBounds(x, y, width, height);
		panel.add(promptEntry);
		promptEntry.setBounds(x, y, width, height);
		panel.add(extraEntry);
		extraEntry.setBounds(x, y, width, height);
		panel.add(answerEntry);
		answerEntry.setBounds(x, y, width, height);
		panel.add(nameSubmit);
		nameSubmit.setBounds(x, y, width, height);
		panel.add(promptSubmit);
		promptSubmit.setBounds(x, y, width, height);
		panel.add(extraSubmit);
		extraSubmit.setBounds(x, y, width, height);
		panel.add(answerSubmit);
		answerSubmit.setBounds(x, y, width, height);
		panel.add(newQu);
		newQu.setBounds(x, y, width, height);
		panel.add(deleteQu);
		deleteQu.setBounds(x, y, width, height);
		
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
