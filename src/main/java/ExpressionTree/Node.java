package ExpressionTree;

public class Node {
	private String value;
	private Node left;
	private Node right;
	private String type;

	// Exemplo: operando("array") Node(operando) Node(operando) tipo
	public Node(String value, Node left, Node right, String type) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.type = type;
	}

	// Exemplo: operador Node(operando/operador) Node(operando/operador)
	public Node(String value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.type = "";
	}

	// Exemplo: operando tipo
	public Node(String value, String type) {
		this.value = value;
		this.left = null;
		this.right = null;
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public String getIdent() {
		if(value.equals("array")) {
			Node rightChild = this.right;
			if (rightChild != null) {
				while (rightChild.right != null) {
					rightChild = rightChild.right;
				}
				return rightChild.getValue();
			}
		}
		return "";
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getLeft() {
		return this.left;
	}

	public Node getRight() {
		return this.right;
	}

	public String getType() {
		if(value.equals("array")) {
			Node rightChild = this.right;
			if (rightChild != null) {
				while (rightChild.right != null) {
					rightChild = rightChild.right;
				}
				return rightChild.getType();
			}
		}
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLeftType(Node node) {
		if (node.left != null) {
			return getLeftType(node.left);
		}
		return this.getType();
	}

	public String getRightType(Node node) {
		if (node.right != null) {
			return this.getRightType(node.right);
		}
		return this.getType();
	}

	@Override
	public String toString() {
		return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
	}

	public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
		if(this.right != null) {
			this.right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
		}
		sb.append(prefix).append(isTail ? "└── " : "┌── ").append(value).append("\n");
		if(this.left != null) {
			this.left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
		}
		return sb;
	}
}
