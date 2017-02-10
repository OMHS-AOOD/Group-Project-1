import java.util.ArrayList;

public class ProblemSet {
	private String name, admin, password;
	private ArrayList<Question> questions;
	public ProblemSet(String n){
		questions = new ArrayList<Question>();
		name = n;
		admin = "Default";
		password = "";
	}
	
	public void addQuestion(Question q){
		questions.add(q);
	}
	public void addQuestion(){
		questions.add(new Question("New Question", "Answer"));
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
	
	public void setAdmin(String n){
		admin = n;
	}
	public void setPassword(String n){
		password = n;
	}
	public int getLength(){
		return questions.size();
	}
	
	public void deleteQuestion(int i){
		questions.remove(i);
	}
}
