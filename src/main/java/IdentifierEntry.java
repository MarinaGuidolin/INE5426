public class IdentifierEntry extends SymbolTableEntry {
	
	private String type;

	public IdentifierEntry(String name, String scope, String type) {
		super(name, scope);
		this.type = type;
	}

	@Override
	public String getValues() {
		return "name=" + name + ", scope=" + scope + ", type=" + type + ", isFunction=" + isFunction();
	}

	@Override
	public boolean isFunction() {
		return false;
	}

}
