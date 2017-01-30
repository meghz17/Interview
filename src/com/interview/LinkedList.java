package com.interview;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class LinkedList
{
	class ListNode
	{
		int val;
		ListNode next;

		ListNode(int val)
		{
			this.val = val;
		}
	}

	public ListNode mergeKLists(ListNode[] lists)
	{
		if (lists == null || lists.length == 0)
			return null;

		ListNode sentinel = new ListNode(-1);
		ListNode ptr = sentinel;

		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2)
			{
				if (o1.val < o2.val)
					return -1;
				else if (o1.val == o2.val)
					return 0;
				else
					return 1;
			}
		});

		for (ListNode node : lists)
		{
			if (node != null)
				queue.offer(node);
		}

		while (queue.size() != 0)
		{
			ListNode temp = queue.poll();

			if (temp.next != null)
				queue.offer(temp.next);

			ptr.next = new ListNode(temp.val);
			ptr = ptr.next;
		}

		return sentinel.next;
	}

	public ListNode rotateRight(ListNode head, int k)
	{
		if (head == null)
			return head;

		ListNode prev = head;
		ListNode curr = head;
		int count = 0;

		while (curr != null)
		{
			curr = curr.next;
			count++;
		}

		k = k % count;

		curr = head;
		while (curr != null && k > 0)
		{
			curr = curr.next;
			k--;
		}

		if (curr == null)
			return head;

		while (curr.next != null)
		{
			prev = prev.next;
			curr = curr.next;
		}

		curr.next = head;
		head = prev.next;
		prev.next = null;

		return head;
	}

	public ListNode reverseKGroup(ListNode head, int k)
	{
		if (head == null)
			return null;

		ListNode curr = head;
		int count = 0;
		while (curr != null)
		{
			count++;
			curr = curr.next;
		}

		if (count < k)
			return head;

		ListNode prev = null;
		curr = head;
		ListNode last = head;
		int c = k;
		while (curr != null && c > 0)
		{
			last = curr.next;
			curr.next = prev;
			prev = curr;
			curr = last;
			c--;
		}

		head.next = reverseKGroup(curr, k);

		return prev;
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2)
	{
		int sum = 0, carry = 0, left = 0, right = 0, value;

		ListNode x = l1;
		ListNode y = l2;

		ListNode root = new ListNode(-1);
		ListNode ptr = root;

		while (x != null || y != null)
		{
			left = 0;
			right = 0;

			if (x != null)
			{
				left = x.val;
				x = x.next;
			}

			if (y != null)
			{
				right = y.val;
				y = y.next;
			}
			sum = left + right + carry;

			carry = sum / 10;

			value = (sum >= 10) ? sum % 10 : sum;

			ptr.next = new ListNode(value);

			ptr = ptr.next;
		}

		if (carry == 1)
		{
			ptr.next = new ListNode(1);
		}

		return root.next;
	}

	private ListNode reverse(ListNode head, ListNode prev)
	{
		if (head == null)
			return prev;

		ListNode second = head.next;
		head.next = prev;
		return reverse(second, head);
	}

	public ListNode plusOne(ListNode head)
	{
		if (head == null)
			return head;

		head = reverse(head, null);

		ListNode prev = head;
		ListNode current = head;
		int carry = 1, sum;

		while (current != null && carry == 1)
		{
			sum = (current.val + carry);

			current.val = (sum) % 10;

			carry = (sum) / 10;

			prev = current;
			current = current.next;
		}

		if (carry == 1)
		{
			prev.next = new ListNode(1);
		}

		head = reverse(head, null);

		return head;
	}

	public ListNode merge(ListNode a, ListNode b)
	{
		ListNode sentinel = new ListNode(-1);
		ListNode ptr = sentinel;

		while (a != null && b != null)
		{
			if (a.val < b.val)
			{
				ptr.next = a;
				a = a.next;
			} else
			{
				ptr.next = b;
				b = b.next;
			}

			ptr = ptr.next;
		}

		if (a != null)
			ptr.next = a;

		if (b != null)
			ptr.next = b;

		return sentinel.next;
	}

	public ListNode sortList(ListNode head)
	{
		if (head == null || head.next == null)
			return head;

		ListNode prev = null, slow = head, fast = head;

		while (fast != null && fast.next != null)
		{
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		prev.next = null;

		ListNode l1 = sortList(head);
		ListNode l2 = sortList(slow);

		return merge(l1, l2);
	}

	public ListNode swapPairs(ListNode head)
	{
		if (head == null || head.next == null)
			return head;

		ListNode prev = head;
		ListNode curr = head.next;
		ListNode last = curr;
		head = curr;

		while (curr != null)
		{
			last = curr.next;
			curr.next = prev;
			if (last != null && last.next != null)
			{
				prev.next = last.next;
			} else
			{
				prev.next = last;
				return head;
			}

			prev = last;
			curr = last.next;
		}

		return head;
	}

	public ListNode addTwoNumbersII(ListNode l1, ListNode l2)
	{
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();

		while (l1 != null)
		{
			s1.push(l1.val);
			l1 = l1.next;
		}
		;
		while (l2 != null)
		{
			s2.push(l2.val);
			l2 = l2.next;
		}

		int sum = 0;
		ListNode list = new ListNode(0);
		while (!s1.empty() || !s2.empty())
		{
			if (!s1.empty())
				sum += s1.pop();
			if (!s2.empty())
				sum += s2.pop();
			list.val = sum % 10;
			ListNode head = new ListNode(sum / 10);
			head.next = list;
			list = head;
			sum /= 10;
		}

		return list.val == 0 ? list.next : list;
	}

	public ListNode oddEvenListWithSentinel(ListNode head)
	{
		if (head == null)
			return head;

		ListNode sentinel = new ListNode(-1);
		sentinel.next = head;

		ListNode odd = sentinel;
		ListNode even = sentinel.next;
		ListNode evenHead = even;

		while (even != null && even.next != null)
		{
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}

		odd.next = evenHead;

		return sentinel.next;
	}

	// Odd followed by even
	public ListNode oddEvenList(ListNode head)
	{
		if (head == null)
			return head;

		ListNode odd = head;
		ListNode even = head.next;
		ListNode evenHead = even;

		while (even != null && even.next != null)
		{
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}

		odd.next = evenHead;

		return head;
	}

	public ListNode reverseBetween(ListNode head, int m, int n)
	{
		if (head == null || m == n)
			return head;

		ListNode sentinel = new ListNode(-1);
		sentinel.next = head;

		ListNode pre = sentinel;

		for (int i = 0; i < m - 1; i++)
			pre = pre.next;

		ListNode current = pre.next;
		ListNode last = current.next;

		for (int i = 0; i < n - m; i++)
		{
			current.next = last.next;
			last.next = pre.next;
			pre.next = last;
			last = current.next;
		}

		return sentinel.next;
	}

}