package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class CheatDay
{

    public int cheatPath(int n, int[][] sitting, int k, int[] favouriteStudents)
    {
        Map<Integer, ArrayList<Integer>> arrangement = new HashMap<>();
        for (int i = 0; i < sitting.length; i++) {
            ArrayList<Integer> child = arrangement.getOrDefault(sitting[i][0], new ArrayList<>(2));
            child.add(sitting[i][1]);
            arrangement.put(sitting[i][0], child);
        }
        Set<Integer> fav = new HashSet<>();
        for (int i = 0; i < k; i++) {
            fav.add(favouriteStudents[i]);
        }
        AtomicInteger ans = new AtomicInteger(0);
        maxCheatPath(1, arrangement, fav, ans);
        return ans.get();
    }

    private int maxCheatPath(int root, Map<Integer, ArrayList<Integer>> tree, Set<Integer> favourites, AtomicInteger ans)
    {

        if (root == -1) {
            return 0;
        }
        // check if we reach the leaves
        if (!tree.containsKey(root)) {
            return 1;
        }

        int left = -1, right = -1;
        if (tree.get(root).size() == 2) {
            left = tree.get(root).get(0);
            right = tree.get(root).get(1);
        } else {
            left = tree.get(root).get(0);
        }
        int lH = maxCheatPath(left, tree, favourites, ans);
        int rH = maxCheatPath(right, tree, favourites, ans);

        int localAns = 0;
        if (!favourites.contains(root)) {
            if (!favourites.contains(left) && !favourites.contains(right)) {
                localAns = 1 +Math.max(lH,rH);
            } else {
                if (!favourites.contains(left)) {
                    localAns = 1 + lH;
                }
                if (!favourites.contains(right)) {
                    localAns = 1 + rH;
                }
            }
        } else {
            if (!favourites.contains(left) && !favourites.contains(right)) {
                localAns = Math.max(lH, rH);
            } else {
                if (!favourites.contains(left)) {
                    localAns = lH;
                }
                if (!favourites.contains(right)) {
                    localAns = rH;
                }
            }
        }
        ans.set(Math.max(ans.get(), localAns));

        return localAns;
    }

    public static void main(String[] args)
    {
        int n = 4;
        //int[][] sitting = {{1, 2}, {1, 3}, {3, 4}, {3, 5}};
        int[][] sitting = {{1,2},{1,3},{2,4},{2,5},{3,6},{3,7},{5,8},{5,9},{9,10}};
        int k = 3;
        int[] favouriteStudents = {1,2,3};
        int max = new CheatDay().cheatPath(n, sitting, k, favouriteStudents);
        System.out.println(max);
    }

}
