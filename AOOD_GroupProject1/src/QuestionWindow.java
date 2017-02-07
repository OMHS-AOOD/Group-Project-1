import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class QuestionWindow extends JFrame {
	private JTextField entry;
	private JLabel question, extra;
	private JButton submit;
	private ProblemSet currentSet;
	private int qIndex, numRight, numWrong;
	private JPanel panel;
	private Question currentQu;
	private StudyHelper sh;
	public QuestionWindow(StudyHelper s){
		setSize(800, 450);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		sh = s;
		qIndex = 0;
		JMenuBar jmb = new JMenuBar();
		question = new JLabel("testing");
		extra = new JLabel("also testing");
		submit = new JButton("Submit Answer");
		panel = new JPanel();
		entry = new JTextField();
		JMenu m1 = new JMenu("Options");
		JMenuItem toggleHUD = new JMenuItem("Toggle miniHUD");
		this.add(panel);
		panel.add(question);
		panel.add(extra);
		panel.add(submit);
		panel.add(entry);
		
		entry.setColumns(50);
		question.setLocation(200, 200);
		extra.setLocation(200, 300);
		submit.setLocation(350, 410);
		
		
		this.setJMenuBar(jmb);
		jmb.add(m1);
		m1.add(toggleHUD);
		
		
		toggleHUD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.toggleMiniHUD();
			}
		});
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				submit();
			}
		});
		
		
	}
	
	public void loadWindow(ProblemSet ps){
		setTitle("Quizzing on: " + ps.getName());
		currentSet = ps;
		setVisible(true);
		loadQu();
		
		
	}
	
	public void submit(){
		String answer = entry.getText();
		if(currentQu.getAns().equalsIgnoreCase(answer)){
			numRight++;
		}
		else{
			numWrong++;
		}
		qIndex++;
		if(qIndex < currentSet.getLength()){
			loadQu();
		}
		else{
			//End set
		}
		
	}
	
	public void loadQu(){
		currentQu = currentSet.getQuestionByIndex(qIndex);
		question.setText(currentQu.getPrompt());
		extra.setText(currentQu.getExtra());
	}
	
	
	
	
}
