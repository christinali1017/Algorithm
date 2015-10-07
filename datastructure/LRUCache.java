import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node {
        Node pre, next;
        int key;
        int val;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Map<Integer, Node> map;

    private Node head;

    private Node tail;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        updateToLatest(key, node);
        return node.val;
    }

    private void updateToLatest(int key, Node node) {
         if (node != tail) {
            //remove node from the original place.
            if (node == head) {
                head = head.next;
            } else {
                node.pre.next = node.next;
            }
            node.next.pre = node.pre;
            
            //add node to tail.
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = node;
        }
    }

    public void set(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            //set node's value
            node.val = value;
            updateToLatest(key, node);
        } else {
            Node target = new Node(key, value);
            //If cache is full, remove least recent from head
            if (map.size() >= capacity) {
                map.remove(head.key);
                head = head.next;
                if (head != null) {
                    head.pre = null;
                } else {
                    tail = null;
                }
            }
            if (head == null){
                head = target;
                tail = target;
            } else {
                tail.next = target;
                target.pre = tail;
                tail = target;
                map.put(key, target);
            }
            map.put(key, target);
        }
    }

    public static void main(String[] args) {
        
    }
}
