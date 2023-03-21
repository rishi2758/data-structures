package slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BestTeamWithoutConflicts {

    public void bestTeamScore(int[] scores, int[] ages) {

        int n = ages.length;

        Map<Integer, Integer> scoreByAge = new HashMap<>();
        for (int i = 0; i < n; i++) {
            scoreByAge.put(scores[i], ages[i]);
        }

        Arrays.sort(scores);

        int ans = Integer.MIN_VALUE;
        int i = 1;
        int j = 0;
        while (true) {
            boolean f1 = false;
            boolean f2 = false;
            int pans = scores[i-1];
            while (i < n) {
                f1 = true;
                if (scoreByAge.get(scores[i - 1]) < scoreByAge.get(scores[i])) {
                    pans += scores[i];
                    ++i;
                } else {
                    break;
                }
            }
            --i;
            ans = Math.max(pans, ans);
            while (j < i) {
                f2 = true;
                if (scoreByAge.get(scores[j]) > scoreByAge.get(scores[i])) {
                    pans -= scores[j];
                    ++j;
                } else {
                    break;
                }
            }
            if (!f1 && !f2) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] scores = {1, 3, 5, 10, 15};
        int[] ages = {1, 2, 3, 4, 5};
        new BestTeamWithoutConflicts().bestTeamScore(scores, ages);
    }

}
