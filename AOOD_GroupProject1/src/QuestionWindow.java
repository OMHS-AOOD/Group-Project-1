import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class QuestionWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 38476236516135752L;
	private JTextField entry;
	private JLabel question, extra, ansLab, image;
	private JButton submit;
	private ProblemSet currentSet;
	private int qIndex, numRight, numWrong;
	private JPanel panel;
	private Question currentQu;
	private StudyHelper sh;
	private miniHUD mh;
	private ArrayList<Question> qStorage;
	public QuestionWindow(StudyHelper s, miniHUD m){
		setSize(800, 450);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mh = m;
		sh = s;
		qIndex = 0;	
		JMenuBar jmb = new JMenuBar();
		question = new JLabel("");
		extra = new JLabel("");
		submit = new JButton("Submit Answer");
		ansLab = new JLabel("");
		image = new JLabel();
		panel = new JPanel();
		entry = new JTextField();
		JMenu m1 = new JMenu("Options");
		JMenuItem randomize = new JMenuItem("Randomize question order");
		JMenuItem unrandomize = new JMenuItem("Unrandomize question order");
		JMenuItem toggleHUD = new JMenuItem("Toggle miniHUD");
		panel.setLayout(null);
		entry.setColumns(50);
		entry.setBounds(75, 300, 650, 25);
		question.setBounds(75, 25, 1000, 25);
		extra.setBounds(75, 50, 1000, 25);
		image.setBounds(75, 100, 600, 150);
		submit.setBounds(300, 350, 200, 40);
		ansLab.setBounds(75, 310, 1000, 40);
		this.add(panel);
		panel.add(question);
		panel.add(extra);
		panel.add(submit);
		panel.add(entry);
		panel.add(ansLab);
		panel.add(image);
		
		
		
		
		this.setJMenuBar(jmb);
		jmb.add(m1);
		m1.add(randomize);
		m1.add(unrandomize);
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
		randomize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				randomizeOrder();
			}
		});
		unrandomize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				unrandomizeOrder();
			}
		});
		
		
	}
	
	public void loadWindow(ProblemSet ps){
		numRight = 0;
		numWrong = 0;
		setLocation(0, 0);
		setTitle("Quizzing on: " + ps.getName());
		currentSet = ps;
		setVisible(true);
		mh.setRight(0);
		mh.setWrong(0);
		ansLab.setText("");
		qStorage = new ArrayList<Question>(currentSet.getList());
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
		currentQu = qStorage.get(qIndex);
		question.setText(currentQu.getPrompt());
		extra.setText(currentQu.getExtra());
		if(currentQu.getImage() != null){
			image.setIcon(currentQu.getImage());
		}
		else{
			image.setIcon(null);
		}
		
	}
	
	public void randomizeOrder(){
		ArrayList<Question> randQ = new ArrayList<Question>();
		while(qStorage.size() > 0){
			int randInt = (int)(Math.random() * qStorage.size());
			randQ.add(qStorage.remove(randInt));
		}
		
		qStorage = randQ;
		qIndex = 0;
		mh.setRight(0);
		mh.setWrong(0);
		numRight = 0;
		numWrong = 0;
		ansLab.setText("");
		loadQu();
	}
	
	public void unrandomizeOrder(){
		qStorage = currentSet.getList();
		qIndex = 0;
		mh.setRight(0);
		mh.setWrong(0);
		numRight = 0;
		numWrong = 0;
		ansLab.setText("");
		loadQu();
		
	}
	
	

	
	
	
	
	
}
