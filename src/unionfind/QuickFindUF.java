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