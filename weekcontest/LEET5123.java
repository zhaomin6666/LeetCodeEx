package com.zm.LeetCodeEx.weekcontest;

import java.util.ArrayList;
import java.util.List;

/**
 * 双周赛 2019年12月14日  5123. 字母组合迭代器
 * 请你设计一个迭代器类，包括以下内容：
 * <p>
 * 一个构造函数，输入参数包括：一个 有序且字符唯一 的字符串 characters（该字符串只包含小写英文字母）和一个数字 combinationLength 。
 * 函数 next() ，按 字典序 返回长度为 combinationLength 的下一个字母组合。
 * 函数 hasNext() ，只有存在长度为 combinationLength 的下一个字母组合时，才返回 True；否则，返回 False。
 *  
 * <p>
 * 示例：
 * <p>
 * CombinationIterator iterator = new CombinationIterator("abc", 2); // 创建迭代器 iterator
 * <p>
 * iterator.next(); // 返回 "ab"
 * iterator.hasNext(); // 返回 true
 * iterator.next(); // 返回 "ac"
 * iterator.hasNext(); // 返回 true
 * iterator.next(); // 返回 "bc"
 * iterator.hasNext(); // 返回 false
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= combinationLength <= characters.length <= 15
 * 每组测试数据最多包含 10^4 次函数调用。
 * 题目保证每次调用函数 next 时都存在下一个字母组合。
 *
 * <p>
 * 测试用例：
 * ["CombinationIterator","next","hasNext","next","hasNext","next","hasNext"]
 * [["abc",2],[],[],[],[],[],[]]
 *
 * @author zm
 */
public class LEET5123 {
    public static void main(String[] args) {
        CombinationIterator combinationIterator = new LEET5123().new CombinationIterator("abc", 2);
        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
    }


    class CombinationIterator {
        private List<List<Character>> res;
        private int max;
        private int cur = 0;
        private int combinationLength;

        CombinationIterator(String characters, int combinationLength) {
            this.combinationLength = combinationLength;
            res = permute(characters.toCharArray());
            max = res.size();
        }

        String next() {
            StringBuilder sb = new StringBuilder();
            List<Character> retList = res.get(cur++);
            for (Character c : retList) {
                sb.append(c);
            }
            return sb.toString();
        }

        boolean hasNext() {
            return cur < max;
        }

        private List<List<Character>> permute(char[] chars) {
            List<List<Character>> res = new ArrayList<>();
            backtrack(res, chars, new ArrayList<>(), 0);
            return res;

        }

        private void backtrack(List<List<Character>> res, char[] chars, ArrayList<Character> tmp, int start) {
            if (tmp.size() == combinationLength) {
                res.add(new ArrayList<>(tmp));
                return;
            }
            for (int i = start; i < chars.length; i++) {
                tmp.add(chars[i]);
                backtrack(res, chars, tmp, i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
