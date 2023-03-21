package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiagonalElements
{

    public ArrayList<Integer> solve(TreeNode root)
    {
        if (root == null)
            return null;
        Map<Integer, ArrayList<Integer>> diagonalMap = new HashMap<>();
        int currentDiagonal = 0;
        _solve(root, currentDiagonal, diagonalMap);
        ArrayList<Integer> toReturn = new ArrayList<>();
        for (ArrayList<Integer> element : diagonalMap.values()) {
            toReturn.addAll(element);
        }
        return toReturn;
    }

    private void _solve(TreeNode root, int currentDiagonal, Map<Integer, ArrayList<Integer>> diagonalMap)
    {
        if (root == null)
            return;
        ArrayList<Integer> diagonalElements = diagonalMap.getOrDefault(currentDiagonal, new ArrayList<>());
        diagonalElements.add(root.data);
        diagonalMap.put(currentDiagonal, diagonalElements);
        _solve(root.left, currentDiagonal + 1, diagonalMap);
        _solve(root.right, currentDiagonal, diagonalMap);
    }

}
