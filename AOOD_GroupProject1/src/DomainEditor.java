import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DomainEditor extends JFrame {
	private ProblemSet currentSet;
	private Question currentQu;
	private JPanel panel;
	private JLabel nameLabel, promptLabel, extraLabel, answerLabel;
	private JTextField nameEntry, promptEntry, extraEntry, answerEntry;
	private JButton nameSubmit, promptSubmit, extraSubmit, answerSubmit, newQu, deleteQu; 
	public DomainEditor(){
		setSize(800, 450);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		nameLabel = new JLabel();
		promptLabel = new JLabel();
		extraLabel = new JLabel();
		answerLabel = new JLabel();
		panel = new JPanel();
		nameEntry = new JTextField();
		promptEntry = new JTextField();
		extraEntry = new JTextField();
		answerEntry = new JTextField();
		nameSubmit = new JButton("Change Name");
		promptSubmit = new JButton("Change Prompt");
		extraSubmit = new JButton("Change Extra Info");
		answerSubmit = new JButton("Change Answer");
		newQu = new JButton("New Question");
		deleteQu = new JButton("Delete Question");
		
		this.add(panel);
		//panel.setLayout(null);
		panel.add(nameEntry);
		nameEntry.setBounds(75, 50, 650, 25);
		panel.add(promptEntry);
		promptEntry.setBounds(75, 115, 650, 25);
		panel.add(extraEntry);
		extraEntry.setBounds(75, 180, 650, 25);
		panel.add(answerEntry);
		answerEntry.setBounds(75, 245, 650, 25);
		panel.add(nameSubmit);
		nameSubmit.setBounds(75, 75, 200, 40);
		panel.add(promptSubmit);
		promptSubmit.setBounds(75, 140, 200, 40);
		panel.add(extraSubmit);
		extraSubmit.setBounds(75, 205, 200, 40);
		panel.add(answerSubmit);
		answerSubmit.setBounds(75, 270, 200, 40);
		panel.add(newQu);
		newQu.setBounds(x, y, width, height);
		panel.add(deleteQu);
		deleteQu.setBounds(x, y, width, height);
		panel.add(nameLabel);
		nameLabel.setBounds(x, y, width, height);
		panel.add(promptLabel);
		promptLabel.setBounds(x, y, width, height);
		panel.add(extraLabel);
		extraLabel.setBounds(x, y, width, height);
		panel.add(answerLabel);
		answerLabel.setBounds(x, y, width, height);
		
		nameSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				submitName();
			}
		});
		promptSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				submitPrompt();
			}
		});
		extraSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				submitExtra();
			}
		});
		answerSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				submitAnswer();
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
	
	
	public void updateWindow(){
		nameLabel.setText("Set Name: " + currentSet.getName());
		promptLabel.setText("Current Question: " + currentQu.getPrompt());
		if(currentQu.getExtra() == null){
			extraLabel.setText("Current Extra Info: None");
		}
		else{
			extraLabel.setText("Current Extra Info: " + currentQu.getExtra());
		}
		
		answerLabel.setText("Current Answer: " + currentQu.getAns());
	}
	public void loadWindow(ProblemSet ps){
		currentSet = ps;
		currentQu = currentSet.getQuestionByIndex(0);
		setTitle("Editing: " + currentSet.getName());
		setVisible(true);
		nameLabel.setText("Set Name: " + ps.getName());
		promptLabel.setText("Current Question: " + currentQu.getPrompt());
		updateWindow();
		
		
	}
	public void submitName(){
		if(nameEntry.getText() != null){
			currentSet.setName(nameEntry.getText().trim());
			nameEntry.setText("");
		}
	}
	public void submitPrompt(){
		if(promptEntry.getText() != null){
			currentQu.setPrompt(promptEntry.getText().trim());
			promptEntry.setText("");
		}
	}
	public void submitExtra(){
		if(extraEntry.getText() != null){
			currentQu.setExtra(extraEntry.getText().trim());
			extraEntry.setText("");
		}
	}
	public void submitAnswer(){
		if(answerEntry.getText() != null){
			currentQu.setAnswer(answerEntry.getText().trim());
			answerEntry.setText("");
		}
	}
	public void createNewQuestion(){
		currentSet.addQuestion();
		currentQu = currentSet.getQuestionByIndex(currentSet.getLength()-1);
		updateWindow();
	}

}
