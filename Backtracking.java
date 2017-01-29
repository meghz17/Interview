import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Backtracking
{
	public static final int N = 8;

	public boolean knightsTour(int sol[][], int xMove[], int yMove[], int x, int y, int movei)
	{
		int k, next_x, next_y;

		if (movei == N * N)
			return true;

		System.out.println(movei);

		for (k = 0; k < 8; k++)
		{
			next_x = x + xMove[k];
			next_y = y + yMove[k];
			if (isKnightSafe(sol, next_x, next_y))
			{
				sol[next_x][next_y] = movei;
				if (knightsTour(sol, xMove, yMove, next_x, next_y, movei + 1))
					return true;
				else
					sol[next_x][next_y] = -1;// backtracking
			}
		}

		return false;
	}

	private boolean isKnightSafe(int[][] sol, int x, int y)
	{
		return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
	}

	public ArrayList<String> letterCombinations(int[] arr)
	{
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "");
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");

		ArrayList<String> result = new ArrayList<String>();

		StringBuilder str = new StringBuilder();

		return letterHelper(result, arr, map, 0, str);
	}

	public ArrayList<String> letterHelper(ArrayList<String> result, int[] arr, HashMap<Integer, String> map, int index, StringBuilder str)
	{
		if (index >= arr.length)
		{
			result.add(new String(str));
			return result;
		}

		if (arr[index] == 1)
		{
			result = letterHelper(result, arr, map, index + 1, str);
		} else
		{
			String s = map.get(arr[index]);
			for (int i = 0; i < s.length(); i++)
			{
				str.append(s.charAt(i));
				result = letterHelper(result, arr, map, index + 1, str);
				str.deleteCharAt(str.length() - 1);
			}
		}

		return result;
	}

	public ArrayList<ArrayList<Integer>> combinations(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp, int index, int[] arr, int k)
	{
		if (temp.size() == k)
		{
			result.add(new ArrayList<Integer>(temp));
			return result;
		}

		int prev = -1;
		for (int i = index; i < arr.length; i++)
		{
			index++;
			if (prev != arr[i])
			{
				temp.add(arr[i]);
				result = combinations(result, temp, index, arr, k);
				temp.remove(temp.size() - 1);

			}
			prev = arr[i];
		}

		return result;
	}

	public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp, int index, int[] arr, int sum, int target)
	{
		if (sum > target)
			return result;

		if (sum == target)
		{
			result.add(new ArrayList<Integer>(temp));
			return result;
		}

		int prev = -1;
		for (int i = index; i < arr.length; i++)
		{
			if (prev != arr[i])
			{
				temp.add(arr[i]);
				sum += arr[i];
				index++;
				result = combinationSum(result, temp, index, arr, sum, target);
				sum -= arr[i];
				temp.remove(temp.size() - 1);
			}
			prev = arr[i];
		}

		return result;
	}

	public ArrayList<ArrayList<Integer>> combinationSumIII(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp, int index, int[] arr, int sum, int target, int k)
	{
		if (sum > target)
			return result;

		if (temp.size() == k)
		{
			if (sum == target)
			{
				result.add(new ArrayList<Integer>(temp));
				return result;
			} else
				return result;
		}

		for (int i = index; i < arr.length; i++)
		{
			temp.add(arr[i]);
			sum += arr[i];
			index++;
			result = combinationSumIII(result, temp, index, arr, sum, target, k);
			sum -= arr[i];
			temp.remove(temp.size() - 1);
		}

		return result;
	}

	public boolean isQueenSafe(int[][] sol, int row, int col)
	{
		for (int i = 0; i < row; i++)
		{
			if (sol[i][col] == 1)
				return false;
		}

		int i = row - 1, j = col - 1;

		while (i >= 0 && j >= 0)
		{
			if (sol[i][j] == 1)
				return false;
			i--;
			j--;
		}

		i = row - 1;
		j = col + 1;

		while (i >= 0 && j < sol[0].length)
		{
			if (sol[i][j] == 1)
				return false;
			i--;
			j++;
		}

		return true;
	}

	public boolean isAdditiveHelper(String num, ArrayList<String> list)
	{
		if (list.size() == 3)
		{
			long num1 = Long.parseLong(list.get(0));
			long num2 = Long.parseLong(list.get(1));

			if (list.get(2).equals(Long.toString(num1 + num2)) && (list.get(0).length() == Long.toString(num1).length() && list.get(1).length() == Long.toString(num2).length()))
			{
				System.out.println(list.get(0) + " == " + list.get(1) + " == " + Long.toString(num1 + num2));

				if (num.length() == 0)
					return true;

				list.set(0, list.get(1));
				list.set(1, Long.toString(num1 + num2));
				list.remove(list.size() - 1);
				return isAdditiveHelper(num, list);
			}

			return false;
		}

		for (int i = 0; i < num.length(); i++)
		{
			if (list.size() >= 2)
			{
				int maxLength = Math.max(list.get(0).length(), list.get(1).length());

				if (i + 1 > maxLength + 1)
					return false;
			}

			list.add(num.substring(0, i + 1));

			if (isAdditiveHelper(num.substring(i + 1), list))
				return true;

			if (list.size() > 0)
				list.remove(list.size() - 1);
		}

		return false;
	}

	public void permuteUniqueHelper(int[] nums, List<List<Integer>> result, List<Integer> list, boolean[] visited)
	{
		if (list.size() == nums.length)
		{
			result.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < nums.length; i++)
		{
			if (visited[i])
				continue;

			if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1])
				continue;

			visited[i] = true;
			list.add(nums[i]);
			permuteUniqueHelper(nums, result, list, visited);
			list.remove(list.size() - 1);
			visited[i] = false;
		}
	}

	public void combinationSum2Helper(List<List<Integer>> result, List<Integer> temp, int sum, int target, int[] candidates, int index)
	{
		if (sum > target)
			return;

		if (sum == target)
		{
			result.add(new ArrayList<Integer>(temp));
			return;
		}

		int prev = -1;
		for (int i = index; i < candidates.length; i++)
		{
			if (prev != candidates[i])
			{
				sum += candidates[i];
				temp.add(candidates[i]);
				combinationSum2Helper(result, temp, sum, target, candidates, i + 1);
				sum -= candidates[i];
				temp.remove(temp.size() - 1);
			}

			prev = candidates[i];

		}

		return;
	}

	public void combinationSumHelper(List<List<Integer>> result, List<Integer> temp, int[] candidates, int target, int sum, int index)
	{
		if (sum > target)
			return;

		if (sum == target)
		{
			result.add(new ArrayList<Integer>(temp));
			return;
		}

		int prev = -1;
		for (int i = index; i < candidates.length; i++)
		{
			if (prev != candidates[i])
			{
				temp.add(candidates[i]);
				combinationSumHelper(result, temp, candidates, target, sum + candidates[i], i);
				temp.remove(temp.size() - 1);
			}
			prev = candidates[i];
		}
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		List<Integer> temp = new ArrayList<Integer>();

		combinationSumHelper(result, temp, candidates, target, 0, 0);

		return result;
	}

	public void combineHelper(List<List<Integer>> result, List<Integer> temp, int k, int n, int index)
	{
		if (temp.size() == k)
		{
			result.add(new ArrayList<Integer>(temp));
			return;
		}

		for (int i = index; i <= n; i++)
		{
			temp.add(i);
			combineHelper(result, temp, k, n, i + 1);
			temp.remove(temp.size() - 1);
		}

		return;
	}

	public List<List<Integer>> findSubsequences(int[] nums)
	{
		List<List<Integer>> res = new ArrayList<>();
		helper(res, new ArrayList<Integer>(), nums, 0);
		return res;
	}

	private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int id)
	{
		if (list.size() > 1)
			res.add(new ArrayList<Integer>(list));

		List<Integer> unique = new ArrayList<Integer>();

		for (int i = id; i < nums.length; i++)
		{
			if (id > 0 && nums[i] < nums[id - 1])
				continue; // skip non-increase

			if (unique.contains(nums[i]))
				continue; // skip duplicate

			unique.add(nums[i]);
			list.add(nums[i]);
			helper(res, list, nums, i + 1);
			list.remove(list.size() - 1);
		}
	}

	public List<List<Integer>> getFactors(int n)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		getFactorsHelper(result, new ArrayList<Integer>(), n, 2);
		return result;
	}

	public void getFactorsHelper(List<List<Integer>> result, List<Integer> item, int n, int start)
	{
		if (n <= 1)
		{
			if (item.size() > 1)
			{
				result.add(new ArrayList<Integer>(item));
			}
			return;
		}

		for (int i = start; i <= n; ++i)
		{
			if (n % i == 0)
			{
				item.add(i);
				getFactorsHelper(result, item, n / i, i);
				item.remove(item.size() - 1);
			}
		}
	}

	public void combinationSum3Helper(List<List<Integer>> result, List<Integer> temp, int sum, int k, int n, int index)
	{
		if (sum > n || temp.size() > k)
			return;

		if (sum == n && temp.size() == k)
		{
			result.add(new ArrayList<Integer>(temp));
			return;
		}

		for (int i = index; i <= 9; i++)
		{
			sum += i;
			temp.add(i);
			combinationSum3Helper(result, temp, sum, k, n, i + 1);
			sum -= i;
			temp.remove(temp.size() - 1);
		}

		return;
	}

	public List<List<Integer>> combinationSum3(int k, int n)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		List<Integer> temp = new ArrayList<Integer>();

		combinationSum3Helper(result, temp, 0, k, n, 1);

		return result;
	}

	public List<List<Integer>> combine(int n, int k)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		List<Integer> temp = new ArrayList<Integer>();

		combineHelper(result, temp, k, n, 1);

		return result;
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		List<Integer> temp = new ArrayList<Integer>();

		Arrays.sort(candidates);

		combinationSum2Helper(result, temp, 0, target, candidates, 0);

		return result;
	}

	public List<List<Integer>> permuteUnique(int[] nums)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0)
			return result;
		Arrays.sort(nums);
		boolean[] visited = new boolean[nums.length];
		List<Integer> list = new ArrayList<Integer>();
		permuteUniqueHelper(nums, result, list, visited);

		return result;
	}

	public boolean isAdditiveNumber(String num)
	{
		ArrayList<String> list = new ArrayList<String>();

		return isAdditiveHelper(num, list);
	}

	public boolean solveNQueensHelper(List<List<String>> result, int[][] sol, int row)
	{
		if (row >= sol.length)
			return true;

		for (int i = 0; i < sol[0].length; i++)
		{
			if (isQueenSafe(sol, row, i))
			{
				sol[row][i] = 1;

				if (solveNQueensHelper(result, sol, row + 1))
				{
					List<String> temp = new ArrayList<String>();
					for (int j = 0; j < sol.length; j++)
					{
						StringBuilder str = new StringBuilder();

						for (int k = 0; k < sol[0].length; k++)
						{
							if (sol[j][k] == 1)
								str.append("Q");
							else
								str.append(".");
						}

						temp.add(new String(str));
					}

					result.add(new ArrayList<String>(temp));
				}

				sol[row][i] = 0;
			}
		}

		return false;
	}

	public List<List<String>> solveNQueens(int n)
	{
		List<List<String>> result = new ArrayList<List<String>>();
		if (n == 1)
		{
			List<String> temp = new ArrayList<String>();
			temp.add("Q");
			result.add(new ArrayList<String>(temp));
			return result;
		}

		if (n <= 3)
			return result;

		int[][] sol = new int[n][n];

		solveNQueensHelper(result, sol, 0);

		return result;
	}

	private static int count = 0;

	public void combinations(ArrayList<Integer> result, int[] arr, int sum, int x, int index)
	{
		if (sum == x)
		{
			for (int i = 0; i < result.size(); i++)
			{
				System.out.print(result.get(i));
			}
			System.out.println();
			count++;
			return;
		}

		if (sum > x)
			return;

		int prev = -1;

		for (int i = index; i < arr.length; i++)
		{
			if (prev != arr[i])
			{
				sum += arr[i];
				result.add(arr[i]);
				index = i;
				combinations(result, arr, sum, x, index + 1);
				result.remove(result.size() - 1);
				sum -= arr[i];
				prev = arr[i];
			}
		}

		return;
	}

	public boolean checkIfFirstWinner(char[] arr, int player)
	{
		if (player == 1)
			return false;

		for (int i = 0; i < arr.length - 1; i++)
		{
			if (arr[i] == '+' && arr[i + 1] == '+')
				return false;
		}

		return true;
	}

	public boolean flipGameWinnerOne(char[] arr, int player)
	{
		if (checkIfFirstWinner(arr, player))
			return true;

		for (int i = 0; i < arr.length - 1; i++)
		{
			if (arr[i] == '+' && arr[i + 1] == '+')
			{
				arr[i] = '-';
				arr[i + 1] = '-';

				player = (player == 1) ? 2 : 1;

				if (flipGameWinnerOne(arr, player))
					return true;

				player = (player == 1) ? 2 : 1;

				arr[i] = '+';
				arr[i + 1] = '+';
			}
		}

		return false;
	}

}
