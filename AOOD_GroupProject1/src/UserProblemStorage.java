import java.util.ArrayList;

public class UserProblemStorage {
	private ProblemStorage ps;
	private ArrayList<ArrayList<Integer>> numAsked, numRight;
	public UserProblemStorage(ProblemStorage p){
		ps = p;
		numAsked = new ArrayList<ArrayList<Integer>>();
		numRight = new ArrayList<ArrayList<Integer>>();
		
	}
	
	public void addAsked(int setIndex, int qIndex){
		numAsked.get(setIndex).set(qIndex, numAsked.get(setIndex).get(qIndex)+1);
	}
	public void addRight(int setIndex, int qIndex){
		numRight.get(setIndex).set(qIndex, numRight.get(setIndex).get(qIndex)+1);
	}
	public void addSet(ProblemSet p){
		numAsked.add(new ArrayList<Integer>());
		numRight.add(new ArrayList<Integer>());
		for(int i = 0; i < p.getLength(); i++){
			numAsked.get(numAsked.size()-1).add(0);
			numRight.get(numRight.size()-1).add(0);
		}
	}
	public void addQu(int setIndex){
		numAsked.get(setIndex).add(0);
		numRight.get(setIndex).add(0);
	}
	
	public Integer getAsked(int setIndex, int qIndex){
		return numAsked.get(setIndex).get(qIndex);
	}
	public Integer getRight(int setIndex, int qIndex){
		return numRight.get(setIndex).get(qIndex);
	}
	
	public void removeSet(int setIndex){
		numAsked.remove(setIndex);
		numRight.remove(setIndex);
	}
	
	public void removeQuestion(int setIndex, int qIndex){
		numAsked.get(setIndex).remove(qIndex);
		numRight.get(setIndex).remove(qIndex);
	}
}
