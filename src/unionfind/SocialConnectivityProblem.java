package unionfind;

import java.io.*;

/*
*
0 1 2015-08-14T18:00:00
1 9 2015-08-14T18:01:00
0 2 2015-08-14T18:02:00
0 3 2015-08-14T18:04:00
0 4 2015-08-14T18:06:00
0 5 2015-08-14T18:08:00
0 6 2015-08-14T18:10:00
0 7 2015-08-14T18:12:00
0 8 2015-08-14T18:14:00
1 2 2015-08-14T18:16:00
1 3 2015-08-14T18:18:00
1 4 2015-08-14T18:20:00
1 5 2015-08-14T18:22:00
2 1 2015-08-14T18:24:00
2 3 2015-08-14T18:26:00
2 4 2015-08-14T18:28:00
5 5 2015-08-14T18:30:00
*
* */
public class SocialConnectivityProblem {

    public String getEarliestTimeStamp() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/rissingh/Github/personal/data-structures/src/unionfind/socialConnectivity.log")));
        int n = Integer.parseInt(br.readLine());
        WeightedQuickUnion qu = new WeightedQuickUnion(n);
        while (n > 0) {
            String[] input = br.readLine().split(" ");
            int p = Integer.parseInt(input[0]);
            int q = Integer.parseInt(input[1]);
            String timestamp = input[2];
            qu.union(p, q);
            if (qu.getCount() == 1) {
                return timestamp;
            }
            --n;
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Earliest Time ->" + new SocialConnectivityProblem().getEarliestTimeStamp());
    }

}
