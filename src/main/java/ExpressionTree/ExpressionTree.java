package ExpressionTree;

public class ExpressionTree {
	private Node root;

	public ExpressionTree(String value) {
		this.root = new Node(value);
	}

	public ExpressionTree(Node node) {
		this.root = node;
	}

	public ExpressionTree() {
		this.root = null;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

}
