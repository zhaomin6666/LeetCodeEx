package com.zm.LeetCodeEx.algorithms.ex901_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 990. 等式方程的可满足性
 * <p>
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * <p>
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：["a==b","b!=a"]
 * 输出：false
 * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
 * 示例 2：
 * <p>
 * 输出：["b==a","a==b"]
 * 输入：true
 * 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
 * 示例 3：
 * <p>
 * 输入：["a==b","b==c","a==c"]
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：["a==b","b!=c","c==a"]
 * 输出：false
 * 示例 5：
 * <p>
 * 输入：["c==c","b==d","x!=z"]
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] 和 equations[i][3] 是小写字母
 * equations[i][1] 要么是 '='，要么是 '!'
 * equations[i][2] 是 '='
 *
 * @author zm
 */
public class LEET990 {
    public static void main(String[] args) {
        LEET990 l990 = new LEET990();
        System.out.println(l990.new Solution().equationsPossible(new String[]{"a==b","e==c","b==c","a!=e"}));
        System.out.println(l990.new Solution().equationsPossible(new String[]{"a==b", "b!=a"}));
        System.out.println(l990.new Solution().equationsPossible(new String[]{"b==a", "a==b"}));
        System.out.println(l990.new Solution().equationsPossible(new String[]{"a==b", "b==c", "a==c"}));
        System.out.println(l990.new Solution().equationsPossible(new String[]{"a==b", "b!=c", "c==a"}));
        System.out.println(l990.new Solution().equationsPossible(new String[]{"c==c", "b==d", "x!=z"}));
    }

    /**
     * weightedUF
     */
    class Solution {
        public boolean equationsPossible(String[] equations) {
            List<String> notEqualList = new ArrayList<>();
            UnionFind uf = new UnionFind();
            for (String equation : equations) {
                if (equation.charAt(1) == '!') {
                    notEqualList.add(equation);
                } else {
                    uf.union(equation.charAt(0), equation.charAt(3));
                }
            }
            for (String notEqualStr:notEqualList) {
                if (uf.isSameRoot(notEqualStr.charAt(0), notEqualStr.charAt(3))){
                    return false;
                }
            }
            return true;
        }

        class UnionFind {
            int[] ufarr;
            int[] weight;

            public UnionFind() {
                ufarr = new int[26];
                weight = new int[26];
                for (int i = 0; i < 26; i++) {
                    ufarr[i] = i;
                }
                Arrays.fill(weight, 1);
            }

            public char getRoot(char c) {
                int index = c - 'a';
                while (ufarr[index] != index) {
                    index = ufarr[index];
                }
                return (char) index;
            }

            public void union(char c1, char c2) {
                int index1 = getRoot(c1);
                int index2 = getRoot(c2);
                if (weight[index1] >= weight[index2]) {
                    ufarr[index2] = index1;
                    weight[index1] += weight[index2];
                } else {
                    ufarr[index1] = index2;
                    weight[index2] += weight[index1];
                }
            }

            public boolean isSameRoot(char c1, char c2) {
                return getRoot(c1) == getRoot(c2);
            }
        }
    }

    /**
     * 官方题解，不带权重
     */
    class Solution2 {
        public boolean equationsPossible(String[] equations) {
            int length = equations.length;
            int[] parent = new int[26];
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
            }
            for (String str : equations) {
                if (str.charAt(1) == '=') {
                    int index1 = str.charAt(0) - 'a';
                    int index2 = str.charAt(3) - 'a';
                    union(parent, index1, index2);
                }
            }
            for (String str : equations) {
                if (str.charAt(1) == '!') {
                    int index1 = str.charAt(0) - 'a';
                    int index2 = str.charAt(3) - 'a';
                    if (find(parent, index1) == find(parent, index2)) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void union(int[] parent, int index1, int index2) {
            parent[find(parent, index1)] = find(parent, index2);
        }

        public int find(int[] parent, int index) {
            while (parent[index] != index) {
                parent[index] = parent[parent[index]];
                index = parent[index];
            }
            return index;
        }
    }
}
