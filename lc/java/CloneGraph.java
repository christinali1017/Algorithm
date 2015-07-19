package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
		 UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
	     Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
	     Map<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
	     queue.add(node);
	     map.put(node, copy);
	     UndirectedGraphNode temp = null;
	     UndirectedGraphNode copyTemp = null;
	     while (!queue.isEmpty()) {
	    	  temp = queue.poll();
	    	  for (UndirectedGraphNode n : temp.neighbors) {
	    		  if (!map.containsKey(n)) {
	    			  copyTemp = new UndirectedGraphNode(n.label);
	    			  map.put(n, copyTemp);
	    			  map.get(temp).neighbors.add(copyTemp);
	    			  queue.add(n);
	    		  } else { 
	    			  map.get(temp).neighbors.add(map.get(n));
	    		  }
	    	  }
	    }
		return copy;
    }
	
	/*
	 * Recursive, DFS
	 */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        map.put(node, copy);
        cloneGraph(node, copy, map);
        return copy;
    }
    private void cloneGraph(UndirectedGraphNode node, UndirectedGraphNode copy, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (node == null) {
            return;
        }
        for (UndirectedGraphNode cur : node.neighbors) {
            if (!map.containsKey(cur)) {
                UndirectedGraphNode curCopy = new UndirectedGraphNode(cur.label);
                map.put(cur, curCopy);
                copy.neighbors.add(curCopy);
                cloneGraph(cur, curCopy, map);
            } else {
                 copy.neighbors.add(map.get(cur));
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
