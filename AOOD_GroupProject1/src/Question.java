
public class Question {
	private String prompt, answer, extra, type;
	public Question(String p, String a, String e){
		prompt = p;
		answer = a;
		extra = e;
	}
	public Question(String p, String a){
		prompt = p;
		answer = a;
		extra = "";
	}
	public Question(String p){
		prompt = p;
	}
	public void setPrompt(String p){
		prompt = p;
	}
	public void setAnswer(String a){
		answer = a;
	}
	public void setExtra(String e){
		extra = e;
	}
	public void setType(String n){
		type = n;
	}
}
