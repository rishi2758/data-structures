package trie.core;

import java.util.HashMap;

public class TrieNode<T> {
	private HashMap<T, TrieNode<T>> prefixes;

	private boolean isWordEnd;

	private T word;

	public TrieNode() {
		prefixes = new HashMap<>();
		isWordEnd = false;
		word = null;
	}

	@Override
	public String toString() {
		return "TrieNode [prefixes=" + prefixes + ", isWordEnd=" + isWordEnd + ", word=" + word + "]";
	}

	public HashMap<T, TrieNode<T>> getPrefixes() {
		return prefixes;
	}

	public void setPrefixes(HashMap<T, TrieNode<T>> prefixes) {
		this.prefixes = prefixes;
	}

	public boolean isWordEnd() {
		return isWordEnd;
	}

	public void setWordEnd(boolean isWordEnd) {
		this.isWordEnd = isWordEnd;
	}

	public T getWord() {
		return word;
	}

	public void setWord(T word) {
		this.word = word;
	}

}