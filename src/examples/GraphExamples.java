package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import com.sun.org.apache.regexp.internal.recompile;


public class GraphExamples<V,E> {

	static final private Object NUMBER = new Object();
	static final private Object VISITED = new Object();
	static final private Object DISCOVERY = new Object();
	
	private Graph<V,E> g;
	private Vertex<V> [] vertexArray;

	public GraphExamples(Graph<V,E> g){
		this.g=g;
	}
	
	
	public void setNumbers(){
		vertexArray = new  Vertex[g.numberOfVertices()];
		Iterator<Vertex<V>> it = g.vertices();
		int num = 0;
		while(it.hasNext()) {
			vertexArray[num]=it.next();
			vertexArray[num].set(NUMBER, num++);
		}
	}
	
	public int[][] getAdjacencyMatrix(){
		setNumbers();
		int n = g.numberOfVertices();
		int [][] ad = new int[n][n];
		
		
		
		return ad;
	}
	
	public boolean isConnected(int ad[][]){
		return false;
	}
	
	/**
	 * @return the number of connected components of 'g'
	 */
	private int numberOfConnectedComponents() {
		int cnt = 1;
		Vertex<V> v = g.aVertex();
		// visit all vertices which can be reached from v
		visitDFS(v);
		// check wether all vertices have the 
		// VISITED attribute (and remove it)
		Iterator<Vertex<V>> it = g.vertices();
		while (it.hasNext()){
			Vertex<V> w = it.next();
			if (! w.has(VISITED)){
				cnt++;
				visitDFS(w);			
			}
			w.destroy(VISITED);
		}

		return  cnt;
	}

	/**
	 * @return true if 'g' is connected
	 */
	private boolean isConnected() {
		Vertex<V> v = g.aVertex();
		// visit all vertices which can be reached from v
		visitDFS(v);
		// check wether all vertices have the 
		// VISITED attribute (and remove it)
		Iterator<Vertex<V>> it = g.vertices();
		int cnt = 0;
		while (it.hasNext()){
			Vertex<V> w = it.next();
			if (w.has(VISITED)){
				cnt++;
				w.destroy(VISITED); // clean w
			}
		}
		return g.numberOfVertices() == cnt;
	}

	
	/**
	 * vists all vertices starting with 'v' which
	 * can be reached
	 * @param v
	 */
	private void visitDFS(Vertex<V> v) {
		v.set(VISITED,null);
		Iterator<Edge<E>> it = g.incidentEdges(v);
		while (it.hasNext()){
			Vertex<V> w = g.opposite(it.next(), v);
			if ( ! w.has(VISITED)) visitDFS(w);
		}
	}


	
	
	/**
	 * @param from
	 * @param to
	 * @return the shortest path from vertex 'from' to vertex 'to'
	 *  or null if no path exists
	 */
	public Vertex<V>[] shortestPath(Vertex<V> from, Vertex<V> to ){
		ArrayList<Vertex<V>> al = new ArrayList<Vertex<V>>();
		if (from.has(to)){
			while (from != to){
				al.add(from);
				from = (Vertex<V>)from.get(to);
			}
			al.add(to);
			return al.toArray(new Vertex[0]);
		}
		else return null;
	}
	
	private void setGW(Vertex<V> s){
		// sets for all (reachable) vertices the attribute 's' to the value 
		// 'g' where 'g' is the first vertex on the shortest 
		// path to 's' (considering 'hopping' distance)
		s.set(s, s);
		LinkedList<Vertex<V>> q = new LinkedList<>(); 
		q.addLast(s);
		while( ! q.isEmpty()){
			Vertex<V> v = q.removeFirst();
			// now find all neighbours 'w'
			Iterator<Edge<E>> it = g.incidentEdges(v);
			while (it.hasNext()){
				Vertex<V> w = g.opposite(it.next(), v);
				if ( ! w.has(s)){
					w.set(s,v);
					q.addLast(w);
				}
				
			}
 		}
	}
	

	public void setGateways(){
		// sets for all nodes 'v' the attribute 'w' with value 'g'. 
		// which means that the first node on the shortest path
		// from 'v' to 'w' is 'g'
		Iterator<Vertex<V>> it = g.vertices();
		while (it.hasNext()) setGW(it.next());
	}
	
	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		
		
		
		// make an undirected graph
		IncidenceListGraph<String,String> g = 
			new IncidenceListGraph<>(false);
		GraphExamples<String,String> ge = new GraphExamples<>(g);
		Vertex vA = g.insertVertex("A");
		Vertex vB = g.insertVertex("B");
		Vertex vC = g.insertVertex("C");
		Vertex vD = g.insertVertex("D");
		Vertex vE = g.insertVertex("E");
		Vertex vF = g.insertVertex("F");
		Vertex vG = g.insertVertex("G");
		Edge e_a = g.insertEdge(vA,vB,"a");
		Edge e_b = g.insertEdge(vD,vC,"b");
		Edge e_c = g.insertEdge(vD,vB,"c");
		Edge e_d = g.insertEdge(vC,vB,"d");
		Edge e_e = g.insertEdge(vC,vE,"e");
		Edge e_f = g.insertEdge(vB,vE,"f");
		Edge e_g = g.insertEdge(vD,vE,"g");
		Edge e_h = g.insertEdge(vE,vG,"h");
		Edge e_i = g.insertEdge(vG,vF,"i");
		Edge e_j = g.insertEdge(vF,vE,"j");
		System.out.println(g);
		ge.setGateways();
		System.out.print("Path: ");
		for(Vertex<String>v:ge.shortestPath(vA,vG)){
			System.out.print(v);
		};
		System.out.println();
		
//	A__B     F
//	  /|\   /|
//	 / | \ / |
//	C__D__E__G   
//	\     /
//	 \___/
//      
	}


}

