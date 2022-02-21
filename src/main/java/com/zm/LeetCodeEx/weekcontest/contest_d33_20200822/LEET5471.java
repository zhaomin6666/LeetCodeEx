package com.zm.LeetCodeEx.weekcontest.contest_d33_20200822;

import java.util.HashSet;

public class LEET5471 {

    public static void main(String[] args) {
        LEET5471 leet5453 = new LEET5471();
        System.out.println(leet5453.new Solution().containsCycle(new char[][]{{'a','b','b'},
                {'b','z','b'},{'b','b','a'}}));

    }

    class Solution {
        char[][] grid;
        boolean[][] vis;
        public boolean containsCycle(char[][] grid) {
            this.grid = grid;
            vis = new boolean[grid.length][grid[0].length];
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++){
                    if(vis[i][j]){
                        continue;
                    }
                    int last = i*1000+j;
                    HashSet<Integer> set = new HashSet<>();
                    set.add(i*1000+j);
                    boolean flag = check(i,j,set,null,last);
                    if(flag){
                        return flag;
                    }
                }
            }
            return false;
        }

        public boolean check(int i, int j, HashSet<Integer> set, Integer lastlast, Integer last){
            vis[i][j] = true;
            if(i > 0){
                if(grid[i-1][j] == grid[i][j]){
                    int cur = (i-1)*1000+j;
                    if(lastlast == null || cur != lastlast){
                        if(set.contains(cur)){
                            return true;
                        }
                        boolean flag = check(i-1,j,set,last,cur);
                        if(flag){
                            return flag;
                        }
                    }
                }
            }
            if(j > 0){
                if(grid[i][j-1] == grid[i][j]){
                    int cur = i*1000+j-1;
                    if(lastlast == null || cur != lastlast){
                        if(set.contains(cur)){
                            return true;
                        }
                        boolean flag = check(i,j-1,set,last,cur);
                        if(flag){
                            return flag;
                        }
                    }
                }
            }
            if(i < grid.length-1){
                if(grid[i+1][j] == grid[i][j]){
                    int cur = (i+1)*1000+j;
                    if(lastlast == null || cur != lastlast){
                        if(set.contains(cur)){
                            return true;
                        }
                        boolean flag = check(i+1,j,set,last,cur);
                        if(flag){
                            return flag;
                        }
                    }
                }
            }
            if(j < grid[0].length-1){
                if(grid[i][j+1] == grid[i][j]){
                    int cur = i*1000+j+1;
                    if(lastlast == null || cur != lastlast){
                        if(set.contains(cur)){
                            return true;
                        }
                        boolean flag = check(i,j+1,set,last,cur);
                        if(flag){
                            return flag;
                        }
                    }
                }
            }
            return false;
        }
    }
}
