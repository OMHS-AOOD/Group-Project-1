import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class DomainSelect extends JFrame {
	private JScrollPane domainPane;
	private DefaultListModel<String> dlm;
	private JList domainList;
	public DomainSelect(String n, ProblemStorage ps){
		super(n);
		dlm = new DefaultListModel<String>();
		setSize(300, 600);
		setResizable(false);
		setVisible(false);
		for(ProblemSet p: ps.getArray()){
			dlm.addElement(p.getName());
		}
		domainList = new JList(dlm);
		domainPane = new JScrollPane(domainList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(domainPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
