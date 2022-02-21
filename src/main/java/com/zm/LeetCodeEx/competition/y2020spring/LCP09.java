package com.zm.LeetCodeEx.competition.y2020spring;

import java.util.LinkedList;
import java.util.Queue;

public class LCP09 {

    public static void main(String[] args) {
        LCP09 LCP09 = new LCP09();
        System.out.println(LCP09.new Solution().minJump(new int[]{2, 5, 1, 1, 1, 1}));
        System.out.println(LCP09.new Solution().minJump(
                new int[]{3, 7, 6, 1, 4, 3, 7, 8, 1, 2, 8, 5, 9, 8, 3, 2, 7, 5, 1, 1}));
    }

    class Solution {
        public int minJump(int[] jump) {
            int l = jump.length;
            if (jump[0] >= l) {
                return 1;
            }
            boolean[] jumped = new boolean[l];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(jump[0]);
            jumped[0] = true;
            jumped[jump[0]] = true;
            int maxJumped = 0;
            int cnt = 2;
            while (!queue.isEmpty()) {
                int s = queue.size();
                for (int i = 0; i < s; i++) {
                    int index = queue.poll();
                    if (index + jump[index] >= l) {
                        return cnt;
                    }

                    for (int j = maxJumped + 1; j < index; j++) {
                        if (!jumped[j]) {
                            queue.add(j);
                            jumped[j] = true;
                        }
                    }
                    if (!jumped[index + jump[index]]) {
                        queue.add(index + jump[index]);
                        jumped[index + jump[index]] = true;
                    }
                    maxJumped = Math.max(maxJumped, index);
                }
                cnt++;
            }
            return cnt;
        }
    }
}
