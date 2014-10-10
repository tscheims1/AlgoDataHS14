/**
 * 
 */
package examples;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author ps
 *
 */
public class MyTree<E> implements Tree<E> {
	// Positions of this Tree:
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
	
	private TNode castToTNode(Position<E> p){
		TNode n;
		try {
			n = (TNode) p;
		} catch (ClassCastException e) {
			throw new RuntimeException("This is not a Position belonging to MyTree"); 
		}
		if (n.creator == null) throw new RuntimeException("position was allready deleted!");
		if (n.creator != this) throw new RuntimeException("position belongs to another MyTree instance!");			
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
		final TNode p = castToTNode(parent);
		return new Iterator<Position<E>>(){
			Iterator<TNode> it  = p.children.elements();
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Position<E> next() {
				return it.next();
			}

			@Override
			public void remove() {
				throw new RuntimeException("please use the 'remove' method of MyTree");
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see examples.Tree#childrenElements(examples.Position)
	 */
	@Override
	public Iterator<E> childrenElements(Position<E> parent) {
		final TNode p = castToTNode(parent);
		return new Iterator<E>(){
			Iterator<TNode> it  = p.children.elements();
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public E next() {
				return it.next().elem;
			}

			@Override
			public void remove() {
				throw new RuntimeException("please use the 'remove' method of MyTree");
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see examples.Tree#numberOfChildren(examples.Position)
	 */
	@Override
	public int numberOfChildren(Position<E> parent) {
		TNode p = castToTNode(parent);
		return p.children.size();
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
		TNode sib = castToTNode(sibling);
		if (sib == root) throw new RuntimeException("root can not have siblings!");
		TNode newN = new TNode();
		newN.elem = o;
		newN.parent = sib.parent;
		newN.mySiblingPos = sib.parent.children.insertAfter(sib.mySiblingPos,newN);
		size++;
		return newN;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#addSiblingBefore(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> addSiblingBefore(Position<E> sibling, E o) {
		TNode sib = castToTNode(sibling);
		if (sib == root) throw new RuntimeException("root can not have siblings!");
		TNode newN = new TNode();
		newN.elem = o;
		newN.parent = sib.parent;
		newN.mySiblingPos = sib.parent.children.insertBefore(sib.mySiblingPos,newN);
		size++;
		return newN;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#remove(examples.Position)
	 */
	@Override
	public void remove(Position<E> p) {
		TNode n = castToTNode(p);
		if (n.children.size()>0) throw new RuntimeException("can not remove node with children!");
		n.creator = null;
		size--;
		if (n==root) root = null;
		else n.parent.children.remove(n.mySiblingPos);
	}

	/* (non-Javadoc)
	 * @see examples.Tree#isExternal(examples.Position)
	 */
	@Override
	public boolean isExternal(Position<E> p) {
		TNode n = castToTNode(p);
		return n.children.size()==0;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#isInternal(examples.Position)
	 */
	@Override
	public boolean isInternal(Position<E> p) {
		TNode n = castToTNode(p);
		return n.children.size() != 0;
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
		TNode n = castToTNode(p);
		E old = n.elem;
		n.elem = o;
		return old;
	}
	
	public void print(){
		print(root,"");
	}

	/**
	 * @param p
	 * @param indent is the indentationstring to use
	 */
	private void print(TNode p, String indent) {
		System.out.println(indent+p.elem);
		Iterator<TNode> it = p.children.elements();
		while (it.hasNext()) print(it.next(),indent+"..");
	}

	public int height(){
		if (root==null) return -1;
		else return height(root);
	}
	
	public ArrayList<Position<E>> externalNodes(){
		ArrayList<Position<E>> al = new ArrayList<>();
		externalNodes(root,al);
		return al;
	}
	
	
	/**
	 * @param p
	 * @param al
	 */
	private void externalNodes(TNode p, ArrayList<Position<E>> al) {
		if (p.children.size()==0){
			al.add(p);
			return;
		}
		Iterator<TNode> it = p.children.elements();
		while (it.hasNext()) externalNodes(it.next(),al);
		
	}

	class Helper {
		TNode n;
		int depth=-1;
	}
	
	public Position<E> deepestNode(){
		Helper he = new Helper();
		if (root!=null) deepestNode(root,0,he);
		return he.n;
	}
	
	
	/**
	 * @param p
	 * @param depth
	 * @param he
	 */
	private void deepestNode(TNode p, int depth, Helper he) {
		if (p.children.size()==0) {
			if (he.depth < depth){ 
				he.n = p;
				he.depth = depth;
			}
			return;
		}
		Iterator<TNode> it = p.children.elements();
		while (it.hasNext()) deepestNode(it.next(),depth+1,he);
	}

	/**
	 * @param r
	 * @return
	 */
	private int height(TNode r) {
		int h = -1;
		Iterator<TNode> it = r.children.elements();
		while (it.hasNext()) h = Math.max(h,height(it.next()));
		return h+1;
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
		t.print();
		System.out.println("height:"+t.height());
		System.out.println("external nodes:");
		ArrayList<Position<String>> al = t.externalNodes();
		for (Position<String> r:al) System.out.println(r.element());
		System.out.println("deepest (left): "+t.deepestNode().element());
	}

}
