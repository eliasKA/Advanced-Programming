package Assignment1;

public class Identifier implements IdentifierInterface {

	private String identifier;
	
	Identifier(String s){
		identifier = s;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
}
