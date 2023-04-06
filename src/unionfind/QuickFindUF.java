package unionfind;

/**
 * QuickFind Algorithm is a eager approach
 * where union(p,q) -> changes the ids of each element in a set containing p with id of q.
 * p and q can belong to different individual sets & their component/set identifier is stored in ids array.
 * union basically merges these two components if a request is received from the client
 * to merge any element from one to other element across these sets.
 *
 * In this algo the component/set ids of first argument is replaced with second arguments setId
 * you can visualise how this approach has its own pros and cons now.
 * first set could belong to a larger component and second one can be contained inside a different smaller component.
 * now as per the implementation this larger set would be identified by traversing each array and replacing their ids with the other smaller
 * component.
 * other one is components can become bigger & bigger that would lead to memory compaction issue. (as more of data has now been a part of larger pool)
 *
 * for a single union operation it takes approx O(n) time to complete its operation.
 * if there are n elements it would take approx O(n^2) time Space Complexity would also be O(n).
 *
**/

public class QuickFindUF {
    private int n;
    private int[] ids;
    private int count;

    public QuickFindUF(int n) {
        this.n = n;
        count = n;
        ids = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        /**
         * Follow here carefully , although it seems trivial , but if you don't store the ids
         * before changing them while performing union in for loop
         * you may end up inconsistent union stage where
         *
         *     1        5
         * <1,2,3,4,8> <5,6,7>
         *     union(4,5)
         *     5             1
         * <1,2,3,4,5,6,7>  <8>
         *
         *     Why this ??
         *     on traversing ids[0...n] only those elements would be mutating whose id == ids[p],
         *     since ids[p] is itself prone to mutation , it could lead to data inconsistency at runtime.
         *     why is it prone ?
         *
         *     visualise a set with element 4, since every element present in this set
         *     would be mutating i.e. their ids are about to be replaced with ids[q] , if you don't store
         *     ids[p=4], your algorithm would work till p=4 after which
         *     since ids[p=4] is = 5 & ids[p=8] is still = 1.
         *     the condition of union no longer holds true now, by which ids[p=8] is left as is.
         *     resulting in two components with ids 5 and ids 1.
         *     if you change the line 73 if(ids[i] == ids[p])
         *
         *    keep in mind qID can be used as ids[q] directly, why ? because second component is
         *    not mutating it is just being read.
         *    i.e. if you change line 75 with ids[i] = ids[q], it won't make any difference.
         * 
        * */
        int pID = ids[p];
        int qID = ids[q];

        if (pID == qID) return;

        for (int i = 0; i < n; i++) {
            if (ids[i] == pID) {
                ids[i] = qID;
            }
        }
        count--;
    }

    public int find(int i) {
        validate(i);
        return ids[i];
    }

    public boolean connected(int i, int j) {
        validate(i);
        validate(j);
        return ids[i] == ids[j];
    }

    public int getCount() {
        return this.count;
    }

    private void validate(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException();
        }
    }
}