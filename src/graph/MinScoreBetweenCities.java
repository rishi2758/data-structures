package graph;

import java.util.*;

public class MinScoreBetweenCities {

    public int minScore(int n, int[][] roads) {

        ArrayList<int[]>[] adjList = new ArrayList[n+1];

        for(int[] connection : roads) {
            if(adjList[connection[0]] == null) {
                adjList[connection[0]] = new ArrayList<>();
            }
            if(adjList[connection[1]] == null) {
                adjList[connection[1]] = new ArrayList<>();
            }
            adjList[connection[0]].add(new int[] { connection[1], connection[2]});
            adjList[connection[1]].add(new int[] { connection[0], connection[2]});
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, false);

        q.offer(1);
        visited[1] = true;
        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()) {

            int tmp = q.poll();
            for(int[] connected : adjList[tmp]) {
                if(!visited[connected[0]]) {
                    q.offer(connected[0]);
                    visited[connected[0]] = true;
                }
                min = Math.min(min, connected[1]);
            }
        }

        return min;
    }

}