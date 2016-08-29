import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;


public class FileHandler {
	BufferedReader br;
	BufferedWriter bw;
	public FileHandler(){
		try {
			br = new BufferedReader(new FileReader("Instructions.txt"));
			
		}
		catch(Exception e){
			
		}
	}
	public void update(){
		System.out.println(br);
	}
}
