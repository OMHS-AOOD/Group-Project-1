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
	private String path;
	private String imgName;
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
		String check = "";
		if(im != null){
			check = JOptionPane.showInputDialog("Are you sure you want to replace the current image?(Y/N)");	
			if(check == null){
				return;
			}
			check = check.toUpperCase();
		}
		else{
			check = "Y";
		}
		if(check.equals("Y")){
			im = new ImageIcon(f.getAbsolutePath());
			path = f.getAbsolutePath();
			imgName = f.getName();
		}
		
	}
	public void storeImg(){
		if(path != null){
			File f = new File(path);
			if(f.exists() && (path.split(".")[path.split(".").length-1].equalsIgnoreCase("png") || path.split(".")[path.split(".").length-1].equalsIgnoreCase("png"))){
				im = new ImageIcon(path);
				imgName = path.split("\\\\")[path.split("\\\\").length-1];
			}
			else{
				imgName = "None";
			}
			

			
			
		}

	}
	
	public ImageIcon getImage(){
		return im;
	}
	public String getImgName(){
		return imgName;
	}

}
