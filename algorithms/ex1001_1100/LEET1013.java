package com.zm.LeetCodeEx.algorithms.ex1001_1100;

/**
 * 1013. 将数组分成和相等的三个部分
 * <p>
 * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
 * 
 * 形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... +
 * A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
 * 
 * 示例 1：<br>
 * 输出：[0,2,1,-6,6,-7,9,1,2,0,1]<br>
 * 输出：true<br>
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * <p>
 * 示例 2：<br>
 * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]<br>
 * 输出：false
 * <p>
 * 示例 3：<br>
 * 输入：[3,3,6,5,-2,2,5,1,-9,4]<br>
 * 输出：true<br>
 * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4  
 * <p>
 * 
 * 提示：
 * <p>
 * 3 <= A.length <= 50000 <br>
 * -10^4 <= A[i] <= 10^4 <br>
 *
 * @author zm
 */
public class LEET1013 {
	public static void main(String[] args) {
		LEET1013 l1013 = new LEET1013();
		System.out.println(l1013.new Solution2().canThreePartsEqualSum(new int[] { 0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1 }));
		System.out.println(l1013.new Solution2().canThreePartsEqualSum(new int[] { 0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1 }));
		System.out.println(l1013.new Solution2().canThreePartsEqualSum(new int[] { 3, 3, 6, 5, -2, 2, 5, 1, -9, 4 }));
		System.out.println(l1013.new Solution2().canThreePartsEqualSum(new int[] { 1, 1, 2, 1, 1 }));
	}

	/**
	 * 暴力循环
	 * @author zm
	 *
	 */
	class Solution {
		public boolean canThreePartsEqualSum(int[] A) {
			int L = A.length;
			if (L < 3) {
				return false;
			}
			int thirdStartSum = 0;
			for (int i = 2; i < L; i++) {
				thirdStartSum += A[i];
			}
			int firstSum = A[0];
			int split1 = 1;
			int split2 = 2;
			while (split1 < L - 1) {
				int secondSum = A[split1];
				int thirdSum = thirdStartSum;
				while (split2 < L) {
					if (firstSum == secondSum && firstSum == thirdSum) {
						return true;
					}
					secondSum += A[split2];
					thirdSum -= A[split2++];
				}
				firstSum += A[split1];
				thirdStartSum -= A[++split1];
				split2 = split1 + 1;
			}
			return false;
		}
	}
	
	/**
	 * 先计算和/3的值
	 * @author zm
	 *
	 */
	class Solution2 {
		public boolean canThreePartsEqualSum(int[] A) {
			int L = A.length;
			if (L < 3) {
				return false;
			}
			int sum = 0;
			for (int i = 0; i < A.length; i++) {
				sum += A[i];
			}
			double div = sum*1.0/3;
			if (Double.valueOf((int)div).equals(Double.valueOf(div))) {
				int thirdStartSum = 0;
				for (int i = 2; i < L; i++) {
					thirdStartSum += A[i];
				}
				int firstSum = A[0];
				int split1 = 1;
				int split2 = 2;
				while (split1 < L - 1) {
					if (firstSum == (int)div) {
						int secondSum = A[split1];
						int thirdSum = thirdStartSum;
						while (split2 < L) {
							if (firstSum == secondSum && firstSum == thirdSum) {
								return true;
							}
							secondSum += A[split2];
							thirdSum -= A[split2++];
						}
					}
					firstSum += A[split1];
					thirdStartSum -= A[++split1];
					split2 = split1 + 1;
				}
				return false;
			}
			return false;
		}
	}
}
