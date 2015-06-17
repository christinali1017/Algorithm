package datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author wish
 * @version December 10, 2014
 */
public class BinarySearchTree {

	/**
	 * Insert a node into tree
	 * @param x
	 * @param root, root of tree
	 */
	public void insert(TreeNode x, TreeNode root){
		if(x == null) return;
		
		TreeNode parent = null;
		while(root != null){
			parent = root;
			if(x.val < root.val)
				root = root.left;
			else
				root = root.right;
		}
		
		x.parent = parent;
		
		if(parent == null)
			root = x;
		else if(x.val < parent.val)
			parent.left = x;
		else 
			parent.right = x;
	}
	
	/**
	 * Delete a node from tree.
	 * @param x
	 * @param root
	 */
	public void delete(TreeNode x, TreeNode root){
		if(root == null || x == null) return;
		if(x.left == null && x.right == null){
			if(x.equals(x.parent.left)) x.parent.left = null;
			else x.parent.right = null;
			return;
		}
	
		if(x.left == null)
			transplant(root, x, x.right);
		else if(x.right == null)
			transplant(root, x, x.left);
		else{
			TreeNode successor = minimum(x.right);
			if(!successor.parent.equals(x)){
				transplant(root, successor, successor.right);
				successor.right = x.right;
				successor.right.parent =successor; 
			}
			transplant(root, x, successor);
			successor.left = x.left;
			successor.left.parent = successor;
		}
	}
	
	/**
	 * Replace the subtree rooted at node u with the subtree rooted at node v,
	 * node u's parent becomes node v's parent. 
	 * @param root
	 * @param u
	 * @param v
	 */
	public void transplant(TreeNode root, TreeNode u, TreeNode v){
		if(root == null || u == null || v == null) return;
		if(u.parent == null)
			root = v;
		else if(u.equals(u.parent.left))
			u.parent.left = v;
		else
			u.parent.right = v;
		
		if(v != null)
			v.parent = u.parent;
	}
	
	/**
	 * Inorder successor, without parent pointer.
	 * @param x
	 * @param root, root of the tree
	 * @return successor
	 */
	public TreeNode successor(TreeNode x, TreeNode root){
		if(x.right != null) return minimum(x.right);
		
		TreeNode succ = null;
		while(root != null){
			if(x.val < root.val){
				succ = root;
				root = root.left;
			}else if(x.val > root.val)
				root = root.right;
			else
				break;
		}
		return succ;
     }
	
	
	/**
	 * Inorder successor, with parent pointer.
	 * @param x
	 * @return successor
	 */
	public TreeNode successor(TreeNode x){
		if(x.right != null) return minimum(x.right);
		if(x.parent == null) return null;
		
		TreeNode succ = x.parent;
		while(succ != null && x.equals(succ.right)){
			x = succ;
			succ = succ.parent;
		}
		
		return succ;
	}
	
	/**
	 * Inorder predecessor, without parent pointer.
	 * @param x
	 * @param root, root of the tree
	 * @return predecessor
	 */
	public TreeNode predecessor(TreeNode x, TreeNode root){
		if(x.left != null) return maximum(x.left);
		
		TreeNode predecessor = null;
		while(root != null){
			if(x.val < root.val){
				root = root.left;
			}else if(x.val > root.val){
				predecessor = root;
				root = root.right;
			}else
				break;
		}
		return predecessor;
     }
	
	/**
	 * Inorder predecessor, with parent pointer.
	 * @param x
	 * @return predecessor
	 */
	public TreeNode predecessor(TreeNode x){
		if(x.left != null) return maximum(x.left);
		if(x.parent == null) return null;
		
		TreeNode predecessor = x.parent;
		while(predecessor != null && x.equals(predecessor.left)){
			x = predecessor;
			predecessor = predecessor.parent;
		}
		
		return predecessor;
	}
	
	
	/**
	 * Find the maximum node in the tree
	 * @param root
	 * @return the maximum node in the tree
	 */
	public TreeNode maximum(TreeNode root){
		if(root == null){
			System.out.println("Tree is empty");
			return null;
		}
		while(root.right != null){
			root = root.right;
		}
		return root;
	}
	
	/**
	 * Find the minimum node in the tree
	 * @param root
	 * @return the minimum node in the tree
	 */
	public TreeNode minimum(TreeNode root){
		if(root == null){
			System.out.println("Tree is empty");
			return null;
		}
		while(root.left != null){
			root = root.left;
		}
		return root;
	}
	
	/**
	 * Given a root of a binary search tree, search node with value k. Iterative. 
	 * @param root
	 * @param k
	 * @return a pointer to a node with value k if one exists, otherwise return null
	 */
	public TreeNode iterativeTreeSearch(TreeNode root, int k){
		while(root != null && k != root.val){
			if(k < root.val)
				root = root.left;
			else 
				root = root.right;
		}
		if(root == null){
			System.out.println("Tree is empty or tree doesn's have a node with value k");
		}
		return root;
	}
	
	/**
	 * Given a root of a binary search tree, search node with value k
	 * @param root
	 * @param k
	 * @return a pointer to a node with value k if one exists, otherwise return null
	 */
	public TreeNode search(TreeNode root, int k){
		if(root == null) {
			System.out.println("Cannot find a node with value k");
			return root;
		}
		if(root.val ==k) return root;
		if(k < root.val)
			return search(root.left, k);
		else 
			return search(root.right, k);
	}
	
	/**
	 * Breadth first search, find the first node with value x.
	 * @param root
	 * @param x
	 */
	public TreeNode breadthFirstSearch(TreeNode root, int x){
		if(root == null) return null;
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		
		TreeNode current = null;
		while(!queue.isEmpty()){
			current = queue.poll();
			if(current.val == x)
				return current;
			else{
				if(current.left != null) queue.offer(current.left);
				if(current.right != null) queue.offer(current.right);
			}
		}
		return null;
	}
	
	
	/**
	 * Print the binary search tree, preorder
	 * @param root
	 */
	public void preorderRecursive(TreeNode root){
		if(root == null) return;
		System.out.println(root.val);
		preorderRecursive(root.left);
		preorderRecursive(root.right);
	}
	
	/**
	 * Traversal tree pre-order, non-recursive
	 * @param root
	 * @return
	 */
	public static List<Integer> preorderTraversal(TreeNode root){
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		int status = 0;
		if(root == null) return list;
		
		while(!stack.isEmpty() || root != null){
			if(status == 0){
				if(root == null){
					status = 1;
					continue;
				}
				list.add(root.val);
				stack.push(root);
				root = root.left;
				status = 0;
			}else{
				root = stack.pop();
				root = root.right;
				status = 0;
			}
		}
		
		return list;
	}
	
	/**
	 * Print the binary search tree, in-order
	 * @param root
	 */
	public void inorderRecursive(TreeNode root){
		if(root == null) return;
		inorderRecursive(root.left);
		System.out.println(root.val);
		inorderRecursive(root.right);
	}
	
	/**
	 * Traversal tree in-order, non-recursive
	 * @param root
	 * @return in-order sequence as list
	 */
	public static List<Integer> inorderTraversal(TreeNode root){
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		int status = 0;
		
		if(root == null) return list;
		while(!stack.isEmpty() || root != null){
			if(status == 0){
				if(root == null){
					status = 1;
					continue;
				}
				stack.push(root);
				root = root.left;
				status = 0;
			}else{
				root = stack.pop();
				list.add(root.val);
				root = root.right;
				status = 0;
			}
		}
		return list;
	}
	
	/**
	 * Print the binary search tree, postorder
	 * @param root
	 */
	public void postorderRecursive(TreeNode root){
		if(root == null) return;
		postorderRecursive(root.left);
		postorderRecursive(root.right);
		System.out.println(root.val);
	}
	
	/**
	 * Traversal tree post-order, non-recursive
	 * @param root
	 * @return in-order sequence as list
	 */
	public static List<Integer> postorderTraversal(TreeNode root){
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<Integer> statusStack = new Stack<Integer>();
		int status = 0;
		statusStack.push(0);
		if(root == null) return list;
		
		while(!stack.isEmpty() || root != null){
			if(status == 0){
				if(root == null){
					status = statusStack.pop();
					continue;
				}
				stack.push(root);
				statusStack.push(1);
				root = root.left;
				status = 0;
			}else if(status == 1){
				root = stack.peek();
				root = root.right;
				statusStack.push(2);
				status = 0;
			}else{
				list.add(stack.pop().val);
				status = statusStack.pop();
			}
		}
		
		return list;
	}
	
	
	
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.parent = root;
		root.right.parent = root;
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.left.left.parent = root.left;
		root.left.right.parent = root.left;
		root.right.right = new TreeNode(6);
		root.right.right.parent = root.right;
		
		BinarySearchTree tree = new BinarySearchTree();
		tree.inorderRecursive(root);
		System.out.println();
		
		tree.insert(new TreeNode(3), root);
		tree.insert(new TreeNode(12), root);
		tree.insert(new TreeNode(8), root);
		tree.insert(new TreeNode(0), root);
		tree.inorderRecursive(root);
		System.out.println();
		
		TreeNode result = tree.breadthFirstSearch(root, 3);
		System.out.println(result.parent.val);
		
		tree.delete(root.left, root);
		tree.inorderRecursive(root);
		System.out.println();

	}
	
}
