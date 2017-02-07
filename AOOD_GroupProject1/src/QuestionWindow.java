import javax.swing.*;

public class QuestionWindow extends JFrame {
	private JTextField entry;
	private JLabel question, extra;
	private JButton submit;
	private ProblemSet currentSet;
	private int qIndex, numRight, numWrong;
	private Question currentQuestion;
	public QuestionWindow(){
		setSize(800, 450);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		qIndex = 0;
		question = new JLabel();
		extra = new JLabel();
		submit = new JButton("Submit Answer");
		
		
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
