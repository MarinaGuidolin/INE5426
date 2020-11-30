package SymbolTable;

import java.util.HashMap;
import Enum.SemanticReports;

public class SymbolTable {
	private HashMap<String, SymbolTableEntry> symbolTable;

	public SymbolTable() {
		this.symbolTable = new HashMap<String, SymbolTableEntry>();
	}

	public HashMap<String, SymbolTableEntry> getSymbolTable() {
		return symbolTable;
	}

	public SemanticReports insert(String lexeme, boolean isFunction, String type, int declarationLine) {
//		FIRST, VERIFY IF THE LEXEME ALREADY EXISTS
		boolean contains = symbolTable.containsKey(lexeme);
		if (!contains) {
			SymbolTableEntry entry = new SymbolTableEntry(isFunction, type, declarationLine);
			symbolTable.put(lexeme, entry);
			return SemanticReports.OK;
		}
		return SemanticReports.IDENT_ALREADY_EXISTS;
	}

	public SymbolTableEntry getEntry(String lexeme) {
		//		FIRST, VERIFY IF THE LEXEME EXISTS
		if (symbolTable.containsKey(lexeme)) {
			return symbolTable.get(lexeme);
		}
		return null;
	}

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Symbol Table:\n");
		for (String key: symbolTable.keySet()) {
			sb.append("[" + key + ", ");
			sb.append(symbolTable.get(key).toString() + "]\n");
		}
		return sb.toString();
	}
}
