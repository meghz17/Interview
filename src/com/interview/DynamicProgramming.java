package com.interview;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicProgramming
{
	public int longestZigZagSequence(int[] arr)
	{
		int len = arr.length;

		if (len <= 1)
			return 1;

		int[] table = new int[len];
		table[0] = 1;
		for (int i = 1; i < len; i++)
		{
			for (int j = 0; j < i; j++)
			{
				if (j == 0)
				{
					if (arr[i] < arr[j])
						table[i] = -(Math.abs(table[j]) + 1);
					else
						table[i] = Math.abs(table[j]) + 1;
				} else
				{
					if (table[j] > 0 && arr[i] < arr[j])
						table[i] = -Math.max(Math.abs(table[i]), Math.abs(table[j]) + 1);
					else if (table[j] < 0 && arr[i] > arr[j])
						table[i] = Math.max(Math.abs(table[i]), Math.abs(table[j]) + 1);

				}
			}
		}

		return table[len - 1];
	}

	public int coinChangeWithRepetitions(int[] denom, int sum)
	{
		if (sum == 0 || denom.length == 0)
			return 0;

		int[][] table = new int[denom.length + 1][sum + 1];

		table[0][0] = 1;

		for (int i = 1; i < denom.length + 1; i++)
		{
			for (int j = 0; j < sum + 1; j++)
			{
				table[i][j] = table[i - 1][j] + ((j - denom[i - 1] >= 0) ? table[i][j - denom[i - 1]] : 0);
			}
		}

		return table[denom.length][sum];
	}

	public int minimumCoinChanging(int[] arr, int sum)
	{
		int[][] table = new int[arr.length][sum + 1];
		int inc, excl;
		for (int i = 0; i < arr.length; i++)
		{
			table[i][0] = 0;
		}

		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 1; j < sum + 1; j++)
			{
				if (j == arr[i])
				{
					table[i][j] = 1;
				} else
				{
					if (j < arr[i])
					{
						table[i][j] = table[i - 1][j];
					} else
					{
						if (i == 0)
							table[i][j] = 0;
						else
						{
							excl = (table[i - 1][j] == 0) ? 0 : table[i - 1][j];
							inc = (table[i - 1][j - arr[i]] == 0) ? 0 : table[i - 1][j - arr[i]] + 1;

							table[i][j] = (excl == 0) ? inc : Math.min(inc, excl);
						}
					}
				}
			}
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++)
		{
			if (table[i][sum] != 0)
				min = Math.min(min, table[i][sum]);
		}

		return min == Integer.MAX_VALUE ? 0 : min;
	}

	public int numSquares(int n)
	{
		List<Integer> list = new ArrayList<Integer>();

		if (n <= 1)
			return 1;

		for (int i = 1; i * i <= n; i++)
		{
			list.add(i * i);
		}

		int[][] table = new int[list.size()][n + 1];

		for (int j = 0; j < n + 1; j++)
			table[0][j] = j;

		for (int i = 1; i < list.size(); i++)
		{
			for (int j = 0; j < n + 1; j++)
			{
				if (j == 0)
					table[i][j] = 0;
				else
				{
					if (j - list.get(i) >= 0)
					{
						int value = 1 + table[i][j - list.get(i)];
						table[i][j] = Math.min(value, table[i - 1][j]);
					} else
					{
						table[i][j] = table[i - 1][j];
					}

				}
			}
		}

		return table[list.size() - 1][n];
	}

	public int combinationSum4(int[] nums, int target)
	{
		int[] table = new int[target + 1];
		table[0] = 1;

		Arrays.sort(nums);

		for (int i = 1; i <= target; i++)
		{
			for (int j = 0; j < nums.length; j++)
			{
				if (i - nums[j] < 0)
					break;

				table[i] += table[i - nums[j]];
			}
		}

		return table[target];
	}

	public int lengthOfLIS(int[] nums)
	{
		if (nums.length <= 1)
			return nums.length;

		int[] table = new int[nums.length];

		for (int i = 0; i < table.length; i++)
			table[i] = 1;

		int j = 0;
		int len = Integer.MIN_VALUE;
		for (int i = 1; i < nums.length; i++)
		{
			j = 0;
			while (j < i)
			{
				if (nums[i] > nums[j])
				{
					table[i] = Math.max(table[i], table[j] + 1);
				}
				j++;
			}
			len = Math.max(len, table[i]);
		}

		return len == Integer.MIN_VALUE ? 0 : len;
	}

	public int bestStockProfitII(int[] arr)
	{
		int[][] table = new int[arr.length - 1][arr.length];
		int val = 0;
		// Fill the first row
		for (int i = 1; i < arr.length; i++)
		{
			val = arr[i] - arr[0];

			if (val >= 0)
				table[0][i] = Math.max(table[0][i - 1], val);
			else
				table[0][i] = table[0][i - 1];
		}

		for (int i = 1; i < table.length; i++)
		{
			for (int j = i; j < table[0].length; j++)
			{
				val = arr[j] - arr[i];
				if (val == 0)
				{
					table[i][j] = table[i - 1][j];
				} else
				{
					if (val > 0)
						table[i][j] = Math.max(table[i - 1][j], val + table[i][j - 1]);
					else
						table[i][j] = table[i - 1][j];
				}
			}
		}

		return table[arr.length - 2][arr.length - 1];
	}
}
