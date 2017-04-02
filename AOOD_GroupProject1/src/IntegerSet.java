import java.io.Serializable;

public class IntegerSet implements Serializable{
	private int asked, right;
	public IntegerSet(){
		asked = 0;
		right = 0;
	}
	public void addAsked(){
		asked++;
	}
	public void setAsked(int a){
		if(a >= 0){
			asked = a;
		}
		else{
			
		}
		
	}
	public int getAsked(){
		return asked;
	}
	public void addRight(){
		right++;
	}
	public void setRight(int r){
		if(r >= 0){
			right = r;
		}
		else{
			
		}
		
	}
	public int getRight(){
		return right;
	}
}
