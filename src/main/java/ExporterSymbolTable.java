import java.util.HashMap;
import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTree;

public class ExporterSymbolTable extends CC2020BaseListener {
	
//	<Name of Identifier, Is a Function?>
	private HashMap<String, SymbolTableEntry> symbolTable = new HashMap<>();
	
//	Define the actual scope
	private Stack<String> scopes = new Stack<>();
	
	private int NumStatements = 0;
	
	@Override
	public void enterProgram(CC2020Parser.ProgramContext ctx) {
		scopes.push("program");
	}
	
	public void exitProgram(CC2020Parser.ProgramContext ctx) {
		scopes.pop();
	}
	
	@Override
	public void enterFuncdef(CC2020Parser.FuncdefContext ctx) {
		String funcName = ctx.IDENT().getText();

		if (!symbolTable.containsKey(funcName)) {
			System.out.println("Adding " + funcName);
			FunctionEntry funcEntry = new FunctionEntry(funcName, scopes.peek());
			symbolTable.put(funcName, funcEntry);
		}
		
		scopes.push(funcName);
	}
	
	@Override
	public void exitFuncdef(CC2020Parser.FuncdefContext ctx) {
		scopes.pop();
	}
	
	@Override
	public void enterStatelist(CC2020Parser.StatelistContext ctx) {
		if (ctx.getParent().getChild(0).getText().equals("{")) {
			String statementName = "statement" + NumStatements;
			NumStatements++;
			scopes.push(statementName);
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
		String type = ctx.getChild(0).getText();
		String lexem = ctx.getChild(1).getText();
		if (!symbolTable.containsKey(lexem)) {
			System.out.println("Adding " + lexem);
			IdentifierEntry identEntry = new IdentifierEntry(lexem, scopes.peek(), type);
			symbolTable.put((scopes.peek() + "_" + lexem), identEntry);
		}
	}
	
	@Override
	public void enterParamlist(CC2020Parser.ParamlistContext ctx) {
		ParseTree child0 = ctx.getChild(0);
		ParseTree terminalNode = ctx.IDENT();
		if (child0 != null && terminalNode != null) {
			String actualScope = scopes.peek();
			String type = ctx.getChild(0).getText();
			String identifier = ctx.IDENT().getText();
			
			if (symbolTable.containsKey(actualScope)) {
	
				FunctionEntry entry = (FunctionEntry) symbolTable.get(actualScope);
				entry.putParameter("(lexem= " + identifier + ", type= " + type + ")");
				symbolTable.put(actualScope, entry);
				
			}
		}
	}

	public HashMap<String, SymbolTableEntry> getSymbolTable() {
		return this.symbolTable;
	}
}
