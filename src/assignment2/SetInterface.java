package assignment2;

/**
 * @author Alae & Elias
 * @elements objects of type Data
 * @structure none
 * @domain -
 * @constructor Set();
 *              <dl>
 *              <dt><b>PRE-condition -</b>
 *              <dd>-
 *              <dt><b>POST-condition -</b>
 *              <dd>Empty set is created
 *              </dl>
 *
 **/

public interface SetInterface<E extends Data<E>> extends Clonable<SetInterface<E>> {

	/**
	 * Initializes the set object to the empty set
	 * 
	 * @precondition -
	 * @postcondition The set is empty
	 **/
	void init();

	/**
	 * Adds a data object to the set
	 * 
	 * @param id
	 *            The object of type Data that has to be added to the set
	 * @precondition -
	 * @postcondition The data object is in the set
	 * 
	 **/
	void add(E data) throws Exception;

	/**
	 * Returns whether there is already a duplicate of the argument given
	 * 
	 * @param object
	 *            of type Data
	 * @return
	 * @precondition -
	 * @postcondition true: There is a duplicate false: There is not a duplicate
	 */
	boolean isDuplicate(E data);

	/**
	 * Returns the size of the set
	 * 
	 * @return size of the set (how many elements it contains)
	 * @precondition -
	 * @postcondition the size of the set is returned
	 **/
	int size();

	/**
	 * Returns whether the set is empty
	 * 
	 * @return whether the set is empty
	 * @precondition -
	 * @postcondition true: set is empty false: set is not empty
	 **/
	boolean isEmpty();

	/**
	 * Returns an object of type Data that has not yet been returned
	 * 
	 * @return a not-yet returned Identifier object
	 * @precondition The set is not empty
	 * @postcondition An Identifier object is returned
	 **/
	E getElement();

	/**
	 * Removes DAta id from the set
	 * 
	 * @param object
	 *            of type Data
	 * @precondition -
	 * @postcondition Identifier id is not in the set
	 */
	void removeIdentifier(E data);

	/**
	 * Returns the union between two sets
	 * 
	 * @param object
	 *            of type set The set to use to compute the union with
	 * @precondition -
	 * @postcondition the union set is returned
	 */

	SetInterface<E> union(SetInterface<E> set);

	/**
	 * Returns the intersection between two sets
	 * 
	 * @param object
	 *            of type set The set to use to compute the intersection with
	 * @precondition -
	 * @postcondition the intersection set is returned
	 * 
	 */
	SetInterface<E> intersection(SetInterface<E> set);

	/**
	 * Returns the difference between two sets
	 * 
	 * @param object
	 *            of type set The set to use to compute the difference with
	 * @precondition -
	 * @postcondition The difference set of the current set on the parameter set
	 *                is returned
	 */
	SetInterface<E> difference(SetInterface<E> set);

	/**
	 * Returns the symmetric difference between two sets
	 * 
	 * @param object
	 *            of type set The set to use to compute the symmetric difference
	 *            with
	 * @precondition -
	 * @postcondition the symmetric difference set is returned
	 * 
	 */
	SetInterface<E> symmetricDifference(SetInterface<E> set);

}