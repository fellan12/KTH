import java.util.List;
import java.util.LinkedList;

/**
 * A hash table of strings.
 * 
 * @author Stefan Nilsson och Felix De Silva
 * @date 10 feb 2014
 */
public class StringHash implements StringDictionary { 
    private List<String>[] table;

    /**
     * Creates a hash table with the given capacity.
     * 
     * @throws IllegalArgumentException if capacity <= 0.
     */
    public StringHash(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity=" + capacity);

        // We want to do the following:
        //
        //     table = new LinkedList<String>[capacity];
        //
        // However, that won't compile ("generic array creation")
        // since Java generics and arrays don't get along very well.
        // Instead we need to do the following:
        //
        //     table = new LinkedList[capacity];
        //
        // The above will compile, but with a warning. The proper
        // approach is to document why the warning can be safely
        // ignored and then suppress the warning. Thus:

        /* 
         * This array will contain only LinkedList<String>
         * instances, all created in the add method. This
         * is sufficient to ensure type safety.
         */
        @SuppressWarnings("unchecked") // for this declaration only
        LinkedList<String>[] t = new LinkedList[capacity];
        
        table = t;
    }

    /**
     * Adds the given string to this dictionary.
     * Returns <code>true</code> if the dictionary
     * did not already contain the given string.
     * 
     * and if the string is empty "" or null
     * it should not work
     *
     * Complexity: O(1) expected time.
     */
    @Override
    public boolean add(String s) {
        if (s == null || s.equals("")){
        	return false;
        }
        
        int hashc = Math.abs(s.hashCode() % table.length);
        
        
        if (table[hashc]== null){
        	table[hashc] = new LinkedList<String>();
        	table[hashc].add(s);
        	return true;
        }
        if (table[hashc].contains(s)){
        	return false;
        }else{
        	table[hashc].add(s);
        	return true;
        }
    }

    /**
     * Removes the given string from this dictionary
     * if it is present. Returns <code>true</code> if
     * the dictionay contained the specified element.
     *
     * Complexity: O(1) expected time.
     */
    @Override
    public boolean remove(String s) {
        if (s == null || s.equals("")){
        	return false;
        }
        
        int hashc = Math.abs(s.hashCode() % table.length);
 
    	if (table[hashc] != null && table[hashc].contains(s)){
    		table[hashc].remove(s);
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * Returns <code>true</code> if the string is
     * in this dictionary.
     *
     * Complexity: O(1) expected time.
     */
    @Override
    public boolean contains(String s) {
        if (s == null || s.equals("")){
        	return false;
        }
        
        int hashc = Math.abs(s.hashCode() % table.length);
        
        if (table[hashc] != null && table[hashc].contains(s)){
        	return true;
        }else{
        	return false;
        }
    }
}