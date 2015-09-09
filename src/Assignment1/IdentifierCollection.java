package Assignment1;

public class IdentifierCollection implements IdentifierCollectionInterface {

	private Identifier[] identifierArray;
	private int numberOfElements;

	IdentifierCollection() {
		identifierArray = new Identifier[MAX_ELEMENTS];
		numberOfElements = 0;
	}

	IdentifierCollection(IdentifierCollection ic) throws Exception {
		identifierArray = new Identifier[MAX_ELEMENTS];
		numberOfElements = 0;
		int size = ic.size();
		for (int i = 0; i < size; i++) {
			add(ic.getIdentifier());
		}
	}

	public void init() {
		identifierArray = new Identifier[MAX_ELEMENTS];
	}

	public void add(Identifier id) throws Exception {
		if (numberOfElements >= MAX_ELEMENTS) {
			throw new Exception("MAX AMOUNT OF ELEMENTS EXCEEDED");
		}
		if (!isDuplicate(id)) {
			identifierArray[numberOfElements] = id;
			numberOfElements += 1;
		}
	}

	private boolean isDuplicate(Identifier id) {
		for (int i = 0; i < numberOfElements; i++) {
			if (identifierArray[i].isEqualTo(id)) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return numberOfElements;
	}

	@Override
	public Identifier getIdentifier() {
		Identifier id = identifierArray[numberOfElements - 1];
		removeIdentifier(id);
		return id;
	}

	@Override
	public void removeIdentifier(Identifier id) {
		// TODO Auto-generated method stub
		for (int i = 0; i < numberOfElements; i++) {
			if (identifierArray[i].isEqualTo(id)) {
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
		IdentifierCollection secondCollection = new IdentifierCollection(identifierCollection);
		unionCollection.addCollection(secondCollection);
		return unionCollection;
	}

	@Override
	public IdentifierCollection intersection(IdentifierCollection identifierCollection) throws Exception {

		IdentifierCollection intersectionCollection = new IdentifierCollection();
		IdentifierCollection secondCollection = new IdentifierCollection(identifierCollection);
		for (int i = 0; i < secondCollection.numberOfElements; i++) {
			if (isDuplicate(secondCollection.identifierArray[i])) {
				intersectionCollection.add(secondCollection.identifierArray[i]);
			}
		}
		return intersectionCollection;
	}

	@Override
	public IdentifierCollection difference(IdentifierCollection identifierCollection) throws Exception {

		IdentifierCollection differenceCollection = new IdentifierCollection(this);
		IdentifierCollection secondCollection = new IdentifierCollection(identifierCollection);
		for (int i = 0; i < secondCollection.numberOfElements; i++) {
			if (differenceCollection.isDuplicate(secondCollection.identifierArray[i])) {
				differenceCollection.removeIdentifier(secondCollection.identifierArray[i]);
			}
		}
		return differenceCollection;
	}

	@Override
	public IdentifierCollection symmetricDifference(IdentifierCollection identifierCollection) throws Exception {

		IdentifierCollection unionCollection = union(identifierCollection);
		IdentifierCollection intersectionCollection =intersection(identifierCollection);
		IdentifierCollection symDifferenceCollection = unionCollection.difference(intersectionCollection);
		return symDifferenceCollection;
	}
}
