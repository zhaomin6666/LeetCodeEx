package com.zm.LeetCodeEx.competition.y2021spring;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 3. 魔塔游戏
 * <p>
 * 小扣当前位于魔塔游戏第一层，共有 N 个房间，编号为 0 ~ N-1。每个房间的补血道具/怪物对于血量影响记于数组 nums，其中正数表示道具补血数值，即血量增加对应数值；负数表示怪物造成伤害值，即血量减少对应数值；0 表示房间对血量无影响。
 * <p>
 * 小扣初始血量为 1，且无上限。假定小扣原计划按房间编号升序访问所有房间补血/打怪，为保证血量始终为正值，小扣需对房间访问顺序进行调整，每次仅能将一个怪物房间（负数的房间）调整至访问顺序末尾。请返回小扣最少需要调整几次，才能顺利访问所有房间。若调整顺序也无法访问完全部房间，请返回 -1。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,100,100,-250,-60,-140,-50,-50,100,150]
 * <p>
 * 输出：1
 * <p>
 * 解释：初始血量为 1。至少需要将 nums[3] 调整至访问顺序末尾以满足要求。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [-200,-300,400,0]
 * <p>
 * 输出：-1
 * <p>
 * 解释：调整访问顺序也无法完成全部房间的访问。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 */
public class LCP03 {

    public static void main(String[] args) {
        LCP03 LCP03 = new LCP03();
        System.out.println(LCP03.new Solution().magicTower(new int[]{100, 100, 100, -250, -60, -140, -51, -50, 100, 150}));
        System.out.println(LCP03.new Solution().magicTower(new int[]{-200, -300, 400, 0}));
    }

    class Solution {
        public int magicTower(int[] nums) {
            long blood = 1;
            int ret = 0;
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            List<Integer> moveList = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0) {
                    priorityQueue.add(nums[i]);
                }
                blood += nums[i];
                if (blood <= 0) {
                    if (priorityQueue.isEmpty()) {
                        return -1;
                    } else {
                        int moveRoom = priorityQueue.poll();
                        blood -= moveRoom;
                        moveList.add(moveRoom);
                        ret++;
                    }
                }
            }
            for (int moveRoom : moveList) {
                blood += moveRoom;
                if (blood <= 0) {
                    return -1;
                }
            }
            return ret;
        }
    }
}
