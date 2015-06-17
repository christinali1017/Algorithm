package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
/*
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


	OJ's undirected graph serialization:
	Nodes are labeled uniquely.
	
	We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
	As an example, consider the serialized graph {0,1,2#1,2#2,2}.
	
	The graph has a total of three nodes, and therefore contains three parts as separated by #.
	
	First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
	Second node is labeled as 1. Connect node 1 to node 2.
	Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
	Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 */
import java.util.Set;

/*
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class CloneGraph {
	
	/*
	 * BFS
	 */
	  public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		  if(node == null) return null;
		  UndirectedGraphNode copyNode = new UndirectedGraphNode(node.label);
	      LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
	      HashMap<UndirectedGraphNode,UndirectedGraphNode> visited = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
	      
	      queue.add(node);
	      visited.put(node, copyNode);
	      UndirectedGraphNode temp = null;
	      UndirectedGraphNode copyTemp = null;
	      while(!queue.isEmpty()){
	    	  temp = queue.pop();
	    	  for(UndirectedGraphNode n : temp.neighbors){
	    		  if(!visited.containsKey(n)){
	    			  copyTemp = new UndirectedGraphNode(n.label);
	    			  visited.put(n, copyTemp);
	    			  visited.get(temp).neighbors.add(copyTemp);
	    			  queue.add(n);
	    		  }else
	    			  visited.get(temp).neighbors.add(visited.get(n));

	    	  }
	      }
		  return copyNode;
	  }
	
	/*
	 * Recursive, DFS
	 */
	  public static UndirectedGraphNode cloneGraph1(UndirectedGraphNode node) {
		  if(node == null) return null;
		  UndirectedGraphNode copyNode = new UndirectedGraphNode(node.label);
	      HashMap<UndirectedGraphNode,UndirectedGraphNode> visited = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		  graphDFS(node, copyNode , visited);
		  return copyNode;
	    }
	  
	  public static void graphDFS(UndirectedGraphNode node, UndirectedGraphNode copyNode,HashMap<UndirectedGraphNode,UndirectedGraphNode> visited){
		  if(node == null) return;
		  if(!visited.containsKey(node)) visited.put(node, copyNode);
	        for(UndirectedGraphNode n : node.neighbors){
        		if(visited.containsKey(n)){
        			 visited.get(node).neighbors.add(visited.get(n));
        		}else{
	        		UndirectedGraphNode copyTemp = new UndirectedGraphNode(n.label);
	    			visited.put(n, copyTemp);
	    			visited.get(node).neighbors.add(copyTemp);
	        		graphDFS(n, copyTemp,visited);	
        		}
	        	
	        }
	  }
	  
	  
	  /*
	   * DFS print
	   */
	  public static void graphDFS1(UndirectedGraphNode node){
		  if(node == null) return;
	        Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
	        graphDFSHelper(node, visited);

	  }
	  
	  public static void graphDFSHelper(UndirectedGraphNode node, Set<UndirectedGraphNode> visited){
		  if(node == null) return;
	        visited.add(node);
	        System.out.println(node.label);
	        for(UndirectedGraphNode n : node.neighbors){
	        	if(!visited.contains(n)){
	        		graphDFSHelper(n, visited);	
	        	}
	        }
	  }
	  
	  
	  
	  
	  public static void main(String[] args) {
		  UndirectedGraphNode node1 = new UndirectedGraphNode(1);
		  UndirectedGraphNode node2 = new UndirectedGraphNode(2);
		  UndirectedGraphNode node3 = new UndirectedGraphNode(3);
		  UndirectedGraphNode node4 = new UndirectedGraphNode(4);
		  UndirectedGraphNode node5 = new UndirectedGraphNode(5);
		  UndirectedGraphNode node6 = new UndirectedGraphNode(6);
		  UndirectedGraphNode node7 = new UndirectedGraphNode(7);
		  node1.neighbors.add(node2);
		  node1.neighbors.add(node3);
		  node2.neighbors.add(node3);
		  node2.neighbors.add(node5);
		  node3.neighbors.add(node4);
		  node3.neighbors.add(node6);
		  node4.neighbors.add(node6);
		  node6.neighbors.add(node7);
		  graphDFS1(node1);
		  System.out.println();
		  graphDFS1(cloneGraph1(node1));
		  
	}
}
