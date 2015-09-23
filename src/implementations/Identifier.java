package implementations;

import specifications.IdentifierInterface;

public class Identifier implements IdentifierInterface {

	private String idString;

	public Identifier(String identifier) {
		init(identifier);
	}

	@Override
	public IdentifierInterface clone() {

		return new Identifier(idString);
	}

	@Override // ????
	public int compareTo(IdentifierInterface identifier) {
		if (idString.equals(identifier.toString()))
			return 0;
		else
			return 1;
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

		Identifier identifierObj = (Identifier) obj;
		return identifierObj.idString.equals(idString) ? true : false;
	}

	@Override
	public void init(String identifier) {
		idString = identifier;
	}

}
