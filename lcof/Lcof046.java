package com.zm.LeetCodeEx.lcof;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 面试题46. 把数字翻译成字符串
 * <p>
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= num < 2^31
 *
 * @author zm
 */
public class Lcof046 {
    public static void main(String[] args) {
        Lcof046 l046 = new Lcof046();
        System.out.println(JSON.toJSONString(l046.new Solution2().translateNum(18822)));
        System.out.println(JSON.toJSONString(l046.new Solution2().translateNum(12258)));
        System.out.println(JSON.toJSONString(l046.new Solution2().translateNum(605)));
    }

    /**
     * 直接dp
     */
    class Solution {
        public int translateNum(int num) {
            String numStr = String.valueOf(num);

            char[] cs = numStr.toCharArray();
            int len = cs.length;
            if (len <= 1) {
                return len;
            }
            if (len == 2) {
                return num > 25 ? 1 : 2;
            }
            int[] dp = new int[len];
            dp[0] = 1;
            dp[1] = (cs[0] == '0' || (cs[0] - '0') * 10 + (cs[1] - '0') > 25) ? 1 : 2;
            for (int i = 2; i < len; i++) {
                dp[i] = dp[i - 1] + (cs[i - 1] == '0' || ((cs[i - 1] - '0') * 10 + (cs[i] - '0') > 25) ? 0 : dp[i - 2]);
            }
            return dp[len - 1];
        }
    }

    /**
     * 尝试从尾部开始，用两个变量代替dp数组保存dp[i-1]和dp[i-2]
     */
    class Solution2 {
        public int translateNum(int num) {
            if (num < 10) {
                return 1;
            }
            if (num < 26) {
                return 2;
            }
            if (num < 100) {
                return 1;
            }
            int prePreRes = 1;
            int preRes = 1;
            int preNum = num % 10;
            num /= 10;
            int cur = num % 10;
            if (cur * 10 + preNum <= 25 && cur != 0) {
                preRes = 2;
            }
            preNum = cur;
            while (num > 0) {
                num /= 10;
                cur = num % 10;
                if (cur * 10 + preNum <= 25 && cur != 0) {
                    int temp = preRes;
                    preRes += prePreRes;
                    prePreRes = temp;
                } else {
                    prePreRes = preRes;
                }
                preNum = cur;
            }
            return preRes;
        }
    }
}
