import java.awt.Color;
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
	private JButton nameSubmit, promptSubmit, extraSubmit, answerSubmit, newQu, deleteQu, nextQu, lastQu, finish,
			export, selIm, remImg, prevImg, submitAttempts, submitRight;
	private JLabel imgDisplay;
	private JMenuItem selQu, editPass;
	private int qIndex;
	private Database db;
	private QuestionSelect qs;

	public DomainEditor(ProblemStorage p, StudyHelper s, Database d) {
		setSize(900, 450);
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		db = d;
		sh = s;
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
		editRight = new JTextField();
		editAttempts = new JTextField();
		nameSubmit = new JButton("Change Name");
		promptSubmit = new JButton("Change Prompt");
		extraSubmit = new JButton("Change Extra Info");
		answerSubmit = new JButton("Change Answer");
		newQu = new JButton("New Question");
		deleteQu = new JButton("Delete Question");
		submitAttempts = new JButton("Change Attempts");
		submitRight = new JButton("Change Correct");
		nextQu = new JButton(">>");
		lastQu = new JButton("<<");
		finish = new JButton("Close Editor");
		export = new JButton("Export Set");
		selQu = new JMenuItem("Select Question");
		editPass = new JMenuItem("Edit Password");
		selIm = new JButton("Select Image");
		remImg = new JButton("Remove Image");
		prevImg = new JButton("Preview Image");

		m1 = new JMenu("Options");
		qs = new QuestionSelect(this);

		this.setJMenuBar(jmb);
		jmb.add(m1);
		m1.add(selQu);
		m1.add(editPass);

		
		jmb.setBackground(Color.BLACK);
		nameLabel.setForeground(Color.GREEN);
		promptLabel.setForeground(Color.GREEN);
		extraLabel.setForeground(Color.GREEN);
		answerLabel.setForeground(Color.GREEN);
		correctLabel.setForeground(Color.GREEN);
		attemptLabel.setForeground(Color.GREEN);
		panel.setBackground(Color.BLACK);
		
		
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
		newQu.setBounds(10, 360, 150, 30);
		panel.add(deleteQu);
		deleteQu.setBounds(170, 360, 150, 30);
		panel.add(nameLabel);
		nameLabel.setBounds(10, 10, 650, 25);
		panel.add(promptLabel);
		promptLabel.setBounds(10, 60, 650, 25);
		panel.add(extraLabel);
		extraLabel.setBounds(10, 110, 650, 25);
		panel.add(answerLabel);
		answerLabel.setBounds(10, 160, 650, 25);
		panel.add(nextQu);
		nextQu.setBounds(170, 310, 150, 30);
		panel.add(lastQu);
		lastQu.setBounds(10, 310, 150, 30);
		panel.add(finish);
		finish.setBounds(725, 360, 150, 30);
		panel.add(export);
		export.setBounds(330, 360, 150, 30);
		panel.add(selIm);
		selIm.setBounds(330, 310, 150, 30);
		panel.add(prevImg);
		prevImg.setBounds(490, 310, 150, 30);
		panel.add(remImg);
		remImg.setBounds(490, 360, 150, 30);

		panel.add(attemptLabel);
		attemptLabel.setBounds(10, 210, 150, 30);
		panel.add(correctLabel);
		correctLabel.setBounds(170, 210, 150, 30);

		panel.add(submitAttempts);
		panel.add(submitRight);
		submitAttempts.setBounds(10, 270, 150, 20);
		submitRight.setBounds(170, 270, 150, 20);

		panel.add(editAttempts);
		panel.add(editRight);
		editAttempts.setBounds(10, 240, 150, 30);
		editRight.setBounds(170, 240, 150, 30);

		picDisplay = new JFrame("Image Display");
		picDisplay.setVisible(false);
		picDisplay.add(imgDisplay);

		nameSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submitName();
			}
		});
		promptSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submitPrompt();
			}
		});
		extraSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submitExtra();
			}
		});
		answerSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submitAnswer();
			}
		});
		newQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewQuestion();
			}
		});
		deleteQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String check = JOptionPane.showInputDialog("Are you sure?(Y/N)");
				if (check == null) {
					return;
				}
				check = check.toUpperCase();
				if (check.equals("Y")) {
					deleteQu();
				}
			}
		});
		nextQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextQuestion();
			}
		});
		lastQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastQuestion();
			}
		});
		finish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeEditor();
			}
		});
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportProblemSet();
			}
		});
		selQu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showQuestionWindow();
			}

		});
		selIm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getImage();
			}

		});
		remImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeImg();
			}

		});
		prevImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previewImg();
			}

		});

		submitAttempts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer i = Integer.parseInt(editAttempts.getText().trim());
					editAttempts.setText("");
					if (i >= 0) {
						db.getData().get(sh.getCurrentUser().getName()).setAsked(currentSet, currentQu, i);
						updateWindow();
						db.updateFileData();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid number", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.INFORMATION_MESSAGE);

				}

			}

		});
		submitRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer i = Integer.parseInt(editRight.getText().trim());
					editRight.setText("");
					if (i >= 0) {
						db.getData().get(sh.getCurrentUser().getName()).setRight(currentSet, currentQu, i);
						updateWindow();
						db.updateFileData();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid number", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (NumberFormatException err) {
					editRight.setText("");
					JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.INFORMATION_MESSAGE);

				}

			}

		});
		editPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sh.getCurrentUser().getName().equals(currentSet.getAdmin())){
					String password = JOptionPane.showInputDialog("Enter a password: ");
					String passCheck = JOptionPane.showInputDialog("Re-enter the password: ");
					if (!password.equals(passCheck)) {
						JOptionPane.showMessageDialog(null, "Passwords do not match", "New User", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					currentSet.setPassword(password);
				}
				else{
					String setPass = JOptionPane.showInputDialog("Enter the set password: ");
					if(setPass.equals(currentSet.getPassword())){
						String password = JOptionPane.showInputDialog("Enter a password: ");
						String passCheck = JOptionPane.showInputDialog("Re-enter the password: ");
						if (!password.equals(passCheck)) {
							JOptionPane.showMessageDialog(null, "Passwords do not match", "Domain Editor", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						currentSet.setPassword(password);
					}
					else{
						JOptionPane.showMessageDialog(null, "Incorrect Password", "Domain Editor", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}

			}

		});
	}

	public void previewImg() {
		picDisplay.setLocation(0, 0);
		picDisplay.setSize(currentQu.getImage().getIconWidth(), currentQu.getImage().getIconHeight());
		picDisplay.setVisible(true);
		imgDisplay.setIcon(currentQu.getImage());
	}

	public void updateWindow() {
		nameLabel.setText("Set Name: " + currentSet.getName());
		setTitle("Editing: " + currentSet.getName());
		promptLabel.setText("Current Question: " + currentQu.getPrompt());
		if (currentQu.getExtra() == null) {
			extraLabel.setText("Current Extra Info: None");
		} else {
			extraLabel.setText("Current Extra Info: " + currentQu.getExtra());
		}
		answerLabel.setText("Current Answer: " + currentQu.getAns());
		if (currentQu.getImage() == null) {
			prevImg.setVisible(false);
		} else {
			prevImg.setVisible(true);
		}
		attemptLabel.setText(
				"Attempts: " + db.getData().get(sh.getCurrentUser().getName()).getAsked(currentSet, currentQu));
		correctLabel.setText(
				"Correct attempts: " + db.getData().get(sh.getCurrentUser().getName()).getRight(currentSet, currentQu));
	}

	public void loadWindow(ProblemSet ps) {
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

	public void submitName() {
		if (nameEntry.getText() != null) {
			currentSet.setName(currentSet.getName(), db);
			nameEntry.setText("");
			ps.updateFile();
			updateWindow();
		}
	}

	public void submitPrompt() {
		if (promptEntry.getText() != null) {
			currentQu.setPrompt(promptEntry.getText().trim(), db, currentSet);
			promptEntry.setText("");
			ps.updateFile();
			updateWindow();
		}
	}

	public void submitExtra() {
		if (extraEntry.getText() != null) {
			currentQu.setExtra(extraEntry.getText().trim());
			extraEntry.setText("");
			ps.updateFile();
			updateWindow();
		}
	}

	public void submitAnswer() {
		if (answerEntry.getText() != null) {
			currentQu.setAnswer(answerEntry.getText().trim());
			answerEntry.setText("");
			ps.updateFile();
			updateWindow();
		}
	}

	public void createNewQuestion() {

		currentSet.addQuestion(db, currentSet);
		qIndex = currentSet.getLength() - 1;
		currentQu = currentSet.getQuestionByIndex(qIndex);

		updateWindow();
		qs.setSet(currentSet);
		qs.updateList();
		ps.updateFile();
	}

	public void nextQuestion() {
		qIndex++;
		if (qIndex == currentSet.getLength()) {
			qIndex = 0;
		}
		currentQu = currentSet.getQuestionByIndex(qIndex);
		updateWindow();
	}

	public void lastQuestion() {
		qIndex--;
		if (qIndex < 0) {
			qIndex = currentSet.getLength() - 1;
		}
		currentQu = currentSet.getQuestionByIndex(qIndex);
		updateWindow();
	}

	public void deleteQu() {

		currentSet.deleteQuestion(qIndex, db);
		qIndex = 0;
		currentQu = currentSet.getQuestionByIndex(qIndex);
		ps.updateFile();
		qs.updateList();
		updateWindow();
	}

	public void closeEditor() {
		currentQu = null;

		sh.reload(currentSet);
	}

	public void showQuestionWindow() {
		qs.setSet(currentSet);
		qs.setVisible(true);

	}

	public void getImage() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select an image file");
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png");
		jfc.setFileFilter(filter);
		int check = jfc.showOpenDialog(new JFrame());

		if (check == JFileChooser.APPROVE_OPTION) {
			picDisplay.setVisible(false);
			currentQu.storeImg(jfc.getSelectedFile());
			ps.updateFile();
			updateWindow();
		}
	}

	public void removeImg() {
		currentQu.removeImg();
		picDisplay.setVisible(false);
		ps.updateFile();
		updateWindow();
	}

	public void updateQIndex(int q) {
		qIndex = q;
		currentQu = currentSet.getQuestionByIndex(qIndex);
		updateWindow();
	}

	public void exportProblemSet() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select a location to save");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int check = jfc.showSaveDialog(new JFrame());

		if (check == JFileChooser.APPROVE_OPTION) {

			File f = jfc.getSelectedFile();
			String path = f.getAbsolutePath();

			try {
				FileOutputStream fos;
				if (path.endsWith("\\")) {
					fos = new FileOutputStream(new File(path + currentSet.getName() + ".shps"));

				} else {
					fos = new FileOutputStream(new File(path + ".shps"));
				}
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(currentSet);
				oos.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error when writing to file", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}

	}

}
