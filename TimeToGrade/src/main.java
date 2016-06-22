import java.io.*;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//reads the input file
	public static void readInput(String fileName){
		try {
			FileReader file = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR : Unable To Read Input File!!!");
			e.printStackTrace();
		}
	}
	
}
