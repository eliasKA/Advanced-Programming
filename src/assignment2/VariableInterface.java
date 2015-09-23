package assignment2;

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
public interface VariableInterface {

	/**
	 * @precondition
	 * 	-
	 * @postcondition
	 * 	The key of type IdentifierInterface is returned
	 */
	IdentifierInterface getKey();

	/**
	 * @precondition
	 * 	-
	 * @postcondition
	 * 	The value of type Object is returned
	 */
	Object getValue();

}
