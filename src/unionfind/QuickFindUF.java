package unionfind;

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