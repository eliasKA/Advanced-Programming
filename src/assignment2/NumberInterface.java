package assignment2;

/**
 * 
 * @author Alae & Elias
 * 
 * @elements Number: characters of type char
 * @structure linear
 * @domain elements in the number have to be digits
 * 
 *         number must contain a minimum of 1 digit
 * @constructor Number(String numberString);
 *              <dl>
 *              <dt><b>PRE-condition -</b>
 *              <dd>String s has to meet the domain of the number Class
 *              <dt><b>POST-condition -</b>
 *              <dd>number contains the same elements as numberString
 *              </dl>
 *
 * 
 **/
public interface NumberInterface extends Data<NumberInterface> {

	/**
	 * Initializes the object with the String parameter that is given
	 * 
	 * @param s
	 *            A String representation of a Number
	 * @precondition String s has to meet the domain of the number
	 * @postcondition Number is changed to contain the same elements as
	 *                numberString
	 */
	void init(String numberString);

	/**
	 * Returns whether object is equal to current Number object
	 * 
	 * @param obj
	 *            obj of type Object that will be compared to the Identifier
	 *            object.
	 * @return whether object is equal to current Number
	 * @precondition -
	 * @postcondition returns true: The input object is equal to the current
	 *                Number object returns false: The input object is NOT equal
	 *                to the current number object
	 */
	boolean equals(Object obj);

	/**
	 * @return String representation of the Number object
	 * @preconditions -
	 * @postcondition String representation of the Number object is printed
	 **/

	String toString();

	/**
	 * @precondition -
	 * @postcondition - A deep-copy ofNumber has been returned.
	 **/

	public NumberInterface clone();

}
