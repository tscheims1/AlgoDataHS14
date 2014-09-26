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
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see examples.Queue#dequeue()
	 */
	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Queue#head()
	 */
	@Override
	public E head() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Queue#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see examples.Queue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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
