package com.zm.LeetCodeEx;

import java.util.ArrayList;
import java.util.List;

/**
 * 双周赛 2019年12月14日  5127. 删除被覆盖区间
 * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
 * <p>
 * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
 * <p>
 * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
 * <p>
 * 示例：
 * <p>
 * 输入：intervals = [[1,4],[3,6],[2,8]]
 * 输出：2
 * 解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
 * <p>
 * 提示：​​​​​​
 * 1 <= intervals.length <= 1000
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * 对于所有的 i != j：intervals[i] != intervals[j]
 * <p>
 * 测试用例：
 * ["CombinationIterator","next","hasNext","next","hasNext","next","hasNext"]
 * [["abc",2],[],[],[],[],[],[]]
 *
 * @author zm
 */
public class LEET5123 {
    public static void main(String[] args) {
        CombinationIterator combinationIterator = new CombinationIterator("abc", 2);
        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
    }
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
