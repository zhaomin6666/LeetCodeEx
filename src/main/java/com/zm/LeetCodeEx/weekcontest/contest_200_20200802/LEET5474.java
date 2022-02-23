package com.zm.LeetCodeEx.weekcontest.contest_200_20200802;

public class LEET5474 {

    public static void main(String[] args) {
        LEET5474 leet5453 = new LEET5474();
        System.out.println(leet5453.new Solution().minSwaps(new int[][]{{0, 0, 1}, {1, 1, 0}, {1, 0, 0}}));

    }

    class Solution {
        public int minSwaps(int[][] grid) {
            int[] rowIndex = new int[grid.length];
            for (int i = 0; i < grid.length; i++) {
                rowIndex[i] = i;
            }
            int ret = 0;
            for (int i = 0; i < grid.length -1; i++) {
                // 需要找一行后面有 grid.length - i - 1 个0
                boolean findflag = false;
                for (int j = 0; j < grid.length; j++) {
                    if (rowIndex[j] >= i){
                        boolean flag = true;
                        for (int k = i + 1; k < grid.length; k++) {
                            if (grid[j][k] != 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            if (rowIndex[j] != i ){
                                ret += rowIndex[j] - i;
                                for (int k = 0; k < grid.length; k++) {
                                    if (rowIndex[k] >= i && rowIndex[k] < rowIndex[j]) {
                                        rowIndex[k]++;
                                    }
                                }
                                rowIndex[j] = i;
                            }
                            findflag = true;
                            break;
                        }
                    }
                }
                if (!findflag){
                    return -1;
                }
            }
            return ret;
        }
    }
}
