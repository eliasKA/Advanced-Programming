package implementations;

import specifications.IdentifierInterface;
import specifications.VariableInterface;

public class Variable implements VariableInterface {
	private IdentifierInterface key;
	private Object value;
	
	public Variable(IdentifierInterface key,Object value) {
		this.key=key;
		init( value);
	}
	@Override
	public void init(Object value) {
		this.value=value;

	}

	@Override
	public IdentifierInterface getKey() {
		return key;
	}

	@Override
	public Object getValue() {
		
		return value;
	}

}
