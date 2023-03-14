package com.zm.LeetCodeEx.algorithms.ex1301_1400;

import com.alibaba.fastjson.JSON;
import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1305. 两棵二叉搜索树中的所有元素
 * 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 * <p>
 * 示例 1：
 * <p>
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 * 示例 2：
 * <p>
 * 输入：root1 = [1,null,8], root2 = [8,1]
 * 输出：[1,1,8,8]
 * <p>
 * 提示：
 * <p>
 * 每棵树的节点数在 [0, 5000] 范围内
 * -10^5 <= Node.val <= 10^5
 *
 * @author zm
 */
public class LEET1305 {
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(new Solution().getAllElements(CommonFunctions.stringToTreeNode("[2,1,4]"),
				CommonFunctions.stringToTreeNode("[1,0,3]")
		)));
	}

	/**
	 * 中序遍历+合并有序数据
	 */
	static class Solution {
		public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
			List<Integer> list1 = new ArrayList<>();
			List<Integer> list2 = new ArrayList<>();
			treeToListInOrder(root1, list1);
			treeToListInOrder(root2, list2);
			List<Integer> result = new ArrayList<>();
			int index1 = 0;
			int index2 = 0;
			int len1 = list1.size();
			int len2 = list2.size();
			while (index1 < len1 && index2 < len2) {
				if (list1.get(index1) > list2.get(index2)) {
					result.add(list2.get(index2++));
				}
				else {
					result.add(list1.get(index1++));
				}
			}
			if (index1 < len1) {
				while (index1 < len1) {
					result.add(list1.get(index1++));
				}
			}
			if (index2 < len2) {
				while (index2 < len2) {
					result.add(list2.get(index2++));
				}
			}
			return result;
		}

		public void treeToListInOrder(TreeNode node, List<Integer> result) {
			if (node == null) {
				return;
			}
			treeToListInOrder(node.left, result);
			result.add(node.val);
			treeToListInOrder(node.right, result);
		}
	}
}



