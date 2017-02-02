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
	public String getName(){
		return name;
	}
	public void setName(String n){
		name = n;
	}
	
	public Question getQuestionByIndex(int i){
		return questions.get(i);
	}
	
}
