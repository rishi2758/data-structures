package unionfind;

public class WeightedQuickUnionPathSplitting extends WeightedQuickUnion{

    public WeightedQuickUnionPathSplitting(int n) {
        super(n);
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            int pParent = parent[p];
            int pGrandParent = parent[pParent];
            parent[p] = pGrandParent;
            p = pParent;

        }
        return p;
    }

}
