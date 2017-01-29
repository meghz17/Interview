import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class ArrayAlgo
{

	public boolean isNine(int[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			if (arr[i] != 9)
				return false;
		}

		return true;
	}

	public int nextGreaterPalindrome(int[] arr)
	{
		int low = 0;
		int high = arr.length;
		int temp = 0;

		// Deal with special case all nine's - 99, 999
		if (isNine(arr))
		{
			for (int i = 0; i < arr.length; i++)
			{
				temp = temp * 10 + arr[i];
			}

			return temp + 2;
		}

		int mid = low + ((high - low) / 2);
		int carry = 0;
		int k = 0, l = mid;

		// Decide if even or odd and place your pointers
		if (arr.length % 2 == 0)
		{
			k = mid - 1;
			l = mid;
		} else
		{
			k = mid;
			l = mid;
		}

		int left = 0, right = 0;

		// Form left number for comparision for incremental replace or normal
		// replace
		for (int i = k; i >= 0; i--)
		{
			left = left * 10 + arr[i];
		}

		// Form right number for comparision for incremental replace or normal
		// replace
		for (int i = l; i < arr.length; i++)
		{
			right = right * 10 + arr[i];
		}

		// Decide carry on comparision
		if (left <= right)
			carry = 1;

		// Start replacing the right with left as we need a larger palindrome
		while (k >= low)
		{
			arr[k] = (arr[k] + carry) % 10;

			carry = (arr[k]) / 10;

			arr[l] = arr[k];
			k--;
			l++;
		}

		temp = 0;

		// Form the number to return
		for (int i = 0; i < arr.length; i++)
		{
			temp = temp * 10 + arr[i];
		}

		return temp;
	}

	public int rotatedMinimumBinarySearch(int[] arr, int low, int high)
	{
		if (arr[low] < arr[high])
			return low;

		int mid = (low + high) / 2;

		if (mid + 1 <= high && arr[mid] > arr[mid + 1])
			return mid + 1;
		else if (mid - 1 >= 0 && arr[mid] < arr[mid - 1])
			return mid;
		else if (arr[mid] > arr[low])
			return rotatedMinimumBinarySearch(arr, mid + 1, high);
		else
			return rotatedMinimumBinarySearch(arr, low, mid - 1);
	}

	public int rotatedBinarySearch(int[] arr, int low, int high, int x)
	{
		if (low > high)
			return -1;

		int mid = low + (high - low) / 2;

		if (arr[low] == x)
			return low;
		if (arr[high] == x)
			return high;

		if (arr[mid] == x)
			return mid;
		else if ((arr[mid] > x && arr[low] > x) || (arr[mid] < x && arr[low] < x))
			return rotatedBinarySearch(arr, mid + 1, high, x);
		else
			return rotatedBinarySearch(arr, low, mid + 1, x);

	}

	public int searchInsert(int[] arr, int low, int high, int x)
	{
		if (low > high)
			return -1;

		int mid = (low + high) / 2;
		int index;
		if (arr[mid] == x)
			return mid;
		else if (arr[mid] > x)
		{
			index = searchInsert(arr, low, mid - 1, x);

			return (index == -1) ? mid : index;

		} else
		{
			index = searchInsert(arr, mid + 1, high, x);

			return (index == -1) ? mid : index;
		}
	}

	public int reverseNumber(int num)
	{
		int reverse = 0;

		while (num > 0)
		{
			reverse = reverse * 10 + num % 10;
			num = num / 10;
		}

		return reverse;
	}

	public int minTotalDistance(int[][] grid)
	{
		List<Integer> rows = new ArrayList<>();
		List<Integer> cols = new ArrayList<>();
		for (int row = 0; row < grid.length; row++)
		{
			for (int col = 0; col < grid[0].length; col++)
			{
				if (grid[row][col] == 1)
				{
					rows.add(row);
					cols.add(col);
				}
			}
		}
		int row = rows.get(rows.size() / 2);
		Collections.sort(cols);
		int col = cols.get(cols.size() / 2);
		return minDistance1D(rows, row) + minDistance1D(cols, col);
	}

	public List<Integer> spiralOrder(int[][] matrix)
	{
		List<Integer> result = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0)
			return result;

		int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;

		while (l <= r && t <= b)
		{
			for (int i = l; i <= r; i++)
				result.add(matrix[t][i]);
			t++;

			for (int i = t; i <= b; i++)
				result.add(matrix[i][r]);
			r--;

			if (t <= b)
			{
				for (int i = r; i >= l; i--)
					result.add(matrix[b][i]);
				b--;
			}

			if (l <= r)
			{
				for (int i = b; i >= t; i--)
					result.add(matrix[i][l]);
				l++;
			}
		}

		return result;
	}

	class Interval
	{
		int start;
		int end;

		Interval(int start, int end)
		{
			this.start = start;
			this.end = end;
		}
	}

	public List<Interval> merge(List<Interval> intervals)
	{
		// Sort the intervals with start times

		Collections.sort(intervals, new Comparator<Interval>() {

			public int compare(Interval i1, Interval i2)
			{
				if (i1.start > i2.start)
					return 1;
				else if (i1.start < i2.start)
					return -1;

				return 0;
			}
		});

		// Iterate and merge
		int i = 1;
		while (i < intervals.size())
		{
			if (intervals.get(i).start <= intervals.get(i - 1).end)
			{
				intervals.get(i - 1).end = Math.max(intervals.get(i - 1).end, intervals.get(i).end);
				intervals.remove(i);
			} else
			{
				i++;
			}
		}

		return intervals;
	}

	public List<Integer> majorityElementII(int[] nums)
	{
		List<Integer> list = new ArrayList<Integer>();

		if (nums.length == 0)
			return list;

		if (nums.length == 1)
		{
			list.add(nums[0]);
			return list;
		}

		int count1 = 0, count2 = 0, cand1 = nums[0], cand2 = nums[1];

		for (int i = 0; i < nums.length; i++)
		{
			if (nums[i] == cand1)
				count1++;
			else if (nums[i] == cand2)
				count2++;
			else if (count1 == 0)
			{
				cand1 = nums[i];
				count1 = 1;
			} else if (count2 == 0)
			{
				cand2 = nums[i];
				count2 = 1;
			} else
			{
				count1--;
				count2--;
			}
		}

		count1 = 0;
		count2 = 0;
		for (int i = 0; i < nums.length; i++)
		{
			count1 = nums[i] == cand1 ? count1 + 1 : count1;
			count2 = nums[i] == cand2 ? count2 + 1 : count2;
		}

		if (count1 > nums.length / 3)
			list.add(cand1);

		if (cand1 != cand2 && count2 > nums.length / 3)
			list.add(cand2);

		return list;
	}

	private int minDistance1D(List<Integer> points, int origin)
	{
		int distance = 0;
		for (int point : points)
		{
			distance += Math.abs(point - origin);
		}
		return distance;
	}

	public int searchHelper(int[] nums, int target, int low, int high)
	{
		if (low > high)
			return -1;

		int mid = (low + (high - low) / 2);

		if (nums[mid] == target)
			return mid;
		else if (nums[mid] >= nums[low])
		{
			if (target < nums[mid] && target >= nums[low])
				return searchHelper(nums, target, low, mid - 1);
			else
				return searchHelper(nums, target, mid + 1, high);
		} else
		{
			if (target > nums[mid] && target <= nums[high])
				return searchHelper(nums, target, mid + 1, high);
			else
				return searchHelper(nums, target, low, mid - 1);
		}

	}

	public int search(int[] nums, int target)
	{
		return searchHelper(nums, target, 0, nums.length - 1);
	}

	public void setZeroes(int[][] matrix)
	{

		boolean row = false, col = false;
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix[0].length; j++)
			{
				if (matrix[i][j] == 0)
				{
					if (i == 0)
						row = true;
					if (j == 0)
						col = true;
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		for (int i = 1; i < matrix.length; i++)
		{
			for (int j = 1; j < matrix[0].length; j++)
			{
				if (matrix[i][0] == 0 || matrix[0][j] == 0)
					matrix[i][j] = 0;
			}
		}

		if (row)
		{
			for (int j = 0; j < matrix[0].length; j++)
				matrix[0][j] = 0;
		}

		if (col)
		{
			for (int i = 0; i < matrix.length; i++)
				matrix[i][0] = 0;
		}

		return;
	}

	public int findPeakElementHelper(int[] num, int low, int high)
	{
		if (low == high)
			return low;
		else
		{
			int mid1 = (low + high) / 2;
			int mid2 = mid1 + 1;
			if (num[mid1] > num[mid2])
				return findPeakElementHelper(num, low, mid1);
			else
				return findPeakElementHelper(num, mid2, high);
		}

	}

	public List<Integer> grayCode(int n)
	{
		List<Integer> prevList = new ArrayList<Integer>();
		prevList.add(0);

		if (n <= 0)
			return prevList;

		List<Integer> currList;
		boolean flag;

		prevList.add(1);

		for (int i = 2; i <= n; i++)
		{
			int j = 0;
			flag = true;

			currList = new ArrayList<Integer>();

			while (j < prevList.size())
			{
				if (flag)
				{
					currList.add(prevList.get(j) << 1 | 0);
					currList.add(prevList.get(j) << 1 | 1);
				} else
				{
					currList.add(prevList.get(j) << 1 | 1);
					currList.add(prevList.get(j) << 1 | 0);
				}
				flag = flag == true ? false : true;
				j++;
			}

			prevList = new ArrayList<Integer>(currList);
		}

		return prevList;
	}

	public int findDuplicate(int[] nums)
	{
		if (nums == null || nums.length <= 1)
			return -1;

		int slow = 0, fast = 0;

		while (true)
		{
			slow = nums[slow];
			fast = nums[nums[fast]];

			if (slow == fast)
				break;
		}

		fast = 0;
		while (true)
		{
			slow = nums[slow];
			fast = nums[fast];

			if (slow == fast)
				break;
		}

		return slow;
	}

	public int[][] reconstructQueue(int[][] people)
	{
		if (people.length <= 1)
			return people;

		Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] entry1, int[] entry2)
			{
				if (entry1[0] == entry2[0])
					return entry1[1] - entry2[1];

				return entry2[0] - entry1[0];
			}
		});

		Stack<int[]> stack1 = new Stack<int[]>();
		Stack<int[]> stack2 = new Stack<int[]>();

		int i = 1;
		stack1.push(people[0]);
		while (i < people.length)
		{
			if (stack1.peek()[0] == people[i][0])
			{
				stack1.push(people[i]);
			} else
			{
				while (stack1.size() != 0 && people[i][1] < stack1.size())
				{
					stack2.push(stack1.pop());
				}

				stack1.push(people[i]);

				while (stack2.size() != 0)
				{
					stack1.push(stack2.pop());
				}
			}

			i++;
		}

		i = people.length - 1;
		while (stack1.size() != 0)
		{
			people[i] = stack1.pop();
			i--;
		}

		return people;
	}

	public int totalHammingDistance(int[] nums)
	{
		int mask = 1, zeroes, count = 0;
		for (int i = 0; i < 32; i++)
		{
			zeroes = 0;
			for (int j = 0; j < nums.length; j++)
			{
				if ((nums[j] & mask) == 0)
					zeroes++;
			}
			count += zeroes * (nums.length - zeroes);
			mask = mask << 1;
		}

		return count;
	}

	public int[] countBits(int num)
	{
		int[] total = new int[num + 1];
		total[0] = 0;

		if (num == 0)
			return total;

		total[1] = 1;

		if (num == 1)
			return total;

		int index = 0;

		for (int i = 2; i <= num; i++)
		{
			if ((i & i - 1) == 0)
			{
				index = i;
				total[i] = 1;
			} else
			{
				total[i] = total[index] + total[i - index];
			}
		}

		return total;
	}

	public int countBattleships(char[][] board)
	{
		int count = 0;

		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				if (board[i][j] == 'X' && (!((i + 1 < board.length && board[i + 1][j] == 'X') || (j + 1 < board[0].length && board[i][j + 1] == 'X'))))
					count++;

			}

		}

		return count;
	}

	public int findPeakElement(int[] arr)
	{
		int low = 0, high = arr.length - 1;

		return findPeakElementHelper(arr, low, high);
	}

	public int[] maxSlidingWindow(int[] nums, int k)
	{
		if (nums == null || k <= 0)
		{
			return new int[0];
		}

		int[] result = new int[nums.length - k + 1];

		Deque<Integer> queue = (Deque<Integer>) new LinkedList();

		for (int i = 0; i < nums.length; i++)
		{
			while (queue.size() != 0 && queue.peek() < i - k + 1)
			{
				queue.poll();
			}

			while (queue.size() != 0 && nums[i] > nums[queue.peekLast()])
			{
				queue.pollLast();
			}

			queue.offer(i);

			if (i >= k - 1)
			{
				result[i - k + 1] = nums[queue.peek()];
			}

		}

		return result;
	}

	public void swap(int[] nums, int i, int j)
	{
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;

		return;
	}

	public int[][] generateMatrix(int n)
	{
		int[][] result = new int[n][n];
		if (n == 0)
			return result;

		int l = 0, r = n - 1, t = 0, b = n - 1, count = 1;

		while (l <= r && t <= b)
		{
			for (int i = l; i <= r; i++)
				result[t][i] = count++;
			t++;

			for (int i = t; i <= b; i++)
				result[i][r] = count++;
			r--;

			if (t <= b)
			{
				for (int i = r; i >= l; i--)
					result[b][i] = count++;
				b--;
			}

			if (l <= r)
			{
				for (int i = b; i >= t; i--)
					result[i][l] = count++;
				l++;
			}
		}
		return result;
	}

	public int findMin(int[] nums)
	{
		int low = 0, high = nums.length - 1, mid = low;

		while (low <= high)
		{
			if (high - low <= 1)
				return nums[low] > nums[high] ? nums[high] : nums[low];

			mid = (low + (high - low) / 2);

			if (mid - 1 >= low && nums[mid] < nums[mid - 1] && mid + 1 <= high && nums[mid] < nums[mid + 1])
				return nums[mid];
			else if (mid - 1 >= low && nums[mid] > nums[mid - 1] && mid + 1 <= high && nums[mid] > nums[mid + 1])
				return nums[mid + 1];
			else if (nums[mid] >= nums[low])
			{
				if (nums[mid] < nums[high])
					return nums[low];
				low = mid + 1;
			} else
				high = mid - 1;
		}

		return -1;
	}

	public void sortColors(int[] nums)
	{
		int i = 0, j = 0, k = nums.length - 1;

		while (j <= k)
		{
			if (nums[j] == 1)
				j++;
			else if (nums[j] == 0)
			{
				swap(nums, i, j);
				i++;
				j++;
			} else
			{
				swap(nums, j, k);
				k--;
			}
		}

		return;
	}
}
