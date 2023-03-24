package unionfind;

public class UnionFind {

    private int n;
    private int[] ids;
    private int count;

    public UnionFind(int n) {
        this.n = n;
        count = n;
        ids = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public void union(int p, int q) {
        int idP = ids[p];
        int idQ = ids[q];
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == idP) {
                ids[i] = idQ;
            }
        }
    }

    public int find(int i) {
        return ids[i];
    }

    public boolean connected(int i, int j) {
        return ids[i] == ids[j];
    }

    private void validate(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {

    }

}