package specifications;

/**
 * @author Alae & Elias
 * @elements key of type Identifier value of type Object
 * @structure none
 * @domain -
 * @constructor Variable(IdentifierInterface identifier,Object object);
 *              <dl>
 *              <dt><b>PRE-condition -</b>
 *              <dd>-
 *              <dt><b>POST-condition -</b>
 *              <dd>variable with a key identifier and value Object is created
 *              </dl>
 *
 **/
public interface VariableInterface<K extends Data<K>, V extends Clonable<V>> extends Data< VariableInterface<K,V> >{

	/**
	 * Initializes the object with the Identifier and object parameter that is
	 * given
	 * 
	 * @precondition -
	 * @postcondition Variable key is Identifier and value is object
	 */
	void init(Object object);

	/**
	 * @precondition -
	 * @postcondition The key of type IdentifierInterface is returned
	 */
	IdentifierInterface getKey();

	/**
	 * @precondition -
	 * @postcondition The value of type Object is returned
	 */
	Object getValue();

}
