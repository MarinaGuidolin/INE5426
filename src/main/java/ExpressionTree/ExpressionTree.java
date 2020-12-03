package ExpressionTree;

import java.util.ArrayList;

import GCI.*;

public class ExpressionTree {
	private Node root;

	private int counter;

	public ExpressionTree(Node node, int counter) {
		this.root = node;
		this.counter = counter;
	}

	public ExpressionTree() {
		this.root = null;
		this.counter = 0;
	}

	public Node getRoot() {
		return root;
	}

	public int getCounter() {
		return counter;
	}

	public String validateTree(Node node) {
		if (node.getValue().equals("array")) {
			return node.getType();
		} else {
			if (node.getRight() == null && node.getLeft() == null) {
				return node.getType();
			}
			String left = validateTree(node.getLeft());
			String right = validateTree(node.getRight());
			String operator = node.getValue();
			return validateExpressions(left, right, operator);
		}
	}

	public String generateCode(Node node, StringBuilder sb) {
		if (node.getRight() == null && node.getLeft() == null) {
			return node.getValue();
		} else {
			String left = generateCode(node.getLeft(), sb);
			String right = generateCode(node.getRight(), sb);
			String temp = "t" + counter;
			String code = "";
			if (node.getValue() == "array") {
				code = Generator.generateIndexedCopyCode(temp, right, left, false);
			} else {
				code = Generator.generateThreeAddressCode(temp, left, right, node.getValue());
			}
			sb.append(code);
			counter++;
			return temp;
		}
	}

	public String validateExpressions(String type1, String type2, String operator) {
		switch (operator) {
			case "+": return validateSum(type1, type2);
			case "-":
		case "*":
			return validateSubsMult(type1, type2);
		case "/": return validateDivision(type1, type2);
			case "%": return validateMod(type1, type2);
			default: return "";
		}
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
