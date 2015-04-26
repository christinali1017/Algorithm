package leetcode;

public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode pre = fakeHead;
        while(head != null){
            if(head.val == val){
                pre.next = head.next;
                head = pre.next;
            }else{
                pre = head;
                head = head.next;
            }
        }
        return fakeHead.next;
    }
}
