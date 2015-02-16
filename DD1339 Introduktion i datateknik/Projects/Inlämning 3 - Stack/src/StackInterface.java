/**
 * A interface of stack
 * 
 * @author Felix De Silva
 * @date 2014-02-06
 */

public interface StackInterface<T>{
	//Inserts the element on the top of the stack
	void push(T element);
	
	//Remove and return the top element of the stack. If stack is empty, error will happen
	T pop();
	
	//Returns the top element of the stack. If stack is empty, error will happen.
	T top();
	
	//Returns the size of the stack
	int size();
	
	//Indicates if the stack is empty
	boolean isEmpty();
	
}