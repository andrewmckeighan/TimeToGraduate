import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static int maxCoursePerSem;// m
	public static int numCourse;// n
	public static ArrayList<CourseObj> arr;

	public static void main(String[] args) {

		// make sure this works with .in file later on...
		readInput("src/testCase.txt");

	}

	// reads and stores the input file
	public static void readInput(String fileName) {
		File file = new File(fileName);
		String line;
		StringTokenizer st;

		arr = new ArrayList<CourseObj>();
		int i = 0;
		int chk = 0;
		try {
			Scanner scan = new Scanner(file);

			while (scan.hasNextLine()) {
				line = scan.nextLine();
				st = new StringTokenizer(line);
				if (line.equals("-1 -1")) {
					analyze(arr, maxCoursePerSem);
					break;
				} else {
					// this resets everything because the only time a line will
					// have 2 tokens is when you have n and m showing.
					if (st.countTokens() == 2) {
						if (chk > 0) {
							// Analyze the last school
							analyze(arr, maxCoursePerSem);
						}
						System.out.println("new school");
						chk++;
						arr.clear();
						i = 0;
					}
					// Pulls out the individual tokens for each string. This
					// will make it easier to sort through.
					int flag = 0;
					while (st.hasMoreTokens()) {
						if (i == 0) {
							numCourse = Integer.parseInt(st.nextToken());
							i++;
						} else if (i == 1) {
							maxCoursePerSem = Integer.parseInt(st.nextToken());
							i++;
						} else if ((i - 2) < numCourse) {
							CourseObj name = new CourseObj(st.nextToken());// makes
																			// the
																			// object.
																			// Contains
																			// only
																			// name
							arr.add(name);// adds object to arraylist
							i++;
						} else if (i >= numCourse + 2) {
							int curr = 0;
							int ct = 0;
							while ((3 + flag) > ct) {
								if (ct == 0) {// the name of the course
									String tkn = st.nextToken();
									for (int k = 0; k < arr.size(); k++) {
										if (arr.get(k).getName().equals(tkn)) {
											curr = k;
										}
									}
									ct++;
								} else if (ct == 1) { // the semester of the
														// course
									arr.get(curr).setSemester(st.nextToken());
									ct++;

								} else if (ct == 2) {// how many prerequisites
														// the course has
									flag = Integer.parseInt(st.nextToken());
									arr.get(curr).setNumPrereqs(flag);
									ct++;
								} else if (ct > 2) {// if a course has
													// prerequisites, the
													// counter will add them.
									String name = st.nextToken();
									arr.get(curr).addPrereqName(name);
									for (int k = 0; k < arr.size(); k++) {
										if (arr.get(k).getName().equals(name)) {
											arr.get(k).addPriority();
										}
									}
									ct++;
								}

							}

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
	//This method is what determines which class to take and when, it works for the examples given on the worksheet if you input as .txt file.
	public static void analyze(ArrayList<CourseObj> arr, int maxCourses) {
		int countSem = 0;
		sort(arr);
		String semester = "F";
		ArrayList<CourseObj> temp = new ArrayList<CourseObj>();
		while (arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				if (arr.get(i).getNumPrereqs() == 0
						&& (arr.get(i).getSemester().equals(semester) || arr.get(i).getSemester().equals("B"))) {
					temp.add(arr.get(i));
				}
			}

			while (temp.size() > maxCourses) {
				temp.remove(temp.size() - 1);
			}

			for (int i = 0; i < temp.size(); i++) {
				int ele = 0;
				for (int j = 0; j < arr.size(); j++) {
					if (arr.get(j).getName().equals(temp.get(i).getName())) {
						System.out.println(arr.get(j).getName());
						ele = j;
					} else if (arr.get(j).getNumPrereqs() > 0) {
						arr.get(j).removePrereqName(temp.get(i).getName());
					}
				}
				arr.remove(ele);
			}
			if(semester.equals("F")){
				semester = "S";
			}else{
				semester = "F";
			}
			countSem++;
			temp.clear();
		}
		System.out.println("It took "+countSem+" semesters to graduate!");
	}

	public static void sort(ArrayList<CourseObj> arr) {
		int length = arr.size();
		ArrayList<CourseObj> tmp = new ArrayList<CourseObj>();
		mergeSort(arr, tmp, 0, length - 1);
	}

	public static void mergeSort(ArrayList<CourseObj> arr, ArrayList<CourseObj> tmp, int low, int high) {
		if (low < high) {
			int middle = (low + high) / 2;
			mergeSort(arr, tmp, low, middle);
			mergeSort(arr, tmp, middle + 1, high);
			merge(arr, tmp, low, middle + 1, high);
		}

	}

	public static void merge(ArrayList<CourseObj> arr, ArrayList<CourseObj> tmp, int low, int middle, int high) {
		int lowEnd = middle - 1;
		int k = low;
		int num = high - low + 1;

		while (low <= lowEnd && middle <= high) {
			if (arr.get(low).getPriority() <= arr.get(middle).getPriority()) {
				tmp.add(k++, arr.get(middle++));
			} else {
				tmp.add(k++, arr.get(low++));
			}
		}
		while (low <= lowEnd) {
			tmp.add(k++, arr.get(low++));
		}
		while (middle <= high) {
			tmp.add(k++, arr.get(middle++));
		}

		for (int i = 0; i < num; i++, high--) {

			arr.set(high, tmp.get(high));
		}

	}

}
