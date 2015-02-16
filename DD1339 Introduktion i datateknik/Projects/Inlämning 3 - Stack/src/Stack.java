/**
 * A Stack class that works according to description
 * 
 * @author Felix De Silva
 * @date 2014-02-16
 */
public class Stack<T> implements StackInterface<T> {
	//__Field Instances__
	private int size;
	private StackElement<T> first;

	/*
	 * The constructor of the StackElement
	 */
	private static class StackElement<T> {
        public T stackElement;
        public StackElement<T> next;
        
        /*
         * Construktor för ListElement
         */
        public StackElement(T element) {
            this.stackElement = element;
            this.next = null;
        }
        
	}
        
        /*
         * Constructor of the Stack
         */
        public Stack(){
			first = null;
			size = 0;
		}
        
      /*
       * Inserts the element on the top of the stack
       * 
       * @parameter element - the element that is going on top of the stack
       */
		@Override
		public void push(T element) {
			StackElement<T> top = new StackElement<T>(element);
			if (size ==0){
		    	   first = top;
		    	   size++;
		       }else{
		        top.next = first;						
		        first = top;							
		        size++;									
		       }		
		}
		
		/*
		 * Remove and return the top element of the stack. 
		 * If stack is empty, error will happen
		 * 
		 * @return newTop.stackElement - the new top element
		 */
		@Override
		public T pop() {
			StackElement<T> newTop = first;
			first = first.next;
			size--;
			return newTop.stackElement;
		}
	
		/*
		 * Returns the top element of the stack. 
		 * If stack is empty, error will happen.
		 * 
		 * @return first.stackElement - the top element of the stack
		 */
		@Override
		public T top() {
			return first.stackElement;
		}
		
		/*
		 * Returns the size of the stack
		 * 
		 * @return size - the size of the stack
		 */
		@Override
		public int size() {
			return size;
		}
	
		/*
		 * Indicates if the stack is empty
		 * 
		 * @return true/false - if the stack is true/false depends on the stack
		 */
		@Override
		public boolean isEmpty() {
			if (first==null){	
	        	return true;
	        }else{
	        	return false;
	        }
		}
}
