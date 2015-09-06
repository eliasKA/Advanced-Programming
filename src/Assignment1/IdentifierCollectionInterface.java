package Assignment1;

/**
 * @author 
 * 	Alae & Elias
 * @elements
 * 	identifierObjects of type Identifier
 * @structure
 * 	Linear
 * @domain
 * 	Only identifiers that meet the domain of the object Identifier
 * 	Collection contains 0-20 identifiers
 * @constructor
 * 	IdentifierCollection(); 		
 * 	<dl>	
 * 		<dt><b>PRE-condition  -</b><dd>
 * 			-
 * 		<dt><b>POST-condition  -</b><dd> 
 * 			IdentifierCollection object is empty
 *	</dl>
 *	<br>
 *	IdentifierCollection(IdentifierCollection src);
 * 	<dl>	
 * 		<dt><b>PRE-condition  -</b><dd>
 * 			-
 * 		<dt><b>POST-condition  -</b><dd> 
 * 			IdentifierCollection object contains a copy of src
 *	</dl>
 **/

public interface IdentifierCollectionInterface {

	int MAX_ELEMENTS = 20;
	
	/**
	 * Initializes the IdentifierCollection object to the empty collection
	 * @precondition
	 * 	-
	 * @postcondition
	 * 	The collection is empty
	 **/
	void init();
	
	/**
	 * Adds an Identifier object to the collection
	 * @param id
	 * @precondition
	 * 	-
	 * @postcondition
	 * 	The Identifier object is added to the collection
	 **/
	void add(Identifier id);
	
	/**
	 * Returns the size of the collection
	 * @return size of the collection (how many elements it contains)
	 * @precondition
	 * 	-
	 * @postcondition
	 * 	the size of the collection is returned
	 **/
	int size();
	
	/**
	 * Returns the identifier object at index i 
	 * @param i
	 * @return Identifier object at index i
	 * @precondition
	 * 	i < size of the collection && i >= 0
	 * @postcondition
	 * 	Identifier object at index i is returned
	 **/
	Identifier getIdentifierAtIndex(int i);
}
