package specifications;

import implementations.APException;

/**
 * @author Alae & Elias
 * @elements Variables 
 * @structure Linear 
 * @domain -
 * @constructor VariableMap();
 *              <dl>
 *              <dt><b>PRE-condition -</b>
 *              <dd>-
 *              <dt><b>POST-condition -</b>
 *              <dd>empty map is created 
 *              </dl>
 *
 **/
public interface VariableMapInterface {

	/**
	 * Initializes the map object to the empty map
	 * 
	 * @precondition -
	 * @postcondition The map is empty
	 **/
	void init();

	/**
	 * Adds a Variable object to the map
	 *@precondition 
	 * -
	 * @postcondition 
	 * The Variable object is in the map
	 * 
	 **/
	void add(VariableInterface variable) ;

	/**
	 * Returns whether there is already a duplicate of the argument given
	 * 
	 * @precondition 
	 * -
	 * @postcondition 
	 * 				true: There is a duplicate 
	 * 				false: There is not a duplicate
	 */
	boolean isDuplicate(VariableInterface variable);

	/**
	 * Returns the size of the map
	 * 
	 * 
	 * @precondition 
	 * -
	 * @postcondition 
	 * the size of the set is returned
	 **/
	int size();

	/**
	 * Returns whether the map is empty
	 * 
	 * @precondition
	 *  -
	 * @postcondition 
	 * 			true: map is empty 
	 * 			false: map is not empty
	 **/
	boolean isEmpty();

	/**
	 * Returns an object of type Variable that has not yet been returned
	 * 
	 * @return a not-yet returned Identifier object
	 * @precondition The set is not empty
	 * @postcondition 
	 * 	 		Success :returns a variable object with the key : identifier.
	 * 			Failure : Exception is thrown : No variable with key Identifier
	 **/
	VariableInterface getVariable(IdentifierInterface identifier) throws APException;

	/**
	 * Removes variable from the map
	 *
	 * @precondition 
	 * -
	 * @postcondition variable with key Identifier is not in the map
	 */
	void removeVariable(IdentifierInterface identifier);

	
	
	
	
	
	
	
	
}
