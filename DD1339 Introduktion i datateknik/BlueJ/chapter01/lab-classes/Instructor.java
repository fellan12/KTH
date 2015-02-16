/**
 * Instructor is the one who is leading the class.
 * An instructor is a subclass of Person, and therefore has a name and contact details.
 */
public class Instructor extends Person
{
    private int salary;
    private int contractLength;
    
    /**
     * Create a new instructor.
     */
    public Instructor(String name, String id, int salary, int contractLength){
        super(name, id);
        this.salary = salary;
        this.contractLength = contractLength;
    }
    
    /**
     * Return the salary of the instructor.
     */
    public int getSalary(){
        return salary;
    }
    
    /**
     * Return how many years the instructors contract is valid.
     */
    public int getContractLength(){
        return contractLength;
    }
}
