import java.util.ArrayList;
import java.util.List;

class TrieNode
{
	TrieNode[] arr;
	String str;

	public TrieNode()
	{
		arr = new TrieNode[26];
	}
}

class Trie
{
	public TrieNode root;

	public Trie()
	{
		root = new TrieNode();
	}

	public void insert(String word)
	{
		TrieNode ptr = root;

		for (int i = 0; i < word.length(); i++)
		{
			char ch = word.charAt(i);
			int index = ch - 'a';

			if (ptr.arr[index] == null)
			{
				TrieNode t = new TrieNode();
				ptr.arr[index] = t;
				ptr = t;
			} else
			{
				ptr = ptr.arr[index];
			}
		}

		ptr.str = word;

		return;
	}

	public void search(char[][] board, List<String> result, int i, int j, TrieNode root)
	{
		char c = board[i][j];
		if (c == '#' || root.arr[c - 'a'] == null)
			return;

		root = root.arr[c - 'a'];

		board[i][j] = '#';
		if (root.str != null)
		{
			result.add(root.str);
			root.str = null;
		}

		if (i + 1 < board.length)
		{
			search(board, result, i + 1, j, root);
		}

		if (j + 1 < board[0].length)
		{
			search(board, result, i, j + 1, root);
		}

		if (i - 1 >= 0)
		{
			search(board, result, i - 1, j, root);
		}

		if (j - 1 >= 0)
		{
			search(board, result, i, j - 1, root);
		}

		board[i][j] = c;

		return;
	}

	// Given a 2D board and a list of words from the dictionary, find all words
	// in the board.
	public List<String> findWords(char[][] board, String[] words)
	{
		Trie lib = new Trie();

		for (String word : words)
		{
			lib.insert(word);
		}
		List<String> result = new ArrayList<String>();

		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				lib.search(board, result, i, j, lib.root);
			}
		}

		return result;
	}
}
