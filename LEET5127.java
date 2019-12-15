package com.zm.LeetCodeEx;

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
 *
 * @author zm
 */
public class LEET5127 {
    public static void main(String[] args) {
        LEET5127 l5127 = new LEET5127();
        int[][] intervals = {{1, 4}, {3, 6}, {2, 8}, {4, 5}};
        int[][] intervals2 = {{34335, 39239}, {15875, 91969}, {29673, 66453}, {53548, 69161}, {40618, 93111}};
        System.out.println(l5127.removeCoveredIntervals(intervals));
        System.out.println(l5127.removeCoveredIntervals(intervals2));
    }

    public int removeCoveredIntervals(int[][] intervals) {
        int ret = intervals.length;
        boolean[] indexes = new boolean[intervals.length];
        for (int i = 1; i < intervals.length; i++) {
            for (int j = 0; j < i; j++) {
                if (!indexes[j]) {
                    int check = isCoveredInterval(intervals[i], intervals[j]);
                    if (check == -1) {
                        indexes[i] = true;
                        ret--;
                        break;
                    } else if (check == 1) {
                        indexes[j] = true;
                        ret--;

                    }
                }
            }
        }
        return ret;
    }

    public int isCoveredInterval(int[] first, int[] second) {
        if (first[0] <= second[0] && first[1] >= second[1]) {
            return 1;
        } else if (first[0] >= second[0] && first[1] <= second[1]) {
            return -1;
        }
        return 0;
    }
}
