package com.zm.LeetCodeEx.algorithms.ex901_1000;

/**
 * 974. 和可被 K 整除的子数组
 * <p>
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：A = [4,5,0,-2,-3,1], K = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 K = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 *
 * @author zm
 */
public class LEET974 {
    public static void main(String[] args) {
        LEET974 l974 = new LEET974();
        System.out.println((l974.new Solution2().subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5)));
        System.out.println((l974.new Solution2().subarraysDivByK(new int[]{-1, 2, 9}, 2)));
    }

    /**
     * 暴力解超时
     */
    class Solution {
        public int subarraysDivByK(int[] A, int K) {
            int ret = 0;
            for (int i = 0; i < A.length; i++) {
                int sum = 0;
                for (int j = i; j < A.length; j++) {
                    sum += A[j];
                    if (sum % K == 0) {
                        ret++;
                    }
                }
            }
            return ret;
        }
    }

    /**
     * 使用前缀和思想，保存前缀和对K取余后的余数，当下一次再遇到这个余数时，
     * 就说明中间的子序列可以被整除。
     */
    class Solution2 {
        public int subarraysDivByK(int[] A, int K) {
            int[] preSum = new int[10001];
            int ret = 0;
            int sum = 0;
            for (int i = 0; i < A.length; i++) {
                sum += A[i];
                int left = sum % K;
                if (left < 0) {
                    left += K;
                }
                if (left == 0) {
                    ++ret;
                }
                ret += preSum[left];
                preSum[left]++;
            }
            return ret;
        }

    }
}
