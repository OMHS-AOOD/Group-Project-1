import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.file.Files;
import javax.swing.JOptionPane;

public class ProblemStorage {
	private ArrayList<ProblemSet> problems;
	public ProblemStorage(){
		problems = new ArrayList<ProblemSet>();
		getProblemsFromFile();
	}
	public void getProblemsFromFile(){
		URL location = StudyHelper.class.getProtectionDomain().getCodeSource().getLocation();
		File f = new File(location.getPath().substring(0,  location.getPath().length()-4) + "src/ProblemSets");
		try {
			List<String> fileLines = Files.readAllLines(f.toPath(), Charset.defaultCharset());
			int x = 0;
			while(!fileLines.get(x).equals("START")){
				x++;
			}
			String tempName = "";
			int questionIndex = -1;
			for(int i = x; i < fileLines.size(); i++){
				String line = fileLines.get(i);
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
					q.setAnswer(line);
				}
				else if(prefix.equals("EX")){
					ProblemSet ps = problems.get(getPSIndexByName(tempName));
					Question q = ps.getQuestionByIndex(questionIndex);
					q.setExtra(line);
				}
				else if(prefix.equals("AT")){
					ProblemSet ps = problems.get(getPSIndexByName(tempName));
					Question q = ps.getQuestionByIndex(questionIndex);
					q.setType(line);
				}
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Problems set file not found", "Error" , JOptionPane.INFORMATION_MESSAGE);
		} 
		
	}
	
	public ArrayList<ProblemSet> getArray(){
		return problems;
	}
	
	public void addProblemSet(String n){
		if(getPSIndexByName(n) != -1){
			JOptionPane.showMessageDialog(null, "Problems set with that name already exists", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			problems.add(new ProblemSet(n));
		}
	}
	
	public void createProblemSet(String n){
		problems.add(new ProblemSet(n));
	}
	
	public int getPSIndexByName(String n){
		for(int i = 0; i < problems.size(); i++){
			if(problems.get(i).getName().equalsIgnoreCase(n)){
				return i;
			}
		}
		return -1;
	}
}
