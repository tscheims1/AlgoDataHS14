package examples;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;


/**
 * @author ps
 * Various sort programs for int arrays
 */
public class SortTest {
	
	public static long cnt;
	static Random rand = new Random();

	public static void heapSort(int [] a){
		int n = a.length;
		// first we make 'a' a max-heap:
		for (int i=1; i<n; i++) upHeap(a,i);
		System.out.println("heap? " + checkHeap(a,0));
		// Now we remove the max element and move it to the  
		// last position of the array 'a'. repair the heap with 'downHeap'
		// Heap has now only n-1 elements.
		// Repeat until all elements are at place.
		for (int i=n-1; i>=0; i--){
			swap(a,i,0);
			downHeap(a,0,i); 
		}	
	}
	
	
	
	/**
	 * @param a
	 * @param i
	 * @param len
	 */
	private static void downHeap(int[] a, int i, int len) {
		// assume a [i..len-1] is a heap, but the element
		// at position i possibly violates the heap condition.
		// swap a[i] with its bigger child until a[i..len-1] is a heap.
		int left = i*2+1;
		while (left < len){
			int cand = left;
			int right = left+1;
			if (right < len && a[right] > a[left]) cand = right;
			if (a[i] >= a[cand]) break;
			swap(a,i,cand);
			i = cand;
			left = i*2+1; 
		} 		
	}



	/**
	 * @param a
	 * @param i
	 */
	private static void upHeap(int[] a, int i) {
		// assume a[0..i-1] is a heap swap element
		// at position i with its parent and so on
		// until a[0..i] is a max-heap
		int parent = (i-1)/2;
		while (i>0){
			if (a[parent] >= a[i]) break;
			swap(a,i,parent);
			i = parent;
			parent = (i-1)/2;
		}
	}

	static boolean checkHeap(int a[], int len){
		for (int i=a.length-1; i>0 ;i--){ 
			if (a[i]>a[(i-1)/2]) return false;
		}
		return true;
	}


	/**
	 * @param a int aray
	 * @return 'true' if 'a' is sorted
	 */
	public static boolean sortCheck(int[] a) {
		for (int i=0;i<a.length-1;i++){
			if (a[i]>a[i+1]) return false; 
		}
		return true;
	}
	
	
	/**
	 * Non optimized bubble sort for an int array 
	 * @param a
	 */
	public static void bubbleSort(int[] a) {
		cnt=0;
		int m = a.length-1;
		for(int i=m; i>0; i--){ 
			for (int k=0; k < i; k++){
				if(a[k]>a[k+1]) swap(a,k,k+1);
			}
			// now a[i] is on its final position!
		}
	}

	/**
	 * swap the array elements a[i] and a[k]
	 * @param a int array 
	 * @param i position in the array 'a'
	 * @param k position in the array 'a'
	 */
	static void swap(int [] a, int i, int k){
		int tmp=a[i];
		a[i]=a[k];
		a[k]=tmp;
		cnt++;
	}
 
	
	/**
	 * Wrapper which calls the recursive version of the 
	 * quick sort program
	 * @param a the int array to be sorted
	 */
	public static void quickSort(int [] a){
		qSort(a,0,a.length-1);
	}

	/**
	 * recursive version of quick sort (sorts 
	 * the range a[from..to] of the int array 'a')
	 * @param a 
	 * @param from 
	 * @param to
	 */
	private static void qSort(int []a, int from, int to){
		if (from >= to) return; // nothing to do if sequence has length 1 or less
		int piv = partition(a,from,to);
		// now a[to..piv-1] <= a[piv] and 
		// a[piv+1..to]>=a[piv]
		qSort(a,from,piv-1);
		qSort(a,piv+1,to);
	}
	
	/**
	 * partitions the range such that all of the elements 
	 * in the range a[from..piv-1] are smaller than a[piv]
	 * and all elements in the range a[piv+1..to] are greater 
	 * or equal than a[piv]
	 * @param a
	 * @param from
	 * @param to
	 * @return the position 'piv' of the pivot
	 */
	private static int partition(int []a, int from, int to){
		// take a random pivot and put it at the end 
		// of the range
		// (necessary if data not random)
		if (from<to) swap(a,rand.nextInt(to-from)+from,to); 
		int pivot = a[to];
		int left = from-1;
		int right = to;
		while(true){
			while(a[++left] < pivot); // stops at a swap candidate 
			while(a[--right] > pivot)
			{
				if (right==from) break; // break right decrementing when we reach from
			}
			// finished?
			if (right <= left) break;
			swap(a,left,right);
		}
		// final swap
		swap(a,to,left);
		return left;      // return the final position of the pivot (to be changed!)
	}

	

	public static void main(String[] args) {
		long t1=0,t2=0,te1=0,te2=0,eTime=0,time=0;
		int n = 100000;
		// we need a random generator
		Random rand=new Random();
		//rand.setSeed(54326346); // initialize always in the same state
		ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();	
		// new array
		int [] a = new int[n];
		// fill it randomly
		for (int i=0;i<a.length ;i++) a[i]=rand.nextInt(n);
		cnt=0;  // for statistcs reasons
		// get Time
		te1=System.currentTimeMillis();
		t1 = threadBean.getCurrentThreadCpuTime();
		bubbleSort(a);
		te2 = System.currentTimeMillis();
		t2 = threadBean.getCurrentThreadCpuTime();
		time=t2-t1;
		eTime=te2-te1;
		System.out.println("CPU-Time usage: "+time/1000000.0+" ms");
		System.out.println("elapsed time: "+eTime+" ms");
		System.out.println("sorted? "+sortCheck(a));
		System.out.println("swap operation needed: "+cnt);
	}


}
