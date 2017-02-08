import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class QuestionWindow extends JFrame {
	private JTextField entry;
	private JLabel question, extra, ansLab;
	private JButton submit;
	private ProblemSet currentSet;
	private int qIndex, numRight, numWrong;
	private JPanel panel;
	private Question currentQu;
	private StudyHelper sh;
	private miniHUD mh;
	public QuestionWindow(StudyHelper s, miniHUD m){
		setSize(800, 450);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mh = m;
		sh = s;
		qIndex = 0;
		JMenuBar jmb = new JMenuBar();
		question = new JLabel("testing");
		extra = new JLabel("also testing");
		submit = new JButton("Submit Answer");
		ansLab = new JLabel("");
		panel = new JPanel();
		entry = new JTextField();
		JMenu m1 = new JMenu("Options");
		JMenuItem toggleHUD = new JMenuItem("Toggle miniHUD");
		panel.setLayout(null);
		entry.setColumns(50);
		entry.setBounds(75, 300, 650, 25);
		question.setBounds(75, 25, 1000, 50);
		extra.setBounds(75, 50, 1000, 50);
		submit.setBounds(300, 350, 200, 40);
		ansLab.setBounds(300, 310, 1000, 40);
		this.add(panel);
		panel.add(question);
		panel.add(extra);
		panel.add(submit);
		panel.add(entry);
		panel.add(ansLab);
		
		
		
		
		
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
		mh.setRight(0);
		mh.setWrong(0);
		loadQu();
		
		
	}
	
	public void submit(){
		String answer = entry.getText().trim();
		entry.setText("");
		
		if(currentQu.getAns().equalsIgnoreCase(answer)){
			numRight++;
			mh.setRight(numRight);
			ansLab.setText("Correct!");
		}
		else{
			numWrong++;
			mh.setWrong(numWrong);
			ansLab.setText("The correct answer was: " + currentQu.getAns() + ".");
		}
		qIndex++;
		if(qIndex < currentSet.getLength()){
			loadQu();
		}
		else{
			mh.emptyRight();
			mh.emptyLeft();
			JOptionPane.showMessageDialog(null, "You got " + numRight + " correct and " + numWrong + " incorrect.", "Results" , JOptionPane.INFORMATION_MESSAGE);
			currentQu= null;
			question.setText("");
			extra.setText("");
			qIndex = 0;
			sh.reload();
		}
		
	}
	
	public void loadQu(){
		currentQu = currentSet.getQuestionByIndex(qIndex);
		question.setText(currentQu.getPrompt());
		extra.setText(currentQu.getExtra());
	}
	
	
	
	
}
