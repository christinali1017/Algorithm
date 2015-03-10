package leetcode;
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
    	if(head == null || k <=1) return head;
    	
    	int len = 0;
    	ListNode temp = head;
    	while(temp != null){
    		temp = temp.next;
    		len++;
    	}
    	
    	ListNode pre = null;
    	ListNode next = null;
    	ListNode start = null;
    	ListNode end = null;
    	ListNode current = head;
    	while(current != null && len >= k){
    		int count = k;
    		temp = current;
    		while(count > 0 && temp != null){
    			temp = temp.next;
    			count--;
    		}
    		next = temp;
    		start = reverse(current, k);
    		end = current;
    		if(pre == null) head = start;
    		else pre.next = start;
    		end.next = next;
    		pre = end;
    		current = next;
    		len -= k;
    	}
    	return head;
    }
    
    public ListNode reverse(ListNode head, int k){
    	if(head == null || k <=1) return head;
    	
    	ListNode pre = null;
    	ListNode current = head;
    	ListNode next = null;
    	while(k > 0 && current != null){
    		next = current.next;
    		current.next = pre;
    		pre = current;
    		current = next;
    		k--;
    	}
    	
    	return pre;
    }
    
    
    public ListNode reverseKGroup1(ListNode head, int k) {
        if(head == null || head.next == null || k <= 1) return head;
        int len = 0;
        ListNode temp = head;
        while(temp != null){
            len++;
            temp = temp.next;
        }
        if(k > len) return head;
        ListNode res = null, cur = null, pre = null, next = null, savehead = null;
        for(int i = 1; i <= len && i + k <= len+1; i += k){
            savehead = head;
            for(int j = 1; j <= k; j++){
                next = head.next;
                if(cur == null) {
                    cur = head;
                    cur.next = null;
                }else{
                    head.next = cur;
                    cur = head;
                }
                head = next;
            }
            if(res == null) res = cur;
            else{
                pre.next = cur;
            }
            pre = savehead;
            cur = null;
        }
        
        if(head != null) pre.next = head;
        return res;
    }
    
    public static void main(String[] args) {
    	ReverseNodesInKGroup r = new ReverseNodesInKGroup();
    	ListNode head = new ListNode(1);
    	head.next = new ListNode(2);
    	ListNode result = r.reverseKGroup1(head, 2);
    	while(result != null){
    		System.out.println(result.val);
    		result = result.next;
    	}
	}

}
