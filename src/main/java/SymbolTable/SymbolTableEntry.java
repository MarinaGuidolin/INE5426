package SymbolTable;

public class SymbolTableEntry {
	private boolean isFunction;
	private String type;
	private int declarationLine;

	public SymbolTableEntry(boolean isFunction, String type, int declarationLine) {
		this.isFunction = isFunction;
		this.type = type;
		this.declarationLine = declarationLine;
	}

	public boolean isFunction() {
		return isFunction;
	}

	public String getType() {
		return type;
	}

	public int getDeclarationLine() {
		return declarationLine;
	}

	@Override public String toString() {
		return "isFunction = " + isFunction +
				", type = " + type +
				", declaration line = " + declarationLine;
	}
}
