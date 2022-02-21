package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.Arrays;

/**
 * 115. 不同的子序列
 * <p>
 * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 * <p>
 * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "rabbbit", T = "rabbit"
 * 输出：3
 * 解释：
 * <p>
 * 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * <p>
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 示例 2：
 * <p>
 * 输入：S = "babgbag", T = "bag"
 * 输出：5
 * 解释：
 * <p>
 * 如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * <p>
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 * ..^  ^^
 * babgbag
 * ....^^^
 *
 * @author zm
 */
public class LEET115 {
    public static void main(String[] args) {
        LEET115 l115 = new LEET115();
        System.out.println(l115.new Solution4().numDistinct("rabbbit", "rabbit"));
        System.out.println(l115.new Solution4().numDistinct("babgbag", "bag"));
    }

    /**
     * 直接二维dp
     * dp[i][j] 代表T前i字符串可以由S前j字符串组成最多个数.
     * 由于有""的存在，做一需要多初始化第一行第一列
     */
    class Solution {
        public int numDistinct(String s, String t) {
            int ls = s.length();
            int lt = t.length();
            char[] sChar = s.toCharArray();
            char[] tChar = t.toCharArray();
            int[][] dp = new int[lt + 1][ls + 1];
            //初始化第一行
            for (int j = 0; j <= ls; j++) {
                dp[0][j] = 1;
            }

            for (int i = 1; i <= lt; i++) {
                for (int j = 1; j <= ls; j++) {
                    if (tChar[i - 1] == sChar[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
            return dp[lt][ls];
        }
    }

    /**
     * 二维dp转一维
     */
    class Solution2 {
        public int numDistinct(String s, String t) {
            int ls = s.length();
            int lt = t.length();
            if (ls == 0 && lt == 0) {
                return 1;
            }
            char[] sChar = s.toCharArray();
            char[] tChar = t.toCharArray();
            int[] dp = new int[ls + 1];
            //初始化第一行
            for (int j = 1; j <= ls; j++) {
                dp[j] = 1;
            }

            for (int i = 1; i <= lt; i++) {
                int pre = i == 1 ? 1 : 0;
                for (int j = 1; j <= ls; j++) {
                    int temp = dp[j];
                    if (tChar[i - 1] == sChar[j - 1]) {
                        dp[j] = dp[j - 1] + pre;
                    } else {
                        dp[j] = dp[j - 1];
                    }
                    pre = temp;
                }
            }
            return dp[ls];
        }
    }

    /**
     * 使用列主序+倒序避免使用临时值，而我们知道第一行都是1，所以可以直接dp[j+1] += dp[j]
     */
    class Solution3 {
        public int numDistinct(String s, String t) {
            int ls = s.length();
            int lt = t.length();
            char[] sChar = s.toCharArray();
            char[] tChar = t.toCharArray();
            int[] dp = new int[lt + 1];
            dp[0] = 1;
            for (int i = 0; i < ls; i++) {
                for (int j = lt - 1; j >= 0; j--) {
                    if (sChar[i] == tChar[j]) {
                        dp[j + 1] += dp[j];
                    }
                }
            }
            return dp[lt];
        }
    }

    /**
     * 评论中的优化
     *
     * @author 鱼向北游
     */
    class Solution4 {
        public int numDistinct(String s, String t) {
            // dp[0]表示空串
            int[] dp = new int[t.length() + 1];
            // dp[0]永远是1，因为不管S多长，都只能找到一个空串，与T相等
            dp[0] = 1;

            //t的字典
            int[] map = new int[128];
            Arrays.fill(map, -1);

            //从尾部遍历的时候可以遍历 next类似链表 无重复值时为-1，
            //有重复时例如从rabbit的b开始索引在map[b] = 2 next[2] 指向下一个b的索引为3
            // for (int j = t.length() - 1; j >= 0; j--) {
            //     if (t.charAt(j) == s.charAt(i)) {
            //        dp[j + 1] += dp[j];
            //     }
            // }
            //这段代码的寻址就可以从map[s.charAt(i)] 找到索引j 在用next[j] 一直找和 s.charAt(i)相等的字符 其他的就可以跳过了
            //所以这个代码的优化 关键要理解 上面的一维倒算
            //
            // 自己加的帮助理解：
            // 其他的不相等就可以跳过的原因可以看方法三中，只有相同的字符才做操作，不相同的直接跳过：
            // if (sChar[i] == tChar[j]) {
            //    dp[j + 1] += dp[j];
            // }
            // 这里的nexts数组就是为了把相同的字符储存成一个链，用map做辅助储存上一次出现该字符的地方
            // 如abcbdb中b的位置是1,3,5
            // nexts数组就为[-1,-1,-1,1,-1,3]
            // 这样在匹配第五的字符b的时候j=nexts[j]得到3，然后再得到1，直到-1退出这个小的循环。
            //
            //
            int[] nexts = new int[t.length()];
            for (int i = 0; i < t.length(); i++) {
                int c = t.charAt(i);
                nexts[i] = map[c];
                map[c] = i;
            }

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                for (int j = map[c]; j >= 0; j = nexts[j]) {
                    dp[j + 1] += dp[j];
                }
            }
            return dp[t.length()];
        }
    }
}
