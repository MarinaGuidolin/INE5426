public class SymbolTableEntry {
	
	protected String lexem;
	
	public SymbolTableEntry(String lexem) {
		this.lexem = lexem;
	}
	
	public String getValues() {
		return "lexem = " + lexem + "";
	}
	
}
