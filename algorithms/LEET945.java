package com.zm.LeetCodeEx.algorithms;

import java.util.Arrays;

/**
 * 945. 使数组唯一的最小增量
 * <p>
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * <p>
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 * <p>
 * 示例 1:
 * <p>
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 * 示例 2:
 * <p>
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 * 提示：
 * <p>
 * 0 <= A.length <= 40000
 * 0 <= A[i] < 40000
 *
 * @author zm
 */
public class LEET945 {
    public static void main(String[] args) {
        LEET945 l945 = new LEET945();
        //System.out.println((l945.new Solution4().minIncrementForUnique(new int[]{1, 2, 2})));
        //System.out.println((l945.new Solution4().minIncrementForUnique(new int[]{3, 2, 1, 2, 1, 7})));
        System.out.println((l945.new Solution4().minIncrementForUnique(new int[]{3, 2, 1, 2, 1, 3})));
    }

    /**
     * 用数组记录当前数字是否出现过
     */
    class Solution {
        public int minIncrementForUnique(int[] A) {
            int max = 0;
            for (int aItem : A) {
                max = Math.max(max, aItem);
            }
            int[] help = new int[max + A.length + 1];
            int ret = 0;
            for (int aItem : A) {
                int temp = aItem;
                while (help[temp] != 0) {
                    temp = help[temp];
                }
                help[temp] = temp + 1;
                ret += (temp - aItem);
            }
            return ret;
        }
    }


    /**
     * 计数，如果出现次数大于一次那么ans-count[i]，再出现一个没有出现的数就用ans+count[j]
     * 这样ans=所有未出现的数（最小的前n个）-所有重复的数（共n个）
     * 优化：加入最大值限制，最大值应该是数组中的最大数+数组长度（最坏情况有40000个40000）
     * 或可以加入最小值限制，如果数据都很大的话
     */
    class Solution2 {
        public int minIncrementForUnique(int[] A) {
            int[] count = new int[80000];
            int max = 0;
            int min = 0;
            for (int x : A) {
                count[x]++;
                max = Math.max(x, max);
                min = Math.min(x, min);
            }
            int ans = 0, taken = 0;

            for (int x = min; x < max + A.length; ++x) {
                if (count[x] >= 2) {
                    taken += count[x] - 1;
                    ans -= x * (count[x] - 1);
                } else if (taken > 0 && count[x] == 0) {
                    taken--;
                    ans += x;
                }
            }
            return ans;
        }
    }


    /**
     * 先进行排序，然后对于大于数组长度的数另外加在后面
     * <p>
     * 如1,1,1,4,5,6
     * 那么1,1,1这三个数中后面两个数是重复的，会从ans中减去。
     * 1,4中间隔着3个数，可以用来替换之前重复的两个数
     */
    class Solution3 {
        public int minIncrementForUnique(int[] A) {
            Arrays.sort(A);
            int ans = 0, taken = 0;

            for (int i = 1; i < A.length; ++i) {
                if (A[i - 1] == A[i]) {
                    taken++;
                    ans -= A[i];
                } else {
                    int give = Math.min(taken, A[i] - A[i - 1] - 1);
                    ans += give * (give + 1) / 2 + give * A[i - 1];
                    taken -= give;
                }
            }

            if (A.length > 0) {
                ans += taken * (taken + 1) / 2 + taken * A[A.length - 1];
            }
            return ans;
        }
    }

    /**
     * 尝试优化方法1
     * 使用类似于UF的方法，但是并没有把所有相同的结点去进行修改，只把递归到的结点进行了修改
     */
    class Solution4 {
        public int minIncrementForUnique(int[] A) {
            int max = 0;
            for (int aItem : A) {
                max = Math.max(max, aItem);
            }
            int[] help = new int[max + A.length + 1];
            int ret = 0;
            for (int aItem : A) {
                int res = getLast(help, aItem);
                ret += (res - aItem);
            }
            return ret;
        }

        private int getLast(int[] help, int temp) {
            int res;
            if (help[temp] != 0) {
                res = getLast(help, help[temp]);
            } else {
                res = temp;
            }
            help[temp] = res + 1;
            return res;
        }
    }
}
