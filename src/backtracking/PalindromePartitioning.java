package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PalindromePartitioning
{

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        LinkedList<String> currPart = new LinkedList<>();
        Integer[][] memo = new Integer[s.length()][s.length()];
        _partition(s, 0, s.length(), res ,currPart);
        return res;
    }
    
    private void _partition(String s, int i, int j,List<List<String>> res, LinkedList<String> currPart)
    {
        if(i >= j) {
            res.add(new LinkedList<>(currPart));
            return;
        }

        for(int k = i ; k < j ; k++) {
            String temp = s.substring(i,k+1);
            String rev = new StringBuilder(temp).reverse().toString();
            if(temp.equals(rev)) {
                currPart.addLast(temp);
                _partition(s, k+1, j, res, currPart);
                currPart.removeLast();
            }
        }
        
    }
    
    
    public static void main(String[] args)
    {
        String input = "aabb";
        new PalindromePartitioning().partition(input).stream().forEach(System.out::print);
    }
    
}
