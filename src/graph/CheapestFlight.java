package graph;

import java.util.*;
/**
 * Bellman Ford Algorithm
 * */
@SuppressWarnings({"ALL", "unchecked"})
public class CheapestFlight {

    @SuppressWarnings("InnerClassMayBeStatic")
    static class Pair {
        private final int flight;
        private final int fare;

        public Pair(int flight, int fare) {
            this.flight = flight;
            this.fare = fare;
        }

        public Pair(Pair pair, int fare) {
            this.flight = pair.getFlight();
            this.fare = pair.getFare() + fare;
        }

        public int getFlight() {
            return this.flight;
        }

        public int getFare() {
            return this.fare;
        }
    }

    @SuppressWarnings("unchecked")
    private List<Pair>[] buildGraph(int n, int[][] flights) {
        List<Pair>[] adjList = new List[n];
        for (int[] pair : flights) {
            int src = pair[0];
            int dst = pair[1];
            int fare = pair[2];
            if (adjList[src] == null) {
                adjList[src] = new ArrayList<>();
            }
            adjList[src].add(new Pair(dst, fare));
        }
        return adjList;
    }

    public void findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        List<Pair>[] adjList = buildGraph(n, flights);
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(src, 0));
        int level = 0;
        int[] cheapest = new int[n];
        Arrays.fill(cheapest, Integer.MAX_VALUE);
        while (level <= k && !q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Pair tmp = q.poll();
                if (adjList[tmp.getFlight()] != null) {
                    for (Pair pair : adjList[tmp.getFlight()]) {
                        Pair newPair = new Pair(pair, tmp.getFare());
                        if (newPair.getFare() < cheapest[newPair.getFlight()]) {
                            cheapest[newPair.getFlight()] = newPair.getFare();
                        }
                        q.offer(newPair);
                    }
                }
            }
            ++level;
        }
    }

    public static void main(String[] args) {

        int[][] flights = {{0, 1, 5}, {1, 2, 5}, {0, 3, 2}, {3, 1, 2}, {1, 4, 1}, {4, 2, 1}};
        new CheapestFlight().findCheapestPrice(5, flights, 0, 2, 2);
    }

}
