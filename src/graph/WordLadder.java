package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder
{
    
    class GraphNode {
        int level;
        String word;
        
        public GraphNode(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList)
    {

        Set<String> words = new HashSet<>();
        wordList.forEach(word -> words.add(word));

        if (!words.contains(endWord))
            return 0;

        Map<String, List<String>> closestWords = new HashMap<>();
        wordList.forEach(word -> {
            StringBuilder sb = new StringBuilder(word);
            for (int i = 0; i < word.length(); i++) {
                char temp = sb.charAt(i);
                sb.setCharAt(i, '*');
                /**/
                String transformed = sb.toString();
                List<String> mappedWords = new ArrayList<>();
                if (closestWords.containsKey(transformed)) {
                    mappedWords = closestWords.get(transformed);
                }
                mappedWords.add(word);
                closestWords.put(transformed, mappedWords);
                /**/
                sb.setCharAt(i, temp);
            }
        });

        Queue<GraphNode> enque = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        enque.add(new GraphNode(beginWord, 1));
        visited.add(beginWord);
        while (!enque.isEmpty()) {
            GraphNode node = enque.poll();
            int level = node.level;
            StringBuilder sb = new StringBuilder(node.word);
            for (int i = 0; i < node.word.length(); i++) {
                char temp = sb.charAt(i);
                sb.setCharAt(i, '*');
                String transformed = sb.toString();
                if (closestWords.containsKey(transformed)) {
                    List<String> mappedWords = closestWords.get(transformed);
                    for (String string : mappedWords) {
                        if (string.equals(endWord))
                            return level + 1;
                        if (!visited.contains(string)) {
                            visited.add(string);
                            enque.add(new GraphNode(string, level + 1));
                        }
                    }
                }
                sb.setCharAt(i, temp);
            }
        }
        return 0;

    }

    public static void main(String[] args)
    {

        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        System.out.println("ladder length : " + new WordLadder().ladderLength("hit", "cog", Arrays.asList(wordList)));
    }

}
