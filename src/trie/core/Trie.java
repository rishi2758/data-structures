package trie.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Trie {

	private TrieNode<Character> root;

	public Trie() {
		this.root = new TrieNode<Character>();
	}

	public void insert(String word) {
		if (word == null || word.length() == 0) {
			return;
		}
		// aab // abc // aac
		TrieNode<Character> traverse = root;
		for (Character currChar : word.toCharArray()) {
			if (traverse.getPrefixes().containsKey(currChar)) {
				traverse = traverse.getPrefixes().get(currChar);
			} else {
				TrieNode<Character> node = new TrieNode<Character>();
				node.setWord(currChar);
				traverse.getPrefixes().put(currChar, node);
				traverse = node;
			}
		}
		traverse.setWordEnd(true);
	}

	public List<String> searchForPrefix(String prefix) {
		List<String> match = new ArrayList<>();
		if (!this.root.getPrefixes().isEmpty()) {
			TrieNode<Character> traverse = this.root;
			StringBuilder sb = new StringBuilder();
			for (Character currChar : prefix.toCharArray()) {
				if (traverse.getPrefixes().containsKey(currChar)) {
					sb.append(currChar);
					traverse = traverse.getPrefixes().get(currChar);
				} else {
					return Collections.emptyList();
				}
			}
			collectEndWords(traverse, sb, match);
		}
		return match;
	}

	private void collectEndWords(TrieNode<Character> traverse, StringBuilder sb, List<String> matching) {
		if (traverse.isWordEnd()) {
			matching.add(sb.toString());
		}
		for (Map.Entry<Character, TrieNode<Character>> entry : traverse.getPrefixes().entrySet()) {
			sb.append(entry.getKey());
			collectEndWords(entry.getValue(), sb, matching);
			sb.deleteCharAt(sb.length()-1);
		}
	}

	public boolean search(String word) {
		return false;
	}

	public static void main(String[] args) throws JsonProcessingException {
		Trie newTrie = new Trie();
		newTrie.insert("this is automation");
		newTrie.insert("this is");
		newTrie.insert("this");
		newTrie.insert("thi");
		newTrie.insert("th");
		System.out.println(new ObjectMapper().writeValueAsString(newTrie.root));
		System.out.println(new ObjectMapper().writeValueAsString(newTrie.searchForPrefix("th")));
		System.out.println(new ObjectMapper().writeValueAsString(newTrie.searchForPrefix("thi")));
		System.out.println(new ObjectMapper().writeValueAsString(newTrie.searchForPrefix("this")));
		System.out.println(new ObjectMapper().writeValueAsString(newTrie.searchForPrefix("this is")));

	}
}
