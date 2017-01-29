import java.util.ArrayList;
import java.util.HashMap;

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

	public boolean isQueenSafe(int[][] sol, int row, int col, int length)
	{
		for (int i = 0; i < row; i++)
		{
			if (sol[row][col] == 1)
				return false;
		}

		int i = row - 1, j = col - 1;

		while (i >= 0 && j >= 0)
		{
			if (sol[row][col] == 1)
				return false;

			i--;
			j--;
		}

		i = row - 1;
		j = col + 1;

		while (i >= 0 && j < length)
		{
			if (sol[row][col] == 1)
				return false;

			i--;
			j++;
		}

		return true;
	}

	public boolean nQueens(int[][] sol, int row, int length)
	{
		if (row >= length)
			return true;

		for (int i = 0; i < length; i++)
		{
			if (isQueenSafe(sol, row, i, length))
			{
				sol[row][i] = 1;

				if (nQueens(sol, row + 1, length) == true)
					return true;

				sol[row][i] = 0;
			}
		}

		return false;
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
