import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

public class QuestionSelect extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1737478697057004436L;
	private JScrollPane domainPane;
	private DefaultListModel<String> dlm;
	private JList<String> domainList;
	private DomainEditor de;
	private ProblemSet ps;
	public QuestionSelect(DomainEditor d){
		super("Select a Question");
		dlm = new DefaultListModel<String>();
		de = d;
		setSize(500, 250);
		setResizable(false);
		setVisible(false);
		for(Question q: ps.getList()){
			dlm.addElement(q.getPrompt());
		}

		domainList = new JList<String>(dlm);
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
					JMenuItem jm2 = new JMenuItem("Edit Question");
					JMenuItem jm3 = new JMenuItem("Delete Question");
					jp.add(jm2);
					jp.add(jm3);
					jm2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e)
						{
							de.updateQIndex(i); 
						}
					});
					jm3.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e)
						{
							String check = JOptionPane.showInputDialog("Are you sure?(Y/N)");
							if(check == null){
								return;
							}
							check = check.toUpperCase();
							if(check.equals("Y")){
								de.deleteQu();
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
				  de.updateQIndex(i);
			  }
		  }
		}
	}
	
	
	
	public void updateList(){
		dlm.removeAllElements();
		for(Question q: ps.getList()){
			dlm.addElement(q.getPrompt());
		}
	}
	
	
	public void setSet(ProblemSet p){
		ps = p;
		updateList();
	}
}
