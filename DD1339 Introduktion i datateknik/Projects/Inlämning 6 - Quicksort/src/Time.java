/**
 * The Time class is the class that measures the time is take to sort a array
 * 
 * @author Felix De Silva
 * @date 28 feb 2014
 */
public class Time {
	
	/**
	 * the main method of the class
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println("1000 elements vectors");
		measureQuicksort(1000);
		measureQuickSortInsertionMiddle(1000);
		measureQuickSortRandom(1000);
		measureQuickSortInsertionRandom(1000);
		measureArrayssort(1000);
		
		System.out.println("10000 elements vectors");
		measureQuicksort(10000);
		measureQuickSortInsertionMiddle(10000);
		measureQuickSortRandom(10000);
		measureQuickSortInsertionRandom(10000);
		measureArrayssort(10000);
		
		System.out.println("100000 elements vectors");
		measureQuicksort(100000);
		measureQuickSortInsertionMiddle(100000);
		measureQuickSortRandom(100000);
		measureQuickSortInsertionRandom(100000);
		measureArrayssort(100000);
		
		System.out.println("1000000 elements vectors");
		measureQuicksort(1000000);
		measureQuickSortInsertionMiddle(1000000);
		measureQuickSortRandom(1000000);
		measureQuickSortInsertionRandom(1000000);
		measureArrayssort(1000000);
		
	}


	/**
	 * Measures the time to sort the data using the sorter.
	 * Prints the results of the measurement to stdout.
	 */
	private static void measureTime(IntSorter sorter, Data data) {
		if (isBroken(sorter)) { // returns true if sorter doesn't pass tests
			System.out.println(sorter + " is broken");
			return;
		}
		int avarage = 0;
		final Stopwatch clock = new Stopwatch();
		final int reps = 1000;
		final int countMark = 100;
		final String surfix = " ms";
		for (int k = 0; k < reps; k++) {

			clock.reset().start();
			{
				sorter.sort(data.get());
			}
			long time = (clock.stop().milliseconds());

			if(k > countMark){
				avarage += time;
			}
		}
		System.out.println("| Avarage: " +(avarage/(reps-countMark)) + surfix +" |");

	}

	/**
	 * Private method that uses isBroken from SortTest so check is the current sorter is working
	 * 
	 * @param sorter
	 * @return
	 */
	private static boolean isBroken(IntSorter sorter){
		SortTest test = new SortTest();
		return test.isBroken(sorter);
	}
	
	/**
	 * A help method to avoid code duplication
	 * Send the wanted size for the data arrays and the current sorter
	 * 
	 * @param size
	 * @param sorter
	 */
	private static void measureSorter(int size, IntSorter sorter){
		//100 one's elements
		Data one = new Data(size, 1, Data.Order.RANDOM);

		//Random 100 elements
		Data rand = new Data(size, size,Data.Order.RANDOM);

		//100 elements ascending
		Data asc = new Data(size, size, Data.Order.ASCENDING);

		//100 elements descending
		Data des = new Data(size, size, Data.Order.DESCENDING);


		System.out.println("Many ones");
		measureTime(sorter, one);
		System.out.println();
		System.out.println("Random:");
		measureTime(sorter, rand);
		System.out.println();
		System.out.println("Ascending:");
		measureTime(sorter, asc);
		System.out.println();
		System.out.println("Descending:");
		measureTime(sorter, des);
		System.out.println("\n");
	}
	
	/**
	 * Measure the time for the Arrayssort method.
	 * 
	 * @param size - the size of the vector
	 */
	public static void measureArrayssort(int size){
		Arrayssort sorter = new Arrayssort();
		System.out.println("Arrayssort: ");

		measureSorter(size, sorter);
	}
	
	/**
	 * Measure the time for the Quicksort method that has the pivot point at the first element.
	 * 
	 * @param size - the size of the vector
	 */
	public static void measureQuicksort(int size){
		Quicksort sorter = new Quicksort();
		System.out.println("Quicksort + Middle pivot: ");

		measureSorter(size, sorter);
	}

	/**
	 * Measure the time for the Quicksort method that has the pivot point at random elements.
	 * 
	 * @param size - the size of the vector
	 */
	public static void measureQuickSortRandom(int size){
		QuickSortRandom sorter = new QuickSortRandom();
		System.out.println("Quicksort + Random pivot: ");

		measureSorter(size, sorter);
	}

	/**
	 * Measure the time for the Quicksort method that has the pivot point at the first element
	 * and has insertionsort at the last 20 element
	 * 
	 * @param size - the size of the vector
	 */
	public static void measureQuickSortInsertionMiddle(int size){
		QuickSortInsertionMiddle sorter = new QuickSortInsertionMiddle();
		System.out.println("Quicksort + Insertion + Middle pivot: ");

		measureSorter(size, sorter);
	}

	/**
	 * Measure the time for the Quicksort method that has the pivot point at random elements
	 * and has insertionsort at the last 20 elements
	 * 
	 * @param size - the size of the vector
	 */
	public static void measureQuickSortInsertionRandom(int size){
		QuickSortInsertionRandom sorter = new QuickSortInsertionRandom();
		System.out.println("Quicksort + Insertion + Random pivot: ");

		measureSorter(size, sorter);

	}
}
