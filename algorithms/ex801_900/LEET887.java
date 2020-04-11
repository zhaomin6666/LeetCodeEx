package com.zm.LeetCodeEx.algorithms.ex801_900;

import java.util.HashMap;
import java.util.Map;

/**
 * 887. 鸡蛋掉落
 * <p>
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * <p>
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * <p>
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * <p>
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * <p>
 * 你的目标是确切地知道 F 的值是多少。
 * <p>
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 * 示例 2：
 * <p>
 * 输入：K = 2, N = 6
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：K = 3, N = 14
 * 输出：4
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= K <= 100
 * 1 <= N <= 10000
 *
 * @author zm
 */
public class LEET887 {
    public static void main(String[] args) {
        LEET887 l887 = new LEET887();
        System.out.println(l887.new Solution().superEggDrop(2, 10000));
        System.out.println(l887.new Solution().superEggDrop(1, 2));
        System.out.println(l887.new Solution().superEggDrop(2, 6));
        System.out.println(l887.new Solution().superEggDrop(3, 14));
    }


    /**
     * dp[K,N] = dp[K,N-1]+ dp[K-1,N-1] + 1
     * 当一个鸡蛋时直接返回N
     * 两个鸡蛋时dp[2,10000] = 141 后面只会越来越少，所以长度设为142
     * <p>
     * ..移动次数..1..2..3..4..5..6..7..8..9..10.11.12.13.14.15...
     * 蛋数................................................
     * 1...........1..2..3..4..5..6..7..8..9..10.11.12.13.14.15...
     * 2...........1..3..6..10.15.21.28.36.45.55.66.78.91...
     * 3...........1..3..7..14.25.41...
     * 4...........1..3..7..15.30........................
     * 5...........1..3..7..15.31........................
     */
    class Solution {
        private static final int MAX_K_PLUS_ONE = 142;

        public int superEggDrop(int K, int N) {
            if (K == 1) {
                return N;
            }
            int[] array = new int[MAX_K_PLUS_ONE];
            for (int i = 0; i < MAX_K_PLUS_ONE; i++) {
                array[i] = i;
            }
            loop1:
            for (int i = 0; i < K - 1; i++) {
                int[] temp = new int[MAX_K_PLUS_ONE];
                for (int j = 1; j < MAX_K_PLUS_ONE; j++) {
                    temp[j] = temp[j - 1] + array[j - 1] + 1;
                    if (i == K - 2) {
                        if (temp[j] >= N) {
                            return j;
                        }
                    } else {
                        if (temp[j] >= N) {
                            array = temp;
                            continue loop1;
                        }
                    }
                }
                array = temp;
            }
            return 1;
        }
    }

    /**
     * 使用一维数组
     * ans 表示操作次数
     * dp[i] 表示当前操作次数下使用i个蛋能到达到的最大层数
     * 多一个蛋就可以在之前少一个蛋少一个次数的基础上的下一层扔那个多的鸡蛋
     * 如果这个鸡蛋碎了，那么就回到之前少一个蛋少一个次数的情况
     * 如果这个鸡蛋没碎，那么就回到少一个蛋相同次数的情况
     * 可以参照方法一的示例dp数组的每一列。
     */
    class Solution2 {
        public int superEggDrop(int K, int N) {
            int[] dp = new int[K + 1];
            int ans = 0; // 操作的次数
            while (dp[K] < N) {
                for (int i = K; i > 0; i--) {
                    // 从后往前计算
                    dp[i] = dp[i] + dp[i - 1] + 1;
                }
                ans++;
            }
            return ans;
        }
    }

    /**
     * dp(K,N)=1+min(max(dp(K−1,X−1),dp(K,N−X)))  (1≤X≤N)
     * <p>
     * 计算min(max(dp(K−1,X−1),dp(K,N−X)))使用二分法
     */
    class Solution3 {
        public int superEggDrop(int K, int N) {
            return dp(K, N);
        }

        Map<Integer, Integer> memo = new HashMap();

        public int dp(int K, int N) {
            if (!memo.containsKey(N * 100 + K)) {
                int ans;
                if (N == 0) {
                    ans = 0;
                } else if (K == 1) {
                    ans = N;
                } else {
                    int lo = 1, hi = N;
                    while (lo + 1 < hi) {
                        int x = (lo + hi) / 2;
                        int t1 = dp(K - 1, x - 1);
                        int t2 = dp(K, N - x);

                        if (t1 < t2) {
                            lo = x;
                        } else if (t1 > t2) {
                            hi = x;
                        } else {
                            lo = hi = x;
                        }
                    }

                    ans = 1 + Math.min(Math.max(dp(K - 1, lo - 1), dp(K, N - lo)),
                            Math.max(dp(K - 1, hi - 1), dp(K, N - hi)));
                }

                memo.put(N * 100 + K, ans);
            }

            return memo.get(N * 100 + K);
        }
    }
}
