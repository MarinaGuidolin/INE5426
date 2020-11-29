package Scope;

import static java.lang.System.exit;

import SymbolTable.SymbolTable;

public class LinkedStack {
	private ScopeNode top;

	public LinkedStack() {
		top = null;
	}

	public void push(SymbolTable symbolTable, boolean isFor) {
		ScopeNode oldTop = top;
		top = new ScopeNode(symbolTable, oldTop, isFor);
	}

	public boolean isEmpty() {
		return top == null;
	}

	public ScopeNode peek() {
		if (!isEmpty()) {
			return top;
		}
		return null;
	}

	public ScopeNode pop() {
		if (isEmpty()) {
			exit(1);
		}
		ScopeNode oldTop = top;
		top = top.getPrevious();
		return oldTop;
	}

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		ScopeNode actual = top;
		sb.append("SCOPE STACK:\n");
		while (actual != null) {
			sb.append(actual.toString()+'\n');
			actual = top.getPrevious();
		}
		return sb.toString();
	}
}
