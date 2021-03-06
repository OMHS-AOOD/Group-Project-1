import java.awt.Color;
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
	private JButton submit, know, notKnow;
	private ProblemSet currentSet;
	private int qIndex, numRight, numWrong;
	private JPanel panel;
	private Question currentQu;
	private StudyHelper sh;
	private miniHUD mh;
	private SingleQuestionEditor quEdit;
	private ProblemStorage ps;
	private ArrayList<Question> qStorage;
	private User currentUser;
	private Database db;

	public QuestionWindow(StudyHelper s, miniHUD m, ProblemStorage p, Database d) {
		setSize(800, 450);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mh = m;
		sh = s;
		db = d;
		qIndex = 0;
		ps = p;
		quEdit = new SingleQuestionEditor(this, ps, db);

		JMenuBar jmb = new JMenuBar();
		question = new JLabel("");
		extra = new JLabel("");
		submit = new JButton("Submit Answer");
		know = new JButton("I knew the answer");
		notKnow = new JButton("I didn't know the answer");
		ansLab = new JLabel("");
		image = new JLabel();
		panel = new JPanel();
		entry = new JTextField();
		JMenu m1 = new JMenu("Options");
		JMenuItem toggleHUD = new JMenuItem("Toggle miniHUD");
		JMenuItem exitDomain = new JMenuItem("Exit Domain");
		JMenuItem editQuestion = new JMenuItem("Edit Question");
		JMenuItem delQu = new JMenuItem("Delete Question");
		panel.setLayout(null);
		entry.setColumns(50);
		entry.setBounds(75, 300, 650, 25);
		question.setBounds(75, 25, 1000, 25);
		extra.setBounds(75, 50, 1000, 25);
		image.setBounds(75, 100, 600, 150);
		submit.setBounds(300, 350, 200, 40);
		know.setBounds(75, 350, 200, 40);
		notKnow.setBounds(525, 350, 200, 40);
		ansLab.setBounds(75, 310, 1000, 40);

		jmb.setBackground(Color.BLACK);
		m1.setForeground(Color.GREEN);
		toggleHUD.setForeground(Color.GREEN);
		exitDomain.setForeground(Color.GREEN);
		editQuestion.setForeground(Color.GREEN);
		delQu.setForeground(Color.GREEN);
		toggleHUD.setBackground(Color.BLACK);
		exitDomain.setBackground(Color.BLACK);
		editQuestion.setBackground(Color.BLACK);
		delQu.setBackground(Color.BLACK);

		entry.setBackground(Color.BLACK);
		
		entry.setForeground(Color.GREEN);
		question.setForeground(Color.GREEN);
		extra.setForeground(Color.GREEN);
		submit.setForeground(Color.GREEN);
		know.setForeground(Color.GREEN);
		notKnow.setForeground(Color.GREEN);
		ansLab.setForeground(Color.GREEN);
		panel.setBackground(Color.BLACK);
		submit.setBackground(Color.BLACK);
		know.setForeground(Color.BLACK);
		notKnow.setForeground(Color.BLACK);

		this.add(panel);
		panel.add(question);
		panel.add(extra);
		panel.add(submit);
		panel.add(entry);
		panel.add(ansLab);
		panel.add(image);
		panel.add(know);
		panel.add(notKnow);

		know.setVisible(false);
		notKnow.setVisible(false);

		this.setJMenuBar(jmb);
		jmb.add(m1);
		m1.add(exitDomain);
		m1.add(editQuestion);
		m1.add(delQu);
		m1.add(toggleHUD);

		toggleHUD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sh.toggleMiniHUD();
			}
		});
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		});
		know.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				know();
			}
		});
		notKnow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notKnow();
			}
		});
		exitDomain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mh.emptyRight();
				mh.emptyLeft();
				currentQu = null;
				question.setText("");
				extra.setText("");
				qIndex = 0;
				sh.reload(currentSet);
			}
		});
		editQuestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editQuestion();
			}
		});
		delQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteCurrentQu();
			}
		});
	}

	public void loadWindow(ProblemSet ps, boolean randomize, User cU) {
		mh.setVisible(true);
		currentUser = cU;
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
		if (randomize) {
			randomizeOrder();
		}
		loadQu();

	}

	public void submit() {
		submit.setVisible(false);
		know.setVisible(true);
		notKnow.setVisible(true);
		entry.setText("");
		ansLab.setText("The correct answer was: " + currentQu.getAns() + ".");
		db.getData().get(currentUser.getName()).addAsked(currentSet, currentQu);
	}

	public void know() {
		submit.setVisible(true);
		know.setVisible(false);
		notKnow.setVisible(false);
		numRight++;
		mh.setRight(numRight);
		ansLab.setText("");
		db.getData().get(currentUser.getName()).addRight(currentSet, currentQu);
		qIndex++;
		if (qIndex < qStorage.size()) {
			loadQu();
		} else {
			close();
		}

	}

	public void notKnow() {
		submit.setVisible(true);
		know.setVisible(false);
		notKnow.setVisible(false);
		numWrong++;
		mh.setWrong(numWrong);
		ansLab.setText("");

		qIndex++;
		if (qIndex < qStorage.size()) {
			loadQu();
		} else {
			close();
		}

	}

	public void loadQu() {
		currentQu = qStorage.get(qIndex);
		question.setText(currentQu.getPrompt());
		extra.setText(currentQu.getExtra());
		if (currentQu.getImage() != null) {
			image.setIcon(currentQu.getImage());
		} else {
			image.setIcon(null);
		}

	}

	public void randomizeOrder() {
		ArrayList<Question> randQ = new ArrayList<Question>();
		while (qStorage.size() > 0) {
			int randInt = (int) (Math.random() * qStorage.size());
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

	public void editQuestion() {
		quEdit.getAUser(currentUser);
		quEdit.loadWindow(currentSet, currentQu, qIndex);
		this.setVisible(false);
	}

	public void reload() {
		this.setVisible(true);
		if (qIndex >= currentSet.getLength()) {
			close();
		} else {
			this.loadQu();
		}

	}

	public void close() {
		mh.emptyRight();
		mh.emptyLeft();
		JOptionPane.showMessageDialog(null, "You knew " + numRight + " and did not know " + numWrong + ".", "Results",
				JOptionPane.INFORMATION_MESSAGE);
		currentQu = null;
		question.setText("");
		extra.setText("");
		qIndex = 0;
		sh.reload(currentSet);
	}

	public void deleteCurrentQu() {
		String check = JOptionPane.showInputDialog("Are you sure?(Y/N)\nNumber of Attempts: "
				+ db.getData().get(currentUser.getName()).getAsked(currentSet, currentQu)
				+ "\nNumber of correct attempts: "
				+ db.getData().get(currentUser.getName()).getAsked(currentSet, currentQu));

		if (check == null) {
			return;
		}
		check = check.toUpperCase();
		if (check.equals("Y")) {
			currentSet.deleteQuestion(qIndex, db);
			ps.updateFile();
			if (qIndex >= currentSet.getLength()) {
				close();
			} else {
				currentQu = currentSet.getQuestionByIndex(qIndex);
				this.loadQu();
			}

		}

	}

}
