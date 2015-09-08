package Assignment1;

public class Identifier implements IdentifierInterface {

	private String identifier;
	
	Identifier(String s){
		identifier = s;
	}
	
	Identifier(Identifier src){
		identifier = src.toString();
	}
	
	@Override
	public String toString() {
		return identifier;
	}

	@Override
	public void init(String s) {
		identifier = s;
	}

	@Override
	public boolean isEqualTo(Identifier id) {
		return id.toString().equals(identifier)?true:false;
	}
	
}
