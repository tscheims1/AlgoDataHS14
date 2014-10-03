/**
 * 
 */
package examples;

import java.util.Iterator;

import examples.MyLinkedList.LNode;

/**
 * @author ps
 *
 */
public class MyTree<E> implements Tree<E> {
	class TNode implements Position<E>{
		TNode parent;
		E elem;
		MyLinkedList<TNode> children = new MyLinkedList<>();
		Position<TNode> mySiblingPos;
		Object creator = MyTree.this;

		/* (non-Javadoc)
		 * @see examples.Position#element()
		 */
		@Override
		public E element() {
			return elem;
		}
		
	}
	
	// instance variables
	
	private TNode root;
	private int size;
	
	private TNode castToTNode(Position p){
		TNode n;
		try {
			n = (TNode) p;
		} catch (ClassCastException e) {
			throw new RuntimeException("This is not a Position belonging to MyLinkedList"); 
		}
		if (n.creator == null) throw new RuntimeException("position was allready deleted!");
		if (n.creator != this) throw new RuntimeException("position belongs to another MyLinkedList instance!");			
		return n;
}

	/* (non-Javadoc)
	 * @see examples.Tree#root()
	 */
	@Override
	public Position<E> root() {
		return root;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#createRoot(java.lang.Object)
	 */
	@Override
	public Position<E> createRoot(E o) {
		if (root != null) throw new RuntimeException("allready a root present");
		TNode n = new TNode();
		n.elem = o;
		size++;
		root = n;
		return n;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#parent(examples.Position)
	 */
	@Override
	public Position<E> parent(Position<E> child) {
		return castToTNode(child).parent;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#childrenPositions(examples.Position)
	 */
	@Override
	public Iterator<Position<E>> childrenPositions(Position<E> parent) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#childrenElements(examples.Position)
	 */
	@Override
	public Iterator<E> childrenElements(Position<E> parent) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#numberOfChildren(examples.Position)
	 */
	@Override
	public int numberOfChildren(Position<E> parent) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#insertParent(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertParent(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#addChild(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> addChild(Position<E> parent, E o) {
		TNode par = castToTNode(parent);
		TNode child = new TNode();
		child.elem = o;
		size++;
		child.parent = par;
		child.mySiblingPos = par.children.insertLast(child);
		return child;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#addChildAt(int, examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> addChildAt(int pos, Position<E> parent, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#addSiblingAfter(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> addSiblingAfter(Position<E> sibling, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#addSiblingBefore(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> addSiblingBefore(Position<E> sibling, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#remove(examples.Position)
	 */
	@Override
	public void remove(Position<E> p) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see examples.Tree#isExternal(examples.Position)
	 */
	@Override
	public boolean isExternal(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#isInternal(examples.Position)
	 */
	@Override
	public boolean isInternal(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#replaceElement(examples.Position, java.lang.Object)
	 */
	@Override
	public E replaceElement(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyTree<String> t = new MyTree<>();
		Position<String> p = t.createRoot("A");
		Position<String> pB = t.addChild(p, "B");
		t.addChild(p, "C");
		Position<String> pD = t.addChild(p, "D");
		t.addChild(pB, "E");
		t.addChild(pB, "F");
		t.addChild(pD, "G");
	}

}
