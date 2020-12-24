package com.zm.LeetCodeEx.algorithms.ex201_300;

import com.zm.LeetCodeEx.TreeNode;

import java.util.*;

/**
 * 297. 二叉树的序列化与反序列化
 * <p>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 示例: 
 * <p>
 * 你可以将以下二叉树：
 * <p>
 * ....1
 * .../ \
 * ..2   3
 * ./ \
 * 4   5
 * <p>
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 */
public class LEET297 {
    public static void main(String[] args) {
        LEET297 l297 = new LEET297();
        Codec codec = l297.new Codec();
        TreeNode<Integer> treeNode = codec.deserialize("[1,2,3,null,null,4,5]");
        System.out.println(codec.serialize(treeNode));
    }

    /**
     * 超出内存限制。。。
     */
    public class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode<Integer> root) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            Queue<TreeNode<Integer>> queue = new LinkedList<>();
            queue.add(root);
            int nullCnt = 0;
            while (!queue.isEmpty()) {
                TreeNode<Integer> node = queue.poll();
                if (node == null) {
                    nullCnt++;
                } else {
                    for (int i = 0; i < nullCnt; i++) {
                        sb.append("null,");
                        queue.add(null);
                        queue.add(null);
                    }
                    nullCnt = 0;
                    sb.append(node.val);
                    sb.append(",");
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode<Integer> deserialize(String data) {
            String realData = data.substring(1, data.length() - 1);
            String[] dataSplit = realData.split(",");
            TreeNode[] nodes = new TreeNode[dataSplit.length];
            for (int i = dataSplit.length - 1; i >= 0; i--) {
                if (!"null".equals(dataSplit[i])) {
                    nodes[i] = new TreeNode<>(Integer.valueOf(dataSplit[i]));
                    if (i * 2 < dataSplit.length) {
                        nodes[i].left = nodes[i * 2 + 1];
                    }
                    if (i * 2 + 1 < dataSplit.length) {
                        nodes[i].right = nodes[i * 2 + 2];
                    }
                }
            }
            return nodes[0];
        }
    }


    /**
     * 提交记录中看到的解法
     */
    public class Codec2 {
        public static final String spliter = ",";
        public static final String NN = "x";

        //序列化
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            buildString(root, sb);
            return sb.toString();
        }

        private void buildString(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(NN).append(spliter);
            } else {
                sb.append(node.val).append(spliter);
                buildString(node.left, sb);
                buildString(node.right, sb);
            }
        }

        //反序列化
        public TreeNode deserialize(String data) {
            Deque<String> nodes = new LinkedList<>();
            nodes.addAll(Arrays.asList(data.split(spliter)));

            return buildTree(nodes);
        }

        private TreeNode buildTree(Deque<String> nodes) {
            String val = nodes.remove();
            if (val.equals(NN)) {
                return null;
            } else {
                TreeNode node = new TreeNode(Integer.valueOf(val));
                node.left = buildTree(nodes);
                node.right = buildTree(nodes);
                return node;
            }
        }
    }

    /**
     * 官方解法1
     * 递归解法
     */
    public class Codec3 {
        public String rserialize(TreeNode root, String str) {
            if (root == null) {
                str += "None,";
            } else {
                str += str.valueOf(root.val) + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        public TreeNode rdeserialize(List<String> l) {
            if (l.get(0).equals("None")) {
                l.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
            l.remove(0);
            root.left = rdeserialize(l);
            root.right = rdeserialize(l);

            return root;
        }

        public TreeNode deserialize(String data) {
            String[] data_array = data.split(",");
            List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
            return rdeserialize(data_list);
        }
    }
}
