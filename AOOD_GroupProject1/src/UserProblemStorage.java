import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class UserProblemStorage implements Serializable{
	private ProblemStorage ps;
	private HashMap <String, HashMap<String, IntegerSet>> storage;
	public UserProblemStorage(ProblemStorage p){
		ps = p;
		storage = new HashMap <String, HashMap<String, IntegerSet>>();
		for(ProblemSet ps: p.getArray()){
			this.addSet(ps);
			for(Question q: ps.getList()){
				this.addQu(ps, q);
			}
		}
		
	}
	
	public void addAsked(ProblemSet p, Question q){
		storage.get(p.getName()).get(q.getPrompt()).addAsked();
	}
	public void addRight(ProblemSet p, Question q){
		storage.get(p.getName()).get(q.getPrompt()).addRight();
	}
	
	public void setAsked(ProblemSet p, Question q, int i){
		storage.get(p.getName()).get(q.getPrompt()).setAsked(i);
	}
	public void setRight(ProblemSet p, Question q, int i){
		storage.get(p.getName()).get(q.getPrompt()).setRight(i);
	}
	public void addSet(ProblemSet p){
		storage.put(p.getName(), new HashMap<String, IntegerSet>());
		for(Question q: p.getList()){
			addQu(p, q);
		}
	}
	public void addQu(ProblemSet p, Question q){
		storage.get(p.getName()).put(q.getPrompt(), new IntegerSet());
	}
	
	public Integer getAsked(ProblemSet p, Question q){
		return storage.get(p.getName()).get(q.getPrompt()).getAsked();
	}
	public Integer getRight(ProblemSet p, Question q){
		return storage.get(p.getName()).get(q.getPrompt()).getRight();
	}
	
	public void removeSet(ProblemSet p){
		storage.remove(p.getName());
		
	}
	
	public void removeQuestion(ProblemSet p, Question q){
		storage.get(p.getName()).remove(q.getPrompt());
	}
	
	public int getArrayLength(){
		return storage.size();
	}
	public int getQLength(ProblemSet p){
		return storage.get(p.getName()).size();
	}
	
	public void updateSetName(ProblemSet p, String newName){
		HashMap<String, IntegerSet> qus = storage.get(p.getName());
		storage.remove(p.getName());
		storage.put(newName, qus);
	}
	public void updateQuName(ProblemSet p, Question q, String newPrompt){
		IntegerSet iSet =  storage.get(p.getName()).get(q.getPrompt());
		storage.get(p.getName()).remove(q.getPrompt());
		storage.get(p.getName()).put(newPrompt, iSet);
	}
}
