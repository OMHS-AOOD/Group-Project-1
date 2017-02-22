
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProblemStorage {
	private ArrayList<ProblemSet> problems;
	private File f;
	private URL location;
	private ArrayList<UserProblemStorage> userData = new ArrayList<UserProblemStorage>();
	private int index = 0;
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
	        ois.close();
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
			problems.add(new ProblemSet(n, index,userData));
			index++;
			problems.get(problems.size() - 1).setAdmin(u);
			for(UserProblemStorage ups: userData){
				ups.addSet(problems.get(problems.size() - 1));
			}
			updateFile();
		}
	}
	
	public void addProblemSet(String n){
		if(checkForDomainName(n)){
			JOptionPane.showMessageDialog(null, "Problems set with that name already exists", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			problems.add(new ProblemSet(n, index, userData));
			index++;
			for(UserProblemStorage ups: userData){
				ups.addSet(problems.get(problems.size() - 1));
			}
			updateFile();
		}
	}
	public void addProblemSet(ProblemSet p){
		if(checkForDomainName(p.getName())){
			JOptionPane.showMessageDialog(null, "Problems set with that name already exists", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			problems.add(p);
			for(UserProblemStorage ups: userData){
				ups.addSet(problems.get(problems.size() - 1));
			}
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
		for(UserProblemStorage ups: userData){
			ups.removeSet(i);
		}
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
	public int getLength(){
		return problems.size();
	}
	public void loadUserData(ArrayList<UserProblemStorage> data){
		userData = data;
	}
	
}
