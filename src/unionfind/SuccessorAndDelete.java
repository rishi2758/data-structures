package unionfind;

public class SuccessorAndDelete extends FindMaxElementInConnectedComponent {

    private boolean[] deleted;

    public SuccessorAndDelete(int n) {
        super(n);
        this.deleted = new boolean[n];
    }

    public void delete(int x) {
        validate(x);
        deleted[x] = true;
        if (x > 0 && deleted[x - 1]) {
            union(x - 1, x);
        }
        if (x < n - 1 && deleted[x + 1]) {
            union(x, x + 1);
        }
    }

    public int getSuccessor(int x) {
        validate(x);
        if (!deleted[x]) {
            return x;
        }
        return super.find(x) + 1;
    }

}
