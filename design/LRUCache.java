package design;

import java.util.HashMap;

public class LRUCache
{
	class Node
	{
		int key;
		int value;
		Node prev;
		Node next;

		Node(int key, int value)
		{
			this.key = key;
			this.value = value;
		}
	}

	private HashMap<Integer, Node> map;
	private int capacity;
	private Node head;
	private Node tail;

	public LRUCache(int capacity)
	{
		head = new Node(0, 0);
		tail = new Node(0, 0);
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;
		map = new HashMap<Integer, Node>();
		this.capacity = capacity;
	}

	public Node popTail()
	{
		Node node = tail.prev;
		deleteNode(node);
		return node;
	}

	public void deleteNode(Node node)
	{
		if (node == null)
			return;

		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	private void moveToHead(Node node)
	{
		deleteNode(node);
		addNode(node);
	}

	public void addNode(Node node)
	{
		node.prev = head;
		node.next = head.next;

		head.next.prev = node;
		head.next = node;
	}

	public int get(int key)
	{
		if (map.containsKey(key))
		{
			Node node = map.get(key);
			moveToHead(node);
			return node.value;
		}

		return -1;
	}

	public void put(int key, int value)
	{
		Node node = map.get(key);

		if (node == null)
		{
			Node element = new Node(key, value);
			map.put(key, element);
			addNode(element);

			if (map.size() > capacity)
			{
				Node ptr = popTail();
				map.remove(ptr.key);
			}
		} else
		{
			node.value = value;
			moveToHead(node);
		}
	}
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
