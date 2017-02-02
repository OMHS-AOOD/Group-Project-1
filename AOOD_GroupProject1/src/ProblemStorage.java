import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
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
			ArrayList<String> fileLines = Files.readAllLines(f.getPath());
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Problems set file not found", "Error" , JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public ArrayList<ProblemSet> getArray(){
		return problems;
	}
	
	public void createProblemSet(String n){
		problems.add(new ProblemSet(n));
	}
}
