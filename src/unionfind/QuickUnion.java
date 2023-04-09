package unionfind;

/**
 * This is a lazy approach as not every element's id gets changed
 * while performing union operation.
 *
 * Motivation:
 * Reference : Algorithms By Robert Sedgewick & Kevin Wayne.
 * Dynamic connectivity.
 *
 * The input is a sequence of pairs of integers,
 * where each integer represents an object of some type
 * & we are to interpret the pair p q as meaning p is connected to q.
 *
 * We assume that "is connected to" is an equivalence relation:
 * symmetric: If p is connected to q, then q is connected to p.
 * transitive: If p is connected to q and q is connected to r, then p is connected to r.
 * reflexive: p is connected to p.
 *
 * An equivalence relation partitions the objects into equivalence classes or connected components.
 * Our goal is to write a program to filter out extraneous pairs from the sequence:
 * When the program reads a pair p q from the input,
 * it should write the pair to the output only if the pairs it has seen to that point do not imply that p is connected to q.
 * If the previous pairs do imply that p is connected to q,
 * then the program should ignore the pair p q and proceed to read in the next pair.
 *
 * */
public class QuickUnion implements UnionFind {

    protected int[] parent;
    protected int n;
    protected int count;

    public QuickUnion(int n) {
        this.parent = new int[n];
        this.n = n;
        this.count = n;

        for(int i = 0 ; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int p) {
        validate(p);
        while(p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        return pID == qID;
    }

    @Override
    public int componentCount() {
        return this.count;
    }


    /**
     * As you can see here this is the lazy approach , in contrast to quick-find.
     * why lazy ?
     * here the parent[] represents a component structures/groups
     * not every element groupId is changed per union operation
     * only the id for the root of p is changed to root of q
     *
     * parent represents the forest like structure where each element is either the part of
     * a tree or a root itself.
     *
     * here find operation is costly in contrast to quick find algorithm.
     *
     *
     * visualise this algorithm there are lot-of trees in a forest
     * you have been given to put a band around those two trees who are being at greater risk of infection with particular disease.
     * you are given two trees, you need to place radium tape put around the bark of the trees.
     *
     * not a very good example though , but it might help...:p
     *
     * first you need to find the tree itself & then find it's root
     * then check is this root belongs to same band connecting the root of the other tree provided
     * if yes don't need to do anything just return.
     * else put that radium band around those roots.
     *
     * only root's id is getting changed.
     *
     * multiple outcomes through different implementations
     * current implementation
     * changes also finds the root of the qth element and changes the p's
     * root id to q's root id,
     * what if we directly put the id present at q
     * what will happen
     *
     * Let's assume
     *  1 3 1   4 8 4
     * <1,2,3> <4,5,8>
     * union(3,5)
     * if just replace the rootP's ID with immediate parent of q, height of the tree increases as union operation grows ,
     * because let's assume 5 was at the bottom of the second component tree and then tree with root 1 is put below to 5.
     * new height = height of p+ height of q; this one creates a leaner tree in comparison to below approach.
     *  8 3 1 4 8 4
     * <1,2,3,4,5,8>
     * if we replace the root although the probability of height increasing in comparison with above approach is less but
     * if we consider worst case it will still increase , assume the q belong to shorter tree (min height) and p belongs to
     * larger height tree and when p is now placed below q height is increased.
     * also width of the tree increases.
     *
     * Coming to worst case scenarios , both the approaches lead to trees with increased height and width.
     *
     * WeightQuickUnion solves this problem , refer to other code.
     *
     * Time Complexity and Space Complexity for both
     * Quick Union & Find algorithm still remains the same O(N^2) and O(N)
     * but amortized analysis reveals the quick union can be faster than quick find
     * in certain cases where quick union's union operation consists of find operation
     * which the time complexity of O(n) but it could be less than that.(because of number of groups increases)
     * finding root doesn't leads to access to all n elements.
     *
     * whereas in quick union's union operation every element is accessed to perform union operation
     * because it checks union condition within for loop putting each element in another set on by one
     * i.e. changing ids simultaneously.
     *
     * in this case find only traverses lesser number of elements and then changes its ids.
     * find(p) & find(q).
     * */
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if(pID == qID) return;
        parent[pID] = qID;
        count--;
    }

    protected void validate(int p) {
        if(p < 0 || p >= n) {
            throw new IllegalArgumentException("Invalid id "+p+" provided");
        }
    }



}
