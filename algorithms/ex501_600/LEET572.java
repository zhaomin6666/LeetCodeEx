package com.zm.LeetCodeEx.algorithms.ex501_600;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 572 另一个树的子树
 * 给定两个非空二叉树.s.和.t，检验.s.中是否包含和.t.具有相同结构和节点值的子树。s.的一个子树包括.s.的一个节点和这个节点的所有子孙。s.也可以看做它自身的一棵子树。
 * <p>
 * 示例.1:
 * 给定的树.s:
 * <p>
 * .....3
 * ..../.\
 * ...4...5
 * ../.\
 * .1...2
 * 给定的树.t：
 * <p>
 * ...4.
 * ../.\
 * .1...2
 * 返回.true，因为.t.与.s.的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例.2:
 * 给定的树.s：
 * <p>
 * .....3
 * ..../.\
 * ...4...5
 * ../.\
 * .1...2
 * ..../
 * ...0
 * 给定的树.t：
 * <p>
 * ...4
 * ../.\
 * .1...2
 * 返回.false。
 *
 * @author zm
 */
public class LEET572 {
    public static void main(String[] args) {
        LEET572 l572 = new LEET572();
        System.out.println(l572.new Solution2().isSubtree(CommonFunctions.stringToTreeNode("[3,4,5,1,2,null,null,null,null,0]"),
                CommonFunctions.stringToTreeNode("[4,1,2]")));
        System.out.println(l572.new Solution2().isSubtree(CommonFunctions.stringToTreeNode("[3,4,5,1,2]"),
                CommonFunctions.stringToTreeNode("[4,1,2]")));
    }

    class Solution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            if (s.val == t.val) {
                if (isSameTree(s, t)) {
                    return true;
                }
            }
            if (s.right != null) {
                if (isSubtree(s.right, t)) {
                    return true;
                }
            }
            if (s.left != null) {
                if (isSubtree(s.left, t)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isSameTree(TreeNode<Integer> p, TreeNode<Integer> q) {
            if (p != null) {
                if (q == null) {
                    return false;
                }
                return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
            if (q != null) {
                return false;
            }
            return true;
        }
    }

    /**
     * 方法一每个相同节点都会进行一次遍历匹配，会导致重复判断。
     * 可以使用类似字符串匹配的方法，将树转化为先序遍历，并且加入lNull,rNull标识左右子节点为空
     */
    class Solution2 {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            List<String> sList = bfsTree(s);
            List<String> tList = bfsTree(t);
            return checkContains(sList, tList);
        }

        /**
         * 判断sList是否有tList子串
         * KMP
         *
         * @param sList
         * @param tList
         * @return
         */
        private boolean checkContains(List<String> sList, List<String> tList) {
            int sLen = sList.size();
            int pLen = tList.size();
            int[] next = new int[tList.size()];
            updateNext(next, tList);//计算next数组
            int k = -1;
            for (int i = 0; i < sLen; i++) {
                while (k > -1 && !tList.get(k + 1).equals(sList.get(i))) {
                    //ptr和str不匹配，且k>-1（表示ptr和str有部分匹配）
                    k = next[k];//往前回溯
                }
                if (tList.get(k + 1).equals(sList.get(i))) {
                    k = k + 1;
                }
                if (k == pLen - 1) {
                    //说明k移动到ptr的最末端
                    //cout << "在位置" << i-plen+1<< endl;
                    //k = -1;//重新初始化，寻找下一个
                    //i = i - plen + 1;//i定位到该位置，外层for循环i++可以继续找下一个（这里默认存在两个匹配字符串可以部分重叠），感谢评论中同学指出错误。
                    //return i - pLen + 1;//返回相应的位置
                    return true;
                }
            }
            return false;
        }

        private void updateNext(int[] next, List<String> tList) {
            int len = tList.size();
            next[0] = -1;//next[0]初始化为-1，-1表示不存在相同的最大前缀和最大后缀
            int k = -1;//k初始化为-1
            for (int q = 1; q <= len - 1; q++) {
                while (k > -1 && !tList.get(k + 1).equals(tList.get(q)))//如果下一个不同，那么k就变成next[k]，注意next[k]是小于k的，无论k取任何值。
                {
                    k = next[k];//往前回溯
                }
                if (tList.get(k + 1).equals(tList.get(q)))//如果相同，k++
                {
                    k = k + 1;
                }
                next[q] = k;//这个是把算的k的值（就是相同的最大前缀和最大后缀长）赋给next[q]
            }
        }

        private List<String> bfsTree(TreeNode node) {
            if (node == null) {
                return Collections.emptyList();
            }
            List<String> list = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                list.add(String.valueOf(cur.val));
                if (cur.left == null) {
                    list.add("lNull");
                } else {
                    stack.push(cur.left);
                }
                if (cur.right == null) {
                    list.add("rNull");
                } else {
                    stack.push(cur.right);
                }
            }
            return list;
        }
    }

    /**
     * 先判断节点数是否一样，再判断值
     */
    class Solution3 {
        // 判断两棵二叉树是否相等
        private boolean isSame(TreeNode root1, TreeNode root2) {
            if (root1 == null) {
                return root2 == null;
            }

            if (root2 == null) {
                return false;
            }

            return root1.val == root2.val && isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
        }

        private boolean isFoundSame = false;
        private int tCount;

        // 递归计算二叉树s每棵子树的节点数，并同时寻找是否有子树与t相等
        private int calcCount(TreeNode root, TreeNode t) {
            if (isFoundSame) {
                return 0;
            }

            if (root == null) {
                return 0;
            }

            int count = calcCount(root.left, t) + calcCount(root.right, t) + 1;
            if (count == tCount && isSame(root, t)) {
                isFoundSame = true;
                return 0;
            }
            return count;
        }

        // 计算二叉树t的节点个数
        private int calcTCount(TreeNode root) {
            return root == null ? 0 : calcTCount(root.left) + calcTCount(root.right) + 1;
        }

        public boolean isSubtree(TreeNode s, TreeNode t) {
            tCount = calcTCount(t);
            calcCount(s, t);
            return isFoundSame;
        }
    }
}
