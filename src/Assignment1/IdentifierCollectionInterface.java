package Assignment1;

/**
 * @author 
 * 	Alae & Elias
 * @elements
 * 	identifierObjects of type Identifier
 * @structure
 * 	none
 * @domain
 * 	Collection contains 0-20 identifiers
 * @constructor
 * 	IdentifierCollection(); 		
 * 	<dl>	
 * 		<dt><b>PRE-condition  -</b><dd>
 * 			-
 * 		<dt><b>POST-condition  -</b><dd> 
 * 			Empty IdentifierCollection object is created
 *	</dl>
 *	<br>
 *	IdentifierCollection(IdentifierCollection src);
 * 	<dl>	
 * 		<dt><b>PRE-condition  -</b><dd>
 * 			-
 * 		<dt><b>POST-condition  -</b><dd> 
 * 			Copy of the IdentifierObject src is created
 *	</dl>
 **/

public interface IdentifierCollectionInterface {

	static final int MAX_ELEMENTS = 20;
	
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
	 * 		SUCCES: The Identifier object is added to the collection
	 * 		FAILURE: An Exception is thrown with the reason why id was not added
	 **/
	void add(Identifier id) throws Exception;
	
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
	 * Returns an identifier that ha not yet been returned 
	 * @param i
	 * @return Identifier object at index i
	 * @precondition
	 * 	The collection is not empty
	 * @postcondition
	 * 	An Identifier object is returned
	 **/
	Identifier getIdentifier();
	
	/**
	 * Removes Identifier id from the collection
	 * @param id
	 * @precondition
	 * 	Identifier id HAS to be one of the elements in the collection
	 * @postcondition
	 * 	Identifier id has been removed from the collection
	 */
	void removeIdentifier(Identifier id);
	
	IdentifierCollection union(IdentifierCollection identifierCollection) throws Exception;
	IdentifierCollection intersection(IdentifierCollection identifierCollection) throws Exception;
	IdentifierCollection difference(IdentifierCollection identifierCollection) throws Exception;
}
