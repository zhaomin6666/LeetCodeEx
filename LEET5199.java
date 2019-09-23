package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 周赛 2019-9-22
 * 交换字符串中的元素
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * <p>
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * <p>
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * 示例 2：
 * <p>
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * 示例 3：
 * <p>
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 *
 * @author zm
 */
public class LEET5199 {
    public static void main(String[] args) {
        LEET5199 l5199 = new LEET5199();
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>();
        System.out.println(l5199.swap(s, 1, 2));

        String ret = l5199.smallestStringWithSwaps(s, pairs);
        System.out.println(JSON.toJSONString(ret));
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        for (int i = 0; i < pairs.size(); i++) {
            int a = pairs.get(i).get(0);
            int b = pairs.get(i).get(1);
            if (s.charAt(a) > s.charAt(b)) {
                s = swap(s, a, b);
                i = 0;
            }
        }
        return "";
    }

    private String swap(String s, int a, int b) {
        char[] chars = s.toCharArray();
        char temp = chars[b];
        chars[b] = chars[a];
        chars[a] = temp;
        return String.valueOf(chars);
    }
}
