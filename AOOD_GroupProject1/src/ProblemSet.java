import java.io.Serializable;
import java.util.ArrayList;

public class ProblemSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8755540553842138073L;
	private String name, admin, password;
	private ArrayList<Question> questions;
	private ArrayList<UserProblemStorage> userData;
	private int setIndex;
	public ProblemSet(String n, int index, ArrayList<UserProblemStorage> ups){
		questions = new ArrayList<Question>();
		name = n;
		admin = "Default";
		password = "";
		userData = ups;
		setIndex = index;
		addQuestion();
	}
	
	public void addQuestion(Question q){
		
		questions.add(q);
		for(UserProblemStorage ups: userData){
			ups.addQu(setIndex);
		}
	}
	public void addQuestion(){
		questions.add(new Question("New Question", "Answer"));
		for(UserProblemStorage ups: userData){
			ups.addQu(setIndex);
		}
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
		for(UserProblemStorage ups: userData){
			ups.removeQuestion(setIndex, i);
		}
	}
	public ArrayList<Question> getList(){
		return questions;
	}
	
	public int getIndex(){
		return setIndex;
	}
}
