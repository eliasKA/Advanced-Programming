package Assignment1;

public class IdentifierCollection implements IdentifierCollectionInterface{

	private Identifier[] identifierArray;
	private int numberOfElements;

	IdentifierCollection(){
		identifierArray = new Identifier[MAX_ELEMENTS];
		numberOfElements = 0;
	}
	
	IdentifierCollection(IdentifierCollection ic) throws Exception{
		identifierArray = new Identifier[MAX_ELEMENTS];
		numberOfElements = 0;
		
		for(int i = 0; i < ic.size(); i++){
			add(ic.getIdentifier());
		}
	}
	
	public void init() {
		identifierArray = new Identifier[MAX_ELEMENTS];
	}

	public void add(Identifier id) throws Exception{
		if(numberOfElements >= MAX_ELEMENTS){
			throw new Exception("MAX AMOUNT OF ELEMENTS EXCEEDED");
		}
		
		identifierArray[numberOfElements] = id;
		numberOfElements += 1;
	}

	public int size() {
		return numberOfElements;
	}

	@Override
	public Identifier getIdentifier() {
		Identifier id =  identifierArray[numberOfElements-1];
		removeIdentifier(id);
		return id;
	}

	@Override
	public void removeIdentifier(Identifier id) {
		// TODO Auto-generated method stub
		for(int i = 0; i < numberOfElements; i++){
			if(identifierArray[i].isEqualTo(id)){
				removeIdentifierAtIndex(i);
			}
		}
	}
	
	private void removeIdentifierAtIndex(int index){
		for(int i = index; i < numberOfElements; i++){
			identifierArray[i] = identifierArray[i+1];
		}
		numberOfElements -= 1;
	}

}
