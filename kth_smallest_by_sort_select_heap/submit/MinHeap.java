

/**
 * A Heap implementation class
 * 
 * @param heap the array that holds the heap data
 * @param size the number of elements currently stored in the heap
 */
public class MinHeap {
	
	CompareInt[] heap;
	int size;

	/**
	 * Constructs a new heap with maximum capacity n
	 * Remember to index your heap at 1 instead of 0!
	 * @param n the maximum number of elements allowed in the heap
	 */
	public MinHeap(int n) {
		heap = new CompareInt[n+1];
		size = 0;
	}
	
	/**
	 * Adds an element to the heap
	 * 
	 * @param val the value to be added to the heap
	 */
	public void add(CompareInt val) {
        if (heapFull()) throw new IllegalArgumentException();
        // approach is to add a value to the end of the array
        // for the algorithm to work we need to use the 1 to n indices so we increment size first
        this.size++;
        this.heap[this.size] = val;
        // problem this is now an invalid heap (most likely) 
        // because minimum value needs to always be at the root of the tree
		swim(this.size);
	}
	
    
    private boolean heapFull() {
	    return this.size == this.heap.length - 1;
    }
    
    private void swim(int k){
        while(k / 2 >= 1 &&  this.heap[k/2].compareTo(this.heap[k]) > 0){
            swap(k, k / 2);
            k = k / 2;
        }
    }
    
    private void swap(int i, int j) {
	    CompareInt temp = this.heap[i];
	    this.heap[i] = this.heap[j];
	    this.heap[j] = temp;
    }
    
	/**
	 * Extracts the smallest element from the heap
	 */
	public CompareInt extractMin() {
        // we have extracted minimum but left an invalid tree
		CompareInt min = this.heap[1];
        swap(1, this.size);
        this.size--;
        sink(1);
        return min;
	}
    
    private void sink(int k){
        while(2 * k <= this.size){
            // get smallest child
            int smallest = 2 * k;
            if(2 * k + 1 <= this.size && this.heap[2 * k + 1].compareTo(this.heap[2 * k]) < 0){
                smallest = 2 * k + 1;
            }
            // compare smallest child to parent
            if(this.heap[k].compareTo(this.heap[smallest]) < 0){
                break;
            }
            swap(k, smallest);
            k = smallest;
        }
    }
}
