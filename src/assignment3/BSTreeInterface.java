package assignment3;

import java.util.Iterator;

/**
 * 
 * @author Alae & Elias
 * 
 *         /** @elements : objects of type E
 * @structure : Binary Tree
 * @domain : Every parent Node can have a maximum of two children T he elements
 *         in the Tree are sorted so that the left child always greater than the
 *         parent and right child always smaller or equal than the parent. All
 *         elements of type E are valid values for a tree. // TODO : subtree
 * 
 * @constructor - BSTree();
 *              <dl>
 *              <dt><b>PRE-condition</b>
 *              <dd>-
 *              <dt><b>POST-condition</b>
 *              <dd>The new Tree-object is the empty Tree.
 *              </dl>
 **/

public interface BSTreeInterface<E extends Data<E>> extends Clonable<BSTreeInterface<E>> {
	/**
	 * @precondition -
	 * @postcondition - FALSE: Tree is not empty. TRUE: Tree is empty.
	 **/
	boolean isEmpty();

	/**
	 * @precondition -
	 * @postcondition - Tree-Post is empty and has been returned.
	 **/
	BSTreeInterface<E> init();

	/**
	 * @precondition -
	 * @postcondition - The number of elements has been returned.
	 **/
	int size();

	/**
	 * @precondition -
	 * @postcondition - A copy of data has been added to Tree-PRE. 
	 **/
	BSTreeInterface<E> add(E data);

	

	/** @precondition  - The tree is not empty.
	 * 	@postcondition - The data element of tree-PRE is not present in tree-POST.
	 *  				tree-POST has been returned.
	 **/
	BSTreeInterface<E> remove(E data);

	/** @precondition  - 
	 *	@postcondition - TRUE:  Tree contains a copy of d.
	 *	     			
	 *     				 FALSE: Tree does not contain a copy of d.
	 *	     			
	 *	      				
	 **/
	boolean contains(E data);

	/**
	 * @postcondition The data stored in the binary search tree was iterated in
	 *                monotonically non-decreasing order and was added in this
	 *                order to an object of the type Iterator<E>. This object of
	 *                the type Iterator<E> was subsequently returned.
	 **/
	Iterator<E> ascendingIterator();

	/**
	 * @postcondition The data stored in the binary search tree was iterated in
	 *                monotonically non-increasing order and was added in this
	 *                order to an object of the type Iterator<E>. This object of
	 *                the type Iterator<E> was subsequently returned.
	 **/
	Iterator<E> descendingIterator();

}
