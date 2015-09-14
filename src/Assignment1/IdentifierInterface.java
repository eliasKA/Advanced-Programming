package Assignment1;

/**
 * 
 * @author 
 * 	Alae & Elias
 * @elements
 * 	identifier: characters of type char
 * @structure
 * 	linear
 * @domain
 * 	elements in the identifier have to be alphanumeric
 *  first element has to be a letter
 *  identifier must contain a minimum of 1 character
 * @constructor
 * 	Identifier(String s);
 * 	<dl>	
 * 		<dt><b>PRE-condition  -</b><dd>
 * 			String s has to meet the domain of the identifier
 * 		<dt><b>POST-condition  -</b><dd> 
 * 			identifier contains the same elements as s
 *	</dl>
 *
 * 	Identifier(Identifier src);
 * 	<dl>	
 * 		<dt><b>PRE-condition  -</b><dd>
 * 			-
 * 		<dt><b>POST-condition  -</b><dd> 
 * 			Identifier object created is a copy of src
 *	</dl>
 **/

public interface IdentifierInterface {
	
	/**
	 * Initializes the object with the String parameter that is given 
	 * @param s
	 * 	A String representation of an identifier
	 * @precondition
	 * 		String s has to meet the domain of the identifier
	 * @postcondition
	 * 		Identifier is changed to contain the same elements as s
	 */
	void init(String s);
	
	/**
	 * Returns whether Identifier identifier is equal to current Identifier
	 * @param obj
	 * 	obj of type Object that will be compared to the Identifier object.
	 * @return whether Identifier identifier is equal to current Identifier
	 * @precondition
	 * 	-
	 * @postcondition
	 * 		returns true: The input object is equal to the current identifier object
	 * 		returns false: The input object is NOT equal to the current identifier object
	 */
	boolean equals(Object obj);
	
	/**
	 * @return String representation of the identifier object
	 * @precondition
	 * 	-
	 * @postcondition
	 * 	String representation of the identifier object is printed
	 **/
	String toString();
}
 