
public class UnionFindQuickUnion {
    private int[] ids;

    public UnionFindQuickUnion(final int n) {
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    private int root(final int i) {
        int root = i;
        while (root != ids[root]) {
            root = ids[i];
        }
        return root;
    }

    public boolean find(final int i, final int j) {
        return root(i) == root(j);
    }

    public void union(final int i, final int j) {
        ids[root(i)] = root(j);
    }
}
