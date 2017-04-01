import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }
    private int dequeSize = 0;
    private Node first;
    private Node last;

    public Deque() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return dequeSize == 0;
    }

    public int size() {
        return dequeSize;
    }

    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.previous = null;
            first.next = oldFirst;
            dequeSize += 1;
            if (isEmpty()) first = last;
            else if (oldFirst != null) oldFirst.previous = first;
        }
    }

    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = oldLast;
            dequeSize += 1;
            if (isEmpty()) first = last;
            else if (oldLast != null) oldLast.next = last;
        }
    }

    public Item removeFirst() {
        if (dequeSize == 0) throw new java.util.NoSuchElementException();

        Item item = first.item;
        first = first.next;
        dequeSize -= 1;
        if (isEmpty()) last = null;
        return item;
    }

    public Item removeLast() {
        if (dequeSize == 0) throw new java.util.NoSuchElementException();

        Item item = last.item;
        last = last.previous;
        dequeSize -= 1;
        if (isEmpty()) first = null;
        return item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<Integer> deq = new Deque<Integer>();
        deq.addFirst(5);
        deq.addFirst(4);
        deq.addLast(10);
        System.out.println(deq.removeFirst());
        System.out.println(deq.removeLast());
        System.out.println(deq.size());
        System.out.println(deq.isEmpty());
    }
}