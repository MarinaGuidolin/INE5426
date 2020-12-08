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
			String temp = "";
			String code = "";
			if (node.getValue().equals("array")) {
				ArrayList<String> temps = new ArrayList<>();
				generateCodeArray(node, sb, temps);
				for (int i = 0; i < temps.size(); i++) {
					temp = "t" + counter;
					if (i == 0) {
						sb.append(Generator.generateIndexedCopyCode(temp, node.getIdent(), temps.get(i), false));
					} else {
						sb.append(Generator.generateIndexedCopyCode(temp, "t" + (counter-1), temps.get(i), false));
					}
					counter++;
				}
			} else {
				String left = generateCode(node.getLeft(), sb);
				String right = generateCode(node.getRight(), sb);
				temp = "t" + counter;
				code = Generator.generateThreeAddressCode(temp, left, right, node.getValue());
			}
			sb.append(code);
			counter++;
			return temp;
		}
	}

	public void generateCodeArray(Node node, StringBuilder sb, ArrayList<String> temps) {
		if (node.getRight() != null && node.getLeft() != null) {
			String left = generateCode(node.getLeft(), sb);
			temps.add(left);
			generateCodeArray(node.getRight(), sb, temps);
		}
	}

	public String validateExpressions(String type1, String type2, String operator) {
		if (operator.equals("+")) {
			return validateSum(type1, type2);
		} else if (operator.equals("*") || operator.equals("-")) {
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
