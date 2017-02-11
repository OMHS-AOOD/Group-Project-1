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
		
		this.setJMenuBar(jmb);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JMenuItem selDom = new JMenuItem("Select Domain");
		JMenuItem addUser = new JMenuItem("Add User");
		JMenuItem selUser = new JMenuItem("Select Users");
		JMenuItem delUser = new JMenuItem("Delete all Users");
		JMenuItem startDom = new JMenuItem("Start Domain");
		JMenuItem editDom = new JMenuItem("Edit Domain");
		JMenuItem newDom = new JMenuItem("New Domain");
		JMenuItem toggleHUD = new JMenuItem("Toggle miniHUD");
		
		JMenu m1 = new JMenu("User");
		JMenu m2 = new JMenu("Domain");
		JMenu m3 = new JMenu("Windows");
		
		 
		
		
		jmb.add(m1);
		jmb.add(m2);
		jmb.add(m3);
		
		m1.add(addUser);
		m1.add(selUser);
		m1.add(delUser);
		
		m2.add(startDom);
		m2.add(selDom);
		m2.add(editDom);
		m2.add(newDom);
		
		m3.add(toggleHUD);
		
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
		
		
	}
	


}
