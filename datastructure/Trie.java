class TrieNode {
    // Initialize your data structure here.
    public TrieNode[] edges;
    public boolean isLeaf; //check if a trienode is a leaf node
    public TrieNode() {
        // all possible sons
        edges = new TrieNode[26];
    }
}

public class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        root = insert(root, word, 0);
    }
    
    public TrieNode insert(TrieNode node, String word, int len) {
        if (node == null) {
            node = new TrieNode();
        }
        if (len == word.length()) {
            node.isLeaf = true;
            return node;
        }
        int pos = word.charAt(len) - 'a';
        node.edges[pos] = insert(node.edges[pos], word, len + 1);
        return node;
    }
    
    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode temp = searchHelper(root, word, 0);
        return temp == null ? false : temp.isLeaf;
    }
    
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode temp = searchHelper(root, prefix, 0);
        return temp == null ? false : true;
    }
    
    public TrieNode searchHelper(TrieNode node, String word, int len) {
        if (node == null) {
            return null;
        }
        if (len == word.length()) {
            return node;
        }
        int pos = word.charAt(len) - 'a';
        return searchHelper(node.edges[pos], word, len + 1);
    }
}