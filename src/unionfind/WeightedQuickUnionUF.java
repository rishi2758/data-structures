package unionfind;

public class WeightedQuickUnionUF {

    private int[] size;
    private int[] parent;
    private int n;
    private int count;

    public WeightedQuickUnionUF(int n) {
        this.count = n;
        this.n = n;
        this.parent = new int[n];
        this.size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int parentP = find(p);
        int parentQ = find(q);

        if (parentP == parentQ) return;
        if (size[parentP] < size[parentQ]) {
            parent[q] = parentP;
            size[q] += size[parentP];
        } else {
            parent[p] = parentQ;
            size[p] += size[parentQ];
        }
        --count;
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int getCount() {
        return count;
    }

    private void validate(int p) {
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("invalid value " + p);
        }
    }
}
