import java.util.HashMap;
import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ExporterSymbolTable extends CC2020BaseListener {
	
//	<Name of Identifier, Is a Function?>
	private HashMap<Integer, SymbolTableEntry> symbolTable = new HashMap<Integer, SymbolTableEntry>();
	
	private HashMap<Integer, String> indexToName = new HashMap<Integer, String>();
	
//	Define the actual scope
	private Stack<Integer> scopes = new Stack<>();
	
	private int NumberOfStatements = 0;
	
	@Override
	public void enterProgram(CC2020Parser.ProgramContext ctx) {
		
		int tokenIndex = ctx.start.getTokenIndex();
		
		scopes.push(tokenIndex);
		
		indexToName.put(tokenIndex, "global");
	}
	
	public void exitProgram(CC2020Parser.ProgramContext ctx) {
		
		scopes.pop();
	}
	
	@Override
	public void enterFuncdef(CC2020Parser.FuncdefContext ctx) {	
		
		TerminalNode node = ctx.IDENT();
		String funcName = node.getText();
		int tokenIndex = node.getSymbol().getTokenIndex();
		
		if (!symbolTable.containsKey(tokenIndex)) {
			FunctionEntry funcEntry = new FunctionEntry(funcName, scopes.peek());
			symbolTable.put(tokenIndex, funcEntry);
		}
		
		scopes.push(tokenIndex);
		indexToName.put(tokenIndex, funcName);
	}
	
	@Override
	public void exitFuncdef(CC2020Parser.FuncdefContext ctx) {
		scopes.pop();
	}
	
	@Override
	public void enterStatelist(CC2020Parser.StatelistContext ctx) {
		
		if (ctx.getParent().getChild(0).getText().equals("{")) {
			
			int tokenIndex = ctx.getParent().start.getTokenIndex();

			scopes.push(tokenIndex);
			indexToName.put(tokenIndex, "Statement" + NumberOfStatements);
			NumberOfStatements++;
			
		}
	}
	
	@Override
	public void exitStatelist(CC2020Parser.StatelistContext ctx) {
		if (ctx.getParent().getChild(0).getText().equals("{")) {
			scopes.pop();
		}
	}
	
	@Override
	public void enterVardecl(CC2020Parser.VardeclContext ctx) {
		
		String type = ctx.start.getText();
		int tokenIndex = ctx.IDENT().getSymbol().getTokenIndex();
		String lexem = ctx.getChild(1).getText();
		
		if (!symbolTable.containsKey(tokenIndex)) {
			IdentifierEntry identEntry = new IdentifierEntry(lexem, scopes.peek(), type);
			symbolTable.put(tokenIndex, identEntry);
		}
	}
	
	@Override
	public void enterParamlist(CC2020Parser.ParamlistContext ctx) {
		
		ParseTree typeChild = ctx.getChild(0);
		ParseTree lexemChild = ctx.IDENT();
		
		if (typeChild != null && lexemChild != null) {
			
			int actualScope = scopes.peek();
			
			String type = typeChild.getText();
			String identifier = lexemChild.getText();
			
			if (symbolTable.containsKey(actualScope)) {
	
				FunctionEntry entry = (FunctionEntry) symbolTable.get(actualScope);
				entry.putParameter("(lexem= " + identifier + ", type= " + type + ")");
				symbolTable.put(actualScope, entry);
				
			}
		}
	}
	
	public HashMap<Integer,SymbolTableEntry> getSymbolTable() {
		return this.symbolTable;
	}
	
	public HashMap<Integer, String> getKeysName() {
		return this.indexToName;
	}
}
