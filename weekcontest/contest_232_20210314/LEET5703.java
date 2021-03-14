package com.zm.LeetCodeEx.weekcontest.contest_232_20210314;

import java.util.PriorityQueue;

/**
 * 5703. 最大平均通过率
 * <p>
 * 一所学校里有一些班级，每个班级里有一些学生，现在每个班都会进行一场期末考试。给你一个二维数组 classes，其中classes[i] = [passi, totali]，表示你提前知道了第i个班级总共有totali个学生，其中只有passi个学生可以通过考试。
 * <p>
 * 给你一个整数extraStudents，表示额外有extraStudents个聪明的学生，他们 一定能通过任何班级的期末考。你需要给这extraStudents个学生每人都安排一个班级，使得 所有班级的 平均通过率 最大。
 * <p>
 * 一个班级的通过率等于这个班级通过考试的学生人数除以这个班级的总人数。平均通过率是所有班级的通过率之和除以班级数目。
 * <p>
 * 请你返回在安排这 extraStudents 个学生去对应班级后的 最大平均通过率。与标准答案误差范围在10-5以内的结果都会视为正确结果。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：classes = [[1,2],[3,5],[2,2]], extraStudents = 2
 * 输出：0.78333
 * 解释：你可以将额外的两个学生都安排到第一个班级，平均通过率为 (3/4 + 3/5 + 2/2) / 3 = 0.78333 。
 * 示例 2：
 * <p>
 * 输入：classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
 * 输出：0.53485
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= classes.length <= 105
 * classes[i].length == 2
 * 1 <= passi <= totali <= 105
 * 1 <= extraStudents <= 105
 */
public class LEET5703 {

    public static void main(String[] args) {
        LEET5703 leet5703 = new LEET5703();
        System.out.println(leet5703.new Solution().maxAverageRatio(
                new int[][]{{1, 2}, {3, 5}, {2, 2}}, 2));

    }

    class Solution {
        public double maxAverageRatio(int[][] classes, int extraStudents) {
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                    (item1, item2) -> -Double.compare(
                            (item1[0] + 1) * 1.0 / (item1[1] + 1) - item1[0] * 1.0 / item1[1],
                            (item2[0] + 1) * 1.0 / (item2[1] + 1) - item2[0] * 1.0 / item2[1]));
            for (int[] classItem : classes) {
                pq.offer(classItem);
            }
            while (extraStudents > 0) {
                int[] classItem = pq.poll();
                classItem[0]++;
                classItem[1]++;
                pq.offer(classItem);
                extraStudents--;
            }
            double total = 0;
            while (!pq.isEmpty()) {
                int[] classItem = pq.poll();
                total += classItem[0] * 1.0 / classItem[1];
            }
            return total / classes.length;
        }
    }
}
