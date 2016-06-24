import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class main {
	public static int maxCoursePerSem;
	public static int numCourse;
	
	public static void main(String[] args) {
		
		//make sure this works with .in file later on...
		readInput("src/testCase.txt");

	}
	
	//reads and stores the input file
	public static void readInput(String fileName){
		File file = new File(fileName);
		String line;
		StringTokenizer st;
		
		ArrayList<CourseObj> arr = new ArrayList<CourseObj>();
		int i = 0;
		int courseCt = 0;
		try {
			Scanner scan = new Scanner(file);
			
			while(scan.hasNextLine()){
				line = scan.nextLine();
				st = new StringTokenizer(line);
				if(line.equals("-1 -1")){
					break;
				}else{
					//this resets everything because the only time a line will have 2 tokens is when you have n and m showing.
					if(st.countTokens() == 2){
						System.out.println("new school");
						arr.clear();
						i=0;
						courseCt = 0;
					}
					//Pulls out the individual tokens for each string. This will make it easier to sort through.
					while(st.hasMoreTokens()){
						if(i == 0){
							numCourse = Integer.parseInt(st.nextToken());
							i++;
						}else if(i == 1){
							maxCoursePerSem = Integer.parseInt(st.nextToken());
							i++;
						}else if((i-2)<numCourse){
							//skip the names for now...
							st.nextToken();
							i++;
						}else if(i >= numCourse+1){
							//CourseObj obj = new CourseObj(st.nextToken());
							CourseObj obj = null;
							int ct = 0;
							while(st.countTokens()>=0){
								if(ct==0){
									obj = new CourseObj(st.nextToken());
									ct++;
									i++;
								}else if(ct==1){
									obj.setSemester(st.nextToken());
									ct++;
									i++;
								}else if(ct == 2){
									obj.setNumPrereqs(Integer.parseInt(st.nextToken()));
									ct++;
									i++;
								}
								
							}
							arr.add(obj);
							
							courseCt++;
						}
						
						
						//System.out.println(st.nextToken());
						
					}
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error Reading File!");
			e.printStackTrace();
		}
		
	}
	
	public static void analyzeSchool(ArrayList<CourseObj> arr){
		
	}
	
}
