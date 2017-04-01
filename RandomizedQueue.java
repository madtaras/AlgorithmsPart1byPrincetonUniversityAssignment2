import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int n = 0;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) copy[i] = array[i];
        array = copy;
    }

    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();

        if (n == array.length) resize(2 * array.length);
        array[n++] = item;
    }

    public Item dequeue() {
        if (size() == 0) throw new java.util.NoSuchElementException();

        int index = StdRandom.uniform(0, n);
        Item item = array[index];
        array[index] = array[n - 1];
        array[n - 1] = null;
        n -= 1;
        if (n > 0 && n == array.length/4) resize(array.length / 2);
        return item;
    }

    public Item sample() {
        if (size() == 0) throw new java.util.NoSuchElementException();

        int index = StdRandom.uniform(0, n);
        return array[index];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] itArr = array;
        private int itIndex = 0;
        private int arrLen = n - 1;

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
