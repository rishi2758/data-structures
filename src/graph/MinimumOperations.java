package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class GNode {
    int data;
    int steps;
    public GNode (int data,int steps) {
        this.data = data;
        this.steps = steps;
    }
}

public class MinimumOperations
{
    
    public int minimumOperations(int target)
    {
        Set<GNode> visited = new HashSet<>();
        Queue<GNode> queue = new LinkedList<>();
        GNode start = new GNode(0,0);
        queue.add(start);
        while(!queue.isEmpty()) {
            GNode gnode = queue.poll();
            if(gnode.data == target) {
                return gnode.steps;
            }
            visited.add(start);
            if(gnode.data > 0) {
                queue.add(new GNode(gnode.data*2, gnode.steps+1));
            }
            queue.add(new GNode(gnode.data+1,gnode.steps+1));
        }
        return -1;
    }

    public static void main(String[] args)
    {
       int res = new MinimumOperations().minimumOperations(10);
       System.out.println("operations :"+res);
    }

}
