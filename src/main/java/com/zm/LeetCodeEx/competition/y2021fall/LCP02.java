package com.zm.LeetCodeEx.competition.y2021fall;

import java.util.Arrays;

/**
 * 2. 心算挑战
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
 * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 * <p>
 * 示例 1：
 * <p>
 * 输入：cards = [1,2,8,9], cnt = 3
 * <p>
 * 输出：18
 * <p>
 * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 * <p>
 * 示例 2：
 * <p>
 * 输入：cards = [3,3,1], cnt = 1
 * <p>
 * 输出：0
 * <p>
 * 解释：不存在获取有效得分的卡牌方案。
 * <p>
 * 提示：
 * <p>
 * 1 <= cnt <= cards.length <= 10^5
 * 1 <= cards[i] <= 1000
 */
public class LCP02 {

    public static void main(String[] args) {
        LCP02 LCP02 = new LCP02();
        System.out.println(LCP02.new Solution().maxmiumScore(new int[]{1, 2, 8, 9}, 3));
        System.out.println(LCP02.new Solution().maxmiumScore(new int[]{3, 3, 1}, 1));
        System.out.println(LCP02.new Solution().maxmiumScore(new int[]{3, 3, 2, 4}, 2));
        System.out.println(LCP02.new Solution().maxmiumScore(new int[]{1, 2, 3, 4, 5}, 1));
        System.out.println(LCP02.new Solution().maxmiumScore(new int[]{1, 2, 3, 4, 5}, 2));
        System.out.println(LCP02.new Solution().maxmiumScore(new int[]{1, 2, 3, 4, 5}, 3));
        System.out.println(LCP02.new Solution().maxmiumScore(new int[]{1, 2, 3, 4, 5}, 4));
        System.out.println(LCP02.new Solution().maxmiumScore(new int[]{1, 2, 3, 4, 5}, 5));
        System.out.println(LCP02.new Solution().maxmiumScore(new int[]{3, 3, 2, 4}, 2));
    }

    class Solution {
        public int maxmiumScore(int[] cards, int cnt) {
            Arrays.sort(cards);
            int[] oddArr = new int[cards.length];
            int[] evenArr = new int[cards.length];
            int oddEnd = 0;
            int evenEnd = 0;
            for (int i = cards.length - 1; i >= 0; i--) {
                if (cards[i] % 2 == 0) {
                    evenArr[evenEnd] = cards[i];
                    evenEnd++;
                } else {
                    oddArr[oddEnd] = cards[i];
                    oddEnd++;
                }
            }
            int oddIndex = 0;
            int evenIndex = 0;
            int sum = 0;
            if (cnt % 2 == 1) {
                if (evenEnd == 0) {
                    return 0;
                }
                cnt--;
                sum += evenArr[0];
                evenIndex++;
            }
            int ret = getSum(oddArr, evenArr, oddIndex, evenIndex, oddEnd, evenEnd, cnt);
            if (ret == -1) {
                return 0;
            }
            return sum + ret;
        }

        private int getSum(int[] oddArr, int[] evenArr, int oddIndex, int evenIndex, int oddEnd, int evenEnd, int cnt) {
            if (cnt == 0) {
                return 0;
            }
            if (oddIndex + 1 >= oddEnd && evenIndex + 1 >= evenEnd) {
                return -1;
            }
            if (oddIndex + 1 >= oddEnd) {
                int sum = 0;
                while (cnt > 0) {
                    if (evenIndex >= evenEnd) {
                        return -1;
                    }
                    sum += evenArr[evenIndex++];
                    cnt--;
                }
                return sum;
            } else if (evenIndex + 1 >= evenEnd) {
                int sum = 0;
                while (cnt > 0) {
                    if (oddIndex >= oddEnd) {
                        return -1;
                    }
                    sum += oddArr[oddIndex++];
                    cnt--;
                }
                return sum;
            } else {
                int checkOddSum = oddArr[oddIndex] + oddArr[oddIndex + 1];
                int checkEvenSum = evenArr[evenIndex] + evenArr[evenIndex + 1];
                int curSum = 0;
                if (checkOddSum > checkEvenSum) {
                    curSum = oddArr[oddIndex++] + oddArr[oddIndex++];
                } else {
                    curSum = evenArr[evenIndex++] + evenArr[evenIndex++];
                }
                int ret = getSum(oddArr, evenArr, oddIndex, evenIndex, oddEnd, evenEnd, cnt - 2);
                if (ret == -1) {
                    return -1;
                }
                return curSum + ret;
            }
        }
    }
}
