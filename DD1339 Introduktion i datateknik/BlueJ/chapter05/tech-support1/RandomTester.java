import java.util.Random;
/**
 * Write a description of class RandomTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomTester
{
    // instance variables - replace the example below with your own
    private Random randint;

    /**
     * Constructor for objects of class RandomTester
     */
    public RandomTester()
    {
        randint = new Random();
    }

    /**
     * Prints a random nummber between 1-6
     * @return A random integer between 1-6;
     */
    public int throwDice()
    {
        int throwDice = randint.nextInt(6)+1;
        return throwDice;
    }
    
    /**
     * Prints a random nummber
     * @return One random integer;
     */
    public int printOneRandom()
    {
        int oneRandom = randint.nextInt(10);
        return oneRandom;
    }
    
    /**
     * Prints multiple random nummber of your choosing
     * @parameter howMany The number of multiple numbers
     */
    public void printMultipleRandom(int howMany)
    {
        for (int i = 0; i < howMany; i++){
            int multipleRandom = randint.nextInt(100);
            System.out.println(multipleRandom);
        }
    }
    
    /**
     * Prints multiple random nummber of your choosing
     * @parameter howMany The number of multiple numbers
     */
    public int randomToMax(int max, int min)
    {  
        int number = randint.nextInt(max-min)+min;
        return number;
    }
}
