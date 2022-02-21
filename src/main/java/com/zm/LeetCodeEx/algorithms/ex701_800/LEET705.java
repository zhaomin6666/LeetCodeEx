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
public class LEET705 {
    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        String[] methods = new String[]{"MyHashSet", "add", "add", "contains", "contains", "add",
                "contains", "remove", "contains"};
        int n = methods.length;
        int[][] values = new int[][]{{}, {1}, {2}, {1}, {3}, {2}, {2}, {2}, {2}};
        Boolean[] result = new Boolean[n];
        for (int i = 1; i < n; i++) {
            if ("add".equals(methods[i])) {
                myHashSet.add(values[i][0]);
            } else if ("contains".equals(methods[i])) {
                result[i] = myHashSet.contains(values[i][0]);
            } else if ("remove".equals(methods[i])) {
                myHashSet.remove(values[i][0]);
            }
        }
        System.out.println(JSON.toJSONString(result));
    }


}

/**
 * 里面应该用链表的。。但是因为知道总数是10^6。。写成int[1000001]也行啊。。
 */
class MyHashSet {
    private final static int SEG = 1000;
    private boolean[][] myArrays;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        myArrays = new boolean[SEG][SEG];
    }

    public void add(int key) {
        int[] keys = getKeys(key);
        myArrays[keys[0]][keys[1]] = true;
    }

    public void remove(int key) {
        int[] keys = getKeys(key);
        myArrays[keys[0]][keys[1]] = false;
    }

    private int[] getKeys(int key) {
        return new int[]{key / SEG, key % 1000};
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int[] keys = getKeys(key);
        return myArrays[keys[0]][keys[1]];
    }
}

/**
 * 官方题解
 * <p>
 * 使用质数来尽可能避免哈希冲突并使用链表储存
 */
class MyHashSet2 {
    private static final int BASE = 769;
    private LinkedList[] data;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet2() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return;
            }
        }
        data[h].offerLast(key);
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }
}
