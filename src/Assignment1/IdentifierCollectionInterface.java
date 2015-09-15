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
	 * 	The identifier object that has to be added to the collection
	 * @precondition
	 * 	-
	 * @postcondition
	 * 		SUCCESS: The Identifier object is added to the collection
	 * 		FAILURE: An Exception is thrown when the size of the collection exceeds the maximum 
	 * 	 **/
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
	 * Returns whether the collection is empty
	 * @return whether the collection is empty
	 * @precondition
	 * 	-
	 * @postcondition
	 * 	true: collection is empty
	 * 	false: collection is not empty
	 **/
	boolean isEmpty();
	
	/**
	 * Returns an identifier that has not yet been returned 
	 * @return a not-yet returned Identifier object
	 * @precondition
	 * 	The collection is not empty
	 * @postcondition
	 * 	An Identifier object is returned
	 **/
	Identifier getIdentifier();
	
	/**
	 * Removes Identifier id from the collection
	 * @param identifier
	 * @precondition
	 * 	-
	 * @postcondition
	 * 	Identifier id is not in the collection
	 */
	void removeIdentifier(Identifier identifier);
	
	/**
	 * Returns the union between two collections
	 * @param identifierCollection
	 * 	The collection to use to compute the union with
	 * @precondition
	 * -
	 * @postcondition
	 * 	SUCCESS:  the union collection is returned 
	 * 	FAILURE: An Exception is thrown if the resulting collection exceeds the maximum size
	 */
	IdentifierCollection union(IdentifierCollection identifierCollection) throws Exception;
	
	/**
	 * Returns the intersection between two collections
	 * @param identifierCollection
	 * 	The collection to use to compute the intersection with
	 * @precondition
	 * -
	 * @postcondition
	 * 	SUCCESS:  the intersection collection is returned 
	 * 	FAILURE: An Exception is thrown with the reason why no collection is returned
	 */
	IdentifierCollection intersection(IdentifierCollection identifierCollection);
	
	/**
	 * Returns the difference between two collections
	 * @param identifierCollection
	 * 	The collection to use to compute the difference with
	 * @precondition
	 * -
	 * @postcondition
	 * 	The difference collection of the current collection on the parameter collection is returned 
	 */
	IdentifierCollection difference(IdentifierCollection identifierCollection);
	
	/**
	 * Returns the symmetric difference between two collections
	 * @param identifierCollection
	 * 	The collection to use to compute the symmetric difference with
	 * @precondition
	 * -
	 * @postcondition
	 * 	SUCCESS:  the symmetric difference collection is returned 
	 * 	FAILURE: An Exception is thrown when the resulting collection exceeds the maximum size
	 */
	IdentifierCollection symmetricDifference(IdentifierCollection identifierCollection) throws Exception;
	
	// TODO
	boolean contains(Identifier i); // or isDuplicate() or whatever
}
