package ueb12;

public class TreeNode<KeyType extends Comparable<KeyType>, DataType> {
	public KeyType key;
	public DataType data;
	public TreeNode<KeyType, DataType> parent;
	public TreeNode<KeyType, DataType> left;
	public TreeNode<KeyType, DataType> right;

	// Konstruktor:
	public TreeNode(KeyType k, DataType d) {
		key = k;
		data = d;
		left = right = parent = null;
	}

	public TreeNode<KeyType, DataType> getSoleChild() {
		if (!hasSoleChild()) return null;
		if (left == null && right != null) return left;
		if (left != null && right == null) return right;
		return null;
	}

	public TreeNode<KeyType, DataType> getSuccessor() {
		if (isLeaf()) return null;
		if (hasSoleChild()) return getSoleChild();
		if (left.key.compareTo(right.key) >= 0) return left;
		else return right;
	}

	public boolean isLeftChild() {
		return this == parent.left;
	}

	public boolean isRightChild() {
		return this == parent.right;
	}

	public boolean isLeaf() {
		return (left == null && right == null);
	}

	public boolean isRoot() {
		return parent == null;
	}

	public boolean hasChildren() {
		return !isLeaf();
	}

	public boolean hasSoleChild() {
		return (left != null && right == null) || (left == null && right != null);
	}

	public boolean has2Children() {
		return !isLeaf() && !hasSoleChild();
	}

	public String toString() {
		return "("+ key + "," + data + ")";
	}
}