package assignment2;

/**
 * @author Alae & Elias
 * @elements objects of type Data
 * @structure none
 * @domain -unique elements
 * @constructor Set();
 *              <dl>
 *              <dt><b>PRE-condition -</b>
 *              <dd>-
 *              <dt><b>POST-condition -</b>
 *              <dd>Empty set is created
 *              </dl>
 *
 **/

public interface SetInterface<E extends Data<E>> extends Clonable<SetInterface<E>>{

	/**
	 * Initializes the set object to the empty set
	 * 
	 * @precondition -
	 * @postcondition The set is empty
	 **/
	SetInterface<E> init();

	/**
	 * Adds a data object to the set
	 * 
	 * @param id
	 *            The object of type Data that has to be added to the set
	 * @precondition -
	 * @postcondition The data object is in the set
	 * 
	 **/
	SetInterface<E> add(E data) ;

	/**
	 * Returns whether there is already a duplicate of the argument given
	 * 
	 * @param object
	 *            of type Data
	 * @return
	 * @precondition -
	 * @postcondition true: There is a duplicate false: There is not a duplicate
	 */
	boolean contains(E data);

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
	SetInterface<E> remove(E data);

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

	/**
	 * Returns the complement of the two sets
	 * 
	 * @param object
	 *            of type set The set to use to compute the complement
	 *            with
	 * @precondition -
	 * @postcondition the complement set is returned
	 * 
	 */
	SetInterface<E> complement(SetInterface<E> set);
	
	// Bonus Assignment methods
	
	/**
	 * Returns whether the current set is smaller than the parameter set. 
	 * 
	 * @precondition -
	 * @postcondition 
	 * TRUE: current Set is smaller than parameter set
	 * FALSE: current Set is bigger or equal to the parameter set 
	 * 
	 */
	boolean smallerThan(SetInterface<E> set);
	
	/**
	 * Returns whether the current set is bigger than the parameter set. 
	 * 
	 * @precondition -
	 * @postcondition 
	 * TRUE: current Set is bigger than parameter set
	 * FALSE: current Set is smaller or equal to the parameter set 
	 * 
	 */
	boolean biggerThan(SetInterface<E> set);
	
	/**
	 * Returns whether the current set is equal to the parameter set. 
	 * 
	 * @precondition -
	 * @postcondition 
	 * TRUE: current Set is equal to parameter set
	 * FALSE: current Set is smaller or bigger than parameter set 
	 * 
	 */
	boolean equals(SetInterface<E> set);
	
	
	
	
}