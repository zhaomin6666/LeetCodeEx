package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.LinkedList;
import java.util.Queue;

import com.alibaba.fastjson.JSON;
import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.NodeWithNext;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * <p>
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node { <br>
 * int val; <br>
 * Node *left; <br>
 * Node *right; <br>
 * Node *next; <br>
 * } <br>
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * ......1 <br>
 * ..../...\ <br>
 * ..2.......3 <br>
 * ./.\...../.\ <br>
 * 4...5...6...7 <br>
 * 
 * ......1---->null <br>
 * ..../...\..........<br>
 * ..2------>3---->null <br>
 * ./.\...../.\..........<br>
 * 4-->5-->6-->7---->null <br>
 * <p>
 * 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 * <p>
 * 输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
 * <p>
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * <p>
 * 提示：
 * <p>
 * 你只能使用常量级额外空间。 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 
 * @author zm
 */
public class LEET116 {
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(
				new Solution().connect(CommonFunctions.stringToTreeNodeWithNext("[1,2,3,4,5,6,7]"))));
	}

	/**
	 * 用bfs每层循环
	 */
	static class Solution {
		public NodeWithNext connect(NodeWithNext root) {
			if (root == null) {
				return null;
			}
			Queue<NodeWithNext> queue = new LinkedList<>();
			queue.add(root);
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					NodeWithNext temp = queue.poll();
					if (i < size - 1) {
						temp.next = queue.peek();
	                }
					if (temp.left != null) {
						queue.add(temp.left);
					}
					if (temp.right != null) {
						queue.add(temp.right);
					}
				}
			}
			return root;
		}
	}

	/**
	 * 官方题解
	 * 
	 * 1.由于是一颗完美二叉树，所以每层的第一个节点可以使用leftmost=leftmost.left来到达下一层
	 * 2.处理第n层是在遍历第n-1层的时候操作的 3.分清两种情况，一种是把n-1层的某个节点的左子节点next指向右子节点，
	 * 另一种是把n-1层某个节点x的右子节点next指向x的next节点的左子节点。
	 * 
	 * @author zm
	 *
	 */
	static class Solution2 {
		public NodeWithNext connect(NodeWithNext root) {

			if (root == null) {
				return root;
			}

			// Start with the root node. There are no next pointers
			// that need to be set up on the first level
			NodeWithNext leftmost = root;

			// Once we reach the final level, we are done
			while (leftmost.left != null) {

				// Iterate the "linked list" starting from the head
				// node and using the next pointers, establish the
				// corresponding links for the next level
				NodeWithNext head = leftmost;

				while (head != null) {

					// CONNECTION 1
					head.left.next = head.right;

					// CONNECTION 2
					if (head.next != null) {
						head.right.next = head.next.left;
					}

					// Progress along the list (nodes on the current level)
					head = head.next;
				}

				// Move onto the next level
				leftmost = leftmost.left;
			}

			return root;
		}
	}
}
