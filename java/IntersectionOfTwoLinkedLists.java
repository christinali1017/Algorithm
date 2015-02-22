package leetcode;

public class IntersectionOfTwoLinkedLists {
	 public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	       if(headA == null || headB == null) return null;
	       int lenA = 0;
	       int lenB = 0;
	       ListNode temp = headA;
	       while(temp != null){
	           lenA++;
	           temp = temp.next;
	       }
	       temp = headB;
	       while(temp != null){
	           temp = temp.next;
	           lenB++;
	       }
	       int diff = Math.abs(lenA - lenB);
	       if(lenA > lenB){
	           while(diff > 0) {
	               headA = headA.next;
	               diff--;
	           }
	       }else{
	           while(diff > 0){
	               headB = headB.next;
	               diff--;
	           }
	       }
	       while(headA != null && headB != null && !headA.equals(headB)){
	           headA = headA.next;
	           headB = headB.next;
	       }
	       return headA;
	 }
	 
	 
	 public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
	       if(headA == null || headB == null) return null;
	       ListNode tempA = headA;
	       ListNode tempB = headB;
	       while(tempA != null && tempB != null){
	           tempA = tempA.next;
	           tempB = tempB.next;
	       }
	       tempA = tempA == null ? headB : tempA;
	       tempB = tempB == null? headA : tempB;
	       while(tempA != null && tempB != null){
	           tempA = tempA.next;
	           tempB = tempB.next;
	       }
	      tempA = tempA == null ? headB : tempA;
	      tempB = tempB == null ? headA : tempB;
	      while(tempA != null && tempB != null && !tempA.equals(tempB)){
	          tempA = tempA.next;
	          tempB = tempB.next;
	      }
	      return tempA;
	 }
	        
	public static void main(String[] args) {
		IntersectionOfTwoLinkedLists i = new IntersectionOfTwoLinkedLists();
		ListNode headA = new ListNode(1);
		ListNode headB = headA;
		
		ListNode result = i.getIntersectionNode(headA, headB);
		System.out.println(result.val);
	}        	 
}
