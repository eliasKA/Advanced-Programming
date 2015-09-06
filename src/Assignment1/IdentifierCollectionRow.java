package Assignment1;

public class IdentifierCollectionRow implements IdentifierCollectionRowInterface{

	private IdentifierCollection[] idCollArray;
	private int numberOfElements;
	
	IdentifierCollectionRow(int max){
		idCollArray = new IdentifierCollection[max];
		numberOfElements = 0;
	}
	
	public void addCollection(IdentifierCollection ic) {
		idCollArray[numberOfElements] = ic;
		numberOfElements += 1;
	}
	
	public IdentifierCollection getCollectionAtIndex(int i) {
		return idCollArray[i];
	}
	
	public int getNumberOfElements() {
		return numberOfElements;
	}

	

}
