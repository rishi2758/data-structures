package trie;

import java.util.ArrayList;
import java.util.List;

import trie.core.TrieNode;

public class WordSearchII {
	public List<String> findWords(char[][] board, String[] words) {

		/* construct trie for given words **/
		TrieNode<String> root = new TrieNode<>();
		for (String word : words) {
			TrieNode<String> node = root;
			for (Character c : word.toCharArray()) {
				if (!node.getPrefixes().containsKey(String.valueOf(c))) {
					node.getPrefixes().put(c.toString(), new TrieNode<>());
				}
				node = node.getPrefixes().get(String.valueOf(c));
			}
			node.setWordEnd(true);
			node.setWord(word);
		}

		List<String> matching = new ArrayList<>();
		int rows = board.length;
		int cols = board[0].length;
		for (int row = 0; row < rows; ++row) {
			for (int col = 0; col < cols; ++col) {
				if (root.getPrefixes().size() == 1) {
					if (root.getPrefixes().containsKey(String.valueOf(board[row][col]))) {
						matching.add(root.getPrefixes().get(String.valueOf(board[row][col])).getWord());
						break;
					}
				}
				if (root.getPrefixes().containsKey(String.valueOf(board[row][col]))) {
					visit(board, row, col, root, matching);
				}
			}
		}
		return matching;
	}

	private void visit(char[][] board, int row, int col, TrieNode<String> node, List<String> matching) {

		if (row < 0 || col < 0 || row == board.length || col >= board[0].length) {
			return;
		}
		if (node.isWordEnd()) {
			matching.add(node.getWord());
			node.setWordEnd(false);
			return;
		}
		if (node.getPrefixes().containsKey(String.valueOf(board[row][col]))) {
			char restore = board[row][col];
			String temp = String.valueOf(board[row][col]);
			board[row][col] = '#';
			visit(board, row, col + 1, node.getPrefixes().get(temp), matching);
			visit(board, row, col - 1, node.getPrefixes().get(temp), matching);
			visit(board, row - 1, col, node.getPrefixes().get(temp), matching);
			visit(board, row + 1, col, node.getPrefixes().get(temp), matching);
			board[row][col] = restore;
		}
	}

	public static void main(String[] args) {
		/*
		  Input: board = [ ['o','a','a','n'], ['e','t','a','e'], ['i','h','k','r'],
		  ['i','f','l','v'] ] words = ["oath","pea","eat","rain"]
		  <p>
		  Output: ["eat","oath"]

		 */

		char[][] board = { { 'a' } };
		String[] words = { "a" };
		List<String> output = new WordSearchII().findWords(board, words);
		System.out.println(output);
	}
}
