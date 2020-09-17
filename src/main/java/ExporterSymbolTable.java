import java.util.HashMap;

import org.antlr.v4.runtime.tree.TerminalNode;

public class ExporterSymbolTable extends CC2020BaseListener {
	
//	<Name of Identifier, Is a Function?>
	private HashMap<String, Boolean> symbolTable = new HashMap<>();
	
	@Override
	public void enterFuncdef(CC2020Parser.FuncdefContext ctx) {
		String funcName = ctx.IDENT().getText();
		if (!symbolTable.containsKey(funcName)) {
			symbolTable.put(funcName, true);
		}
	}
	
	@Override
	public void visitTerminal(TerminalNode node) {
		int type = node.getSymbol().getType();
		String typeName = CC2020Lexer.VOCABULARY.getSymbolicName(type);
		if (typeName == "IDENT") {
			if (!symbolTable.containsKey(node.getText())) {
				symbolTable.put(node.getText(), false);
			}
		}
	}
	
	public void printSymbolTable() {
		System.out.println("==== Symbol Table ====");
		for (String key: symbolTable.keySet()) {
			System.out.println("<" + key + ", " + symbolTable.get(key) + ">");
		}
	}
}
