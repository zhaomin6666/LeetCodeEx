package com.zm.LeetCodeEx.weekcontest.contest_193_20200614;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 周赛 2020年6月14日
 * <p>
 * 5188. 树节点的第 K 个祖先
 * <p>
 * 给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。
 * <p>
 * 请你设计并实现 getKthAncestor(int node, int k) 函数，函数返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。
 * <p>
 * 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：
 * ["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
 * [[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]
 * <p>
 * 输出：
 * [null,1,0,-1]
 * <p>
 * 解释：
 * TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
 * <p>
 * treeAncestor.getKthAncestor(3, 1);  // 返回 1 ，它是 3 的父节点
 * treeAncestor.getKthAncestor(5, 2);  // 返回 0 ，它是 5 的祖父节点
 * treeAncestor.getKthAncestor(6, 3);  // 返回 -1 因为不存在满足要求的祖先节点
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= n <= 5*10^4
 * parent[0] == -1 表示编号为 0 的节点是根节点。
 * 对于所有的 0 < i < n ，0 <= parent[i] < n 总成立
 * 0 <= node < n
 * 至多查询 5*10^4 次
 *
 * @author zm
 */
public class LEET5188 {
    public static void main(String[] args) {
        LEET5188 l5188 = new LEET5188();
        TreeAncestor treeAncestor = l5188.new TreeAncestor(7, new int[]{-1, 0, 0, 1, 1, 2, 2});

    }

    /**
     * 超时了
     */
    class TreeAncestor {
        int[] p;
        int len;
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        HashMap<Integer, Integer> lvlMap = new HashMap<>();

        public TreeAncestor(int n, int[] parent) {
            this.p = parent;
            this.len = n;
        }

        public int getKthAncestor(int node, int k) {
            if (lvlMap.containsKey(node) && lvlMap.get(node) < k) {
                System.out.println("return-1:" + lvlMap.get(node) + "_" + k);
                return -1;
            }
            System.out.println("getKthAncestor:" + node + "_" + k);
            if (map.containsKey(node) && map.get(node).containsKey(k)) {
                return map.get(node).get(k);
            }
            System.out.println("DoQuery:" + node + "_" + k);
            List<Mtype> tempMyArr = new ArrayList<>();
            Mtype root = new Mtype();
            root.value = node;
            tempMyArr.add(root);
            while (k > 0) {
                if (node == -1) {
                    break;
                }
                if (map.containsKey(node) && map.get(node).containsKey(k)) {
                    node = map.get(node).get(k);
                    break;
                }
                System.out.println("query:" + node + "_" + k);
                int max = -1;
                int value = -1;
                if (map.containsKey(node)) {
                    for (Map.Entry<Integer, Integer> entry : map.get(node).entrySet()) {
                        if (entry.getKey() > k) {
                            break;
                        } else {
                            max = entry.getKey();
                            value = entry.getValue();
                        }
                    }
                }
                if (max != -1) {
                    node = value;
                } else {
                    node = p[node];
                }
                for (Mtype mtemp : tempMyArr) {
                    if (max != -1) {
                        mtemp.lvl += max;
                    } else {
                        mtemp.lvl++;
                    }
                    if (map.containsKey(mtemp.value)) {
                        System.out.println("putResMap:" + mtemp.value + "_" + mtemp.lvl + "_" + node);
                        map.get(mtemp.value).put(mtemp.lvl, node);
                    } else {
                        HashMap<Integer, Integer> tempMap = new HashMap<>();
                        tempMap.put(mtemp.lvl, node);
                        map.put(mtemp.value, tempMap);
                        System.out.println("putResMap:" + mtemp.value + "_" + mtemp.lvl + "_" + node);
                    }
                    if (node == 0) {
                        System.out.println("putlvlMap:" + mtemp.value + "_" + mtemp.lvl);
                        lvlMap.put(mtemp.value, mtemp.lvl);
                    }
                }
                Mtype next = new Mtype();
                next.value = node;
                tempMyArr.add(next);
                if (max != -1) {
                    k -= max;
                } else {
                    k--;
                }

            }
            return node;
        }

        class Mtype {
            int lvl = 0;
            int value;
        }
    }
}
