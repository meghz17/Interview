public class StringAlgo
{
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
}
