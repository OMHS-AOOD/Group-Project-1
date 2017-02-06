import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends JFrame {
	private StudyHelper sh;
	private JLabel userDisplay;
	private JMenuBar jmb;
	public MainMenu(String name, StudyHelper s){
		super(name);
		sh = s;
		jmb = new JMenuBar();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 450);
		setResizable(false);
		setVisible(true);
		JPanel panel = new JPanel();
		add(panel);
		
		this.setJMenuBar(jmb);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Graphics g = panel.getGraphics();
		JMenuItem selDom = new JMenuItem("Select Domain");
		JMenuItem addUser = new JMenuItem("Add User");
		JMenuItem selUser = new JMenuItem("Select User");
		JMenuItem dispUser = new JMenuItem("Display Users");
		
		JMenu m1 = new JMenu("User");
		JMenu m2 = new JMenu("Domain");
		
		 
		
		
		jmb.add(m1);
		jmb.add(m2);
		
		m1.add(addUser);
		m1.add(selUser);
		m1.add(dispUser);
		
		m2.add(selDom);
		
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
		selDom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.selectDomain();
			}
		});
	}
	


}
