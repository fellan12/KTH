/**
 * the class for Quicksort with middle pivot
 * 
 * @author Felix De Silva
 * @date 28 feb 2014
 */
public class Quicksort implements IntSorter {

	@Override
	public void sort(int[] v) {
		qsort(v, 0, (v.length-1));

	}
	
	// Sorts the elements of the subvector v[first..last].
	private void qsort(int[] v, int first, int last) {
	    if (first >= last) // Less than two elements
	        return;

	    // Choose a pivot element.
	    int p = v[last -(last - first)/2];

	    // Partition the elements so that every number of
	    // v[first..mid] <= p and every number of v[mid+1..last] > p.
	    int[] mid = partition(v, first, last, p);

	    qsort(v, first, mid[0]);
	    qsort(v, mid[1], last);
	}
	
	/**
	 * Reorders the elements of the subarray v[first..last] so that
	 * all elements in v[first..low-1] are less than pivot,
	 * all elements in v[low..high] are equal to pivot,
	 * all elements in v[high+1..last] are greater than pivot.
	 * 
	 * Precondition: first < last.
	 */
	private int[] partition(int[] v, int first, int last, int pivot) {
	    int low = first;
	    int mid = first;
	    int high = last;

	    while (mid <= high) {
	        // Invariant:
	        //  - v[first..low-1] < pivot
	        //  - v[low..mid-1] = pivot
	        //  - v[mid..high] are unknown
	        //  - v[high+1..last] > pivot
	        //
	        //       < pivot   = pivot      unknown     > pivot
	        //     -----------------------------------------------
	        // v: |          |          |a            |           |
	        //     -----------------------------------------------
	        //     ^          ^          ^           ^           ^
	        //    first      low        mid         high        last
	        //
	        int a = v[mid];
	        if (a < pivot) {
	            v[mid] = v[low];
	            v[low] = a;
	            low++;
	            mid++;
	        } else if (a == pivot) {
	            mid++;
	        } else { // a > pivot
	            v[mid] = v[high];
	            v[high] = a;
	            high--;
	        }
	        
	    }
	    return new int[]{low-1,high+1};
	}


}
