package datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph_AdjacentList {
	/**
	 * Length of adjacent list
	 */
	int n;
	
	/**
	 * adjacent list
	 */
	List<Integer>[] adj;
	
	@SuppressWarnings("unchecked")
	public Graph_AdjacentList(int n){
		this.n = n;
		adj = (List<Integer>[]) new List[n];
		for(int i = 0; i < n; i++){
			adj[i] = new ArrayList<Integer>();
		}
	}
	
	/**
	 * Add edge from i to j
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j){
		adj[i].add(j);
	}
	
	
	/**
	 * Remove edge from i to j
	 * @param i
	 * @param j
	 */
	public void removeEdge(int i, int j){
		Iterator<Integer> ite = adj[i].iterator();
		while(ite.hasNext()){
			if(ite.next() == j){
				ite.remove();
				return;
			}
		}
		
	}
	
	/**
	 * @param i
	 * @param j
	 * @return true if there is edge from i to j, otherwise, return false
	 */
	public boolean heasEdge(int i, int j){
		return adj[i].contains(j);
	}
	
	
	/**
	 * @param i
	 * @return out edges of i
	 */
	public List<Integer> outEdges(int i){
		return adj[i];
	}
	
	/**
	 * @param i
	 * @return incoming edges of i
	 */
	List<Integer> inEdges(int i){
		List<Integer> edges = new ArrayList<Integer>();
		for(int j = 0; j < n; j++){
			if(adj[j].contains(i))
				edges.add(j);
		}
		return edges;
	}
	
	/**
	 * BFS from node s
	 * @param s
	 */
//	public void graphBFS(int s){
//		if(adj[s] == null || adj[s].size() == 0) return;
//		for(GraphNode n : neighbors){
//			n.visited = false;
//		}
//		
//		Queue<GraphNode> queue = new LinkedList<GraphNode>();
//		queue.offer(s);
//		s.visited = true;
//		s.distance = 0;
//		GraphNode current = null;
//		while(!queue.isEmpty()){
//			current = queue.poll();
//			for(GraphNode n : neighbors){
//				if(!n.visited){
//					n.predecessor = current;
//					n.distance = current.distance+1;
//					queue.offer(n);
//					n.visited = true;
//				}
//			}
//		}
//	}
	
	
	
	/**
	 * Breadth first search tree
	 * @param s
	 */
//	public void graphBTFTree(GraphNode s){
//		unmarked all vertices;
//		Queue<GraphNode> queue = new LinkedList<GraphNode>();
//		queue.offer(s);
//		s.visited = true;
//		s.distance = 0;
//		
//		while(!queue.isEmpty()){
//			GraphNode u = queue.poll();
//			for(GraphNode n : neighbors){
//				n.distance = u.distance + 1;
//				n.visited = true;
//				queue.offer(n);
//				add edge uv to BTSTree;
//				
//			}
//		}
//	
//
//		
//	}
	

	/**If graph if not connected, this dfs can not viisted all nodes in graph
	 * @param s
	 */
//	public void graphDFS(GraphNode s){
//		marked = new Array;
//		if(s != null)
//			s.visited = true;
//		for(GraphNode n : neighbors){
//			if(!n.visited){
//				graphDFS(n);
//			}
//		}
//	}
	
	/**
	 * DFS visit all nodes in graph, even though graph is not connected.
	 * O(v+e)
	 */
//	public void dfs(G){
//		for each vertex u in G.vertex;
//				u.visited = false;
//				u.predecessor = null;
//		for each vertex u in G.V
//			if u.visited == false
//				dfsVisit(G, u);
//	}
//	
//	public void dfsVisit(G, u){
//		u.visited = true;
//		for each node n in G.neighbors
//			if n.visted == false
//				n.predecessor = u
//				dfsVisited(G, n)
//	}
	
	
	/**
	 * Topological sort DFS
	 */
//	public void topologicalSortDFS(){
//		for each vertex u in G.vertex
//			u.visited = false;
//			u.predecessor = null;
//		for each vertex u in G.V
//			if u.visited == false
//				Visit(G, u);
//	}
	
//	public void visit(u){
//		u.visited = true;
//		for each node n in G.neighbors{
//			if n.visted == false
//				visit(n)
//		}
//		stack.push(u);
//	}
	
	/**
	 * Topological sort
	 * Start from nodes without incomings
	 * O(v+e)
	 */
//	public void topologicalSortByIncomings(){
//		list = new LinkedList();
//		s = new Set();
//		
//		for(GraphNode n : G){
//			if(n has no incomings)
//				s.add(n);
//		}
//		
//		while(!s.isEmplty()){
//			node = s.get(0);
//			add node to tail of list
//			for(n in node's neighbors){
//				remove node between node and n
//				if(n has incomings)
//					s.add(n);
//			}
//		
//			if(G has edges){
//				syso(G is not a DAG)
//			}
//			
//			return list;
//		}
//	}
	
	/**
	 * shortest path initial
	 */
//	public void initialSingleSource(G, s){
//		for each vertec v in G.V{
//			v.distance = infinity;
//			v.predecessor = null;
//			
//		}
//		s.distance = 0;
//	}
	
	/**
	 * Relax
	 */
//	public void relax(u, v, w){
//		if(v.distance > u.distance +w(u, v)){
//			v.distance = u.distance + w(u. v);
//			v.predecessor = u;
//		}
//	}
	
	/**
	 * Bellman-ford to find the single source shortest path
	 * edge can have negative weight
	 * no negative cycle allowed
	 */
//	public void bellman_ford(G, w, s){
//		initialSingleSource(G, s);
//		
//		/* iterate #vertex -1 */
//		for(i = 1 to G.V -1){
//			for each edge(u, v) in G.E
//				Relax(u, v, w)
//		}
//		
//		for(each edge (u, v) in G.E){
//			if(v.d > u.d + w(u. v)){
//				return "negative cycle in graph"
//			}
//		}
//		
//	}
	
	/**
	 * Graph only with positive weight
	 */
//	public void dijkdtra(G, w, s){
//		initialSingleSource(G, s);
//		set = new Set();
//		queue = G.V;
//		while(!queue.isEmpty()){
//			u = extractMin(queue);
//			set.add(u);
//			for(each vertex v in u.neighbors){
//				relax(u, v, w);
//			}
//		}
//	}
	
//	public void Dijkstra(Graph, source){
//		dist[source] = 0;
//		for each vertex v in graph{
//			if v != source{
//				dist[v] = infinity;
//				previous[v] = null;
//				add v to queue
//			}
//		}
//		
//		while(!queue.isEmpty()){
//			u = vertex in queue with min dist[u];
//			remove u from queue
//			for each neighbor v of u{
//				len = dist[u] + w(u, v);
//				if(len < dist[v]){
//					dist[v] = len;
//					previous[v] = u;
//				}
//			}
//			
//		}
//		
//		return dist[], previous;
//	}

}
