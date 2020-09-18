public abstract class SymbolTableEntry {
	
	protected String scope;
	protected String name;
	
	public SymbolTableEntry(String name, String scope) {
		this.name = name;
		this.scope = scope;
	}
	
	public abstract String getValues();
	
	public abstract boolean isFunction();

	public String getScope() {
		return scope;
	}
	
}
