package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

@SuppressWarnings("ALL")
public class MinimumGeneticMutation
{
    @SuppressWarnings("InnerClassMayBeStatic")
    static class GraphNode {
        final int level;
        final String word;
        
        public GraphNode(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }

    public int minMutation(String start, String end, String[] bank)
    {
        
        Map<String, List<String>> closestMutations = new HashMap<>();
        for (String gene : bank) {
            StringBuilder sb = new StringBuilder(gene);
            for (int i = 0; i < gene.length(); i++) {
                char temp = sb.charAt(i);
                sb.setCharAt(i, '*');
                /**/
                String transformed = sb.toString();
                List<String> mappedWords = new ArrayList<>();
                if (closestMutations.containsKey(transformed)) {
                    mappedWords = closestMutations.get(transformed);
                }
                mappedWords.add(gene);
                closestMutations.put(transformed, mappedWords);
                /**/
                sb.setCharAt(i, temp);
            }
        }
        
        Set<String> visited = new HashSet<>();
        Queue<GraphNode> enque = new LinkedList<>();
        enque.add(new GraphNode(start, 0));
        visited.add(start);
        while(!enque.isEmpty()) {
            GraphNode node = enque.poll();
            int level = node.level;
            StringBuilder sb = new StringBuilder(node.word);
            for (int i = 0; i < node.word.length(); i++) {
                char temp = sb.charAt(i);
                sb.setCharAt(i, '*');
                String transformed = sb.toString();
                if (closestMutations.containsKey(transformed)) {
                    List<String> mappedWords = closestMutations.get(transformed);
                    for (String string : mappedWords) {
                        if (string.equals(end))
                            return level +1;
                        if (!visited.contains(string)) {
                            visited.add(string);
                            enque.add(new GraphNode(string, level + 1));
                        }
                    }
                }
                sb.setCharAt(i, temp);
            }
        }
        return -1;
    }

    public static void main(String[] args)
    {
        /* "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]*/
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        System.out.println("min mutation :"+new MinimumGeneticMutation().minMutation("AACCGGTT", "AAACGGTA", bank));

    }

}
