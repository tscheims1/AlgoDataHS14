/**
 * 
 */
package examples;

/**
 * @author ps
 *
 */
public class MyQueue<E> implements Queue<E> {

	private E[] stor;
	private int in; // points to the next free position
	private int out; // points to the next element to be dequeued
	private int size;
	
	public MyQueue(){
		this(1);
	}

	public MyQueue(int capacity){
		stor =(E[]) new Object[capacity];
	}
	
	/* (non-Javadoc)
	 * @see examples.Queue#enqueue(java.lang.Object)
	 */
	@Override
	public void enqueue(E o) {
		if (size == stor.length) expand();
		if (in==stor.length) in=0;
		stor[in++]=o;
		size++;
	}

	/**
	 * 
	 */
	private void expand() {
		System.out.println("expanding....");
		E [] old = stor;
		stor = (E[]) new Object[2*stor.length];
		for (int i=0;i<size;i++){
			stor[i]=old[(out+i) % old.length];
		}
		in=size;
		out=0;
	}

	/* (non-Javadoc)
	 * @see examples.Queue#dequeue()
	 */
	@Override
	public E dequeue() {
		if (size==0) throw new RuntimeException("Empty Queue!");
		E ret = stor[out++];
		if (out == stor.length) out = 0;
		size--;		
		return ret;
	}

	/* (non-Javadoc)
	 * @see examples.Queue#head()
	 */
	@Override
	public E head() {
		if (size==0) throw new RuntimeException("Empty Queue!");
		return stor[out];
	}

	/* (non-Javadoc)
	 * @see examples.Queue#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see examples.Queue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size==0;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue q=new MyQueue();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.enqueue(9);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
	}

}
