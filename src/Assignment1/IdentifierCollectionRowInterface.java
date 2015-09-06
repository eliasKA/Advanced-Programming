package Assignment1;

/**
 * @author 
 * 	Alae & Elias
 * @elements
 * 	identifiercollections of type IdentifierCollection
 * @structure
 * 	Linear
 * @domain
	-
 * @constructor
 * 	IdentifierCollection(int m); 		
 * 	<dl>	
 * 		<dt><b>PRE-condition  -</b><dd>
 * 			m >= 0
 * 		<dt><b>POST-condition  -</b><dd> 
 * 			IdentifierCollectionRow object is empty and contains m slots
 *	</dl>
 *	<br>
 **/

public interface IdentifierCollectionRowInterface {
	
	/**
	 * Adds an IdentifierCollection object to the row
	 * @param ic
	 * @precondition
	 * 	-
	 * @postcondition
	 * 	The IdentifierCollection object is added to the row
	 **/
	void addCollection(IdentifierCollection ic);
	
	/**
	 * Returns the IdentifierCollection object at index i 
	 * @param i
	 * @return IdentifierCollection object at index i
	 * @precondition
	 * 	i < number of elements in the row && i >= 0
	 * @postcondition
	 * 	IdentifierCollection object at index i is returned
	 **/
	IdentifierCollection getCollectionAtIndex(int i);
	
	/**
	 * Returns the number of collections in the row
	 * @param i
	 * @return number of collections in the row
	 * @precondition
	 * 	-
	 * @postcondition
	 * 	number of collections in the row is returned
	 **/
	int getNumberOfElements();
	
}
