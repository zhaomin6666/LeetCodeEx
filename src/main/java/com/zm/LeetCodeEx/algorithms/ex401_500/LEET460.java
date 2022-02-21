package com.zm.LeetCodeEx.algorithms.ex401_500;

import java.util.HashMap;
import java.util.Map;

/**
 * 460. LFU缓存
 * <p>
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 * <p>
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。 put(key, value) -
 * 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 * <p>
 * 进阶： 你是否可以在 O(1) 时间复杂度内执行两项操作？
 * <p>
 * 示例：
 * <p>
 * LFUCache cache = new LFUCache(2); //capacity (缓存容量) <br>
 * cache.put(1,1);<br>
 * cache.put(2,2);<br>
 * cache.get(1); // 返回 1 <br>
 * cache.put(3,3); // 去除 key 2 <br>
 * cache.get(2); // 返回 -1 (未找到key 2) <br>
 * cache.get(3); // 返回 3 <br>
 * cache.put(4,4); // 去除 key 1 <br>
 * cache.get(1); // 返回 -1 (未找到 key 1) <br>
 * cache.get(3); // 返回 3 <br>
 * cache.get(4); // 返回 4 <br>
 *
 * @author zm
 */
public class LEET460 {
	public static void main(String[] args) {
		LEET460 l460 = new LEET460();
		LFUCache cache = l460.new LFUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1)); // 返回 1
		cache.put(3, 3); // 去除 key 2
		System.out.println(cache.get(2)); // 返回 -1 (未找到key 2)
		System.out.println(cache.get(3)); // 返回 3
		cache.put(4, 4); // 去除 key 1
		System.out.println(cache.get(1)); // 返回 -1 (未找到 key 1)
		System.out.println(cache.get(3)); // 返回 3
		System.out.println(cache.get(4)); // 返回 4
	}

	/**
	 * 每一个频次用一个链表保存从新到旧的数据
	 * firstLinkedList-->freq最大的链表-->...-->freq最小的链表-->lastLinkedList 每一个链表保存从新到旧的数据
	 * head-->最新的数据-->...-->最久的数据-->tail 当head.post指向tail的时候这个频次就没有数据此时删除链表
	 * 用Map记录Key-Node可以快速获取Node，Node中又直接存储了他的前节点、后节点以及所在频次链表， 所以可以对结点以及链表方便的进行操作。
	 * 
	 * @author zm 加上上述理解
	 * @author 原作者：sweetiee 
	 * @link 链接：https://leetcode-cn.com/problems/lfu-cache/solution/java-13ms-shuang-100-shuang-xiang-lian-biao-duo-ji/
	 * @source：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
	 */
	class LFUCache {
		Map<Integer, Node> cache; // 存储缓存的内容，Node中除了value值外，还有key、freq、所在doublyLinkedList、
									// 所在doublyLinkedList中的postNode、所在doublyLinkedList中的preNode，具体定义在下方。
		DoublyLinkedList firstLinkedList; // firstLinkedList.post 是频次最大的双向链表
		DoublyLinkedList lastLinkedList; // lastLinkedList.pre 是频次最小的双向链表，满了之后删除 lastLinkedList.pre.tail.pre
		// 这个Node即为频次最小且访问最早的Node
		int size;
		int capacity;

		public LFUCache(int capacity) {
			cache = new HashMap<>(capacity);
			firstLinkedList = new DoublyLinkedList();
			lastLinkedList = new DoublyLinkedList();
			firstLinkedList.post = lastLinkedList;
			lastLinkedList.pre = firstLinkedList;
			this.capacity = capacity;
		}

		public int get(int key) {
			Node node = cache.get(key);
			if (node == null) {
				return -1;
			}
			// 该key访问频次+1
			freqInc(node);
			return node.value;
		}

		public void put(int key, int value) {
			if (capacity == 0) {
				return;
			}
			Node node = cache.get(key);
			// 若key存在，则更新value，访问频次+1
			if (node != null) {
				node.value = value;
				freqInc(node);
			} else {
				// 若key不存在
				if (size == capacity) {
					// 如果缓存满了，删除lastLinkedList.pre这个链表（即表示最小频次的链表）中的tail.pre这个Node（即最小频次链表中最先访问的Node），如果该链表中的元素删空了，则删掉该链表。
					cache.remove(lastLinkedList.pre.tail.pre.key);
					lastLinkedList.removeNode(lastLinkedList.pre.tail.pre);
					size--;
					if (lastLinkedList.pre.head.post == lastLinkedList.pre.tail) {
						removeDoublyLinkedList(lastLinkedList.pre);
					}
				}
				// cache中put新Key-Node对儿，并将新node加入表示freq为1的DoublyLinkedList中，若不存在freq为1的DoublyLinkedList则新建。
				Node newNode = new Node(key, value);
				cache.put(key, newNode);
				if (lastLinkedList.pre.freq != 1) {
					DoublyLinkedList newDoublyLinedList = new DoublyLinkedList(1);
					addDoublyLinkedList(newDoublyLinedList, lastLinkedList.pre);
					newDoublyLinedList.addNode(newNode);
				} else {
					lastLinkedList.pre.addNode(newNode);
				}
				size++;
			}
		}

		/**
		 * node的访问频次 + 1
		 */
		void freqInc(Node node) {
			// 将node从原freq对应的双向链表里移除, 如果链表空了则删除链表。
			DoublyLinkedList linkedList = node.doublyLinkedList;
			DoublyLinkedList preLinkedList = linkedList.pre;
			linkedList.removeNode(node);
			if (linkedList.head.post == linkedList.tail) {
				removeDoublyLinkedList(linkedList);
			}
			// 将node加入新freq对应的双向链表，若该链表不存在，则先创建该链表。
			node.freq++;
			if (preLinkedList.freq != node.freq) {
				DoublyLinkedList newDoublyLinedList = new DoublyLinkedList(node.freq);
				addDoublyLinkedList(newDoublyLinedList, preLinkedList);
				newDoublyLinedList.addNode(node);
			} else {
				preLinkedList.addNode(node);
			}
		}

		/**
		 * 增加代表某1频次的双向链表
		 */
		void addDoublyLinkedList(DoublyLinkedList newDoublyLinedList, DoublyLinkedList preLinkedList) {
			newDoublyLinedList.post = preLinkedList.post;
			newDoublyLinedList.post.pre = newDoublyLinedList;
			newDoublyLinedList.pre = preLinkedList;
			preLinkedList.post = newDoublyLinedList;
		}

		/**
		 * 删除代表某1频次的双向链表
		 */
		void removeDoublyLinkedList(DoublyLinkedList doublyLinkedList) {
			doublyLinkedList.pre.post = doublyLinkedList.post;
			doublyLinkedList.post.pre = doublyLinkedList.pre;
		}

	}

	class Node {
		int key;
		int value;
		int freq = 1;
		Node pre; // Node所在频次的双向链表的前继Node
		Node post; // Node所在频次的双向链表的后继Node
		DoublyLinkedList doublyLinkedList; // Node所在频次的双向链表

		public Node() {
		}

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	class DoublyLinkedList {
		int freq; // 该双向链表表示的频次

		DoublyLinkedList pre; // 该双向链表的前继链表（pre.freq < this.freq）

		DoublyLinkedList post; // 该双向链表的后继链表 (post.freq > this.freq)

		Node head; // 该双向链表的头节点，新节点从头部加入，表示最近访问

		Node tail; // 该双向链表的尾节点，删除节点从尾部删除，表示最久访问

		public DoublyLinkedList() {
			head = new Node();
			tail = new Node();
			head.post = tail;
			tail.pre = head;
		}

		public DoublyLinkedList(int freq) {
			head = new Node();
			tail = new Node();
			head.post = tail;
			tail.pre = head;
			this.freq = freq;
		}

		void removeNode(Node node) {
			node.pre.post = node.post;
			node.post.pre = node.pre;
		}

		void addNode(Node node) {
			node.post = head.post;
			head.post.pre = node;
			head.post = node;
			node.pre = head;
			node.doublyLinkedList = this;
		}
	}
}
