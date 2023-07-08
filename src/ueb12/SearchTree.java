package ueb12;

import java.util.ArrayList;

public class SearchTree<KeyType extends Comparable<KeyType>, DataType> {
	public TreeNode<KeyType, DataType> root;

	public TreeNode<KeyType, DataType> search(KeyType k ){
		TreeNode<KeyType, DataType> crtNode = root;
		while (crtNode != null) {
			if (k.compareTo(crtNode.key) == 0) return crtNode;
			if (k.compareTo(crtNode.key) < 0) {
				crtNode = crtNode.left;
			} else {
				crtNode = crtNode.right;
			}
		}
		return null;
	}

	public DataType isMember(KeyType k ){
		TreeNode<KeyType,DataType> node = search(k);
		if (node == null) return null;
		return node.data;
	}

	public boolean insert(DataType d, KeyType k) {
		TreeNode<KeyType, DataType> node = new TreeNode<KeyType, DataType>(k, d);
		
		if (root == null) { root = node; return true; }

		TreeNode<KeyType, DataType> crt = root;
		TreeNode<KeyType, DataType> parent = null;
		
		while (crt != null) {
			parent = crt;
			if (k.compareTo(crt.key) == 0) { return false; }
			if (k.compareTo(crt.key) < 0) { crt = crt.left; }
			else { crt = crt.right; }
		}

		node.parent = parent;
		if (k.compareTo(parent.key) > 0) { parent.right = node; }
		else { parent.left = node; }
		return true;
	}

	public boolean remove(KeyType k) {
		TreeNode<KeyType, DataType> node = search(k);
		
		if (node == null) { return true; }
		if (node.isRoot()) return false;

		TreeNode<KeyType, DataType> parent = node.parent;
		if (node.isLeaf()) {
			if (node.isLeftChild()) { parent.left = null; } 
			else if (node.isRightChild()) { parent.right = null; }

		} else if (node.hasSoleChild()) {
			if (node.left != null) { 
				if (node == parent.left) { parent.left = node.left; }
				if (node == parent.right) { parent.right = node.left; } 
			} else if (node.right != null) { 
				if (node == parent.left) { parent.left = node.right; }
				if (node == parent.right) { parent.right = node.right; } 
			}
		} else if (node.has2Children()) {
			TreeNode<KeyType, DataType> successor = getSuccessor(node);
			node.key = successor.key;
			node.data = successor.data;
			successor = null;
		}
		return true;
	}

	private TreeNode<KeyType, DataType> getSuccessor(TreeNode<KeyType, DataType> startNode) {
		return minimum(startNode.right);
	}

	public TreeNode<KeyType, DataType> minimum() {
		// TreeNode<KeyType, DataType> node = root;
		// while (node.left != null) { node = node.left; }
		// return node;
		return minimum(root);
	}

	private TreeNode<KeyType, DataType> minimum(TreeNode<KeyType, DataType> startNode) {
		TreeNode<KeyType, DataType> node = startNode;
		while (node.left != null) { node = node.left; }
		return node;
	}

	public TreeNode<KeyType, DataType> maximum() {
		TreeNode<KeyType, DataType> node = root;
		while (node.right != null) { node = node.right; }
		return node;
	}

	public void clear() { root = null; }

	public int size() { return size(root); }

	private int size(TreeNode<KeyType, DataType> startNode) {
		if (startNode == null) return 0;
		return 1 + size(startNode.left) + size(startNode.right);
	}

	public int depth() { return depth(root); }

	private int depth(TreeNode<KeyType, DataType> node) {
		if (node == null) return 0;
        else {
            int leftDepth = depth(node.left);
            int rightDepth = depth(node.right);
 
            if (leftDepth > rightDepth) { return (leftDepth + 1); }
            else { return (rightDepth + 1); }
                
        }
	}

	public String toString() { return toString(root); }

	private String toString(TreeNode<KeyType, DataType> node) {
		String output = "";

		if (node == null) return "";

		output += node.toString() + "\n";
		
		output += toString(node.left);
		output += toString(node.right);

		return output;
	}

	/* needed to implement foreach method (lambda functions) */
	public interface InnerFunc <KeyType extends Comparable<KeyType>, DataType> {
		void function(TreeNode<KeyType,DataType> s);
	}

	public void forEach(TreeNode<KeyType,DataType> startNode, InnerFunc<KeyType,DataType> func ) {
			if (startNode == null) return;
			func.function(startNode);
			forEach(startNode.left, func);
			forEach(startNode.right, func);
	}

	private TreeNode<KeyType,DataType> getMin(ArrayList<TreeNode<KeyType, DataType>> list) {
		TreeNode<KeyType,DataType> min = list.get(0);
		for (int i = 1; i < list.size(); i++) { if (list.get(i).key.compareTo(min.key) < 0) { min = list.get(i); } }
		return min;
	}

	public ArrayList<TreeNode<KeyType,DataType>> top(int amount, ArrayList<TreeNode<KeyType,DataType>> list, TreeNode<KeyType,DataType> startNode) {
		if (startNode == null) return list;

		if (amount > list.size()) { list.add(startNode); }
		else { 
			TreeNode<KeyType,DataType> min = getMin(list);
			System.out.println(min.key + " vs. " + startNode.key);
			if (startNode.key.compareTo(min.key) > 0) { list.remove(min); list.add(startNode); }
		}
		if (startNode.left != null) { top(amount, list, startNode.left); }
		if (startNode.right != null) { top(amount, list, startNode.right); }
		// get the words with the most occurrences in the text by adding them to a PriorityQueue and traversing through the tree
		// if the current node has more occurrences than the smallest node in the queue, remove the smallest node and add the current node
		return list;
	}

}