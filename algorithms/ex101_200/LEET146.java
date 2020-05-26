package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 146. LRU缓存机制
 * <p>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 *  
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * LRUCache cache = new LRUCache(2); //缓存容量
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 * @author zm
 */

public class LEET146 {
    public static void main(String[] args) {
        LEET146 l146 = new LEET146();

        String functionStr = "[\"LRUCache\",\"put\",\"put\",\"get\",\"put\",\"get\",\"put\",\"get\",\"get\",\"get\"]";
        String paramStr = "[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]";
        //printResult(l146, functionStr, paramStr);

        printResult(l146, "[\"LRUCache\",\"put\",\"put\",\"put\",\"put\",\"get\",\"get\"]",
                "[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]");
    }

    private static void printResult(LEET146 l146, String functionStr, String paramStr) {
        JSONArray functions = JSON.parseArray(functionStr);
        JSONArray params = JSON.parseArray(paramStr);
        Integer[] rets = new Integer[functions.size()];
        LRUCache cache = l146.new LRUCache(params.getJSONArray(0).getInteger(0));
        for (int i = 1; i < functions.size(); i++) {
            String function = functions.getString(i);
            if ("put".equals(function)) {
                cache.put(params.getJSONArray(i).getInteger(0), params.getJSONArray(i).getInteger(1));
            } else {
                rets[i] = cache.get(params.getJSONArray(i).getInteger(0));
            }
        }
        System.out.println(Arrays.toString(rets));
    }

    class LRUCache {
        private HashMap<Integer, MyLinkedNode> map;
        private int maxEleCount;
        private int curEleCount = 0;
        private MyLinkedNode head = new MyLinkedNode(520, 1314);
        private MyLinkedNode tail = new MyLinkedNode(520, 1314);

        {
            head.next = tail;
            tail.pre = head;
        }

        public LRUCache(int capacity) {
            map = new HashMap<>(capacity);
            maxEleCount = capacity;
        }

        public int get(int key) {
            MyLinkedNode retTemp = map.get(key);
            if (retTemp == null) {
                return -1;
            }
            // 处理前后结点
            retTemp.pre.next = retTemp.next;
            retTemp.next.pre = retTemp.pre;
            // 移到头结点
            retTemp.next = head.next;
            head.next.pre = retTemp;
            head.next = retTemp;
            retTemp.pre = head;
            return retTemp.val;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                MyLinkedNode cur = map.get(key);
                // 处理前后结点
                MyLinkedNode preNode = cur.pre;
                preNode.next = cur.next;
                cur.pre = preNode;
                cur.next.pre = preNode;
                // 移到头结点
                cur.next = head.next;
                head.next.pre = cur;
                head.next = cur;
                cur.pre = head;
                cur.val = value;
            } else {
                if (curEleCount == maxEleCount) {
                    MyLinkedNode del = tail.pre;
                    tail.pre = del.pre;
                    del.pre.next = tail;
                    map.remove(del.key);
                    curEleCount--;
                }
                MyLinkedNode cur = new MyLinkedNode(value, key);
                map.put(key, cur);
                cur.next = head.next;
                head.next.pre = cur;
                head.next = cur;
                cur.pre = head;
                curEleCount++;
            }
        }

        private class MyLinkedNode {
            MyLinkedNode pre;
            MyLinkedNode next;
            int val;
            int key;

            private MyLinkedNode(int val, int key) {
                this.val = val;
                this.key = key;
            }
        }
    }
}
