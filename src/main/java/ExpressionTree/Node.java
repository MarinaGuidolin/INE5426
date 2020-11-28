package ExpressionTree;

public class Node {
	private String value;
	private Node left;
	private Node right;

//	CONSTRUCTOR FOR A INTERMEDIARY NODE
	public Node(String value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

//	CONSTRUCTOR FOR A LEAF
	public Node(String value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
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

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
	}

	public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
		if(right!=null) {
			right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
		}
		sb.append(prefix).append(isTail ? "└── " : "┌── ").append(value.toString()).append("\n");
		if(left!=null) {
			left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
		}
		return sb;
	}
}
