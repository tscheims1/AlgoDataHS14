/**
 * 
 */
package examples;

import java.util.Iterator;

/**
 * @author ps
 *
 */
public class MyLinkedList<E> implements List<E> {
	class LNode implements Position<E>{
		E elem;
		LNode prev, next;
		Object creator = MyLinkedList.this; //  pointer to outer instance

		/* (non-Javadoc)
		 * @see examples.Position#element()
		 */
		@Override
		public E element() {
			return elem;
		}
		
	}
	
	private LNode castToLNode(Position p){
			LNode n;
			try {
				n = (LNode) p;
			} catch (ClassCastException e) {
				throw new RuntimeException("This is not a Position belonging to MyLinkedList"); 
			}
			if (n.creator == null) throw new RuntimeException("position was allready deleted!");
			if (n.creator != this) throw new RuntimeException("position belongs to another List instance!");			
			return n;
	}
	
	// instance variables
	
	private LNode first, last;
	private int size;

	/* (non-Javadoc)
	 * @see examples.List#first()
	 */
	@Override
	public Position<E> first() {
		return first;
	}

	/* (non-Javadoc)
	 * @see examples.List#last()
	 */
	@Override
	public Position<E> last() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#isFirst(examples.Position)
	 */
	@Override
	public boolean isFirst(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see examples.List#isLast(examples.Position)
	 */
	@Override
	public boolean isLast(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see examples.List#next(examples.Position)
	 */
	@Override
	public Position<E> next(Position<E> p) {
		return castToLNode(p).next;
	}

	/* (non-Javadoc)
	 * @see examples.List#previous(examples.Position)
	 */
	@Override
	public Position<E> previous(Position<E> p) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#replaceElement(examples.Position, java.lang.Object)
	 */
	@Override
	public E replaceElement(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertFirst(java.lang.Object)
	 */
	@Override
	public Position<E> insertFirst(E o) {
		LNode n = new LNode();
		n.elem = o;
		n.next = first;
		if (first != null) first.prev = n;
		else last = n;
		size++;	
		first = n;	
		return n;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertLast(java.lang.Object)
	 */
	@Override
	public Position<E> insertLast(E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertBefore(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertBefore(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertAfter(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertAfter(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#remove(examples.Position)
	 */
	@Override
	public void remove(Position<E> p) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see examples.List#positions()
	 */
	@Override
	public Iterator<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#elements()
	 */
	@Override
	public Iterator<E> elements() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see examples.List#isEmpty()
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
		List<String> li = new MyLinkedList<>();
		li.insertFirst("hans");
		li.insertFirst("heiri");
		Position pp = li.insertFirst("susi");
		li.insertFirst("heidi");
		Position<String> p = li.first();
		while(p != null){
			System.out.println(p.element());
			p = li.next(p);
		}
	}

}
