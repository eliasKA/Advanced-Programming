package Assignment1;

public class IdentifierCollection implements IdentifierCollectionInterface{

	private Identifier[] identifierArray;
	private int numberOfElements;

	IdentifierCollection(){
		identifierArray = new Identifier[MAX_ELEMENTS];
		numberOfElements = 0;
	}
	
	IdentifierCollection(IdentifierCollection ic){
		identifierArray = new Identifier[MAX_ELEMENTS];
		numberOfElements = 0;
		
		for(int i = 0; i < ic.size(); i++){
			add(ic.getIdentifierAtIndex(i));
		}
	}
	
	public void init() {
		identifierArray = new Identifier[MAX_ELEMENTS];
	}

	public void add(Identifier id) {
		identifierArray[numberOfElements] = id;
		numberOfElements += 1;
	}

	public int size() {
		return numberOfElements;
	}

	public Identifier getIdentifierAtIndex(int i) {
		return identifierArray[i];
	}

}
