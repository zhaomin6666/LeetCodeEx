package com.zm.LeetCodeEx.algorithms.ex701_800;

import com.alibaba.fastjson.JSON;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 705. 设计哈希集合
 * <p>
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * <p>
 * 实现 MyHashSet 类：
 * <p>
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * @author zm
 */
public class LEET706 {
    public static void main(String[] args) {
        MyHashMap myHashSet = new MyHashMap();
        String[] methods = new String[]{"MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"};
        int n = methods.length;
        int[][] values = new int[][]{{}, {1, 1}, {2, 2}, {1}, {3}, {2, 1}, {2}, {2}, {2}};
        int[] result = new int[n];
        for (int i = 1; i < n; i++) {
            if ("add".equals(methods[i])) {
                myHashSet.put(values[i][0], values[i][1]);
            } else if ("get".equals(methods[i])) {
                result[i] = myHashSet.get(values[i][0]);
            } else if ("remove".equals(methods[i])) {
                myHashSet.remove(values[i][0]);
            }
        }
        System.out.println(JSON.toJSONString(result));
    }


}

/**
 * 参考一下MyHashSET
 */
class MyHashMap {
    private static final int BASE = 769;
    private LinkedList[] data;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<int[]>();
        }
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int h = hash(key);
        Iterator<int[]> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            int[] element = iterator.next();
            if (element[0] == key) {
                element[1] = value;
                return;
            }
        }
        data[h].offerLast(new int[]{key, value});
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int h = hash(key);
        Iterator<int[]> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            int[] element = iterator.next();
            if (element[0] == key) {
                return element[1];
            }
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int h = hash(key);
        Iterator<int[]> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            int[] element = iterator.next();
            if (element[0] == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    private static int hash(int key) {
        return key % BASE;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
