package graph;

import java.util.*;

public class CommonNodeMinDistance {

    public void closestMeetingNode(int[] edges, int node1, int node2) {

        int n = edges.length;

        List<Integer>[] adjList = buildAdjList(edges, n);
        int[] d1 = calculateDistance(adjList, n, node1);
        int[] d2 = calculateDistance(adjList, n, node2);

        int ans = Integer.MAX_VALUE;
        int i = -1;
        int index = 0;

        while (index < n) {
            if (d1[index] != Integer.MAX_VALUE && d2[index] != Integer.MAX_VALUE) {
                int potentialAns = Math.max(d1[index], d2[index]);
                if (potentialAns < ans) {
                    i = index;
                    ans = potentialAns;
                }
            }
            ++index;
        }

    }

    public int[] calculateDistance(List<Integer>[] adjList, int n, int source) {
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        q.offer(source);
        int level = 0;
        d[source] = 0;
        while (!q.isEmpty()) {
            int tmp = q.poll();
            set.add(tmp);
            for (int child : adjList[tmp]) {
                if (!set.contains(child)) {
                    d[child] = level + 1;
                    q.offer(child);
                }
            }
            ++level;
        }
        return d;
    }

    @SuppressWarnings("unchecked")
    private List<Integer>[] buildAdjList(int[] edges, int n) {

        List<Integer>[] adjList = new List[n];

        for (int source = 0; source < n; source++) {
            if (adjList[source] == null) {
                adjList[source] = new ArrayList<>();
            }
            if (edges[source] != -1) {
                adjList[source].add(edges[source]);
            }
        }

        return adjList;
    }

    public static void main(String[] args) {
        int[] edges = {2,2,3,-1};
        new CommonNodeMinDistance().closestMeetingNode(edges, 0, 1);
    }

}
