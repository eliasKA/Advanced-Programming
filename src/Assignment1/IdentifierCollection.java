
package Assignment1;

public class IdentifierCollection implements IdentifierCollectionInterface {

	private Identifier[] identifierArray;
	private int numberOfElements;
	private static final String EXC_MAX_EXCEEDED = "ERROR, MAX AMOUNT OF ELEMENTS EXCEEDED";
	
	IdentifierCollection() {
		init();
	}

	IdentifierCollection(IdentifierCollection srcCollection){
		init();
		int size = srcCollection.size();
		
		for (int i = 0; i < size; i++) {
			try {
				add(srcCollection.identifierArray[i]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean equals(Object obj){
		if(obj==null){
			return false;
		}else if(obj==this){
			return true;
		}else if(obj.getClass()!=getClass()){
			return false;
		}
		
		IdentifierCollection collectionObj = (IdentifierCollection) obj;
		
		if((collectionObj).numberOfElements != numberOfElements){
			return false;
		}
		
		for(int i = 0; i < numberOfElements; i++){
			if(!identifierArray[i].equals(collectionObj.identifierArray[i])){
				return false;
			}
		}
		
		return true;
	}
	
	public void init() {
		identifierArray = new Identifier[MAX_ELEMENTS];
		numberOfElements = 0;
	}

	public void add(Identifier id) throws Exception {
		if (numberOfElements >= MAX_ELEMENTS) {
			throw new Exception(EXC_MAX_EXCEEDED);
		}
		
		if (!isDuplicate(id)) {
			identifierArray[numberOfElements] = id;
			numberOfElements += 1;
		}
	}

	public boolean isDuplicate(Identifier id) {
		for (int i = 0; i < numberOfElements; i++) {
			if (identifierArray[i].equals(id)) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return numberOfElements;
	}

	public Identifier getIdentifier(){
		Identifier id = identifierArray[0];
		removeIdentifierAtIndex(0);
		return id;
	}

	public void removeIdentifier(Identifier identifier) {
		for (int i = 0; i < numberOfElements; i++) {
			if (identifierArray[i].equals(identifier)) {
				removeIdentifierAtIndex(i);
			}
		}
	}

	private void removeIdentifierAtIndex(int index) {
		for (int i = index; i < numberOfElements; i++) {
			identifierArray[i] = identifierArray[i + 1];
		}
		numberOfElements -= 1;
	}

	private void addCollection(IdentifierCollection identifierCollection) throws Exception {
		for (int i = 0; i < identifierCollection.numberOfElements; i++) {
			add(identifierCollection.identifierArray[i]);
		}
	}

	@Override
	public IdentifierCollection union(IdentifierCollection identifierCollection) throws Exception {

		IdentifierCollection unionCollection = new IdentifierCollection(this);
		unionCollection.addCollection(identifierCollection);
		return unionCollection;
	}

	@Override
	public IdentifierCollection intersection(IdentifierCollection identifierCollection){

		IdentifierCollection intersectionCollection = new IdentifierCollection();
		for (int i = 0, j = 0; i < identifierCollection.numberOfElements; i++) {
			if (isDuplicate(identifierCollection.identifierArray[i])) {
				try {
					intersectionCollection.add(identifierCollection.identifierArray[i]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return intersectionCollection;
	}

	@Override
	public IdentifierCollection difference(IdentifierCollection identifierCollection){
		IdentifierCollection differenceCollection = new IdentifierCollection(this);
		for (int i = 0; i < identifierCollection.numberOfElements; i++) {
			if (differenceCollection.isDuplicate(identifierCollection.identifierArray[i])) {
				differenceCollection.removeIdentifier(identifierCollection.identifierArray[i]);
			}
		}
		return differenceCollection;
	}

	@Override
	public IdentifierCollection symmetricDifference(IdentifierCollection identifierCollection) throws Exception {
		IdentifierCollection unionCollection = union(identifierCollection);
		IdentifierCollection intersectionCollection = intersection(identifierCollection);
		IdentifierCollection symDifferenceCollection = unionCollection.difference(intersectionCollection);
		return symDifferenceCollection;
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < numberOfElements; i++) {
			result += " " + identifierArray[i];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}
