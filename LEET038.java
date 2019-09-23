package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

/**
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 * <p>
 * 注意：整数顺序将表示为一个字符串。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "1"
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "1211"
 *
 * @author zm
 */
public class LEET038 {
    public static void main(String[] args) {
        LEET038 l038 = new LEET038();
        int n = 6;
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            JSON.toJSONString(l038.countAndSay(n));
        }
        long t2 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            JSON.toJSONString(l038.countAndSay2(n));
        }
        long t3 = System.currentTimeMillis();
        System.out.println(t2 - t1); // 2319
        System.out.println(t3 - t2); // 1614
        System.out.println(JSON.toJSONString(l038.countAndSay(n)));
        System.out.println(JSON.toJSONString(l038.countAndSay2(n)));
    }

    /**
     * 使用两个字符串，没有必要
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String s1 = "1";
        String s2 = "";
        boolean flag = true;
        for (int i = 1; i < n; i++) {
            if (flag) {
                s2 = countAndSay(s1);
            } else {
                s1 = countAndSay(s2);
            }
            flag = !flag;
        }
        if (flag) {
            return s1;
        }
        return s2;
    }

    private String countAndSay(String s1) {
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        for (int i = 0; i < s1.length(); i++) {
            if (i < s1.length() - 1 && s1.charAt(i) == s1.charAt(i + 1)) {
                cnt++;
            } else {
                sb.append(cnt).append(s1.charAt(i));
                cnt = 1;
            }
        }
        return sb.toString();
    }

    /**
     * 使用了一个pre来记录上一个字符，而不是方法1中的判断下一个字符，减少判断条件里面的运算次数
     *
     * @param n
     * @return
     */
    public String countAndSay2(int n) {
        String str = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            char pre = str.charAt(0);
            int cnt = 1;
            for (int j = 1; j < str.length(); j++) {
                char c = str.charAt(j);
                if (c == pre) {
                    cnt++;
                } else {
                    sb.append(cnt).append(pre);
                    pre = c;
                    cnt = 1;
                }
            }
            sb.append(cnt).append(pre);
            str = sb.toString();
        }
        return str;
    }
}
