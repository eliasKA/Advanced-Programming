package assignment3;



public class Identifier implements IdentifierInterface {

	private String idString;

	public Identifier(String identifier) {
		init(identifier);
	}

	@Override
	public IdentifierInterface clone() {

		return new Identifier(idString);
	}

	@Override
	public int compareTo(IdentifierInterface identifier) {
		return toString().compareTo(identifier.toString());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj == this) {
			return true;
		} else if (obj.getClass() != getClass()) {
			return false;
		}
		
		return toString().equals(obj.toString()) ? true : false;
	}

	@Override
	public void init(String identifier) {
		idString = identifier;
	}

	public String toString(){
		return idString;
	}
}
