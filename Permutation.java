import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        String c = StdIn.readLine();
        String[] array = c.split(" ", -1);

        for (int i = 0; i < array.length; i++) {
            queue.enqueue(array[i]);
        }

        Iterator<String> iterator = queue.iterator();
        for (int i = 0; i < k; i++) {
            StdOut.println(iterator.next());
        }
    }
}
