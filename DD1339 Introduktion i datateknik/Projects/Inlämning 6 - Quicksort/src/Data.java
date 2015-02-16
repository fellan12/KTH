import java.util.Arrays;
import java.util.Random;

/**
 * The Data class creates and holds an immutable array of random ints.
 * 
 * @author Stefan Nilsson
 * @version 2012-12-14
 */
public final class Data {
    public enum Order {
        RANDOM, ASCENDING, DESCENDING
    }

    private final int max; // The maximum data value
    private final Order order;
    private final int[] data;

    /**
     * Creates an array with n random numbers from the
     * interval 1..max arranged in the given order.
     *
     * @throws IllegalArgumentException if {@code n < 0} or {@code max < 1}.
     */
    public Data(int n, int max, Order order) {
        if (n < 0 || max < 1) {
            throw new IllegalArgumentException("n=" + n + " max=" + max);
        }
        this.max = max;
        this.order = order;
        this.data = createData(n);
    }

    /**
     * Returns a copy of the data.
     * Time complexity: Theta(n).
     */
    public int[] get() {
        return Arrays.copyOf(data, data.length);
    }

    /**
     * Returns a string describing the data. The exact details
     * of the representation are unspecified and subject to change,
     * but this is typical: "1000 from 1-10 ASCENDING".
     */
    @Override public String toString() {
        return data.length + " from 1-" + max + " " + order;
    }
    
    /**
     * Creates a random array with n elements as specified by
     * the instance variables max and order.
     */
    private int[] createData(int n) {
        final Random rand = new Random();
        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            v[i] = 1 + rand.nextInt(max);
        }
        switch (order) {
        case RANDOM:
            break; // Elements already in random order.
        case ASCENDING:
            Arrays.sort(v);
            break;
        case DESCENDING:
            Arrays.sort(v);
            for (int i = 0, j = n-1; i < n/2; i++, j--) {
                int temp = v[i];
                v[i] = v[j];
                v[j] = temp;
            }
            break;
        }
        return v;
    }

    /**
     * Example demonstrating how this class can be used.
     */
    public static void main(String[] args) {
        Data[] test = {
            new Data(0, 20, Data.Order.RANDOM),
            new Data(1, 20, Data.Order.RANDOM),
            new Data(10, 20, Data.Order.RANDOM),
            new Data(10, 1, Data.Order.RANDOM),
            new Data(10, 5, Data.Order.RANDOM),
            new Data(10, 5, Data.Order.ASCENDING),
            new Data(10, 5, Data.Order.DESCENDING),
        };
        for (Data d : test) {
            System.out.printf("%s: %s%n", d, Arrays.toString(d.get()));
        }
    }
}
