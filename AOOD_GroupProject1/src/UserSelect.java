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
	/**
	 * 
	 */
	private static final long serialVersionUID = -4036552631328610643L;
	private JScrollPane userPane;
	private DefaultListModel<String> dlm;
	private JList<String> userList;
	private StudyHelper sh;
	private Database db;
	public UserSelect(String n, Database d, StudyHelper s){
		super(n);
		dlm = new DefaultListModel<String>();
		sh = s;
		db = d;
		setSize(300, 250);
		setResizable(false);
		setVisible(false);
		for(User u: db.getUserArray()){
			dlm.addElement(u.getName());
		}

		userList = new JList<String>(dlm);
		userPane = new JScrollPane(userList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(userPane);
		userList.addMouseListener(new CoolAdapter());
	}
	
	private class CoolAdapter extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e){
			if(e.getButton() == MouseEvent.BUTTON3){
				final int i = userList.getSelectedIndex();
				if(i != -1){
					JPopupMenu jp = new JPopupMenu();
					JMenuItem jm1 = new JMenuItem("Select User");
					JMenuItem jm2 = new JMenuItem("Delete User");
					JMenuItem jm3 = new JMenuItem("Edit Password");
					jp.add(jm1);
					jp.add(jm2);
					jp.add(jm3);
					jm1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e)
						{
							sh.selectUser(i);
						}
					});
					jm2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e)
						{
							sh.deleteUser(i);
							dlm.removeAllElements();
							for(User u: db.getUserArray()){
								dlm.addElement(u.getName());
							}
						}
					});
					jp.show(userPane.getViewport(), e.getX(),e.getY());
				}
				
				
			}
		}
		public void mouseClicked(MouseEvent e)
		{
		  if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
			  final int i = userList.getSelectedIndex();
			  if(i != -1){
				  sh.selectUser(i);
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
