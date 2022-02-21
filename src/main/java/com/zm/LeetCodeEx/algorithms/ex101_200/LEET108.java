package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 108. 将有序数组转换为二叉搜索树
 * <p>
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过.1。 <br>
 * 
 * 示例: <br>
 * 
 * 给定有序数组:.[-10,-3,0,5,9], <br>
 * 
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树： <br>
 * 
 * ......0 <br>
 * ...../.\ <br>
 * ...-3...9 <br>
 * .../.../ <br>
 * .-10..5 <br>
 * 
 * 
 * @author zm
 */
public class LEET108 {
	public static void main(String[] args) {
		LEET108 l108 = new LEET108();
		System.out.println(
				CommonFunctions.treeNodeToString(l108.new Solution().sortedArrayToBST(new int[] { -10, -3, 0, 5, 9 })));
		System.out.println(CommonFunctions.treeNodeToString(
				l108.new Solution().sortedArrayToBST(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 })));

	}

	class Solution {
        private int[] nums;

        public TreeNode sortedArrayToBST(int[] nums) {
            this.nums = nums;
            return helper(0, nums.length - 1);
        }

        private TreeNode helper(int l, int r) {
            if (l > r) {
                return null;
            }
            // 这里也可以不用+1，只是为了和题目示例的答案相同
            int mid = (l + r + 1) >>> 1;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = helper(l, mid - 1);
            node.right = helper(mid + 1, r);
            return node;
        }
    }
}
