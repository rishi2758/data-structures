package trees;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ValidateBinaryTreeNodes
{

    public boolean validateBinaryTreeNodes(int n , int[] leftChild , int[] rightChild) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(0);
        while(!queue.isEmpty()){
            int node = queue.poll();
            if(visited.contains(node)){
                return false;
            }
            visited.add(node);
            if(leftChild[node] != -1){
                queue.add(leftChild[node]);
            }
            if(rightChild[node] != -1){
                queue.add(rightChild[node]);
            }
        }
        
        return visited.size() == n;
    }
    
    public static void main(String[] args)
    {
        
    }
    
}
