package Assignment1;

/**
 * 
 * @author 
 * 	Alae & Elias
 * @elements
 * 	identifier of type String
 * @structure
 * 	none
 * @domain
 * 	identifier has to be alphanumeric
 *  identifier has to start with a letter
 *  identifier must contain a minimum of 1 character
 * @constructor
 * 	Identifier(String s);
 * 	<dl>	
 * 		<dt><b>PRE-condition  -</b><dd>
 * 			-
 * 		<dt><b>POST-condition  -</b><dd> 
 * 			Identifier object contains identifier s
 *	</dl>
 **/

public interface IdentifierInterface {

	/**
	 * Returns the identifier of type String
	 * @precondition
	 * 	-
	 * @postcondition
	 * 	identifier is returned
	 **/
	String getIdentifier();
}
