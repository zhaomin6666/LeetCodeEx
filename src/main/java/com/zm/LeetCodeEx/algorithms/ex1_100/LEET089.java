package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 89. 格雷编码
 * <p>
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * <p>
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 * <p>
 * 示例 1:<br>
 * 输入: 2 <br>
 * 输出: [0,1,3,2]<br>
 * 解释: <br>
 * 00 - 0 <br>
 * 01 - 1 <br>
 * 11 - 3 <br>
 * 10 - 2 <br>
 * 对于给定的 n，其格雷编码序列并不唯一。 <br>
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。 <br>
 * 00 - 0 <br>
 * 10 - 2 <br>
 * 11 - 3 <br>
 * 01 - 1
 * <p>
 * 示例 2: 输入: 0 <br>
 * 输出: [0] <br>
 * 解释: 我们定义格雷编码序列必须以 0 开头。 <br>
 * 给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 2^0 = 1。<br>
 * 因此，当 n = 0 时，其格雷编码序列为 [0]。
 *
 * 
 * @author zm
 */
public class LEET089 {
	public static void main(String[] args) {
		LEET089 l089 = new LEET089();
		System.out.println(JSON.toJSONString(l089.new Solution2().grayCode(0)));
		System.out.println(JSON.toJSONString(l089.new Solution2().grayCode(1)));
		System.out.println(JSON.toJSONString(l089.new Solution2().grayCode(2)));
		System.out.println(JSON.toJSONString(l089.new Solution2().grayCode(3)));
		System.out.println(JSON.toJSONString(l089.new Solution2().grayCode(4)));
	}

	class Solution {
		List<Integer> ret;

		public List<Integer> grayCode(int n) {
			int total = (int) Math.pow(2, n);
			ret = new ArrayList<>(total);
			if (n == 0) {
				ret.add(0);
			} else {
				grayCodeHelper(n);
			}
			return ret;
		}

		public void grayCodeHelper(int n) {
			if (n == 1) {
				ret.add(0);
				ret.add(1);
			} else {
				grayCodeHelper(n - 1);
				for (int i = ret.size() - 1; i >= 0; i--) {
					ret.add(ret.get(i) + (1 << (n - 1)));
				}
			}
		}
	}

	/**
	 * 00^00 00 <br>
	 * 00^01 01 <br>
	 * 01^10 11 <br>
	 * 01^11 10
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public List<Integer> grayCode(int n) {
			int total = (int) Math.pow(2, n);
			List<Integer> ret = new ArrayList<>(total);
			for (int i = 0; i < total; i++) {
				ret.add((i >> 1) ^ i);
			}
			return ret;
		}
	}
}
