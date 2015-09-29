package specifications;

import implementations.APException;

/**
 * @author Alae & Elias
 * @elements Variables 
 * @structure none 
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
public interface VariableMapInterface<K extends Data<K>, V extends Clonable<V>>{

	String APEXCEPTION_NO_VAR = "NO SUCH VARIABLE HAS BEEN DECLARED";
	
	/**
	 * Initializes the map object to the empty map
	 * 
	 * @precondition -
	 * @postcondition The map is empty
	 **/
	void init();

	/**
	 * Adds a variable with key and value to the map
	 *@precondition 
	 * -
	 * @postcondition 
	 * 	The variable is in the map
	 * 
	 **/
	void add(K key, V value) ;

	/**
	 * Returns whether there is already a duplicate of the argument given
	 * 
	 * @precondition 
	 * -
	 * @postcondition 
	 * 				true: There is a duplicate 
	 * 				false: There is not a duplicate
	 */
	boolean isDuplicate(K key);

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
	 * Returns the value that is linked to the key
	 * 
	 * @precondition 
	 * @postcondition 
	 * 	 		Success : returns a value
	 * 			Failure : Exception is thrown : No variable linked to key
	 **/
	 V getVariable(K key) throws APException;

	/**
	 * Removes variable with key from the map
	 *
	 * @precondition 
	 * -
	 * @postcondition variable with key Identifier is not in the map
	 */
	void removeVariable(K key);
}
