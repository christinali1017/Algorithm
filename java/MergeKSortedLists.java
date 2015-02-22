package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
public class MergeKSortedLists {
	public static ListNode mergeKLists(List<ListNode> lists){
        if(lists == null || lists.size() == 0) return null;
        ListNode res = new ListNode(-1);
        ListNode h1 = res;
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
            public int compare(ListNode l1, ListNode l2){
                return l1.val - l2.val;
            }
        });
        for(ListNode l : lists){
            if(l != null) queue.add(l);
        }
        while(!queue.isEmpty()){
            ListNode current = queue.poll();
            h1.next = current;
            h1 = h1.next;
            if(current.next != null)
                queue.offer(current.next);
        }
        return res.next;
	}
	
	/*
	 * use merge sort
	 */
	public  ListNode mergeKLists1(List<ListNode> lists){
		  if(lists == null || lists.size() == 0) return null;
		  return helper(lists, 0, lists.size()-1);
		}
		public ListNode helper(List<ListNode> lists, int start, int end){
		    if(start >= end) return lists.get(start);
		    int mid = (start + end)/2;
		    return mergeTwoLists(helper(lists, start, mid), helper(lists, mid+1, end));
		}
		
	    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	      if(l1 == null && l2 == null) return null;
	      ListNode head = new ListNode(-1);
	      ListNode temp = head;
	      while(l1 != null || l2 != null){
	          if(l1 != null && l2 != null){
	              if(l1.val < l2.val){
	                  temp.next = l1;
	                  l1 = l1.next;
	              }else{
	                  temp.next = l2;
	                  l2 = l2.next;
	              }
	          }else if(l1 != null){
	              temp.next = l1;
	              l1 = l1.next;
	          }else{
	              temp.next = l2;
	              l2 = l2.next;
	          }
	          temp = temp.next;
	      }
	      return head.next;
	    }
	    
	    /* Brute force: time limit exceeded*/
		public  ListNode mergeKLists2(List<ListNode> lists){
		    if(lists == null || lists.size() == 0) return null;
		    ListNode head = new ListNode(-1);
		    ListNode temp = head;
		    List<ListNode> l = new ArrayList<ListNode>();
		    for(int i = 0; i < lists.size(); i++){
		        if(lists.get(i) != null) l.add(lists.get(i));
		    }
		    while(l.size() > 0){
		    	int min = 0;
		        for(int i = 0; i < l.size(); i++){
		            if(l.get(i) == null) l.remove(i);
		            else {
		            	if(l.get(i).val < l.get(min).val) min = i;
		            }
		        }
		        if(l.size() > 0){
		        	temp.next = l.get(min);
		        	if(l.get(min).next != null) l.set(min,l.get(min).next);
		        	else l.remove(min);
		        	temp = temp.next;
		        }
		    }
		    return head.next;
		}
	

	/*Brute force, use merge2 method */
	public  ListNode mergeKLists3(List<ListNode> lists) {
		if( lists == null || lists.size() == 0) return null;
		ListNode result = lists.get(0);
		for(int i = 1; i < lists.size(); i++){
			ListNode currentList = lists.get(i);
			result = mergeTwoLists(result, currentList);

		}
		
		return result;
	}
	
	public static void main(String[] args) {
//		ListNode ln = new ListNode(2);
//		ln.next = new ListNode(5);	
//		ln.next.next = new ListNode(8); 
//		ln.next.next.next= new ListNode(33); 
//		ln.next.next.next.next= new ListNode(50);
//		
//		ListNode list1 = new ListNode(1);
//		list1.next = new ListNode(2);	
//		list1.next.next = new ListNode(3); 
//		list1.next.next.next= new ListNode(4); 
//		list1.next.next.next.next= new ListNode(5);
//		list1.next.next.next.next.next= new ListNode(6);
//		list1.next.next.next.next.next.next= new ListNode(7);
//		
//		
//		ListNode list2 = new ListNode(11);
//		list2.next = new ListNode(12);	
//		list2.next.next = new ListNode(13); 
//		list2.next.next.next= new ListNode(14); 
//		list2.next.next.next.next= new ListNode(15);
//		list2.next.next.next.next.next= new ListNode(16);
//		list2.next.next.next.next.next.next= new ListNode(17);
		
		List<ListNode> lists = new ArrayList<ListNode>();
		ListNode ln = null;
		ListNode list1 = null;
		lists.add(ln);
		lists.add(list1);
//		lists.add(list2);
		MergeKSortedLists m = new MergeKSortedLists();
		ListNode result = m.mergeKLists2(lists);
		while(result != null){
			System.out.print(result.val + "-->");
			result = result.next;
		}
	}
}
