import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2129276826899147774L;
	private StudyHelper sh;
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
		
		panel.setBackground(Color.BLACK);
		jmb.setBackground(Color.BLACK);
		
		this.setJMenuBar(jmb);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JMenuItem selDom = new JMenuItem("Select Domain");
		JMenuItem addUser = new JMenuItem("Add User");
		JMenuItem selUser = new JMenuItem("Select Users");
		
		JMenuItem nameCh = new JMenuItem("Change Username");
		JMenuItem passCh = new JMenuItem("Change Password");
		
		JMenuItem delUser = new JMenuItem("Delete all Users");
		JMenuItem startDom = new JMenuItem("Start Domain");
		JMenuItem editDom = new JMenuItem("Edit Domain");
		JMenuItem newDom = new JMenuItem("New Domain");
		JMenuItem impDom = new JMenuItem("Import Domain");
		JMenuItem toggleHUD = new JMenuItem("Toggle miniHUD");
		JMenuItem randQus = new JMenuItem("Toggle Randomize Questions");

		

		
		
		JMenu m1 = new JMenu("User");
		JMenu m2 = new JMenu("Domain");
		JMenu m3 = new JMenu("Windows");
		
		m1.setForeground(Color.GREEN);
		m2.setForeground(Color.GREEN);
		m3.setForeground(Color.GREEN);
		 
		
		
		jmb.add(m1);
		jmb.add(m2);
		jmb.add(m3);
		
		m1.add(addUser);
		m1.add(selUser);
		m1.add(randQus);
		m1.add(delUser);
		
		addUser.setBackground(Color.BLACK);
		addUser.setForeground(Color.GREEN);
		selUser.setBackground(Color.BLACK);
		selUser.setForeground(Color.GREEN);
		randQus.setBackground(Color.BLACK);
		randQus.setForeground(Color.GREEN);
		delUser.setBackground(Color.BLACK);
		delUser.setForeground(Color.GREEN);
		
		
		m2.add(startDom);
		m2.add(selDom);
		m2.add(editDom);
		m2.add(newDom);
		m2.add(impDom);
		
		startDom.setBackground(Color.BLACK);
		startDom.setForeground(Color.GREEN);
		selDom.setBackground(Color.BLACK);
		selDom.setForeground(Color.GREEN);
		editDom.setBackground(Color.BLACK);
		editDom.setForeground(Color.GREEN);
		newDom.setBackground(Color.BLACK);
		newDom.setForeground(Color.GREEN);
		impDom.setBackground(Color.BLACK);
		impDom.setForeground(Color.GREEN);
		
		m3.add(toggleHUD);
		
		toggleHUD.setBackground(Color.BLACK);
		toggleHUD.setForeground(Color.GREEN);
		addUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.addNewUser();
			}
		});
		selUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.showUsersWindow();
			}
		});
		selDom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.selectDomain();
			}
		});
		delUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.deleteUsers();
			}
		});
		startDom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.startProblems();
			}
		});
		toggleHUD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.toggleMiniHUD();
			}
		});
		editDom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.startEditor();
			}
		});
		newDom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.newDomain();
			}
		});
		impDom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.importProblemSet();
			}
		});
		randQus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.toggleUserRandomization();
			}
		});
		nameCh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.changeUsername();
			}
		});
		passCh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sh.changePassword();
			}
		});
		
		
		
	
		
	}
	


}
