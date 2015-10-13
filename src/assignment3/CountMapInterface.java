package assignment3;

/**
 * @author Alae & Elias
 * @elements keys and their counters
 * @structure none
 * @domain unique keys 
 * @constructor CountMap();
 *              <dl>
 *              <dt><b>PRE-condition -</b>
 *              <dd>-
 *              <dt><b>POST-condition -</b>
 *              <dd>empty map is created 
 *              </dl>
 *
 **/
public interface CountMapInterface<E extends Data<E>>{
	
	/**
	 * Initializes the map object to the empty map
	 * @precondition -
	 * @postcondition The map is empty
	 **/
	CountMapInterface<E> init();
	
	/**
	 * Adds a key to the map and increments its counter
	 *@precondition  -
	 *@postcondition 
	 *		PRE-map does not contain key: add key to the map 
	 * 		PRE-map contains key: increment counter of key with one
	 **/
	CountMapInterface<E> add(E key);
	
	/**
	 * Returns the counted value of the key
	 *@precondition  -
	 *@postcondition 
	 *		PRE-map does not contain key: return 0 
	 * 		PRE-map contains key: return counter of key
	 **/
	int getCount(E key);
}
