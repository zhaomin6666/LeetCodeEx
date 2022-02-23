package com.zm.LeetCodeEx.weekcontest.contest_268_20211121;

import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 */
public class LEET5186 {
    public static void main(String[] args) {
        LEET5186 leet5186 = new LEET5186();
        RangeFreqQuery obj = leet5186.new RangeFreqQuery(new int[]{12, 33, 33, 33, 22, 2, 34, 33, 22, 12, 34, 56});
        System.out.println(obj.query(1, 2, 4));
        System.out.println(obj.query(9, 11, 33));
    }

    class RangeFreqQuery {
        HashMap<Integer, TreeMap<Integer, Integer>> table = new HashMap<>();
        int[] cnt = new int[10001];

        public RangeFreqQuery(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                TreeMap<Integer, Integer> tree = table.get(arr[i]);
                if (tree == null) {
                    tree = new TreeMap<>();
                    table.put(arr[i], tree);
                }
                tree.put(i, ++cnt[arr[i]]);
            }
        }

        public int query(int left, int right, int value) {
            TreeMap<Integer, Integer> treeMap = table.get(value);
            if (treeMap == null || treeMap.isEmpty()){
                return 0;
            }
            if (treeMap.floorEntry(right) == null || treeMap.ceilingEntry(left) == null){
                return 0;
            }
            return treeMap.floorEntry(right).getValue() - treeMap.ceilingEntry(left).getValue() + 1;
        }
    }
}
