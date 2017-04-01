import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int N = 0;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) copy[i] = array[i];
        array = copy;
    }

    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();

        if (N == array.length) resize(2 * array.length);
        array[N++] = item;
    }

    public Item dequeue() {
        if (size() == 0) throw new java.util.NoSuchElementException();

        int index = StdRandom.uniform(0, N);
        Item item = array[index];
        array[index] = array[N - 1];
        array[N - 1] = null;
        N -= 1;
        if (N > 0 && N == array.length/4) resize(array.length / 2);
        return item;
    }

    public Item sample() {
        if (size() == 0) throw new java.util.NoSuchElementException();

        int index = StdRandom.uniform(0, N);
        return array[index];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] itArr = array;
        private int itIndex = 0;
        private int arrLen = N - 1;

        public boolean hasNext() {
            return !(itIndex == arrLen);
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            if (itIndex == 0) StdRandom.shuffle(itArr);

            Item item = null;
            while (item == null) {
                item = itArr[itIndex];
                itIndex += 1;
            }

            return item;
        }
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {

    }
}
