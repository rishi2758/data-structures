package unionfind;

public class WeightedQuickUnionPathHalving extends WeightedQuickUnion{
    public WeightedQuickUnionPathHalving(int n) {
        super(n);
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            int pParent = parent[p];
            int pGrandParent = parent[pParent];
            parent[p] = pGrandParent;
            p = pGrandParent;
        }
        return p;
    }
}
