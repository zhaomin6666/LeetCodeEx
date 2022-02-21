package com.zm.LeetCodeEx.weekcontest.contest_268_20211121;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LEET5933 {
    public static void main(String[] args) {
        LEET5933 leet5931 = new LEET5933();
        System.out.println(leet5931.new Solution().kMirror(7, 17));
    }

    class Solution {
        public long kMirror(int k, int n) {
            long sum = 0;
            int length = 1;
            Long[] arrByLength = null;
            int index = 0;
            while (n > 0) {
                if (arrByLength == null) {
                    arrByLength = generateArr(length++);
                }
                boolean got = false;
                while (index < arrByLength.length) {
                    boolean c = checkK(arrByLength[index], k);
                    if (c) {
                        sum += arrByLength[index];
                        System.out.println(arrByLength[index]);
                        got = true;
                        index++;
                        break;
                    }
                    index++;
                }
                if (!got) {
                    arrByLength = null;
                    n++;
                    index = 0;
                }
                n--;
            }
            return sum;
        }

        private Long[] generateArr(int length) {
            List<Long> retList = new ArrayList<>();
            doG(length, retList, "", "", 1);
            return retList.toArray(new Long[retList.size()]);
        }

        private void doG(int length, List<Long> retList, String pre, String end, int start) {
            if (length == 0) {
                retList.add(Long.valueOf(pre + end));
            } else if (length == 1) {
                for (int i = start; i < 10; i++) {
                    retList.add(Long.valueOf(pre + i + end));
                }
            } else {
                for (int i = start; i < 10; i++) {
                    doG(length - 2, retList, pre + i, i + end, 0);
                }
            }
        }

        private boolean checkK(Long num, int k) {
            StringBuilder sb = new StringBuilder();
            while (num > 0) {
                sb.append(num % k);
                num /= k;
            }
            char[] cs = sb.toString().toCharArray();
            for (int i = 0; i < cs.length / 2; i++) {
                if (cs[i] != cs[cs.length - i - 1]) {
                    return false;
                }
            }
            return true;
        }
    }
}
