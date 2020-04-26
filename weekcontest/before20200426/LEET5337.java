package com.zm.LeetCodeEx.weekcontest.before20200426;

import java.util.*;

/**
 * 双周赛 2020年3月7日 5337. 每个元音包含偶数次的最长子字符串
 * <p>
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 * 示例 2：
 * <p>
 * 输入：s = "leetcodeisgreat"
 * 输出：5
 * 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 * 示例 3：
 * <p>
 * 输入：s = "bcbcbc"
 * 输出：6
 * 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母。
 *
 * @author zm
 */
public class LEET5337 {
    public static void main(String[] args) {
        LEET5337 l5337 = new LEET5337();
        System.out.println(l5337.new Solution().findTheLongestSubstring("eleetminicoworoep"));
        System.out.println(l5337.new Solution().findTheLongestSubstring("leetcodeisgreat"));
        System.out.println(l5337.new Solution().findTheLongestSubstring("bcbcbc"));
    }

    /**
     * 设定 a,e,i,o,u在为偶数时状态为0
     * 即aaeiou==>0b11110,a==>0b00001,aa==>0b00000,aeu==>0b10011
     * 遍历字符串，用map记录每新增一个字符的状态第一次新出现的位置，当拥有这个状态再遇到这个状态时，说明必定的未新增任何元音字母或新增了偶数个元音
     * 字母。例如如果是aa==>0b00000，那么只有可能是aauu/aapfg/aaiittoo...此类才有可能与aa处于相同状态，如果为aaa，那么此时状态
     * 就是0b00001，与a相同。
     * 用当前位置减去第一次出现的位置就可以获得中间满足要求的字符串。
     * 初始时所有字符都为0，非元音不影响状态。
     */
    class Solution {
        private char[] v = {'a', 'e', 'i', 'o', 'u'};

        public int findTheLongestSubstring(String s) {
            int[] cnt = new int[26];
            int L = s.length();

            Map<Integer, Integer> map = new HashMap<>();

            map.put(0, 0);
            int ret = 0;
            for (int i = 1; i <= L; i++) {
                int c = s.charAt(i - 1) - 'a';
                cnt[c]++;
                int key = getKey(cnt);
                if (map.containsKey(key)) {
                    int st = map.get(key);
                    if (i - st > ret) {
                        ret = i - st;
                    }
                } else {
                    map.put(key, i);
                }
            }
            return ret;
        }

        private int getKey(int[] cnt) {
            int key = 0;
            for (int i = 0; i < 5; i++) {
                int c = cnt[v[i] - 'a'];
                key |= (1 << i) * (c % 2);
            }
            return key;
        }
    }
}
