package com.zm.LeetCodeEx.weekcontest.contest_203_20200823;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LEET5497 {

    public static void main(String[] args) {
        LEET5497 leet5453 = new LEET5497();
        System.out.println(leet5453.new Solution().findLatestStep(new int[]{3,5,1,2,4},1));

    }

    class Solution {
        public int findLatestStep(int[] arr, int m) {
            int len = arr.length;
            UnionFind uf = new UnionFind(len+1);
            int ret = -1;
            for(int i = 0; i < len; i++){
                uf.union(arr[i]-1,arr[i]);
                if (uf.hasWeight(m+1)){
                    ret = i+1;
                }
            }
            return ret;
        }
    }

    class UnionFind {
        int[] ufarr;
        int[] weight;
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, Integer> weightCnt = new HashMap<>();

        public UnionFind(int size) {
            ufarr = new int[size];
            weight = new int[size];
            for (int i = 0; i < size; i++) {
                ufarr[i] = i;
                set.add(i);
            }
            Arrays.fill(weight, 1);
            weightCnt.put(1, size);
        }

        public int getRoot(int index) {
            while (ufarr[index] != index) {
                index = ufarr[index];
            }
            return index;
        }

        public void union(int c1, int c2) {
            int index1 = getRoot(c1);
            int index2 = getRoot(c2);
            if (weight[index1] >= weight[index2]) {
                ufarr[index2] = index1;
                set.remove(index2);
                weightCnt.put(weight[index1], weightCnt.get(weight[index1])-weight[index1]);
                weightCnt.put(weight[index2], weightCnt.get(weight[index2])-weight[index2]);
                weight[index1] += weight[index2];
                weightCnt.put(weight[index1], weightCnt.getOrDefault(weight[index1], 0) + weight[index1]);
            } else {
                ufarr[index1] = index2;
                set.remove(index1);
                weightCnt.put(weight[index1], weightCnt.get(weight[index1])-weight[index1]);
                weightCnt.put(weight[index2], weightCnt.get(weight[index2])-weight[index2]);
                weight[index2] += weight[index1];
                weightCnt.put(weight[index2], weightCnt.getOrDefault(weight[index2], 0) + weight[index2]);
            }
        }

        public boolean hasWeight(int target) {
            return weightCnt.containsKey(target) && weightCnt.get(target) > 0;
        }
    }
}
