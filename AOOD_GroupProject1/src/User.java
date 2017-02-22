import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8480537917758471033L;
	private String name;
	private String password;
	private boolean randomize = false;
	private UserProblemStorage ups;
	public User(String n, String p, ProblemStorage ps){
		name = n;
		password = p;
		ups = new UserProblemStorage(ps);
	}
	public User(String n, ProblemStorage ps){
		name = n;
		password = "";
		ups = new UserProblemStorage(ps);
	}
	public String getName(){
		return name;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String p){
		password = p;
	}
	
	public boolean equals(User u){
		if(u.getName().equals(name)){
			return true;
		}
		return false;
	}
	
	public boolean getRandomize(){
		return randomize;
	}
	
	public void toggleRandomize(){
		randomize = !randomize;
	}
	public void setName(String newName) {
		name = newName;
		
	}
	
	public UserProblemStorage getUserPS(){
		return ups;
	}
	
	public void setUPS(ProblemStorage ps){
		ups = new UserProblemStorage(ps);
	}
}
