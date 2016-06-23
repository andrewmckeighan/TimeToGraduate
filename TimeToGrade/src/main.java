import java.io.*;
import java.util.Scanner;

public class main {
	public int maxCoursePerSem;
	public int numCourse;
	
	public static void main(String[] args) {
		
		//make sure this works with .in file later on...
		readInput("src/testCase.txt");

	}
	
	//reads the input file
	public static void readInput(String fileName){
		File file = new File(fileName);
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				if(scan.nextLine() == "-1 -1"){
					break;
				}
				while(scan.hasNext()){
					System.out.println(scan.nextLine());
				}
				
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error Reading File!");
			e.printStackTrace();
		}
		
	}
	
}
