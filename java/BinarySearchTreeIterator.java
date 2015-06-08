//O(h) space

public class BSTIterator {
    Stack<TreeNode> stack; 
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if (stack.isEmpty()) {
            return -1;
        }
        TreeNode cur = stack.pop();
        int res = cur.val;
        if (cur.right != null) {
            cur = cur.right;
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }
        return res;
    }
}

	
 //o(n) space, unqualified

 class BinarySearchTreeIterator {
    Stack<Integer> stack; 
    public BSTIterator(TreeNode root) {
        stack = new Stack<Integer>();
        inorderReverse(stack, root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if (hasNext()) {
            return stack.pop();
        }
        return -1;
    }
    
    private void inorderReverse(Stack<Integer> stack, TreeNode root) {
        if (root == null) {
            return;
        }
        inorderReverse(stack, root.right);
        stack.push(root.val);
        inorderReverse(stack, root.left);
    }
}