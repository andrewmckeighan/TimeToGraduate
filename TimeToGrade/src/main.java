import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class main {
	public static int maxCoursePerSem;//m
	public static int numCourse;//n
	
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
					int flag = 0;
					while(st.hasMoreTokens()){
						if(i == 0){
							numCourse = Integer.parseInt(st.nextToken());
							i++;
						}else if(i == 1){
							maxCoursePerSem = Integer.parseInt(st.nextToken());
							i++;
						}else if((i-2)<numCourse){
							CourseObj name = new CourseObj(st.nextToken());//makes the object. Contains only name
							arr.add(name);//adds object to arraylist
							i++;
						}else if(i >= numCourse+2){
							int curr = 0;
							int ct = 0;
							while((3+flag)> ct){
								if(ct==0){//the name of the course
									String tkn = st.nextToken();
									for(int k =0; k< arr.size(); k++){
										if(arr.get(k).getName().equals(tkn)){
											curr = k;
										}
									}
									ct++;
								}else if(ct==1){ //the semester of the course
									arr.get(curr).setSemester(st.nextToken());
									ct++;
									
								}else if(ct == 2){//how many prerequisites the course has
									flag = Integer.parseInt(st.nextToken());
									arr.get(curr).setNumPrereqs(flag);
									ct++;
								}else if(ct > 2){//if a course has prerequisites, the counter will add them.
									String name = st.nextToken();
									arr.get(curr).addPrereqName(name);
									for(int k =0; k< arr.size(); k++){
										if(arr.get(k).getName().equals(name)){
											arr.get(k).addPriority();
										}
									}
									ct++;
								}
								
							}
							
							courseCt++;
							break;
						}
						
						
					}
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error Reading File!");
			e.printStackTrace();
		}
		
	}
	
	//Search through each course object and look in the prereq names for the names of classes to be able to assign them a priority.
	//Needs to be redone!!!
	public static CourseObj searchForClass(ArrayList<CourseObj> arr , String key){
		for(int j =0; j<arr.size(); j++){
			if(arr.get(j).getName().equals(key)){
				return arr.get(j);
			}
		}
		return null;
	}
	
}
