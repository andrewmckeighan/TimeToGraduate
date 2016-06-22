
public class CourseObj {

	public String name;
	public String semester;
	public int numPrereq;
	int priority;
	/*
	 * The whole idea is that it will make the objects based off of the name
	 * and then as it goes into the specifics of each class, it will fill in 
	 * the rest of the information.
	 */
	
	//constructor for the individual courses
	public CourseObj(String name){
		this.name = name;
	}
	
	//returns the name of the course
	public String getName(){
		return name;
	}
	
	//sets the name of the course
	public void setName(String name){
		this.name = name;
	}
	
	//returns the semester that you can take the course
	public String getSemester(){
		return semester;
	}
	
	//sets the semester value
	public void setSemester(String semester){
		this.semester = semester;
	}
	
	//returns the number of prerequisites for the class.
	public int getNumPrereqs(){
		return numPrereq;
	}
	
	//sets the number of prerequisites you need before you can take the class.
	public void setNumPrereqs(int numPrereq){
		this.numPrereq = numPrereq;
	}
	/*
	 * Priority basically is the number of classes that need this class as a prerequisite.
	 * For Example: if two courses have this one as a prerequisite, then its priority 2.
	 * If 0 classes require this course, then it is priority 0.
	 * A course with a higher priority number should be chosen over one with lower priority.
	 */
	public int getPriority(){
		return priority;
	}
	
	//sets the priority.
	public void setPriority(int priority){
		this.priority = priority;
	}
	
	
	
	
}
