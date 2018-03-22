package main;

import java.util.ArrayList;

public class BinaryTreeOfSearch {

	static class Node {

		private int key;
		private int value;

		private Node left;
		private Node right;
		private Node parent;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node r) {
			this.right = r;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node p) {
			this.parent = p;
		}

		public boolean isLeaf(){
			return left == null && right == null;
		}

	}

	//private ArrayList<Node> tree;
	private Node root;

	public BinaryTreeOfSearch() {
		//	tree = new ArrayList<>();
	}

	public Node find(int key) {
		return find(key, root);
	}

	public Node find(int key, Node cur) {
		return cur == null ? null :
				key == cur.getKey() ? cur :
						key < cur.getKey() ? find(key, cur.getLeft()) :
								find(key, cur.getRight());
	}

	public void add(Node n) {
		if (root == null)
			root = n;
		else add(n, root);
	}

	public void add(Node n, Node cur) {
		if (cur.getKey() == n.getKey())
			cur.setValue(n.getValue());
		else if (n.getKey() < cur.getKey())
			if (cur.getLeft() == null)
				cur.setLeft(n);
			else
				add(n, cur.getLeft());
		else
			if(cur.getRight() == null)
				cur.setRight(n);
			else
				add(n, cur.getRight());
	}

	int getDeep(Node cur){
		return cur.isLeaf() ? 1 : 1 + Math.max(getDeep(cur.getLeft()), getDeep(cur.getRight()));
	}

	void print(){
		print(root, 1);
	}

	void print(Node node, int level){
		System.out.println(node.getKey()+"-"+level+"\t");
		if(node.getLeft() != null)
			print(node.getLeft(), level+1);
		if(node.getRight() != null)
			print(node.getRight(), level+1);
	}
}
