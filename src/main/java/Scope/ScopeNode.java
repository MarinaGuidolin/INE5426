package Scope;

import SymbolTable.SymbolTable;

public class ScopeNode {
	private SymbolTable symbolTable;
	private ScopeNode previous;
	private boolean isFor;

	public ScopeNode(SymbolTable symbolTable, ScopeNode previous, boolean isFor) {
		this.symbolTable = symbolTable;
		this.previous = previous;
		this.isFor = isFor;
	}

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	public ScopeNode getPrevious() {
		return previous;
	}

	public boolean isFor() {
		return isFor;
	}

	@Override public String toString() {
		String init = isFor ? "FOR SCOPE:" : "SCOPE:";
		return init + '\n' + symbolTable.toString();
	}
}
