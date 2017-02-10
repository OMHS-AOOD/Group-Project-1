import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.file.Files;
import javax.swing.JOptionPane;

public class ProblemStorage {
	private ArrayList<ProblemSet> problems;
	private File f;
	private URL location;
	public ProblemStorage(){
		problems = new ArrayList<ProblemSet>();
		location = StudyHelper.class.getProtectionDomain().getCodeSource().getLocation();
		f = new File(location.getPath().substring(0,  location.getPath().length()-4) + "src/Problems");
		getProblemsFromFile();
		updateFile();
	}
	
	public void getProblemsFromFile(){
		try {
	    	
	    	FileInputStream fis = new FileInputStream(f);
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    ArrayList<ProblemSet> p = (ArrayList<ProblemSet>) ois.readObject();
	        problems = p;
		}
	    catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(null, "Error when trying to read problems file", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	
	public ArrayList<ProblemSet> getArray(){
		return problems;
	}
	
	public void addProblemSet(String n, String u){
		if(checkForDomainName(n)){
			JOptionPane.showMessageDialog(null, "Problems set with that name already exists", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			problems.add(new ProblemSet(n));
			problems.get(problems.size() - 1).setAdmin(u);
			updateFile();
		}
	}
	
	public void addProblemSet(String n){
		if(checkForDomainName(n)){
			JOptionPane.showMessageDialog(null, "Problems set with that name already exists", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			problems.add(new ProblemSet(n));
			updateFile();
		}
	}
	
	
	public int getPSIndexByName(String n){
		for(int i = 0; i < problems.size(); i++){
			if(problems.get(i).getName().equalsIgnoreCase(n)){
				return i;
			}
		}
		return -1;
	}
	
	public ProblemSet getPSByIndex(int n){
		return problems.get(n);
	}
	
	public void removeDomain(int i){
		problems.remove(i);
		updateFile();
	}
	
	public void updateFile(){
		try {
	    	FileOutputStream fos = new FileOutputStream(f);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(problems);
			oos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error when writing to problems file", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public boolean checkForDomainName(String name){
		for(ProblemSet p: problems){
			if(p.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
}
