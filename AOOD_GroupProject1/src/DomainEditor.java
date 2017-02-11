import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class DomainEditor extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2267435614024328026L;
	private ProblemSet currentSet;
	private StudyHelper sh;
	private Question currentQu;
	private ProblemStorage ps;
	private JPanel panel;
	private JLabel nameLabel, promptLabel, extraLabel, answerLabel;
	private JTextField nameEntry, promptEntry, extraEntry, answerEntry;
	private JButton nameSubmit, promptSubmit, extraSubmit, answerSubmit, newQu, deleteQu, nextQu, lastQu, finish; 
	private int qIndex;
	public DomainEditor(ProblemStorage p, StudyHelper s){
		setSize(900, 350);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		sh =s;
		ps = p;
		qIndex = 0;
		
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
		nextQu = new JButton(">>");
		lastQu = new JButton("<<");
		finish = new JButton("Close Editor");
		
		this.add(panel);
		panel.setLayout(null);
		panel.add(nameEntry);
		nameEntry.setBounds(10, 35, 650, 25);
		panel.add(promptEntry);
		promptEntry.setBounds(10, 85, 650, 25);
		panel.add(extraEntry);
		extraEntry.setBounds(10, 135, 650, 25);
		panel.add(answerEntry);
		answerEntry.setBounds(10, 185, 650, 25);
		panel.add(nameSubmit);
		nameSubmit.setBounds(675, 30, 150, 30);
		panel.add(promptSubmit);
		promptSubmit.setBounds(675, 80, 150, 30);
		panel.add(extraSubmit);
		extraSubmit.setBounds(675, 130, 150, 30);
		panel.add(answerSubmit);
		answerSubmit.setBounds(675, 180, 150, 30);
		panel.add(newQu);
		newQu.setBounds(10, 280, 150, 30);
		panel.add(deleteQu);
		deleteQu.setBounds(170, 280, 150, 30);
		panel.add(nameLabel);
		nameLabel.setBounds(10, 10, 650, 25);
		panel.add(promptLabel);
		promptLabel.setBounds(10, 60, 650, 25);
		panel.add(extraLabel);
		extraLabel.setBounds(10, 110, 650, 25);
		panel.add(answerLabel);
		answerLabel.setBounds(10, 160, 650, 25);
		panel.add(nextQu);
		nextQu.setBounds(170, 230, 150, 30);
		panel.add(lastQu);
		lastQu.setBounds(10, 230, 150, 30);
		panel.add(finish);
		finish.setBounds(725, 280, 150, 30);
		
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
				createNewQuestion();
			}
		});
		deleteQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String check = JOptionPane.showInputDialog("Are you sure?(Y/N)");
				if(check == null){
					return;
				}
				check = check.toUpperCase();
				if(check.equals("Y")){
					deleteQu();
				}
			}
		});
		nextQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				nextQuestion();
			}
		});
		lastQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				lastQuestion();
			}
		});
		finish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				closeEditor();
			}
		});
	}
	
	
	public void updateWindow(){
		nameLabel.setText("Set Name: " + currentSet.getName());
		setTitle("Editing: " + currentSet.getName());
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
		setLocation(0, 0);
		currentSet = ps;
		currentQu = currentSet.getQuestionByIndex(qIndex);
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
			ps.updateFile();
			updateWindow();
		}
	}
	public void submitPrompt(){
		if(promptEntry.getText() != null){
			currentQu.setPrompt(promptEntry.getText().trim());
			promptEntry.setText("");
			ps.updateFile();
			updateWindow();
		}
	}
	public void submitExtra(){
		if(extraEntry.getText() != null){
			currentQu.setExtra(extraEntry.getText().trim());
			extraEntry.setText("");
			ps.updateFile();
			updateWindow();
		}
	}
	public void submitAnswer(){
		if(answerEntry.getText() != null){
			currentQu.setAnswer(answerEntry.getText().trim());
			answerEntry.setText("");
			ps.updateFile();
			updateWindow();
		}
	}
	public void createNewQuestion(){
		currentSet.addQuestion();
		currentQu = currentSet.getQuestionByIndex(currentSet.getLength()-1);
		updateWindow();
		ps.updateFile();
	}
	
	public void nextQuestion(){
		qIndex++;
		if(qIndex == currentSet.getLength()){
			qIndex = 0;
		}
		currentQu = currentSet.getQuestionByIndex(qIndex);
		updateWindow();
	}
	public void lastQuestion(){
		qIndex--;
		if(qIndex < 0){
			qIndex = currentSet.getLength()-1;
		}
		currentQu = currentSet.getQuestionByIndex(qIndex);
		updateWindow();
	}
	public void deleteQu(){
		currentSet.deleteQuestion(qIndex);
		qIndex = 0;
		currentQu = currentSet.getQuestionByIndex(qIndex);
		ps.updateFile();
		updateWindow();
	}
	public void closeEditor(){
		currentSet = null;
		currentQu = null;
		sh.reload();
	}
	/*
	public void exportProblemSet(){
		try {
	    	FileOutputStream fos = new FileOutputStream(f);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(problems);
			oos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error when writing to problems file", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	*/
	public void importProblemSet(){
		
	}

}
