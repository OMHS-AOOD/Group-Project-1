import javax.swing.*;

public class QuestionWindow extends JFrame {
	private JTextField entry;
	private JLabel question, extra;
	private JButton submit;
	private ProblemSet currentSet;
	private int qIndex, numRight, numWrong;
	private JPanel panel;
	private Question currentQuestion;
	public QuestionWindow(){
		setSize(800, 450);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		qIndex = 0;
		question = new JLabel("testing");
		extra = new JLabel("also testing");
		submit = new JButton("Submit Answer");
		panel = new JPanel();
		entry = new JTextField();
		
		this.add(panel);
		panel.add(question);
		panel.add(extra);
		panel.add(submit);
		panel.add(entry);
		question.setLocation(200, 200);
		extra.setLocation(200, 300);
		submit.setLocation(350, 410);
		
		
	}
	
	public void loadWindow(ProblemSet ps){
		setTitle("Quizzing on: " + ps.getName());
		currentSet = ps;
		setVisible(true);
		
		
	}
	
	public void submit(){
		String answer = entry.getText();
		if(currentQuestion.getAns().equalsIgnoreCase(answer)){
			numRight++;
		}
		else{
			numWrong++;
		}
	}
	
	public void runSet(){
		while(qIndex < currentSet.getLength()){
			
		}
	}
	
	
}
