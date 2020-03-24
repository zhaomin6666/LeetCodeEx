package com.zm.LeetCodeEx.algorithms;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 93. 复原IP地址
 * <p>
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * @author zm
 */
public class LEET093 {
    public static void main(String[] args) {
        LEET093 l091 = new LEET093();

        System.out.println(JSON.toJSONString(
                (l091.new Solution().restoreIpAddresses("10101010"))));
//        System.out.println(JSON.toJSONString(
//                (l091.new Solution().restoreIpAddresses("25525511115"))));
//        System.out.println(JSON.toJSONString(
//                (l091.new Solution().restoreIpAddresses("25525511135"))));
//        System.out.println(JSON.toJSONString(
//                (l091.new Solution().restoreIpAddresses("010010"))));
//        System.out.println(JSON.toJSONString(
//                (l091.new Solution().restoreIpAddresses(""))));
//        System.out.println(JSON.toJSONString(
//                (l091.new Solution().restoreIpAddresses("0000"))));

    }

    class Solution {
        private List<String> ret = new ArrayList<>();
        private LinkedList<String> result = new LinkedList<>();
        private String s;
        private int l;

        public List<String> restoreIpAddresses(String s) {
            this.s = s;
            this.l = s.length();
            if (l < 4) {
                return ret;
            }
            checkIp(0, 4);
            return ret;
        }

        private void checkIp(int used, int left) {
            if (left == 1) {
                String v = s.substring(used);
                if (!valid(v)) {
                    return;
                }
                result.add(v);
                ret.add(String.join(".", result));
                result.removeLast();
            } else {
                int start = Math.max(used + 1, l - 3 * (left - 1));
                int end = Math.min(used + 3, l - (left - 1));
                for (int i = start; i <= end; i++) {
                    String v = s.substring(used, i);
                    if (!valid(v)) {
                        return;
                    }
                    result.add(v);
                    checkIp(i, left - 1);
                    result.removeLast();
                }
            }
        }

        /**
         * 如果第一个数为0，那么只有可能是'0'满足，不然就需要<=255
         *
         * @param segment
         * @return
         */
        private boolean valid(String segment) {
            return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (segment.length() == 1);
        }

    }
}
