import java.util.ArrayList;

public class FunctionEntry extends SymbolTableEntry {

	private ArrayList<String> parameters = null;

	public FunctionEntry(String name, int scope) {
		super(name, scope);
		this.parameters = new ArrayList<String>();
	}


	@Override
	public String getValues() {
		String values = "lexem=" + name + ", params=[";
		for (int i = 0; i < parameters.size(); i++) {
			if (i == parameters.size() - 1) {
				values += parameters.get(i);
			} else {
				values += parameters.get(i) + ", ";
			}
		}
		values += "]";
		values += ", isFunction=" + isFunction();
		return values;
	}

	public void putParameter(String parameter) {
		parameters.add(parameter);
	}

	public ArrayList<String> getParameters() {
		return this.parameters;
	}

	@Override
	public boolean isFunction() {
		return true;
	}

}
