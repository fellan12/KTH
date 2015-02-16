


import java.util.NoSuchElementException;

/**
 * An iterator over vertices in a Graph object.
 * 
 * @author Stefan Nilsson
 * @version 2013-01-01
 */

public interface VertexIterator {
	/**
	 * Returns true if the iteration has more elements.
	 * 
	 * @return true if the iteration has more elements
	 */
	boolean hasNext();

	/**
	 * Returns the next element in the iteration.
	 * 
	 * @return the next element in the iteration
	 * @throws NoSuchElementException
	 *             if the iteration has no more elements
	 */
	int next() throws NoSuchElementException;
}
