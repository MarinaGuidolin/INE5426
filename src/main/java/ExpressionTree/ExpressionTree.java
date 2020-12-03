package ExpressionTree;

public class ExpressionTree {
	private Node root;

	public ExpressionTree(Node node) {
		this.root = node;
	}

	public ExpressionTree() {
		this.root = null;
	}

	public Node getRoot() {
		return root;
	}

	public String postOrder(Node node) {
		if (node.getValue().equals("array")) {
			return node.getType();
		} else {
			if (node.getRight() == null && node.getLeft() == null) {
				return node.getType();
			}
			String left = postOrder(node.getLeft());
			String right = postOrder(node.getRight());
			String operator = node.getValue();
			return validateExpressions(left, right, operator);
		}
	}

	public String validateExpressions(String type1, String type2, String operator) {
		if (operator.equals("+") || operator.equals("-")) {
			return validateSum(type1, type2);
		} else if (operator.equals("*")) {
			return validateSubsMult(type1, type2);
		} else if (operator.equals("/")) {
			return validateDivision(type1, type2);
		} else if (operator.equals("%")) {
			return validateMod(type1, type2);
		}
		return "";
	}

	public String validateSum(String type1, String type2) {
		if (type1.equals("int") && type2.equals("int")) {
			return "int";
		} else if (type1.equals("string") && type2.equals("string")) {
			return "string";
		} else if ((type1.equals("float") || type1.equals("int")) && (type2.equals("float") || type2.equals("int"))) {
			return "float";
		} else {
			return "";
		}
	}

	public String validateSubsMult(String type1, String type2) {
		if (type1.equals("int") && type2.equals("int")) {
			return "int";
		} else if ((type1.equals("float") || type1.equals("int")) && (type2.equals("float") || type2.equals("int"))) {
			return "float";
		} else {
			return "";
		}
	}

	public String validateDivision(String type1, String type2) {
		if ((type1.equals("float") || type1.equals("int")) && (type2.equals("float") || type2.equals("int"))) {
			return "float";
		} else {
			return "";
		}
	}

	public String validateMod(String type1, String type2) {
		if (type1.equals("int") && type2.equals("int")) {
			return "int";
		} else if ((type1.equals("float") || type1.equals("int")) && (type2.equals("float") || type2.equals("int"))) {
			return "float";
		} else {
			return "";
		}
	}

}
