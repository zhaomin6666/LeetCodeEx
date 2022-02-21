package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 96. 不同的二叉搜索树
 * <p>
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
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
public class LEET096 {
	public static void main(String[] args) {
		LEET096 l095 = new LEET096();
		System.out.println(l095.new Solution().numTrees(1));
		System.out.println(l095.new Solution().numTrees(2));
		System.out.println(l095.new Solution().numTrees(3));
		System.out.println(l095.new Solution().numTrees(4));
		System.out.println(l095.new Solution().numTrees(19));
	}

	/**
	 * 卡特兰数问题 满足递推关系式：h(n)= h(0)*h(n-1)+h(1)*h(n-2) + ... + h(n-1)*h(0) (n>=2)
	 * 例如：h(2)=h(0)*h(1)+h(1)*h(0)=1*1+1*1=2
	 * h(3)=h(0)*h(2)+h(1)*h(1)+h(2)*h(0)=1*2+1*1+2*1=5
	 * 另类递推关系式：h(n)=h(n-1)*(4*n-2)/(n+1)
	 * @author zm
	 *
	 */
	class Solution {
		public int numTrees(int n) {
			if (n <= 1) {
				return 1;
			}
			long k = 1;
			int i = 1;
			while (i <= n) {
				k = k * (4 * i - 2) / (++i);
			}
			return (int)k;
		}
	}
}
