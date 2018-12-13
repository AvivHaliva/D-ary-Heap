# D-ary-Heap
This is a D-ary Heap implementation that was a project for the course "Data Structures" at Tel Aviv University 2017.

#### Wikipedia Definition of D-ary Heap:
The d-ary heap or d-heap is a priority queue data structure, a generalization of the binary heap in which the nodes have d children instead of 2. Like binary heaps, d-ary heaps are an in-place data structure that uses no additional storage beyond that needed to store the array of items in the heap.
The time complexity in this data structure is O(log n / log d) for insert and decrease key operations, and O(d log n / log d) for delete min operation. 


#### Operations:
- **getSize()** - return the number of items in the heap.
- **arrayToHeap(int [] arr)** - convert an array of integers into a heap DS (more efficient than inserting one by one).
- **parent(i,d)** - return the index of the parent of i in the array.
- **child(i,k,d)** - return the index of the k-th child of i in the array.
- **insert(item)** - insert item to the heap.
- **Delete_Min()** - delete the minimal item in the heap.
- **Get_Min()** - return the minimal item in the heap.
- **Decrease_Key(item, delta)** - decrease the key of item by delta.
- **Delete(item)** - delte item from the heap.
- **DHeapSort(int[] arr, int d)** - sort arr using d-heap sort.
