import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private JMenuBar jmb;
	private JMenu m1;
	private JFrame picDisplay;
	private JLabel nameLabel, promptLabel, extraLabel, answerLabel, attemptLabel, correctLabel;
	private JTextField nameEntry, promptEntry, extraEntry, answerEntry, editAttempts, editRight;
	private JButton nameSubmit, promptSubmit, extraSubmit, answerSubmit, newQu, deleteQu, nextQu, lastQu, finish, export, selIm, remImg, prevImg, submitAttempts, submitRight;
	private JLabel imgDisplay;
	private JMenuItem selQu; 
	private int qIndex;
	private User currentUser;
	private QuestionSelect qs;
	public DomainEditor(ProblemStorage p, StudyHelper s){
		setSize(900, 370);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		sh =s;
		ps = p;
		qIndex = 0;
		
		
		jmb = new JMenuBar();
		nameLabel = new JLabel();
		promptLabel = new JLabel();
		extraLabel = new JLabel();
		answerLabel = new JLabel();
		imgDisplay = new JLabel();
		correctLabel = new JLabel();
		attemptLabel = new JLabel();
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
		export = new JButton("Export Set");
		selQu = new JMenuItem("Select Question");
		selIm = new JButton("Select Image");
		remImg = new JButton("Remove Image");
		prevImg = new JButton("Preview Image");
		
		m1 = new JMenu("Options");
		qs = new QuestionSelect(this);
		
		this.setJMenuBar(jmb);
		jmb.add(m1);
		m1.add(selQu);
		
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
		panel.add(export);
		export.setBounds(330, 280, 150, 30);
		panel.add(selIm);
		selIm.setBounds(330, 230, 150, 30);
		panel.add(prevImg);
		prevImg.setBounds(490, 230, 150, 30);
		panel.add(remImg);
		remImg.setBounds(490, 280, 150, 30);
		
		panel.add(attemptLabel);
		attemptLabel.setBounds(675, 220, 150, 30);
		panel.add(correctLabel);
		correctLabel.setBounds(675, 250, 150, 30);
		
		
		
		picDisplay = new JFrame("Image Display");
		picDisplay.setVisible(false);
		picDisplay.add(imgDisplay);
		
		
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
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				exportProblemSet();
			}
		});
		selQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				showQuestionWindow();
			}

			
		});
		selIm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				getImage();
			}

			
		});
		remImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				removeImg();
			}

			
		});
		prevImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				previewImg();
			}

			
		});
	}
	
	
	public void previewImg(){
		picDisplay.setLocation(0,0);
		picDisplay.setSize(currentQu.getImage().getIconWidth(), currentQu.getImage().getIconHeight());
		picDisplay.setVisible(true);
		imgDisplay.setIcon(currentQu.getImage());
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
		if(currentQu.getImage() == null){
			prevImg.setVisible(false);
		}
		else{
			prevImg.setVisible(true);
		}
		//attemptLabel.setText("Attempts: " + currentUser.getUserPS().getAsked(currentSet.getIndex(), qIndex));
		//correctLabel.setText("Correct attempts: " + currentUser.getUserPS().getRight(currentSet.getIndex(), qIndex));
	}
	public void loadWindow(ProblemSet ps){
		qIndex = 0;
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
		qIndex = currentSet.getLength()-1;
		currentQu = currentSet.getQuestionByIndex(qIndex);
		updateWindow();
		qs.setSet(currentSet);
		qs.updateList();
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
		qs.updateList();
		updateWindow();
	}
	public void closeEditor(){
		currentSet = null;
		currentQu = null;
		sh.reload();
	}

	public void showQuestionWindow() {
		qs.setSet(currentSet);
		qs.setVisible(true);
		
	}
	
	public void getImage(){
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select an image file");
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png");
		jfc.setFileFilter(filter);
		int check = jfc.showOpenDialog(new JFrame());

		if(check == JFileChooser.APPROVE_OPTION) {
			picDisplay.setVisible(false);
			currentQu.storeImg(jfc.getSelectedFile());
			ps.updateFile();
			updateWindow();
		}
	}
	
	public void removeImg(){
		currentQu.removeImg();
		picDisplay.setVisible(false);
		ps.updateFile();
		updateWindow();
	}
	
	
	public void updateQIndex(int q){
		qIndex = q;
		currentQu = currentSet.getQuestionByIndex(qIndex);
		updateWindow();
	}
	
	
	public void exportProblemSet(){
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select a location to save");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int check = jfc.showSaveDialog(new JFrame());

		if(check == JFileChooser.APPROVE_OPTION) {
			
			File f = jfc.getSelectedFile();
			String path = f.getAbsolutePath();
			
			try {
				FileOutputStream fos;
				if(path.endsWith("\\")){
			    	fos = new FileOutputStream(new File(path + currentSet.getName() + ".shps"));

				}
				else{
			    	fos = new FileOutputStream(new File(path + ".shps"));
				}
			    ObjectOutputStream oos = new ObjectOutputStream(fos);
			    oos.writeObject(currentSet);
				oos.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error when writing to file", "Error" , JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		
		
	}

	public void loadUser(User u){
		currentUser = u;
	}

}
