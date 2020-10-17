package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordSearchII
{

    class TrieNode
    {
        private HashMap<Character,TrieNode> prefixes;

        private boolean isWordEnd;

        private String word;

        public TrieNode()
        {
            prefixes = new HashMap<>();
            isWordEnd = false;
            word = null;
        }

        @Override
        public String toString()
        {
            return "TrieNode [prefixes=" + prefixes + ", isWordEnd=" + isWordEnd + ", word=" + word + "]";
        }
        
    }

    public List<String> findWords(char[][] board, String[] words)
    {

        /** construct trie for given words **/
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (Character c : word.toCharArray()) {
                if(!node.prefixes.containsKey(c)) {
                    node.prefixes.put(c, new TrieNode());
                }
                node = node.prefixes.get(c);
            }
            node.isWordEnd = true;
            node.word = word;
        }
        
        List<String> matching = new ArrayList<>();
        int rows = board.length;
        int cols = board[0].length;
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if(root.prefixes.size() == 1) {
                   if(root.prefixes.containsKey(board[row][col])) {
                       matching.add(root.prefixes.get(board[row][col]).word);
                       break;
                   }
                }
                if(root.prefixes.containsKey(board[row][col])) {
                    visit(board, row, col, root, matching);
                }
            }
        }
        return matching;
    }

    private void visit(char[][] board, int row, int col, TrieNode node, List<String> matching)
    {

        if (row < 0 || col < 0 || row == board.length || col >= board[0].length) {
            return;
        }
        if (node.isWordEnd) {
            matching.add(node.word);
            node.isWordEnd = false;
            return;
        }
        if (node.prefixes.containsKey(board[row][col])) {
            char temp = board[row][col];
            board[row][col] = '#';
            visit(board, row, col + 1, node.prefixes.get(temp), matching);
            visit(board, row, col - 1, node.prefixes.get(temp), matching);
            visit(board, row - 1, col, node.prefixes.get(temp), matching);
            visit(board, row + 1, col, node.prefixes.get(temp), matching);
            board[row][col] = temp;
        }
    }
    
    public static void main(String[] args)
    {
        /**
         * Input: 
            board = [
              ['o','a','a','n'],
              ['e','t','a','e'],
              ['i','h','k','r'],
              ['i','f','l','v']
            ]
            words = ["oath","pea","eat","rain"]
            
            Output: ["eat","oath"]
         * 
         * */
        
       // char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
       // String[] words = {"oath","pea","eat","rain"};
        char[][] board = {{'a'}};
        String[] words = {"a"};
        List<String> output = new WordSearchII().findWords(board, words);
        System.out.println(output);
    }
}
