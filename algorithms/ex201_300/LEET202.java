package com.zm.LeetCodeEx.algorithms.ex201_300;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * <p>
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * <p>
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 * <p>
 * 示例：
 * <p>
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 *
 * @author zm
 */
public class LEET202 {
    public static void main(String[] args) {
        LEET202 l202 = new LEET202();
        System.out.println(l202.new Solution().isHappy(7));
        System.out.println(l202.new Solution().isHappy(18));
        System.out.println(l202.new Solution().isHappy(19));
    }


    /**
     * 时间复杂度 O(log n)
     * 空间复杂度 O(log n)
     */
    class Solution {
        public boolean isHappy(int n) {
            HashSet<Integer> set = new HashSet<>();
            while (!set.contains(n)) {
                set.add(n);
                int sum = 0;
                while (n > 0) {
                    int digit = n % 10;
                    sum += digit * digit;
                    n = n / 10;
                }
                if (sum == 1) {
                    return true;
                }
                n = sum;
            }
            return false;
        }
    }


    /**
     * 时间复杂度 O(log n)
     * 空间复杂度 O(1)
     */
    class Solution2 {
        public boolean isHappy(int n) {
            int slow = n;
            int fast = getNext(n);
            // 因为1的下一个还是1，所以这里不用担心fast会跳过1而错过
            while (fast != 1 && fast != slow) {
                slow = getNext(slow);
                fast = getNext(getNext(fast));
            }
            // 最后当slow=fast的时候也要判断一下时候是1的循环
            return fast == 1;
        }

        private int getNext(int n) {
            int sum = 0;
            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n = n / 10;
            }
            return sum;
        }
    }

    /**
     * 根据我们的探索，我们猜测会有以下三种可能。
     * <p>
     * 最终会得到 11。
     * 最终会进入循环。
     * 值会越来越大，最后接近无穷大。
     * 第三个情况比较难以检测和处理。我们怎么知道它会继续变大，而不是最终得到 11 呢？我们可以仔细想一想，每一位数的最大数字的下一位数是多少。
     * <p>
     * Digits	Largest	         Next
     * 1	    9	             81
     * 2	    99  	         162
     * 3	    999 	         243
     * 4	    9999	         324
     * 13   	9999999999999	 1053
     * 对于 33 位数的数字，它不可能大于 243243。这意味着它要么被困在 243243 以下的循环内，要么跌到 11。
     * 44 位或 44 位以上的数字在每一步都会丢失一位，直到降到 33 位为止。
     * 所以我们知道，最坏的情况下，算法可能会在 243243 以下的所有数字上循环，然后回到它已经到过的一个循环或者回到 11。
     * 但它不会无限期地进行下去，所以我们排除第三种选择。
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度：O(log n)。和上面一样。
     * 空间复杂度：O(1)O(1)，我们没有保留我们所遇到的数字的历史记录。硬编码哈希集的大小是固定的。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution3 {
        private Set<Integer> cycleMembers =
                new HashSet<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));

        public int getNext(int n) {
            int totalSum = 0;
            while (n > 0) {
                int d = n % 10;
                n = n / 10;
                totalSum += d * d;
            }
            return totalSum;
        }


        public boolean isHappy(int n) {
            while (n != 1 && !cycleMembers.contains(n)) {
                n = getNext(n);
            }
            return n == 1;
        }
    }
}
