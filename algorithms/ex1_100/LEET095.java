package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * <p>
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3 输出: [   [1,null,3,2],   [3,2,null,1],   [3,1,null,null,2],   [2,1,3],  
 * [1,null,2,null,3] ] 解释: 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1       3     3     2     1 
 *  \     /     /     / \     \ 
 *   3   2     1     1   3     2 
 *  /   /      \                \ 
 * 2   1        2                3
 *
 * @author zm
 */
public class LEET095 {
	public static void main(String[] args) {
		LEET095 l095 = new LEET095();
		l095.new Solution().generateTrees(3)
				.forEach(treeNode -> System.out.println(CommonFunctions.treeNodeToString(treeNode)));
	}

	/**
     * 卡特兰数问题 满足递推关系式：h(n)= h(0)*h(n-1)+h(1)*h(n-2) + ... + h(n-1)*h(0) (n>=2)
     * 例如：h(2)=h(0)*h(1)+h(1)*h(0)=1*1+1*1=2
     * h(3)=h(0)*h(2)+h(1)*h(1)+h(2)*h(0)=1*2+1*1+2*1=5
     *
     * @author zm
     */
    class Solution {
        public List<TreeNode> generateTrees(int n) {
            if (n == 0) {
                return new LinkedList<>();
            }
            return helper(1, n);
        }

        private List<TreeNode> helper(int l, int r) {
            List<TreeNode> list = new LinkedList<>();
            if (l > r) {
                list.add(null);
                return list;
            }
            for (int i = l; i <= r; i++) {
                List<TreeNode> leftTrees = helper(l, i - 1);
                List<TreeNode> rightTrees = helper(i + 1, r);
                // 将左右树的所有可能进行组合
                for (TreeNode left : leftTrees) {
                    for (TreeNode right : rightTrees) {
                        TreeNode cur = new TreeNode(i);
                        cur.left = left;
                        cur.right = right;
                        list.add(cur);
                    }
                }
            }
            return list;
        }
    }
}
