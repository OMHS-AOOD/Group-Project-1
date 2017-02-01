import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends JFrame {
	private StudyHelper sh;
	private JLabel userDisplay;
	public MainMenu(String name, StudyHelper s){
		super(name);
		sh = s;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 450);
		setResizable(false);
		setVisible(true);
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Graphics g = panel.getGraphics();
		JButton start = new JButton("Start");
		JButton addUser = new JButton("Add User");
		JButton selUser = new JButton("Select User");
		JButton dispUser = new JButton("Display Users");
		panel.add(start);
		panel.add(addUser);
		panel.add(selUser);
		panel.add(dispUser);
		addUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.addNewUser();
			}
		});
		dispUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.showUsers();
			}
		});
		selUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.selectUser();
			}
		});
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//start
			}
		});
	}
	


}
