package unionfind;

public class QuickUnionUF {

    private int[] parent;
    private int n;
    private int count;

    public QuickUnionUF(int n) {
        this.parent = new int[n];
        this.n = n;
        this.count = n;

        for(int i = 0 ; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int p) {
        validate(p);

    }

    public boolean connected(int p, int q) {

    }

    public void union(int p, int q) {

    }

    private void validate(int p) {
        if(p < 0 || p >= n) {
            throw new IllegalArgumentException("Invalid id "+p+" provided");
        }
    }

}
