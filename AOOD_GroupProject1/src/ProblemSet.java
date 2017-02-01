import java.util.ArrayList;

public class ProblemSet {
	private String name;
	private ArrayList<Question> questions;
	public ProblemSet(String n){
		questions = new ArrayList<Question>();
		name = n;
	}
	
	public void addQuestion(Question q){
		questions.add(q);
	}
	
}
