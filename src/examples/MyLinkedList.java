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
			if (n.creator != this) throw new RuntimeException("position belongs to another MyLinkedList instance!");			
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
		return last;
	}

	/* (non-Javadoc)
	 * @see examples.List#isFirst(examples.Position)
	 */
	@Override
	public boolean isFirst(Position<E> p) {
		return castToLNode(p)==first;
	}

	/* (non-Javadoc)
	 * @see examples.List#isLast(examples.Position)
	 */
	@Override
	public boolean isLast(Position<E> p) {
		return castToLNode(p)==last;
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
		return castToLNode(p).prev;
	}

	/* (non-Javadoc)
	 * @see examples.List#replaceElement(examples.Position, java.lang.Object)
	 */
	@Override
	public E replaceElement(Position<E> p, E o) {
		LNode n = castToLNode(p);
		E old = n.elem;
		n.elem = o;
		return old;
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
		LNode n = new LNode();
		n.elem = o;
		n.prev = last;
		if (last != null) last.next = n;
		else first = n;
		size++;	
		last = n;	
		return n;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertBefore(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertBefore(Position<E> p, E o) {
		LNode n = castToLNode(p);
		LNode newN = new LNode();
		newN.elem = o;
		newN.next = n;
		newN.prev = n.prev;
		if (n==first){
			first = newN; 
		}
		else {
			n.prev.next = newN;
		}
		n.prev = newN;
		size++;
		return newN;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertAfter(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertAfter(Position<E> p, E o) {
		LNode n = castToLNode(p);
		LNode newN = new LNode();
		newN.elem = o;
		newN.prev = n;
		newN.next = n.next;
		if (n==last){
			last = newN; 
		}
		else {
			n.next.prev = newN;
		}
		n.next = newN;
		size++;
		return newN;
	}

	/* (non-Javadoc)
	 * @see examples.List#remove(examples.Position)
	 */
	@Override
	public void remove(Position<E> p) {
		LNode n = castToLNode(p);
		size--;
		n.creator = null; // invalidate p
		
		// left side:
		if (n==first) {
			first=n.next;
			if (first != null) first.prev = null;
		}
		else n.prev.next = n.next;
		
		// right side:
		if (n==last) {
			last =n.prev;
			if (last!=null) last.next = null;
		}
		else n.next.prev = n.prev;
		
	}

	/* (non-Javadoc)
	 * @see examples.List#positions()
	 */
	@Override
	public Iterator<Position<E>> positions() {
		return new Iterator<Position<E>>(){
			LNode current = first;
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Position<E> next() {
				LNode ret = current;
				// switch to next
				current = current.next;
				return ret;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}};
	}

	/* (non-Javadoc)
	 * @see examples.List#elements()
	 */
	@Override
	public Iterator<E> elements() {
		return new Iterator<E>(){
			LNode current = first;
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public E next() {
				LNode ret = current;
				// switch to next
				current = current.next;
				return ret.elem;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}};
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
		return size==0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> li = new MyLinkedList<>();
		Position pp = li.insertLast("hans");
		li.insertLast("heiri");
		pp = li.insertLast("susi");		
		li.insertLast("heidi");
		li.remove(pp);

		Position<String> p = li.first();
		while(p != null){
			System.out.println(p.element());
			p = li.next(p);
		}
		Iterator<Position<String>> it = li.positions();
		while (it.hasNext()) System.out.println(it.next().element());
		
	}

}
