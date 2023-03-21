package graph;

import java.util.*;

public class SumOfDistancesInTree {

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            Arrays.fill(graph[edge[0]],0);
            Arrays.fill(graph[edge[1]],0);
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            Queue<Integer> q = new LinkedList<>();
            Set<Integer> set = new HashSet<>();
            q.offer(i);
            int level = 0;
            while (!q.isEmpty()) {
                int levelSize = q.size();
                ans[i] = ans[i] + (level * levelSize);
                while (levelSize-- > 0) {
                    int node = q.poll();
                    int[] connections = graph[node];
                    set.add(node);
                    for (int c = 0; c < connections.length; c++) {
                        if (connections[c] > 0 && !set.contains(c)) {
                            q.offer(c);
                        }
                    }
                    graph[i][node] += level;
                }
                ++level;
            }
        }
        return ans;
    }

    public int[] sumOfDistancesInTreeNaive(int n, int[][] edges) {
        boolean[][] graph = new boolean[n][n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = true;
            graph[edge[1]][edge[0]] = true;
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            Queue<Integer> q = new LinkedList<>();
            Set<Integer> set = new HashSet<>();
            q.offer(i);
            int level = 0;
            while (!q.isEmpty()) {
                int levelSize = q.size();
                ans[i] = ans[i] + (level * levelSize);
                while (levelSize-- > 0) {
                    int node = q.poll();
                    boolean[] connections = graph[node];
                    set.add(node);
                    for (int c = 0; c < connections.length; c++) {
                        if (connections[c] && !set.contains(c)) {
                            q.offer(c);
                        }
                    }
                }
                ++level;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        int[] ans = new SumOfDistancesInTree().sumOfDistancesInTree(6, edges);
        for (int a :
                ans) {
            System.out.print(a + ",");
        }
    }

}
