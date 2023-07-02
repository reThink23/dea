package ueb12;

public class SearchTree<KeyType extends Comparable<KeyType>, DataType> {
	private TreeNode<KeyType, DataType> root;

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
			TreeNode<KeyType, DataType> successor = node.getSuccessor();
			node.key = successor.key;
			node.data = successor.data;
			successor = null;
		}
		return true;
	}

	public TreeNode<KeyType, DataType> minimum() {
		TreeNode<KeyType, DataType> node = root;
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
		int counter = 0;

		if (startNode.isLeaf()) return counter;

		TreeNode<KeyType, DataType> node = startNode;
		while (node.left != null) { node = node.left; counter++; }

		return counter + size(startNode.right);
	}

	public String toString() { return toString(root); }

	private String toString(TreeNode<KeyType, DataType> startNode) {
		String output = "";

		if (startNode.isLeaf()) return output;

		TreeNode<KeyType, DataType> node = startNode;
		while (node.left != null) { node = node.left; output += "\n" + node.toString(); }

		return output + toString(startNode.right);
	}

}