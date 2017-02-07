import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;


public class UserSelect extends JFrame {
	private JScrollPane domainPane;
	private DefaultListModel<String> dlm;
	private JList domainList;
	private StudyHelper sh;
	private Database db;
	public UserSelect(String n, Database d, StudyHelper s){
		super(n);
		dlm = new DefaultListModel<String>();
		sh = s;
		db = d;
		setSize(300, 600);
		setResizable(false);
		setVisible(false);
		for(User u: db.getUserArray()){
			dlm.addElement(u.getName());
		}

		domainList = new JList(dlm);
		domainPane = new JScrollPane(domainList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(domainPane);
		domainList.addMouseListener(new CoolAdapter());
	}
	
	private class CoolAdapter extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e){
			if(e.getButton() == MouseEvent.BUTTON3){
				final int i = domainList.getSelectedIndex();
				if(i != -1){
					JPopupMenu jp = new JPopupMenu();
					JMenuItem jm1 = new JMenuItem("Select User");
					jp.add(jm1);
					jm1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e)
						{
							sh.selectUser(i);
						}
					});
					jp.show(domainPane.getViewport(), e.getX(),e.getY());
				}
				
				
			}
		}
	}
	public void updateList(){
		dlm.removeAllElements();
		for(User u: db.getUserArray()){
			dlm.addElement(u.getName());
		}
	}
}
