public abstract class SymbolTableEntry {
	
	protected String name;
	protected int scope;
	
	public SymbolTableEntry(String name, int scope) {
		this.name = name;
		this.scope = scope;
	}
	
	public abstract String getValues();
	
	public abstract boolean isFunction();

	public int getScope() {
		return scope;
	}
	
}
