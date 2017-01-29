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
}
