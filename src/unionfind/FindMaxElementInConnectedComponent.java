package unionfind;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FindMaxElementInConnectedComponent extends WeightedQuickUnionPathHalving {

    private int max[];

    public FindMaxElementInConnectedComponent(int n) {
        super(n);
        this.max = new int[n];
        for (int i = 1; i < n; i++) {
            max[i] = i;
        }
    }

    @Override
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
        max[parentP] = max[parentQ] = Math.max(max[parentP], max[parentQ]);
        --count;
    }

    @Override
    public int find(int p) {
        validate(p);
        return max[super.find(p)];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        UnionFind uf = new FindMaxElementInConnectedComponent(n);
        while (n > 0) {
            String[] input = br.readLine().split(" ");
            int p = Integer.parseInt(input[0]);
            int q = Integer.parseInt(input[1]);
            uf.union(p, q);
            --n;
        }
        int t = Integer.parseInt(br.readLine());
        while (t > 0) {
            int p = Integer.parseInt(br.readLine());
            System.out.println("Max Element in Component " + uf.find(n));
            --t;
        }
    }

}
