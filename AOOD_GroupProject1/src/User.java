
public class User {
	private String name;
	private String password;
	public User(String n, String p){
		name = n;
		password = p;
	}
	public User(String n){
		name = n;
		password = "";
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
}
