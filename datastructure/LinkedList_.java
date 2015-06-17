package datastructure;

public class LinkedList_ {
	//public ListNode head;
	
	public void remove(ListNode head, ListNode node){
		if(head == null || node == null) return;
		
		if(head.equals(node)){
			head = head.next;
			return;
		}
		
		node.previous.next = node.next;
		if(node.next != null)
			node.next.previous = node.previous;
	}
	
	/**
	 * Reverse linked list in place
	 * @param head
	 * @return head of list
	 */
	public ListNode reverse(ListNode head){
		if(head == null) return null;
		
		ListNode previous = null,
				 next = null;
		
		while(head != null){
			next = head.next;
			head.next = previous;
			previous = head;
			head = next;
		}
		
		head = previous;
		return head;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);

		System.out.println("--------------");
		ListNode temp = head;
		while(temp != null){
			System.out.print(temp.val + "-->");
			temp = temp.next;
		}
		
		System.out.println();
		System.out.println("----------------");
		LinkedList_ list = new LinkedList_();
		head = list.reverse(head);
		temp = head;
		while(temp != null){
			System.out.print(temp.val + "-->");
			temp = temp.next;
			
		}
	}

}
