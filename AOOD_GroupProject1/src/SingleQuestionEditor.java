import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SingleQuestionEditor extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2267435614024328026L;
	private ProblemSet currentSet;
	private Question currentQu;
	private ProblemStorage ps;
	private JPanel panel;
	private JFrame picDisplay;
	private JLabel promptLabel, extraLabel, answerLabel;
	private JTextField promptEntry, extraEntry, answerEntry;
	private JButton promptSubmit, extraSubmit, answerSubmit, deleteQu, finish, selIm, remImg, prevImg;
	private JLabel imgDisplay;
	private int qIndex;
	private QuestionWindow qw;
	public SingleQuestionEditor(QuestionWindow q, ProblemStorage p){
		setSize(900, 300);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		ps = p;
		qw = q;		
		promptLabel = new JLabel();
		extraLabel = new JLabel();
		answerLabel = new JLabel();
		imgDisplay = new JLabel();
		panel = new JPanel();
		promptEntry = new JTextField();
		extraEntry = new JTextField();
		answerEntry = new JTextField();
		promptSubmit = new JButton("Change Prompt");
		extraSubmit = new JButton("Change Extra Info");
		answerSubmit = new JButton("Change Answer");
		deleteQu = new JButton("Delete Question");
		finish = new JButton("Close Editor");
		selIm = new JButton("Select Image");
		remImg = new JButton("Remove Image");
		prevImg = new JButton("Preview Image");
		
		
		this.add(panel);
		panel.setLayout(null);
		panel.add(promptEntry);
		promptEntry.setBounds(10, 35, 650, 25);
		panel.add(extraEntry);
		extraEntry.setBounds(10, 85, 650, 25);
		panel.add(answerEntry);
		answerEntry.setBounds(10, 135, 650, 25);
		panel.add(promptSubmit);
		promptSubmit.setBounds(675, 30, 150, 30);
		panel.add(extraSubmit);
		extraSubmit.setBounds(675, 80, 150, 30);
		panel.add(answerSubmit);
		answerSubmit.setBounds(675, 130, 150, 30);
		panel.add(deleteQu);
		deleteQu.setBounds(170, 230, 150, 30);
		panel.add(promptLabel);
		promptLabel.setBounds(10, 10, 650, 25);
		panel.add(extraLabel);
		extraLabel.setBounds(10, 60, 650, 25);
		panel.add(answerLabel);
		answerLabel.setBounds(10, 110, 650, 25);
		panel.add(finish);
		finish.setBounds(725, 230, 150, 30);
		panel.add(selIm);
		selIm.setBounds(10, 180, 150, 30);
		panel.add(prevImg);
		prevImg.setBounds(10, 180, 150, 30);
		panel.add(remImg);
		remImg.setBounds(10, 230, 150, 30);
		
		
		
		picDisplay = new JFrame("Image Display");
		picDisplay.setVisible(false);
		picDisplay.add(imgDisplay);
		
		

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

		finish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				closeEditor();
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
		setTitle("Editing: " + currentSet.getName() + " Question");
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
	}
	public void loadWindow(ProblemSet ps, Question q, int qi){
		setLocation(0, 0);
		currentSet = ps;
		currentQu = q;
		qIndex = qi;
		setTitle("Editing: " + currentSet.getName());
		setVisible(true);
		promptLabel.setText("Current Question: " + currentQu.getPrompt());
		updateWindow();
		
		
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

	

	public void deleteQu(){
		currentSet.deleteQuestion(qIndex);
		qIndex = 0;
		currentQu = currentSet.getQuestionByIndex(qIndex);
		ps.updateFile();
		updateWindow();
		closeEditor();
	}
	
	public void closeEditor(){
		currentSet = null;
		currentQu = null;
		this.setVisible(false);
		qw.reload();
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

		
		
	


}
