import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class BinaryTree
{
	class TreeNode
	{
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val)
		{
			this.val = val;
		}
	}

	class TreeLinkNode
	{
		int val;
		TreeLinkNode left;
		TreeLinkNode right;
		TreeLinkNode next;

		TreeLinkNode(int val)
		{
			this.val = val;
		}
	}

	public void connectPerfectTree(TreeLinkNode root)
	{
		if (root == null)
			return;
		TreeLinkNode curr = root;
		TreeLinkNode ptr = root;
		while (ptr != null)
		{
			curr = ptr;
			while (curr != null)
			{
				if (curr.left != null)
					curr.left.next = curr.right;
				if (curr.right != null && curr.next != null)
					curr.right.next = curr.next.left;

				curr = curr.next;
			}

			ptr = ptr.left;
		}

	}

	public List<List<Integer>> levelOrder(TreeNode root)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null)
			return result;

		Queue<TreeNode> queue = (Queue<TreeNode>) new LinkedList();
		Queue<Integer> level = (Queue<Integer>) new LinkedList();
		queue.offer(root);
		level.offer(0);

		List<Integer> list = new ArrayList<Integer>();

		while (queue.size() != 0)
		{
			TreeNode ptr = queue.poll();
			int l = level.poll();

			if (ptr.left != null)
			{
				queue.offer(ptr.left);
				level.offer(l + 1);
			}

			if (ptr.right != null)
			{
				queue.offer(ptr.right);
				level.offer(l + 1);
			}

			if (l > result.size())
			{
				result.add(new ArrayList<Integer>(list));
				list = new ArrayList<Integer>();
			}

			list.add(ptr.val);
		}

		if (list.size() > 0)
			result.add(new ArrayList<Integer>(list));

		return result;

	}

	public void recursiveRightSideView(TreeNode root, List<Integer> result, int depth)
	{
		if (root == null)
			return;

		if (depth == result.size())
			result.add(root.val);

		recursiveRightSideView(root.right, result, depth + 1);
		recursiveRightSideView(root.left, result, depth + 1);

		return;
	}

	// Iterative solution
	public List<Integer> rightSideView(TreeNode root)
	{
		List<Integer> result = new ArrayList<Integer>();
		if (root == null)
			return result;

		Queue<TreeNode> queue = (Queue<TreeNode>) new LinkedList();

		queue.offer(root);

		while (queue.size() != 0)
		{
			int n = queue.size();
			for (int i = 0; i < n; i++)
			{
				TreeNode temp = queue.poll();
				if (i == 0)
				{
					result.add(temp.val);
				}

				if (temp.right != null)
					queue.offer(temp.right);

				if (temp.left != null)
					queue.offer(temp.left);
			}
		}

		return result;
	}

	private static int value;
	private static int count;

	public void kthSmallestHelper(TreeNode root, int k)
	{
		if (root == null)
			return;

		kthSmallestHelper(root.left, k);
		count--;
		if (count == 0)
		{
			value = root.val;
			System.out.println(root.val);
		}

		kthSmallestHelper(root.right, k);
	}

	public int kthSmallest(TreeNode root, int k)
	{
		count = k;
		kthSmallestHelper(root, k);

		return value;
	}

	public void connect(TreeLinkNode root)
	{
		while (root != null)
		{
			TreeLinkNode tempChild = new TreeLinkNode(0);
			TreeLinkNode currentChild = tempChild;
			while (root != null)
			{
				if (root.left != null)
				{
					currentChild.next = root.left;
					currentChild = currentChild.next;
				}
				if (root.right != null)
				{
					currentChild.next = root.right;
					currentChild = currentChild.next;
				}
				root = root.next;
			}
			root = tempChild.next;
		}

	}

	private static int maxLeft = 0;
	private static int maxRight = 0;

	// Recursive using pre order traversal
	public HashMap<Integer, ArrayList<Integer>> verticalOrderHelper(TreeNode root, int distance, HashMap<Integer, ArrayList<Integer>> map)
	{
		if (root == null)
			return map;

		maxLeft = Math.min(maxLeft, distance);
		maxRight = Math.max(maxRight, distance);

		ArrayList<Integer> temp;
		if (map.containsKey(distance))
		{
			temp = map.get(distance);
			temp.add(root.val);
			map.put(distance, temp);
		} else
		{
			temp = new ArrayList<Integer>();
			temp.add(root.val);
			map.put(distance, temp);
		}

		map = verticalOrderHelper(root.left, distance - 1, map);
		map = verticalOrderHelper(root.right, distance + 1, map);

		return map;
	}

	// Iterative using level order or BFS
	public List<List<Integer>> verticalOrder(TreeNode root)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (root == null)
			return result;

		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		int maxLeft = 0;
		int maxRight = 0;

		Queue<TreeNode> elements = (Queue<TreeNode>) new LinkedList();
		Queue<Integer> distances = (Queue<Integer>) new LinkedList();

		elements.offer(root);
		distances.offer(0);

		while (elements.size() != 0)
		{
			TreeNode tempNode = elements.poll();
			int dist = distances.poll();

			maxLeft = Math.min(maxLeft, dist);
			maxRight = Math.max(maxRight, dist);

			ArrayList<Integer> temp;
			if (map.containsKey(dist))
			{
				temp = map.get(dist);
				temp.add(tempNode.val);
				map.put(dist, temp);
			} else
			{
				temp = new ArrayList<Integer>();
				temp.add(tempNode.val);
				map.put(dist, temp);
			}

			if (tempNode.left != null)
			{
				elements.offer(tempNode.left);
				distances.offer(dist - 1);
			}

			if (tempNode.right != null)
			{
				elements.offer(tempNode.right);
				distances.offer(dist + 1);
			}
		}

		int i = maxLeft;
		while (i <= maxRight)
		{
			if (map.containsKey(i))
			{
				result.add(new ArrayList<Integer>(map.get(i)));
			}
			i++;
		}

		return result;

	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
	{
		if (root == null || root == p || root == q)
			return root;

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);

		if (left != null && right != null)
			return root;

		return left == null ? right : left;

	}

	public boolean isValidBST(TreeNode root, long min, long max)
	{
		if (root == null)
			return true;

		return ((root.val > min && root.val < max) && isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max));
	}

	public boolean isValidBST(TreeNode root)
	{
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	private int maxValue;

	public int maxPathSumHelper(TreeNode root)
	{
		if (root == null)
			return 0;

		int left = Math.max(0, maxPathSumHelper(root.left));
		int right = Math.max(0, maxPathSumHelper(root.right));

		maxValue = Math.max(maxValue, left + right + root.val);

		return Math.max(left, right) + root.val;

	}

	public int maxPathSum(TreeNode root)
	{
		maxValue = Integer.MIN_VALUE;
		maxPathSumHelper(root);
		return maxValue;
	}

	public TreeNode findLeavesHelperFirst(TreeNode root, List<Integer> temp)
	{
		if (root == null)
			return root;

		if (root.left == null && root.right == null)
		{
			temp.add(root.val);
			return null;
		}

		root.left = findLeavesHelperFirst(root.left, temp);
		root.right = findLeavesHelperFirst(root.right, temp);

		return root;
	}

	public int height(TreeNode root, List<List<Integer>> result)
	{
		if (root == null)
			return -1;

		int level = Math.max(height(root.left, result), height(root.right, result)) + 1;

		if (level + 1 > result.size())
			result.add(new ArrayList<Integer>());

		result.get(level).add(root.val);

		return level;
	}

	public List<List<Integer>> findLeaves(TreeNode root)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// List<Integer> temp = new ArrayList<Integer>();
		/*
		 * while(root != null) { root = findLeavesHelper(root, temp);
		 * result.add(new ArrayList<Integer>(temp)); temp = new
		 * ArrayList<Integer>(); }
		 */

		height(root, result);

		return result;
	}
}
