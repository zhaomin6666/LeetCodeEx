package com.zm.LeetCodeEx.lcof;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.LinkedList;

/**
 * 面试题59 - II. 队列的最大值
 * <p>
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 * <p>
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 *
 * @author zm
 */
public class Lcof059 {
    public static void main(String[] args) {
        Lcof059 l059 = new Lcof059();

        String s1 = "[\"MaxQueue\",\"max_value\",\"pop_front\",\"max_value\",\"push_back\",\"max_value\",\"pop_front\",\"max_value\",\"pop_front\",\"push_back\",\"pop_front\",\"pop_front\",\"pop_front\",\"push_back\",\"pop_front\",\"max_value\",\"pop_front\",\"max_value\",\"push_back\",\"push_back\",\"max_value\",\"push_back\",\"max_value\",\"max_value\",\"max_value\",\"push_back\",\"pop_front\",\"max_value\",\"push_back\",\"max_value\",\"max_value\",\"max_value\",\"pop_front\",\"push_back\",\"push_back\",\"push_back\",\"push_back\",\"pop_front\",\"pop_front\",\"max_value\",\"pop_front\",\"pop_front\",\"max_value\",\"push_back\",\"push_back\",\"pop_front\",\"push_back\",\"push_back\",\"push_back\",\"push_back\",\"pop_front\",\"max_value\",\"push_back\",\"max_value\",\"max_value\",\"pop_front\",\"max_value\",\"max_value\",\"max_value\",\"push_back\",\"pop_front\",\"push_back\",\"pop_front\",\"max_value\",\"max_value\",\"max_value\",\"push_back\",\"pop_front\",\"push_back\",\"push_back\",\"push_back\",\"pop_front\",\"max_value\",\"pop_front\",\"max_value\",\"max_value\",\"max_value\",\"pop_front\",\"push_back\",\"pop_front\",\"push_back\",\"push_back\",\"pop_front\",\"push_back\",\"pop_front\",\"push_back\",\"pop_front\",\"pop_front\",\"push_back\",\"pop_front\",\"pop_front\",\"pop_front\",\"push_back\",\"push_back\",\"max_value\",\"push_back\",\"pop_front\",\"push_back\",\"push_back\",\"pop_front\"]";
        String s2 = "[[],[],[],[],[46],[],[],[],[],[868],[],[],[],[525],[],[],[],[],[123],[646],[],[229],[],[],[],[871],[],[],[285],[],[],[],[],[45],[140],[837],[545],[],[],[],[],[],[],[561],[237],[],[633],[98],[806],[717],[],[],[186],[],[],[],[],[],[],[268],[],[29],[],[],[],[],[866],[],[239],[3],[850],[],[],[],[],[],[],[],[310],[],[674],[770],[],[525],[],[425],[],[],[720],[],[],[],[373],[411],[],[831],[],[765],[701],[]]";
        //printResult(l059, s1, s2);
        String s3 = "[\"MaxQueue\",\"pop_front\",\"pop_front\",\"pop_front\",\"pop_front\",\"pop_front\",\"push_back\",\"max_value\",\"push_back\",\"max_value\"]\n";
        String s4 = "[[],[],[],[],[],[],[15],[],[9],[]]";
        printResult(l059, s3, s4);


    }

    private static void printResult(Lcof059 l059, String s1, String s2) {
        MaxQueue obj = l059.new MaxQueue();
        JSONArray a1 = JSON.parseArray(s1);
        JSONArray a2 = JSON.parseArray(s2);
        Object[][] ret = new Object[a1.size()][1];
        for (int i = 0; i < a1.size(); i++) {
            if (a1.getString(i).equals("max_value")) {
                ret[i] = new Integer[]{obj.max_value()};
            } else if (a1.getString(i).equals("pop_front")) {
                ret[i] = new Integer[]{obj.pop_front()};
            } else {
                if (a1.getString(i).equals("push_back")) {
                    obj.push_back(a2.getJSONArray(i).getInteger(0));
                }
                ret[i] = new String[]{null};
            }
        }
        System.out.println(JSON.toJSONString(ret));
    }

    class MaxQueue {
        private LinkedList<Integer> queue;
        private LinkedList<Integer> deque;

        public MaxQueue() {
            queue = new LinkedList<>();
            deque = new LinkedList<>();
        }

        public int max_value() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.peekFirst();
        }

        public void push_back(int value) {
            queue.addLast(value);
            while (!deque.isEmpty() && deque.peekLast() < value) {
                deque.removeLast();
            }
            deque.addLast(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            int ret = queue.removeFirst();
            if (ret == deque.peekFirst()) {
                deque.removeFirst();
            }
            return ret;
        }
    }
}
