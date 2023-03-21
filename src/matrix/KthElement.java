package matrix;

public class KthElement {

    public void kthSmallest(int[][] matrix, int k) {
        int r = matrix.length;
        int c = matrix[0].length;

        int low = 0;
        int high = matrix[r - 1][c - 1];
        while (low <= high) {
            int mid = (low + high) / 2;
            if (countLessThanOrEqual(matrix, mid) < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }

    private int countLessThanOrEqual(int[][] matrix, int mid) {

        int count = 0;

        int r = matrix.length;
        int c = matrix[0].length;
        int i = 0;
        int j = c - 1;
        while (i < r && j >= 0) {

            if (matrix[i][j] > mid) {
                j--;
            } else {
                count += j + 1;
                ++i;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int[][] matrix = {{1, 3, 7}, {5, 10, 12}, {6, 10, 15}};
        int k = 5;
        for (int[] ints : matrix) {
            for (int c = 0; c < ints.length; c++) {
                System.out.println(ints[c] + " has " + new KthElement().countLessThanOrEqual(matrix, ints[c]) + "smaller numbers");
            }
        }
        new KthElement().kthSmallest(matrix, k);
    }

}
