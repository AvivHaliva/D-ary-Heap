import java.util.Random;

public class DHeap
{
    private int size, max_size, d;
    private DHeap_Item[] array;

	// Constructor
	// m_d >= 2, m_size > 0
    DHeap(int m_d, int m_size) {
               max_size = m_size;
			   d = m_d;
               array = new DHeap_Item[max_size];
               size = 0;
    }
	
	/**
	 * public int getSize()
	 * Returns the number of elements in the heap.
	 */
	public int getSize() {
		return size;
	}
	
  /**
     * public int arrayToHeap()
     *
     * The function builds a new heap from the given array.
     * Previous data of the heap should be erased.
     * preconidtion: array1.length() <= max_size
     * postcondition: isHeap()
     * 				  size = array.length()
     * Returns number of comparisons along the function run. 
	 */
    public int arrayToHeap(DHeap_Item[] array1) 
    {
    	//insert all array1 elements to this.array
        for (int i=0; i< array1.length;i++) {
        	array[i] = array1[i];
        	array[i].setPos(i);
        }
        
        
        int sumComparisons = 0;
        this.size = array1.length;
        
        for (int ind = parent(size, d) ; ind >=0 ; ind --) {
        	//System.out.println(array[ind].getKey());
        	sumComparisons += HeapifyDown(ind, 0);
        }
        
        return sumComparisons;
    }

    /**
     * public boolean isHeap()
     *
     * The function returns true if and only if the D-ary tree rooted at array[0]
     * satisfies the heap property or has size == 0.
     *   
     */
    public boolean isHeap() 
    {
        if (size == 0) {
        	return true;
        }
        
        // parent(size-1, d) last nonLeaf node
        for (int i = 0 ; i < parent(size-1, d) ; i++) {
        	for (int k = 1 ; k < d+1 ; k++) {
        		if (child(i, k, d) < size && array[i].getKey() > array[child(i, k, d)].getKey()) {
           			return false;
           		}
        	}
        }

        return true;
        
    }


 /**
     * public static int parent(i,d), child(i,k,d)
     * (2 methods)
     *
     * precondition: i >= 0, d >= 2, 1 <= k <= d
     *
     * The methods compute the index of the parent and the k-th child of 
     * vertex i in a complete D-ary tree stored in an array. 
     * Note that indices of arrays in Java start from 0.
     */
    public static int parent(int i, int d){
    		return (i-1)/d;
    	}
    
    public static int child (int i, int k, int d){
    	return d*i+k;
    	} 

    /**
    * public int Insert(DHeap_Item item)
    *
	* Inserts the given item to the heap.
	* Returns number of comparisons during the insertion.
	*
    * precondition: item != null
    *               isHeap()
    *               size < max_size
    * 
    * postcondition: isHeap()
    */
    public int Insert(DHeap_Item item) 
    {   
    	//first - we insert the item to the first vacant place in the array;
    	array[size] = item;
    	item.setPos(size);
    	size++;
    	return HeapifyUp(item.getPos());
    }

	 private int HeapifyUp(int i) {
		 int cntComparisons = 0;
		 while (i>=1) {
			 cntComparisons ++;
			 if (array[i].getKey() < array[parent(i, d)].getKey()) {
				 swap (i,parent(i, d));	
				 i = parent(i, d);
			 }
			 else {
				 break;
			 }
		} 
		  
		 return cntComparisons;
		}
	
	private void swap(int i, int parent) {
		DHeap_Item temp = array[i];
		array [i] = array[parent];
		array[parent] = temp;
		
		array[i].setPos(i);
		array [parent].setPos(parent);
		
	}

/**
    * public int Delete_Min()
    *
	* Deletes the minimum item in the heap.
	* Returns the number of comparisons made during the deletion.
    * 
	* precondition: size > 0
    *               isHeap()
    * 
    * postcondition: isHeap()
    */
    public int Delete_Min()
    {
     	// first - we delete the min item in array[0] and reaplace it with the last item array[size]
    	array [0] = array[size-1];
    	array[0].setPos(0);
    	size--;
    	//make sure we get a legal heap
    	return HeapifyDown(0,0);
    }

    private int HeapifyDown(int i, int cntComparisons) {
    	int smallest = i;
    	/*check if i children are bigger (or equal) than him - else swap*/
		for (int k = 1; k < d+1; k++){
			if (child(i, k, d) < size) {
				cntComparisons ++;
				if(array [child(i, k, d)].getKey() < array[smallest].getKey()) {
					smallest = child(i, k, d);
				}
			}
		}
    	if (smallest > i) {
    		swap(smallest, i);
    		return HeapifyDown(smallest,cntComparisons);
    	}
    	return cntComparisons;
}

	/**
     * public DHeap_Item Get_Min()
     *
	 * Returns the minimum item in the heap.
	 *
     * precondition: heapsize > 0
     *               isHeap()
     *		size > 0
     * 
     * postcondition: isHeap()
     */
    public DHeap_Item Get_Min()
    {
    	return array[0];
    }
	
  /**
     * public int Decrease_Key(DHeap_Item item, int delta)
     *
	 * Decerases the key of the given item by delta.
	 * Returns number of comparisons made as a result of the decrease.
	 *
     * precondition: item.pos < size;
     *               item != null
     *               isHeap()
     * 
     * postcondition: isHeap()
     */
    public int Decrease_Key(DHeap_Item item, int delta)
    {
    	// pre - delta < 0 
    	if (item.getPos() == -1) {
    		return 0;
    	}
    	
    	item.setKey(item.getKey() - delta);
    	return HeapifyUp(item.getPos());
    }
	
	  /**
     * public int Delete(DHeap_Item item)
     *
	 * Deletes the given item from the heap.
	 * Returns number of comparisons during the deletion.
	 *
     * precondition: item.pos < size;
     *               item != null
     *               isHeap()
     * 
     * postcondition: isHeap()
     */
    public int Delete(DHeap_Item item)
    {
    	int comparisonsCnt;
    	/* set the item to be deleted to min*/
    	comparisonsCnt = Decrease_Key(item, Math.abs(item.getKey())+ Math.abs(this.Get_Min().getKey()) + 1);
    	comparisonsCnt += Delete_Min();
    	return comparisonsCnt;
    }
	
	/**
	* Sort the input array using heap-sort (build a heap, and 
	* perform n times: get-min, del-min).
	* Sorting should be done using the DHeap, name of the items is irrelevant.
	* 
	* Returns the number of comparisons performed.
	* 
	* postcondition: array1 is sorted 
	*/
	public static int DHeapSort(int[] array1, int d) {
		DHeap dheap = new DHeap(d, array1.length);
		int comparisonsCnt ;
		/* create an array of Dheap items with keys from array1 */
		DHeap_Item [] arr = new DHeap_Item[array1.length];
		for (int i=0;i<array1.length;i++) {
			arr[i] = new DHeap_Item("", array1[i]);
		}
		comparisonsCnt = dheap.arrayToHeap(arr);
		for (int i=0;i<array1.length;i++) {
			array1[i] = dheap.Get_Min().getKey();
			comparisonsCnt += dheap.Delete_Min();
		}
		return comparisonsCnt;	
	}
	
}
