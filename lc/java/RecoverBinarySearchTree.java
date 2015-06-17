package leetcode;
public class RecoverBinarySearchTree {
	/*Morris traversal O(1) */
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode[] arr = new TreeNode[3];
        recoverTree(root, arr);
        int temp = arr[0].val;
        arr[0].val = arr[1].val;
        arr[1].val = temp;
    }
    
    public void recoverTree(TreeNode root, TreeNode[] arr){
        if(root == null) {
            return;
        }

        TreeNode pre = null; /* predecessor*/
        TreeNode previous = null;
        while (root != null) {
            if (root.left == null) {
                if (previous != null && previous.val > root.val) {
                    if(arr[0] == null) {
                        arr[0] = previous;
                    }
                    arr[1] = root;
                }
                previous = root;
                root = root.right;
            }else{
                /* find predecessor */
                pre = root.left;
                while (pre.right != null && pre.right != root) {
                    pre = pre.right;
                }

                /* if predecessor's right == null, set current node as its right child */
                if (pre.right == null) {
                    pre.right = root;
                    root = root.left;
                } else {
                    /* recover tree, when return to parent node the second time */
                    pre.right = null;
                    if (previous != null && previous.val > root.val) {
                        if(arr[0] == null) {
                            arr[0] = previous;
                        }
                        arr[1] = root;
                    }   
                    previous = root;
                    root = root.right;
                }
            }
        }
    }
    
    
    /*Recursion*/
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode[] arr = new TreeNode[3];
        recoverTree(root, arr);
        int temp = arr[0].val;
        arr[0].val = arr[1].val;
        arr[1].val = temp;
    }
    
    public void recoverTree(TreeNode root, TreeNode[] arr) {
        if(root == null) {
            return;
        }
        recoverTree(root.left, arr);
        if (arr[2] != null && arr[2].val > root.val) {
            if(arr[0] == null) {
                arr[0] = arr[2];
            }
            arr[1] = root;
        }
        arr[2] = root;
        recoverTree(root.right, arr);
    }
    
    
}
