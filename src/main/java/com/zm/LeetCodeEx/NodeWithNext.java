package com.zm.LeetCodeEx;

public class NodeWithNext {
	public int val;
	public NodeWithNext left;
	public NodeWithNext right;
	public NodeWithNext next;

	public NodeWithNext() {
	}

	public NodeWithNext(int _val) {
		val = _val;
	}

	public NodeWithNext(int _val, NodeWithNext _left, NodeWithNext _right, NodeWithNext _next) {
		val = _val;
		left = _left;
		right = _right;
		next = _next;
	}
}