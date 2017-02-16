import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2748485916265420653L;
	private String prompt, answer, extra, type;
	private ImageIcon im;
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
	
	public String getAns(){
		return answer;
	}
	public String getPrompt(){
		return prompt;
	}
	public String getExtra(){
		return extra;
	}
	
	
	public void storeImg(File f){
		im = new ImageIcon(f.getAbsolutePath());
	}
	
	public ImageIcon getImage(){
		return im;
	}

}
