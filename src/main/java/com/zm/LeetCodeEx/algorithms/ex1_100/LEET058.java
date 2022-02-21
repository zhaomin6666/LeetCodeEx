package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

/**
 * 58. 最后一个单词的长度
 * <p>
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: "Hello World"
 * 输出: 5
 *
 * @author zm
 */
public class LEET058 {
    public static void main(String[] args) {
        LEET058 l058 = new LEET058();
        System.out.println(JSON.toJSONString(l058.lengthOfLastWord2(" a nsa")));
        System.out.println(JSON.toJSONString(l058.lengthOfLastWord2("Hello")));
        System.out.println(JSON.toJSONString(l058.lengthOfLastWord2("   ")));
    }

    public int lengthOfLastWord(String s) {
        int left = -1;
        int right = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (i + 1 < s.length() && s.charAt(i + 1) != ' ') {
                    left = i;
                }
            } else {
                right = i;
            }

        }
        return right - left;
    }

    public int lengthOfLastWord2(String s) {
        boolean isWord = false;
        int right = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (isWord) {
                    return right - i;
                }
            } else {
                if (!isWord) {
                    isWord = true;
                    right = i;
                }
            }
        }
        return right + 1;
    }

    public int lengthOfLastWord3(String s) {
        String[] sArray = s.split(" ");
        if (sArray.length > 0) {
            return sArray[sArray.length - 1].length();
        }
        return 0;
    }
}
