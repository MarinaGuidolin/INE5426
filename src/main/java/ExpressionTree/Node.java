package ExpressionTree;

public class Node {
	private String value;
	private Node left;
	private Node right;
	private String type;

	public Node(String value, Node left, Node right, String type) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.type = type;
	}

	public Node(String value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.type = "";
	}

	public Node(String value, String type) {
		this.value = value;
		this.left = null;
		this.right = null;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public String getIdent() {
		if(value == "array") {
			Node rightChild = right;
			while (rightChild.right != null) {
				rightChild = rightChild.right;
			}
			return rightChild.getValue();
		}
		return "";
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public String getType() {
		if(value == "array") {
			Node rightChild = right;
			while (rightChild.right != null) {
				rightChild = rightChild.right;
			}
			return rightChild.getType();
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLeftType(Node node) {
		if (node.left != null) {
			return getLeftType(node.left);
		}
		return getType();
	}

	public String getRightType(Node node) {
		if (node.right != null) {
			return getRightType(node.right);
		}
		return getType();
	}

	@Override
	public String toString() {
		return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
	}

	public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
		if(right!=null) {
			right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
		}
		sb.append(prefix).append(isTail ? "└── " : "┌── ").append(value).append("\n");
		if(left!=null) {
			left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
		}
		return sb;
	}
}
