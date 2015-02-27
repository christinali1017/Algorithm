package leetcode;

import java.util.HashMap;

public class CopyListWithRandomPointer {
	/*copy list and divide*/
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode temp = head;
        while(temp != null){
            RandomListNode next = temp.next;
            temp.next = new RandomListNode(temp.label);
            temp.next.next = next;
            temp = next;
        }
        temp = head;
        while(temp != null){
            if(temp.random != null){
                temp.next.random = temp.random.next;
            }
            temp = temp.next.next;
        }
        RandomListNode fakeHead = new RandomListNode(-1);
        RandomListNode copy = fakeHead;
        while(head != null){
            RandomListNode next = head.next.next;
            copy.next = head.next;
            copy = copy.next;
            copy.next = null;
            head.next = next;
            head = next;
        }
        return fakeHead.next;
    }
    
    /*Use hashmap store old and copy, then copy the random pointer*/
    public RandomListNode copyRandomList1(RandomListNode head) {
        if(head == null) return null;
        RandomListNode copy = new RandomListNode(-1);
        RandomListNode temp = head;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        while(temp != null){
            copy.next = new RandomListNode(temp.label);
            map.put(temp, copy.next);
            copy = copy.next;
            temp = temp.next;
        }
        copy = map.get(head);
        RandomListNode saveHead = copy;
        while(head != null){
            copy.random = map.get(head.random);
            head = head.next;
            copy = copy.next;
        }
        return saveHead;
    }
    
    public static void main(String[] args) {
		RandomListNode ln = new RandomListNode(2);
		
		ln.next = new RandomListNode(1);
		ln.next.next = new RandomListNode(8); 
		ln.next.next.next= new RandomListNode(3); 
		ln.next.next.next.next= new RandomListNode(5);
		
		ln.random = ln.next.next;
		ln.next.random = ln;
		
		CopyListWithRandomPointer c = new CopyListWithRandomPointer();
		RandomListNode copy = c.copyRandomList(ln);
		
		while(copy != null){
			System.out.print("node is: "+copy.label);
			if(copy.random != null)
				System.out.println(", random is: " +copy.random.label);
			System.out.println();
			copy = copy.next;
			
		}
		
	}

}
