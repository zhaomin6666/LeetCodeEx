package com.zm.LeetCodeEx.weekcontest.before20200426;

/**
 * 周赛 2020年2月23日 5170. 验证二叉树
 * <p>
 * 二叉树上有 n 个节点，按从 0 到 n - 1 编号，其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。
 * <p>
 * 只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。
 * <p>
 * 如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。右子节点也符合该规则。
 * <p>
 * 注意：节点没有值，本问题中仅仅使用节点编号。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 * 输出：false
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：n = 2, leftChild = [1,0], rightChild = [-1,-1]
 * 输出：false
 * 示例 4：
 * <p>
 * <p>
 * <p>
 * 输入：n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^4
 * leftChild.length == rightChild.length == n
 * -1 <= leftChild[i], rightChild[i] <= n - 1
 *
 * @author zm
 */
public class LEET5170 {
    public static void main(String[] args) {
        LEET5170 l5170 = new LEET5170();
        System.out.println(l5170.validateBinaryTreeNodes(4, new int[]{1, -1, 3, -1}, new int[]{2, -1, -1, -1}));
        System.out.println(l5170.validateBinaryTreeNodes(4, new int[]{1, -1, 3, -1}, new int[]{2, 3, -1, -1}));
    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        boolean[] checkedNode = new boolean[n];
        checkedNode[0] = true;
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                if (checkedNode[leftChild[i]]) {
                    return false;
                } else {
                    checkedNode[leftChild[i]] = true;
                }
            }
            if (rightChild[i] != -1) {
                if (checkedNode[rightChild[i]]) {
                    return false;
                } else {
                    checkedNode[rightChild[i]] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!checkedNode[i]) {
                return false;
            }
        }
        return true;
    }
}
