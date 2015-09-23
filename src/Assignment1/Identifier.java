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
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}else if(obj==this){
			return true;
		}else if(obj.getClass()!=getClass()){
			return false;
		}
		
		Identifier identifierObj = (Identifier) obj;
		return identifierObj.identifier.equals(identifier)?true:false;
	}	
}
