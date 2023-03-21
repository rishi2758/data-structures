package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinimumJump {

    @SuppressWarnings("unchecked")
    public void jump(int[] nums) {
        List<Integer>[] adjList = new List[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            if (adjList[i] == null) {
                adjList[i] = new ArrayList<>();
            }
            int t = nums[i];
            while (t > 0) {
                adjList[i].add(i + t);
                --t;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                int tmp = q.poll();
                if (tmp == nums.length - 1) {
                    return;
                }
                for (int n : adjList[tmp]) {
                    q.offer(n);
                }
                --size;
            }
            ++ans;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        new MinimumJump().jump(nums);
    }

}
