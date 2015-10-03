package specifications;

import implementations.APException;

/**
 * @author Alae & Elias
 * @elements Variables of K V
 * @structure none
 * @domain - key is unique
 * @constructor VariableMap();
 *              <dl>
 *              <dt><b>PRE-condition -</b>
 *              <dd>-
 *              <dt><b>POST-condition -</b>
 *              <dd>empty map is created 
 *              </dl>
 *
 **/
public interface VariableMapInterface<K extends Data<K>, V extends Clonable<V>> extends Clonable<VariableMapInterface<K, V>>{

	
	/**
	 * Initializes the map object to the empty map
	 * 
	 * @precondition -
	 * @postcondition The map is empty
	 **/
	VariableMapInterface<K,V> init();

	/**
	 * Adds a variable with key and value to the map
	 *@precondition  -
	 *@postcondition 
	 * 	The variable is in the map overwrite !
	 * 
	 **/
	VariableMapInterface<K,V> add(K key, V value) ;

	/**
	 * Returns whether there is already a duplicate of the argument given
	 * 
	 * @precondition 
	 * -
	 * @postcondition 
	 * 				true: There is a duplicate 
	 * 				false: There is not a duplicate
	 */
	boolean contains(K key);

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
	 * 	 		Success : returns a value coresponds to the key
	 * 			Failure : Exception is thrown : No variable linked to key
	 **/
	 V getValue(K key) throws APException;

	/**
	 * Removes variable with key from the map
	 *
	 * @precondition 
	 * -
	 * @postcondition variable with key Identifier is not in the map
	 */
	 VariableMapInterface<K,V> removeVariable(K key);
}
