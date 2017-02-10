import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.file.Files;
import javax.swing.JOptionPane;

public class ProblemStorage {
	private ArrayList<ProblemSet> problems;
	private File f,f2;
	private URL location;
	public ProblemStorage(){
		problems = new ArrayList<ProblemSet>();
		location = StudyHelper.class.getProtectionDomain().getCodeSource().getLocation();
		f = new File(location.getPath().substring(0,  location.getPath().length()-4) + "src/ProblemSets");
		f2 = new File(location.getPath().substring(0,  location.getPath().length()-4) + "src/Problem");
		getProblemsFromFile();
		updateFile();
	}
	public void getProblemsFromFile(){
		
		try {
			List<String> fileLines = Files.readAllLines(f.toPath(), Charset.defaultCharset());
			int x = 0;
			while(!fileLines.get(x).equals("START")){
				x++;
			}
			String tempName = "";
			int questionIndex = -1;
			for(int i = x; i < fileLines.size(); i++){
				String line = fileLines.get(i).trim();
				if(line.length() > 3){
					String prefix = line.substring(0, 2);
					String data = line.substring(3);
					if(prefix.equals("DN")){
						addProblemSet(data);
						tempName = data;
						questionIndex = -1;
					}
					else if(prefix.equals("DC")){
						ProblemSet ps = problems.get(getPSIndexByName(tempName));
						ps.setAdmin(data);
					}
					else if(prefix.equals("PS")){
						ProblemSet ps = problems.get(getPSIndexByName(tempName));
						ps.setPassword(data);
					}
					else if(prefix.equals("QU")){
						ProblemSet ps = problems.get(getPSIndexByName(tempName));
						ps.addQuestion(new Question(data));
						questionIndex++;
					}
					else if(prefix.equals("AN")){
						ProblemSet ps = problems.get(getPSIndexByName(tempName));
						Question q = ps.getQuestionByIndex(questionIndex);
						q.setAnswer(data);
					}
					else if(prefix.equals("EX")){
						ProblemSet ps = problems.get(getPSIndexByName(tempName));
						Question q = ps.getQuestionByIndex(questionIndex);
						q.setExtra(data);
					}
					else if(prefix.equals("AT")){
						ProblemSet ps = problems.get(getPSIndexByName(tempName));
						Question q = ps.getQuestionByIndex(questionIndex);
						q.setType(data);
					}
				}
				
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Problems set file not found", "Error" , JOptionPane.INFORMATION_MESSAGE);
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
	    	FileOutputStream fos = new FileOutputStream(f2);
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
