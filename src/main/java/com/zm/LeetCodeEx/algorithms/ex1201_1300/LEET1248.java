package com.zm.LeetCodeEx.algorithms.ex1201_1300;

/**
 * 1248. 统计「优美子数组」
 * <p>
 * 给你一个整数数组 nums 和一个整数 k。
 * <p>
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * <p>
 * 请返回这个数组中「优美子数组」的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 * <p>
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 *
 * @author zm
 */
public class LEET1248 {
    public static void main(String[] args) {
        LEET1248 l1160 = new LEET1248();
        System.out.println(l1160.new Solution2().numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
        System.out.println(l1160.new Solution2().numberOfSubarrays(new int[]{2, 4, 6}, 1));
        System.out.println(l1160.new Solution2().numberOfSubarrays(new int[]{1, 1, 1}, 3));
        System.out.println(l1160.new Solution2().numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));


    }

    class Solution {
        public int numberOfSubarrays(int[] nums, int k) {
            int l = nums.length;
            int iMax = l - k;
            int i = -1;
            int j = -1;
            int numOfOdd = 0;
            int ret = 0;
            while (i <= iMax && j < l) {
                if (numOfOdd < k) {
                    if (++j >= l) {
                        break;
                    }
                    if ((nums[j] & 1) == 1) {
                        numOfOdd++;
                    }
                } else {
                    int nextEvenNum = 0;
                    while (++j < l && (nums[j] & 1) == 0) {
                        nextEvenNum++;
                    }
                    int preEvenNum = 0;
                    while ((nums[++i] & 1) == 0) {
                        preEvenNum++;
                    }
                    ret += (nextEvenNum + 1) * (preEvenNum + 1);
                }
            }
            return ret;
        }
    }


    /**
     * 用map[j]数组保存在j个奇数情况下指针走了多少步
     * 如nums:[2, 2, 2, 1, 2, 2, 1, 2, 2, 2]
     * map:[4,3,4]
     * 当i=6时，sum=2，此时满足条件，ret+=4（map[0]，第一个奇数前面的可能情况，0个2,1个2,2个2,3个2。
     * 即可能是[2221221],[221221],[21221],[1221]）
     * 每次i++，等于又加了一轮第一个奇数前面的可能情况（即[22212212],[2212212],[212212],[12212]）。
     * 若nums:[2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1]后面多加一个1
     * 那么map:[4,3,4,1]
     * 当i=10时，sum=3，ret+=3（map[1]，第二个奇数前面的可能情况，0个2,1个2,2个2。
     * 即可能是[2212221],[212221],[12221]）
     */
    class Solution2 {
        public int numberOfSubarrays(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int ans = 0, sum = 0;
            int[] map = new int[nums.length + 1];
            map[0] = 1;
            for (int i : nums) {
                sum += i & 1;
                map[sum]++;
                if (sum >= k) {
                    ans += map[sum - k];
                }
            }
            return ans;
        }
    }
}
