/**
 * the class for SortTest that tests that a sortingalgorithm works
 * as it should do by comparing it to Arrays sort
 * 
 * @author Felix De Silva
 * @date 28 feb 2014
 */
import java.util.*;
public class SortTest {
	
	/**
	 * Checks that the sorter is working
	 * 
	 * @param sorter
	 * @return
	 */
	public boolean isBroken(IntSorter sorter){
		//Empty
		Data zero = new Data(0, 10, Data.Order.RANDOM);

		//100 one's element
		Data one = new Data(100, 1, Data.Order.RANDOM);

		//Random 100 element
		Data rand = new Data(100, 100,Data.Order.RANDOM);

		//100 element ascending
		Data asc = new Data(100, 100, Data.Order.ASCENDING);

		//100 element descending
		Data des = new Data(100, 100, Data.Order.DESCENDING);

		//check that empty is correct
		int[] correct = zero.get();
		Arrays.sort(correct);
		int[] testing = zero.get();
		sorter.sort(testing);

		for (int i=0; i<zero.get().length; i++){
			if (correct[i]!=testing[i]){
				return true;
			}
		}

		//check that one is correct
		int[] correctOne = one.get();
		Arrays.sort(correctOne);
		int[] testingOne = one.get();
		sorter.sort(testingOne);

		for (int i=0; i<one.get().length; i++){
			if (correctOne[i]!=testingOne[i]){
				return true;
			}
		}

		//check that rand is correct
		int[] correctRand = rand.get();
		Arrays.sort(correct);
		int[] testingRand = rand.get();
		sorter.sort(testing);

		for (int i=0; i<rand.get().length; i++){
			if (correctRand[i]!=testingRand[i]){
				return true;
			}
		}
		
		//check that asc is correct
		int[] correctAsc = asc.get();
		Arrays.sort(correct);
		int[] testingAsc = asc.get();
		sorter.sort(testing);

		for (int i=0; i<asc.get().length; i++){
			if (correctAsc[i]!=testingAsc[i]){
				return true;
			}
		}
		
		//check that one is correct
		int[] correctDes = des.get();
		Arrays.sort(correct);
		int[] testingDes = des.get();
		sorter.sort(testing);

		for (int i=0; i<des.get().length; i++){
			if (correctDes[i]!=testingDes[i]){
				return true;
			}
		}
		return false;
	}
}
