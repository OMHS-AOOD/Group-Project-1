import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

public class DomainSelect extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1737478697057004436L;
	private JScrollPane domainPane;
	private DefaultListModel<String> dlm;
	private JList<String> domainList;
	private StudyHelper sh;
	private ProblemStorage ps;
	private JButton delete, edit, launch;
	private JPanel panel;
	public DomainSelect(String n, ProblemStorage prob, StudyHelper s){
		super(n);
		dlm = new DefaultListModel<String>();
		sh = s;
		ps = prob;
		
		panel = new JPanel();
		setSize(300, 400);
		setResizable(false);
		setVisible(false);
		for(ProblemSet p: ps.getArray()){
			dlm.addElement(p.getName());
		}

		domainList = new JList<String>(dlm);
		domainPane = new JScrollPane(domainList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(domainPane);
		domainPane.setBounds(0, 0, 300, 300);
		add(panel);
		domainList.addMouseListener(new CoolAdapter());
		
		JButton delete = new JButton("Delete");
		JButton edit = new JButton("Edit");
		JButton launch = new JButton("Launch");
		panel.add(launch);
		panel.add(edit);
		panel.add(delete);
		
		launch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final int i = domainList.getSelectedIndex();
				if(i != -1){
					sh.setDomain(i);
				}
				else{
					JOptionPane.showMessageDialog(null, "No domain selected", "Error" , JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final int i = domainList.getSelectedIndex();
				if(i != -1){
					sh.setDomain(i);
					sh.startEditor();
				}
				else{
					JOptionPane.showMessageDialog(null, "No domain selected", "Error" , JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				final int i = domainList.getSelectedIndex();
				if(i != -1){
					sh.deleteDomain(i);
					dlm.removeAllElements();
					for(ProblemSet p: ps.getArray()){
						dlm.addElement(p.getName());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "No domain selected", "Error" , JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
	}
	
	private class CoolAdapter extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e){
			if(e.getButton() == MouseEvent.BUTTON3){
				final int i = domainList.getSelectedIndex();
				if(i != -1){
					JPopupMenu jp = new JPopupMenu();
					JMenuItem jm1 = new JMenuItem("Select Domain");
					JMenuItem jm2 = new JMenuItem("Edit Domain");
					JMenuItem jm3 = new JMenuItem("Delete Domain");
					jp.add(jm1);
					jp.add(jm2);
					jp.add(jm3);
					jm1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e)
						{
							sh.setDomain(i);
						}
					});
					jm2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e)
						{
							sh.setDomain(i);
							sh.startEditor();
						}
					});
					jm3.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e)
						{
							sh.deleteDomain(i);
							dlm.removeAllElements();
							for(ProblemSet p: ps.getArray()){
								dlm.addElement(p.getName());
							}
							
						}
					});
					jp.show(domainPane.getViewport(), e.getX(),e.getY());
				}
				
				
			}
		}
		public void mouseClicked(MouseEvent e)
		{
		  if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
			  final int i = domainList.getSelectedIndex();
			  if(i != -1){
				  sh.setDomain(i);
				  sh.startProblems();
			  }
		  }
		}
	}
	
	public void updateList(){
		dlm.removeAllElements();
		for(ProblemSet p: ps.getArray()){
			dlm.addElement(p.getName());
		}
	}
}
