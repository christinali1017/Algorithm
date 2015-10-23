
public class UnionFindQuickFind {
    private int[] ids;

    public UnionFindQuickFind(final int n) {
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public boolean find(final int i, final int j) {
        return ids[i] == ids[j];
    }

    public void union(final int i, final int j) {
        int pid = ids[i];
        for (int k = 0; k < ids.length; k++) {
            if (ids[k] == pid) {
                ids[k] = ids[j];
            }
        }
    }

}
