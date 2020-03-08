package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class CommonFunctions {
	/**
	 * 把words写成map key = 某个单词 ; value = 出现次数
	 * 
	 * @param words
	 * @return
	 */
	public static HashMap<String, Integer> convertListToMap(String[] words) {
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			if (map.get(words[i]) == null) {
				map.put(words[i], 1);
			} else {
				map.put(words[i], map.get(words[i]) + 1);
			}
		}
		return map;
	}

	/**
	 * 把word写成map key = 某个字母 ; value = 出现次数
	 *
     * @param word
	 * @return
	 */
	public static HashMap<Character, Integer> convertStringToMap(String word) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < word.length(); i++) {
			if (map.get(word.charAt(i)) == null) {
				map.put(word.charAt(i), 1);
			} else {
				map.put(word.charAt(i), map.get(word.charAt(i)) + 1);
			}
		}
		return map;
	}

	public static ListNode<Integer> stringToListNode(String input) {
		// Generate array from the input
		int[] nodeValues = stringToIntegerArray(input);

		// Now convert that list into linked list
		ListNode<Integer> dummyRoot = new ListNode<>(0);
		ListNode<Integer> ptr = dummyRoot;
		for (int item : nodeValues) {
			ptr.next = new ListNode<>(item);
			ptr = ptr.next;
		}
		return dummyRoot.next;
	}

	public static String listNodeToString(ListNode<Integer> node) {
		if (node == null) {
			return "[]";
		}

        StringBuilder result = new StringBuilder();
		while (node != null) {
            result.append(Integer.toString(node.val)).append(", ");
			node = node.next;
		}
		return "[" + result.substring(0, result.length() - 2) + "]";
	}

	/**
	 * "[1,2,3]" ----> [1,2,3]
	 * 
	 * @param input
	 * @return
	 */
	public static int[] stringToIntegerArray(String input) {
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return new int[0];
		}

		String[] parts = input.split(",");
		int[] output = new int[parts.length];
		for (int index = 0; index < parts.length; index++) {
			String part = parts[index].trim();
			output[index] = Integer.parseInt(part);
		}
		return output;
	}

	/**
	 * [1,2,3] 0 最后一个节点连接到第一个节点 [1,2,3] 1 最后一个节点连接到第二个节点
	 * 
	 * @param input
	 * @param pos
	 * @return
	 */
	public static ListNode stringToListNodeWithCircle(String input, int pos) {
		// Generate array from the input
		int[] nodeValues = stringToIntegerArray(input);

		// Now convert that list into linked list
        ListNode dummyRoot = new ListNode<>(0);
		ListNode ptr = dummyRoot;
		for (int item : nodeValues) {
            ptr.next = new ListNode<>(item);
			ptr = ptr.next;
		}
		ListNode posNode = dummyRoot.next;
		for (int i = 0; i < pos; i++) {
			posNode = posNode.next;
		}
		if (pos >= 0) {
			ptr.next = posNode;
		}
		return dummyRoot.next;
	}

	/**
	 * 获取一个listnode在链表中的位置
	 * 
	 * @param head
	 * @param node
	 * @return
	 */
	public static int findIndexOfNode(ListNode head, ListNode node) {
		if (head == null) {
			return -1;
		}
		int i = 0;
		while (head != null) {
			if (head == node) {
				return i;
			} else {
				i++;
				head = head.next;
			}
		}
		return -1;
	}

    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static TreeNode<Integer> stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode<Integer> root = new TreeNode<>(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode<>(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode<>(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public static void prettyPrintTree(TreeNode node) {
        prettyPrintTree(node, "", true);
    }

    public static int factorial(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public static List<List<String>> stringArrayToList(String[][] args) {
        List<List<String>> retList = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            List<String> tempList = new ArrayList<>();
            for (int j = 0; j < args[i].length; j++) {
                tempList.add(args[i][j]);
            }
            retList.add(tempList);
        }
        return retList;
    }
    
    public static int[][] stringToIntegerArray2(String strs) {
    	return JSON.parseObject(strs, int[][].class);
	}
}
