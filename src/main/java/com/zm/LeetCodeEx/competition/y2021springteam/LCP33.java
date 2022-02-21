package com.zm.LeetCodeEx.competition.y2021springteam;

import java.util.PriorityQueue;

/**
 * LCP 33. 蓄水
 * <p>
 * 给定 N 个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第 i 个水缸配备的水桶容量记作 bucket[i]。小扣有以下两种操作：
 * <p>
 * 升级水桶：选择任意一个水桶，使其容量增加为 bucket[i]+1
 * 蓄水：将全部水桶接满水，倒入各自对应的水缸
 * 每个水缸对应最低蓄水量记作 vat[i]，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。
 * <p>
 * 注意：实际蓄水量 达到或超过 最低蓄水量，即完成蓄水要求。
 * <p>
 * 示例 1：
 * <p>
 * 输入：bucket = [1,3], vat = [6,8]
 * <p>
 * 输出：4
 * <p>
 * 解释：
 * 第 1 次操作升级 bucket[0]；
 * 第 2 ~ 4 次操作均选择蓄水，即可完成蓄水要求。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：bucket = [9,0,1], vat = [0,2,2]
 * <p>
 * 输出：3
 * <p>
 * 解释：
 * 第 1 次操作均选择升级 bucket[1]
 * 第 2~3 次操作选择蓄水，即可完成蓄水要求。
 * <p>
 * 提示：
 * <p>
 * 1 <= bucket.length == vat.length <= 100
 * 0 <= bucket[i], vat[i] <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/o8SXZn
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LCP33 {

    public static void main(String[] args) {
        System.out.println(new Solution().storeWater(new int[]{9, 0, 1}, new int[]{0, 2, 2}));
        System.out.println(new Solution().storeWater(new int[]{1, 3}, new int[]{6, 8}));
        System.out.println(new Solution2().storeWater(new int[]{16, 29, 42, 70, 42, 9},
                new int[]{89, 44, 50, 90, 94, 91}));
        System.out.println(new Solution().storeWater(new int[]{9, 0, 1}, new int[]{0, 2, 2}));
    }

    /**
     * 枚举操作倒水次数
     * 由于有vat有限制，所以倒水次数最大为10000
     */
    static class Solution {
        public int storeWater(int[] bucket, int[] vat) {
            int ret = 10001;
            int poorMax = 10000;
            boolean hasAboveZero = false;
            for (int vatItem : vat) {
                if (vatItem > 0) {
                    hasAboveZero = true;
                    break;
                }
            }
            if (!hasAboveZero) {
                return 0;
            }
            // 遍历倒水次数i
            for (int i = 1; i <= poorMax; i++) {
                if (i > ret) {
                    return ret;
                }
                int addCap = 0;
                // 计算每个桶需要加多少次水
                for (int j = 0; j < bucket.length; j++) {
                    int cap = vat[j] / i + (vat[j] % i == 0 ? 0 : 1);
                    addCap += Math.max(cap - bucket[j], 0);
                    if (addCap + i > ret) {
                        break;
                    }
                }
                ret = Math.min(ret, addCap + i);
            }
            return ret;
        }
    }

    /**
     * 优先队列记录需要倒的最多的次数
     */
    static class Solution2 {
        public int storeWater(int[] bucket, int[] vat) {
            int ret = 0;
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] == 0 && vat[i] != 0) {
                    bucket[i]++;
                    ret++;
                }
            }
            // 把每个桶当前需要到多少次记录在优先队列里，次数最多的拿出来给bucket加水，这样就可以减少统一的倒水次数
            PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> -Double.compare(o1[0], o2[0]));
            for (int i = 0; i < bucket.length; i++) {
                if (vat[i] != 0) {
                    pq.add(new double[]{vat[i] * 1.0 / bucket[i], i});
                }
            }
            if (pq.isEmpty()) {
                return 0;
            }
            int curAddWaterCount = 0;
            // 当一次都不加水的时候，ret就是最大的所需倒水次数
            ret += (int) Math.ceil(pq.peek()[0]);
            while (!pq.isEmpty() && pq.peek()[0] > 1) {
                double[] needAdd = pq.poll();
                int index = (int) needAdd[1];
                // 给bucket加水
                bucket[index]++;
                curAddWaterCount++;
                // 重新计算最大所需倒水次数
                pq.add(new double[]{vat[index] * 1.0 / bucket[index], index});
                // 当前加水次数只会增加，超过目前最好值就可以停止了
                if (curAddWaterCount > ret) {
                    break;
                }
                // 当前加水次数+最大倒水次数
                ret = Math.min(ret, curAddWaterCount + (int) Math.ceil(pq.peek()[0]));
            }
            return ret;
        }
    }
}
