public class IdentifierEntry extends SymbolTableEntry {
	
	private String type;

	public IdentifierEntry(String name, int scope, String type) {
		super(name, scope);
		this.type = type;
	}

	@Override
	public String getValues() {
		return "lexem=" + name +  ", type=" + type + ", isFunction=" + isFunction();
	}

	@Override
	public boolean isFunction() {
		return false;
	}

}
