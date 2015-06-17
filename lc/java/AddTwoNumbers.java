package leetcode;
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode fakeHead = new ListNode(-1);
        ListNode res = fakeHead;
        int carry = 0;
        while(l1 != null && l2 != null){
            int sum = l1.val + l2.val + carry;
            int mod = sum % 10;
            carry = sum / 10;
            res.next = new ListNode(mod);
            res = res.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
            int sum = l1.val + carry;
            int mod = sum % 10;
            carry = sum / 10;
            res.next = new ListNode(mod);
            l1 = l1.next;
            res = res.next;
        }
        while(l2 != null){
            int sum = l2.val + carry;
            int mod = sum % 10;
            carry = sum / 10;
            res.next = new ListNode(mod);
            l2 = l2.next;
            res = res.next;
        }
        if(carry != 0)
            res.next = new ListNode(carry);
        return fakeHead.next;
    }
    public static void main(String[] args) {
    	AddTwoNumbers a = new AddTwoNumbers();
    	
    	ListNode l1 = new ListNode(1);
		
    	ListNode l2 = new ListNode(9);
		l2.next = new ListNode(9);	
	
    	ListNode result = a.addTwoNumbers(l1, l2);
    	while(result != null){
    		System.out.println(result.val);
    		result = result.next;
    	}
	}

}
