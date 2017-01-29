import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class StringAlgo
{
	// Given a string S and a string T, find the minimum window in S which will
	// contain all the characters in T in complexity O(n).
	public String minWindow(String s, String t)
	{
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (char ch : s.toCharArray())
			map.put(ch, 0);

		for (char ch : t.toCharArray())
		{
			if (map.containsKey(ch))
				map.put(ch, map.get(ch) + 1);
			else
				return "";
		}

		int start = 0, end = 0, head = 0, len = Integer.MAX_VALUE, counter = t.length();

		while (end < s.length())
		{
			char ch = s.charAt(end);

			if (map.get(ch) > 0)
			{
				counter--;
			}

			map.put(ch, map.get(ch) - 1);
			end++;

			while (counter == 0)
			{
				if (end - start < len)
				{
					len = end - start;
					head = start;
				}

				char temp = s.charAt(start);

				map.put(temp, map.get(temp) + 1);

				if (map.get(temp) > 0)
					counter++;

				start++;
			}
		}

		return len == Integer.MAX_VALUE ? "" : s.substring(head, head + len);
	}

	public StringBuilder reverse(StringBuilder str, int i, int j)
	{
		char temp = ' ';
		while (i < j)
		{
			temp = str.charAt(i);
			str.setCharAt(i, str.charAt(j));
			str.setCharAt(j, temp);

			i++;
			j--;
		}

		return str;
	}

	public String reverseWords(String s)
	{
		s.trim();

		if (s.length() == 0)
			return s;

		StringBuilder str = new StringBuilder(s);

		int start = 0;
		int end = 0;

		while (end < str.length())
		{
			if (str.charAt(end) == ' ')
			{
				if (start != end)
				{
					str = reverse(str, start, end - 1);
					start = end + 1;
					end++;
				} else
				{
					str.deleteCharAt(end);
				}

			} else
				end++;
		}

		str = reverse(str, start, end - 1);
		str = reverse(str, 0, str.length() - 1);

		return new String(str);
	}

	public int removeBinary(String a)
	{
		StringBuilder str = new StringBuilder(a);
		int i = 0;
		int count = 0;
		while (i < str.length() - 2)
		{
			if (str.charAt(i) == '1' && str.charAt(i + 1) == '0' && str.charAt(i + 2) == '0')
			{
				str.replace(i, i + 3, "");
				count += 3;
				i--;
				continue;
			}

			i++;
		}

		return count;
	}

	public int myAtoi(String str)
	{
		str = str.trim();

		if (str.length() == 0)
			return 0;

		long result = 0;

		boolean flag = false;
		int i = 0;
		if (str.charAt(i) == '-' || str.charAt(i) == '+')
		{
			if (str.charAt(i) == '-')
				flag = true;
			i++;
		}

		while (i < str.length() && str.charAt(i) == 0)
			i++;

		while (i < str.length())
		{
			int index = str.charAt(i) - '0';

			if (index < 0 || index > 9)
				break;

			result = result * 10 + index;

			if (result > Integer.MAX_VALUE)
			{
				result = flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
				break;
			}

			i++;
		}

		return flag ? (int) -result : (int) result;
	}

	public HashMap<String, HashSet<String>> map;

	public boolean isUnique(String str)
	{
		if (str.length() <= 2)
			return true;

		String abbr = str.charAt(0) + Integer.toString(str.length() - 2) + str.charAt(str.length() - 1);

		if (!map.containsKey(abbr))
			return true;
		else
		{
			HashSet<String> set = map.get(abbr);
			if (set.contains(str) && set.size() == 1)
				return true;
		}

		return false;
	}

	public List<List<String>> groupStrings(String[] strings)
	{
		List<List<String>> result = new ArrayList<List<String>>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strings)
		{
			int offset = str.charAt(0) - 'a';
			String key = "";
			for (int i = 0; i < str.length(); i++)
			{
				char c = (char) (str.charAt(i) - offset);
				if (c < 'a')
				{
					c += 26;
				}
				key += c;
			}
			if (!map.containsKey(key))
			{
				List<String> list = new ArrayList<String>();
				map.put(key, list);
			}
			map.get(key).add(str);
		}
		for (String key : map.keySet())
		{
			List<String> list = map.get(key);
			Collections.sort(list);
			result.add(list);
		}
		return result;
	}

	public String reverseWords2(String s)
	{
		s = s.trim();

		if (s.length() == 0)
			return s;

		StringBuilder str = new StringBuilder(s);

		int start = 0;
		int end = 0;

		while (end < str.length())
		{
			if (str.charAt(end) == ' ')
			{
				if (start != end)
				{
					str = reverse(str, start, end - 1);
					start = end + 1;
					end++;
				} else
				{
					str.deleteCharAt(end);
				}

			} else
				end++;
		}

		str = reverse(str, start, end - 1);
		str = reverse(str, 0, str.length() - 1);

		return new String(str);
	}

	public List<List<String>> groupAnagrams(String[] strs)
	{
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		List<List<String>> result = new ArrayList<List<String>>();

		for (int i = 0; i < strs.length; i++)
		{
			char[] ch = strs[i].toCharArray();
			Arrays.sort(ch);
			String str = new String(Arrays.toString(ch));

			if (map.containsKey(str))
			{
				result.get(map.get(str)).add(strs[i]);
			} else
			{
				List<String> temp = new ArrayList<String>();
				temp.add(strs[i]);
				result.add(new ArrayList<String>(temp));
				map.put(str, result.size() - 1);
			}
		}

		return result;

	}

	private int low, maxLen;

	public void extendPalindrome(String s, int i, int j)
	{
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j))
		{
			i--;
			j++;
		}

		if (maxLen < j - i - 1)
		{
			low = i + 1;
			maxLen = j - i - 1;
		}
	}

	public String longestPalindrome(String s)
	{
		int len = s.length();
		if (len < 2)
			return s;

		for (int i = 0; i < len - 1; i++)
		{
			extendPalindrome(s, i, i);
			extendPalindrome(s, i, i + 1);
		}

		return s.substring(low, low + maxLen);
	}
}
