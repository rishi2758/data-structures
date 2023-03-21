package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * Given a chess board of size n*m, A knight, is present at [sx, sy].
 * We are given a destination position [dx, dy].
 * We need to see if the knight can go from the starting position to the destination position,
 * if yes, then we need to return the minimum number of moves, if not return -1.
 * */
@SuppressWarnings("ALL")
public class MinKnightMoves {

    //   x,y
    // x+2,y+-1
    // x-2,y+-1
    // x+-1,y+2
    // x+-1,y-2

    @SuppressWarnings("InnerClassMayBeStatic")
    public static class Coordinate {
        final int x;
        final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int minimumMoves(int n, int m, int sx, int sy, int dx, int dy) {
        if (n < 0 && m < 0) {
            return -1;
        }

        Queue<Coordinate> q = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        q.add(new Coordinate(sx, sy));

        int min = -1;

        while (!q.isEmpty()) {
            Coordinate tmp = q.poll();

            if (tmp.x == dx && tmp.y == dy) {
                return min;
            }

            visited.add(tmp);

            Coordinate m1 = new Coordinate(tmp.x + 2, tmp.y + 1);
            Coordinate m2 = new Coordinate(tmp.x + 2, tmp.y - 1);
            Coordinate m3 = new Coordinate(tmp.x - 2, tmp.y + 1);
            Coordinate m4 = new Coordinate(tmp.x - 2, tmp.y - 1);
            Coordinate m5 = new Coordinate(tmp.x + 1, tmp.y + 2);
            Coordinate m6 = new Coordinate(tmp.x - 1, tmp.y + 2);
            Coordinate m7 = new Coordinate(tmp.x + 1, tmp.y - 2);
            Coordinate m8 = new Coordinate(tmp.x - 1, tmp.y - 2);

            //check constraints x >= 0 && y < m && y >= 0  && x < n
            if (m1.x >= 0 && m1.y < m && m1.y >= 0 && m1.x < n && !visited.contains(m1)) {
                q.offer(m1);
            }
            if (m2.x >= 0 && m2.y < m && m1.y >= 0 && m1.x < n && !visited.contains(m2)) {
                q.offer(m2);
            }
            if (m3.x >= 0 && m3.y < m && m1.y >= 0 && m1.x < n && !visited.contains(m3)) {
                q.offer(m3);
            }
            if (m4.x >= 0 && m4.y < m && m1.y >= 0 && m1.x < n && !visited.contains(m4)) {
                q.offer(m4);
            }
            if (m5.x >= 0 && m5.y < m && m1.y >= 0 && m1.x < n && !visited.contains(m5)) {
                q.offer(m5);
            }
            if (m6.x >= 0 && m6.y < m && m1.y >= 0 && m1.x < n && !visited.contains(m6)) {
                q.offer(m6);
            }
            if (m7.x >= 0 && m7.y < m && m1.y >= 0 && m1.x < n && !visited.contains(m7)) {
                q.offer(m7);
            }
            if (m8.x >= 0 && m8.y < m && m1.y >= 0 && m1.x < n && !visited.contains(m8)) {
                q.offer(m8);
            }
            ++min;
        }

        return -1;
    }


    public int minStepToReachTarget(int[] KnightPos, int[] TargetPos, int N) {
        int n = N;
        int m = N;
        int sx = KnightPos[0];
        int sy = KnightPos[1];

        int dx = TargetPos[0];
        int dy = TargetPos[1];

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n + 1][n + 1];
        q.add(new int[]{sx, sy, 0});

        while (!q.isEmpty()) {
            int[] tmp = q.poll();

            if (tmp[0] == dx && tmp[1] == dy) {
                return tmp[2];
            }

            visited[tmp[0]][tmp[1]] = true;

            int[] m1 = {tmp[0] + 2, tmp[1] + 1, tmp[2] + 1};
            int[] m2 = {tmp[0] + 2, tmp[1] - 1, tmp[2] + 1};
            int[] m3 = {tmp[0] - 2, tmp[1] + 1, tmp[2] + 1};
            int[] m4 = {tmp[0] - 2, tmp[1] - 1, tmp[2] + 1};
            int[] m5 = {tmp[0] + 1, tmp[1] + 2, tmp[2] + 1};
            int[] m6 = {tmp[0] - 1, tmp[1] + 2, tmp[2] + 1};
            int[] m7 = {tmp[0] + 1, tmp[1] - 2, tmp[2] + 1};
            int[] m8 = {tmp[0] - 1, tmp[1] - 2, tmp[2] + 1};

            //check constraints x >= 0 && y < m && y >= 0  && x < n
            if (m1[0] >= 1 && m1[1] <= m && m1[1] >= 1 && m1[0] <= n && !visited[m1[0]][m1[1]]) {
                q.offer(m1);
            }
            if (m2[0] >= 1 && m2[1] <= m && m2[1] >= 1 && m2[0] <= n && !visited[m2[0]][m2[1]]) {
                q.offer(m2);
            }
            if (m3[0] >= 1 && m3[1] <= m && m3[1] >= 1 && m3[0] <= n && !visited[m3[0]][m3[1]]) {
                q.offer(m3);
            }
            if (m4[0] >= 1 && m4[1] <= m && m4[1] >= 1 && m4[0] <= n && !visited[m4[0]][m4[1]]) {
                q.offer(m4);
            }
            if (m5[0] >= 1 && m5[1] <= m && m5[1] >= 1 && m5[0] <= n && !visited[m5[0]][m5[1]]) {
                q.offer(m5);
            }
            if (m6[0] >= 1 && m6[1] <= m && m6[1] >= 1 && m6[0] <= n && !visited[m6[0]][m6[1]]) {
                q.offer(m6);
            }
            if (m7[0] >= 1 && m7[1] <= m && m7[1] >= 1 && m7[0] <= n && !visited[m7[0]][m7[1]]) {
                q.offer(m7);
            }
            if (m8[0] >= 1 && m8[1] <= m && m8[1] >= 1 && m8[0] <= n && !visited[m8[0]][m8[1]]) {
                q.offer(m8);
            }
        }

        return -1;
    }


    public static void main(String[] args) {

        int m = 8;
        int n = 8;

        int sx = 1;
        int sy = 7;

        int dx = 1;
        int dy = 7;

        int ans = new MinKnightMoves().minimumMoves(n, m, sx, sy, dx, dy);
        System.out.println(ans);
    }


}
