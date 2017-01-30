package com.interview;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphAlgo
{
	private int max = Integer.MIN_VALUE;

	public void longestConsecutiveHelper(int[][] table, boolean[] visited, int val, int index)
	{
		max = Math.max(max, val);

		for (int i = 0; i < table.length; i++)
		{
			if (i != index && !visited[i] && table[index][i] == 1)
			{
				visited[i] = true;
				longestConsecutiveHelper(table, visited, val + 1, i);
			}
		}

		return;
	}

	public void dfs(char[][] grid, int i, int j)
	{
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0')
			return;

		grid[i][j] = '0';

		dfs(grid, i + 1, j);
		dfs(grid, i - 1, j);
		dfs(grid, i, j + 1);
		dfs(grid, i, j - 1);
	}

	public void dfs(int[][] rooms, int i, int j, int d)
	{
		if (i < 0 || j < 0 || i >= rooms.length || j >= rooms[0].length || rooms[i][j] < d)
			return;

		rooms[i][j] = d;

		dfs(rooms, i + 1, j, d + 1);
		dfs(rooms, i - 1, j, d + 1);
		dfs(rooms, i, j + 1, d + 1);
		dfs(rooms, i, j - 1, d + 1);
	}

	public void countComponentsHelper(int n, HashMap<Integer, ArrayList<Integer>> map, HashSet<Integer> visited, int index)
	{
		for (int i : map.get(index))
		{
			if (!visited.contains(i))
			{
				visited.add(i);
				countComponentsHelper(n, map, visited, i);
			}
		}
	}

	public int countComponents(int n, int[][] edges)
	{
		int count = 0;

		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

		HashSet<Integer> visited = new HashSet<Integer>();

		for (int i = 0; i < n; i++)
			map.put(i, new ArrayList<Integer>());

		for (int[] edge : edges)
		{
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}

		for (int i = 0; i < n; i++)
		{
			if (!visited.contains(i))
			{
				visited.add(i);
				countComponentsHelper(n, map, visited, i);
				count++;
			}
		}

		return count;
	}

	public int numIslands(char[][] grid)
	{
		int count = 0;
		int n = grid.length;
		if (n == 0)
			return 0;
		int m = grid[0].length;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				if (grid[i][j] == '1')
				{
					dfs(grid, i, j);
					++count;
				}
			}
		}
		return count;
	}

	public int longestConsecutiveUsingGraph(int[] nums)
	{
		int N = nums.length;
		int[][] table = new int[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (i == j || nums[i] == nums[j] + 1 || nums[i] == nums[j] - 1)
					table[i][j] = 1;

		boolean[] visited = new boolean[N];

		for (int i = 0; i < N; i++)
		{
			if (!visited[i])
			{
				visited[i] = true;
				longestConsecutiveHelper(table, visited, 1, i);
			}
		}

		return max == Integer.MIN_VALUE ? 0 : max;
	}

	public int longestConsecutive(int[] nums)
	{
		int res = 0;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++)
		{
			int num = nums[i];

			if (!map.containsKey(num))
			{
				int left = map.containsKey(num - 1) ? map.get(num - 1) : 0;
				int right = map.containsKey(num + 1) ? map.get(num + 1) : 0;

				int sum = left + right + 1;

				res = Math.max(res, sum);

				map.put(num, sum);

				map.put(num - left, sum);

				map.put(num + right, sum);
			}
		}

		return res;
	}
}
