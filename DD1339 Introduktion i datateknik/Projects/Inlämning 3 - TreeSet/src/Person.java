/**
 * 
 * A TreeSet that is functional and sorts persons from left to right by age
 * 
 * @author Felix De Silva
 * @date 2014-02-05 
 */

import java.util.TreeSet;

public class Person implements Comparable<Person>{
	//__Field Instances__//
	//The age of the person
	private int age;
	//The name of the person
	private String name;
	
	/*
	 * The constructor of the person class
	 * 
	 * @parameter name - the name of the person
	 * @parameter age - the age of the person
	 */
	public Person(int age, String name){
		this.age = age;
		this.name = name;
	}
	
	/*
	 * The main method of the Person class
	 * 
	 * Prints out the TreeSet of persons
	 * 
	 * @parameter args
	 */
	public static void main(String[] args){
		TreeSet<Person> persons = new TreeSet<>();
		Person kashi = new Person(21, "Kashi");
		Person felix = new Person(22, "Felix");
		
		persons.add(felix);
		persons.add(kashi);
		
		System.out.println(persons);
	}
	
	/*
	 * The toString method for the person
	 * 
	 * @return s - the name and the age of the person
	 */
	@Override
	public String toString(){
	
		String s = name;
		
		return s;
	}
	
	/*
	 * The age of the Person
	 * 
	 * @return age - the age of the person
	 */
	public int getAge(){
		return age;
	}
	
	/*
	 * 
	 */
	@Override
	public int compareTo(Person person) { 
		if(person.getAge() < this.age){		
			return 1;
		}else if(person.getAge() == this.age){
			return 0;
		}else{
			return -1;
		}
		
	}
}
