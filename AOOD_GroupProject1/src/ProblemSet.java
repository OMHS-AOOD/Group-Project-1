import java.io.Serializable;
import java.util.ArrayList;

public class ProblemSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8755540553842138073L;
	private String name, admin, password;
	private ArrayList<Question> questions;

	public ProblemSet(String n, Database db) {
		questions = new ArrayList<Question>();
		name = n;
		admin = "Default";
		password = "";
		addQuestion(db, this);
	}

	public void addQuestion(Question q, Database db, ProblemSet ps) {

		questions.add(q);
		for (User u : db.getUserArray()) {
			db.getData().get(u.getName()).addQu(ps, q);
		}
		db.updateFileData();

	}

	public void addQuestion(Database db, ProblemSet ps) {
		Question q = new Question("New Question", "Answer");
		questions.add(q);
		for (User u : db.getUserArray()) {
			db.getData().get(u.getName()).addQu(ps, q);
		}
		db.updateFileData();

	}

	public String getName() {
		return name;
	}

	public void setName(String n, Database db) {
		for (User u : db.getUserArray()) {
			db.getData().get(u.getName()).updateSetName(this, n);
		}
		db.updateFileData();
		name = n;
		
	}

	public Question getQuestionByIndex(int i) {
		return questions.get(i);
	}

	public void setAdmin(String n) {
		admin = n;
	}

	public void setPassword(String n) {
		password = n;
	}

	public int getLength() {
		return questions.size();
	}

	public void deleteQuestion(int i, Database db) {
		Question q = questions.remove(i);
		for (User u : db.getUserArray()) {
			db.getData().get(u.getName()).removeQuestion(this, q);

		}
		db.updateFileData();

	}

	public ArrayList<Question> getList() {
		return questions;
	}

}
