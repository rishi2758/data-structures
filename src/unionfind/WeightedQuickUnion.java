package unionfind;

import java.util.Arrays;

public class WeightedQuickUnion extends QuickUnion {

    private int[] size;

    public WeightedQuickUnion(int n) {
        super(n);
        this.size = new int[n];
        Arrays.fill(size, 1);
    }

    public void union(int p, int q) {
        int parentP = find(p);
        int parentQ = find(q);
        if (parentP == parentQ) return;
        if (size[parentP] < size[parentQ]) {
            parent[parentP] = parentQ;
            size[parentQ] += size[parentP];
        } else {
            parent[parentQ] = parentP;
            size[parentP] += size[parentQ];
        }
        --count;
    }
}
