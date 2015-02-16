/**
 * A singly linked list.
 * 
 * @author Felix
 * @version	2014-01-30
 */
public class LinkedList<T> { 
    private ListElement<T> first;   // First element in list.
    private ListElement<T> last;    // Last element in list.
    private int size;               // Number of elements in list.
    
    /**
     * A list element.
     */
    private static class ListElement<T> {
        public T data;
        public ListElement<T> next;
        
        /*
         * Konstruktor för ListElement
         */
        public ListElement(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    /**
     * This TEST METHOD returns true if the following invariants hold:
     * <ul>
     *   <li> size equals the number of list elements, </li>
     *   <li> if size == 0, first == null and last == null, </li>
     *   <li> if size > 0, first != null and last != null, </li>
     *   <li> if size == 1, first == last, </li>
     *   <li> last.next == null. </li>
     * </ul>
     * 
     * Tidskomplexitet O(n)
     */
    public boolean isHealthy() {
    	ListElement<T> list = first;
        int number = 0;
        while(list != null) {
            number++; 
            list = list.next;
        }
        if(size != number) {
            return false;
        }
        if(size == 0) {
            if(first!=null||last!=null) 
                return false;
        }
        if(size > 0) {
            if(first==null||last==null) 
                return false;
        }
        if(size == 1) {
            if(first != last)
                return false;
        }
        if(!isEmpty()) {
            if(last.next != null) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Konstruktor för LinkedList
     * 
     * Creates an empty list.
     */
    public LinkedList() {
        first = null;											//Sets first to null
        last = null;											//Sets last to null
    	size = 0;												//sets size to 0
    }

    /**
     * Inserts the given element at the beginning of this list.
     * 
     * Tidskomplexitet O(1) (konstant tid)
     */
    public void addFirst(T element) {
    	ListElement<T> newFirst = new ListElement<T>(element);	//Creates an ListElement Object
    	if (size ==0){
    	   first = newFirst;
    	   last = newFirst;
    	   size++;
       }else{
    	
        newFirst.next = first;									//Sets so current newFirst.next points to first
        first = newFirst;										//Sets so newFirst is the "new" first
        size++;													//Increase size by one
       }
    }

    /**
     * Inserts the given element at the end of this list.
     * 
     * Tidskomplexitet O(1) (konstant tid)
     */
    public void addLast(T element) {
        ListElement<T> newLast = new ListElement<T>(element);	//Creates an ListElement Object
        if (size ==0){
     	   first = newLast;
     	   last = newLast;
     	   size++;
        }else{
        last.next = newLast;									//Sets so current last.next points to newLast
        last = newLast;											//Sets so the newLast is the "new" last
        size++;													//Increase size by one
        }
    }

    /**
     * Returns the first element of this list.
     * Returns <code>null</code> if the list is empty.
     * 
     * Tidskomplexitet O(1) (konstant tid)
     */
    public T getFirst() {
        return first.data;										//returns the data in first
    }

    /**
     * Returns the last element of this list.
     * Returns <code>null</code> if the list is empty.
     * 
     * Tidskomplexitet O(1) (konstant tid)
     */
    public T getLast() {
        return last.data;										//returns the data in last
    }

    /**
     * Returns the element at the specified position in this list.
     * Returns <code>null</code> if <code>index</code> is out of bounds.
     * 
     * Tidskomplexitet O(n) (Värsta fall)
     */
    public T get(int index) {
        if (index<0 || index >= size){							//checks for illegal index	
        	return null;
        }else{
        	ListElement<T> element = first;						//Sets element to first
        	for(int i=0; i<index;i++){							//Iterates thourgh index value
        		element = element.next; 						//Sets	element to element.next 
        	}
        	return element.data;								//returns element.data
        }  
    }

    /**
     * Removes and returns the first element from this list.
     * Returns <code>null</code> if the list is empty.
     * 
     * Tidskomplexitet O(1) (konstant tid)
     */
    public T removeFirst() {
        if (size > 1){											//Checks that list is not empty
        	ListElement<T> oldFirst = first;					//Sets oldFirst to first 
            first = first.next;									//Sets first to first.next
            size--;												//Decrease size by one
            return oldFirst.data;								//return oldFirst.data
        }else if (size == 1){
        	ListElement<T> oldFirst = first;					//Sets oldFirst to first 
            first = null;										//Sets first to null
            last = null;										//Sets last to null
            size--;												//Decrease size by one
            return oldFirst.data;								//return oldFirst.data
        }else{
        	return null;
        }
    }
    
    /**
     * Removes and returns the first element from this list.
     * Returns <code>null</code> if the list is empty.
     * 
     * Tidskomplexitet O(1) (konstant tid)
     */
    public T removeFirst2(){
        if (size != 0){											//Checks that list is not empty
        	ListElement<T> oldFirst = first;					//Sets oldFirst to first 
            first = first.next;									//Sets first to first.next
            size--;												//Decrease size by one
            return oldFirst.data;								//return oldFirst.data
         }else{
        	return null;
        }
    }

    /**
     * Removes all of the elements from this list.
     * 
     * Tidskomplexitet O(1) (konstant tid)
     */
    public void clear() {										//Resets the list to empty
    	first = null;
    	last = null;
    	size = 0;
    }

    /**
     * Returns the number of elements in this list.
     * 
     * Tidskomplexitet O(1) (konstant tid)
     */
    public int size() {											
        return size;
    }

    /**
     * Returns <code>true</code> if this list contains no elements.
     * 
     * Tidskomplexitet O(1) (konstant tid)
     */
    public boolean isEmpty() {
        if (size == 0){											//Checks if the list is empty
        	return true;
        }else{
        	return false;
        }
    }

    /**
     * Returns a string representation of this list. The string
     * representation consists of a list of the elements enclosed in
     * square brackets ("[]"). Adjacent elements are separated by the
     * characters ", " (comma and space). Elements are converted to
     * strings by the method toString() inherited from Object.
     * 
     * Tidskomplexitet O(n^2) (Värsta fall)
     */
    @Override
    public String toString() {
    	ListElement<T> listOfElements = first;					//Sets listOfElements to first
    	String elements = "[";									//Sets the first bracket of the element list			
        while(listOfElements!=null){
        	elements +=  listOfElements.data;					//Adds the element in a list
        	if (listOfElements.next != null){					//checks that we are not at last element
        		elements += ", ";								//Adds the comma and space
        	}
        	listOfElements = listOfElements.next; 				//Sets listOfElements to next value	
        }
        elements += "]";										//Sets the end bracket
        
        return elements;										//return elements
    }
}