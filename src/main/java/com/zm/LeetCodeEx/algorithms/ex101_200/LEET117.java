package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.alibaba.fastjson.JSON;
import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.NodeWithNext;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 117. 填充每个节点的下一个右侧节点指针II
 * <p>
 * 给定一个二叉树
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
 * ./.\.......\ <br>
 * 4...5.......7 <br>
 * <p>
 * ......1---->null <br>
 * ..../...\..........<br>
 * ..2------>3---->null <br>
 * ./.\.......\..........<br>
 * 4-->5------>7---->null <br>
 * <p>
 * 输入：root = [1,2,3,4,5,null,7]
 * <p>
 * 输出：[1,#,2,3,#,4,5,7,#]
 * <p>
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * <p>
 * 提示：
 * <p>
 * 你只能使用常量级额外空间。 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * @author zm
 */
public class LEET117 {
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(
				new Solution2().connect(CommonFunctions.stringToTreeNodeWithNext("[1,2,3,4,5,6,7]"))));
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
	 * @author zm
	 */
	static class Solution2 {
		// leftmost记录每一行最左边的第一个节点，用于寻找下一层第一个节点的时候作为遍历的开头
		private NodeWithNext prev, leftmost;

		public void processChild(NodeWithNext childNode) {
			if (childNode != null) {
				// If the "prev" pointer is alread set i.e. if we
				// already found atleast one node on the next level,
				// setup its next pointer
				if (prev != null) {
					prev.next = childNode;
				}
				else {
					// Else it means this child node is the first node
					// we have encountered on the next level, so, we
					// set the leftmost pointer
					leftmost = childNode;
				}
				prev = childNode;
			}
		}

		public NodeWithNext connect(NodeWithNext root) {
			if (root == null) {
				return root;
			}
			// The root node is the only node on the first level
			// and hence its the leftmost node for that level
			leftmost = root;
			// Variable to keep track of leading node on the "current" level
			NodeWithNext curr = leftmost;
			// We have no idea about the structure of the tree,
			// so, we keep going until we do find the last level.
			// the nodes on the last level won't have any children
			while (leftmost != null) {
				// "prev" tracks the latest node on the "next" level
				// while "curr" tracks the latest node on the current
				// level.
				prev = null;
				curr = leftmost;
				// We reset this so that we can re-assign it to the leftmost
				// node of the next level. Also, if there isn't one, this
				// would help break us out of the outermost loop.
				leftmost = null;
				// Iterate on the nodes in the current level using
				// the next pointers already established.
				while (curr != null) {
					// Process both the children and update the prev
					// and leftmost pointers as necessary.
					processChild(curr.left);
					processChild(curr.right);
					// Move onto the next node.
					curr = curr.next;
				}
			}
			return root;
		}
	}
}
