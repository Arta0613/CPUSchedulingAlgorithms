// Modified HeapSort algorithm from
// http://www.sanfoundry.com/java-program-to-sort-an-array-of-10-elements-using-heap-sort-algorithm/
// Modified BinarySearch algortihm from
// http://www.algolist.net/Algorithms/Binary_search

public class MyPriorityQueue extends MyArrayList {

    private int n;

    public MyPriorityQueue() {
        super();
    }

    public MyPriorityQueue(int n) {
        super(n);
    }

    public void buildheap() {
        n = getNumElements() - 1;
        for (int i = n / 2; i >= 0; i--) {
            maxheap(i);
        }
    }

    public void maxheap(int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest;
        if (left <= n && peek(left).compareTo(peek(i)) > 0) {
            largest = left;
        } else {
            largest = i;
        }
        if (right <= n && peek(right).compareTo(peek(largest)) > 0) {
            largest = right;
        }
        if (largest != i) {
            exchange(i, largest);
            maxheap(largest);
        }
    }

    public void exchange(int i, int j) {
        Comparable t = peek(i);
        set(i, peek(j));
        set(j, t);
    }

    public void sort() {
        buildheap();
        for (int i = n; i > 0; i--) {
            exchange(0, i);
            n = n - 1;
            maxheap(0);
        }
    }

    public boolean add(Comparable c) {
        boolean retVal = true;
        super.pushBack(c);
        if(!contains(c))
            retVal = false;
        sort();
        return retVal;
    }

    public Comparable poll() {
        if(!isEmpty()) {
            Comparable temp = peek(0);
            popFront();
            return temp;
        }
        return null;
    }

    public boolean contains(Comparable c) {
        return binarySearch(getmMyArray(), c, getHead(), getTail()) != -1;
    }

    public boolean remove(Comparable c) { // removes a single instance
        int i = binarySearch(getmMyArray(), c, getHead(), getTail());
        if(i != -1) {
            if(i == getHead()) {
                poll();
                return true;
            } else if(i == getTail()) {
                popBack();
                return true;
            }
            for(int x = i + 1; x <= getTail(); x++) {
                set((x - 1) - getHead(), peek(x - getHead()));
            }
            popBack();
            return true;
        }
        return false;
    }

    private int binarySearch(Comparable[] array, Comparable value, int left, int right) {
        if (left > right)
            return -1;
        int middle = (left + right) / 2;
        if (array[middle].compareTo(value) == 0)
            return middle;
        else if (array[middle].compareTo(value) > 0)
            return binarySearch(array, value, left, middle - 1);
        else
            return binarySearch(array, value, middle + 1, right);
    }

    public int size() {
        return super.getNumElements();
    }

    public boolean isFull() {
        if(head != -1) {
            if(numElements == 0) {
                head = -1;
                tail = -1;
                return numElements >= size;
            }
            for(int x = getHead(); x <= getTail(); x++) {
                mMyArray[x - getHead()] = peek(x - getHead());
            }
            for(int i = 0; i < getHead(); i++) {
                sPopBack();
            }
            head = -1;
            tail = head + numElements;
        }
        return numElements >= size;
    }
}