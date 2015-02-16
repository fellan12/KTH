import java.util.*;
public class Student {
	
	private String name;

	public Student(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public static void main(String[] args) {
		ArrayList<Student> myList = new ArrayList<Student>();
		Student s1 = new Student("Felix");
		Student s2 = new Student("BitchPer");
		myList.add(s1);
		myList.add(s2);
		
		for(Object st : myList) {
			System.out.println(st);
			}
	}
}
