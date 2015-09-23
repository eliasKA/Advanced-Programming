package implementations;

import specifications.Data;
import specifications.ListInterface;

public class List<E extends Data<E>> implements ListInterface<E> {

	
	private int numberOfElement;
	
	
	@Override
	public boolean isEmpty() {
		return size()==0;
	}

	@Override
	public ListInterface<E> init() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numberOfElement;
	}

	@Override
	public ListInterface<E> insert(E d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E retrieve() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListInterface<E> remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean find(E d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean goToFirst() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean goToLast() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean goToNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean goToPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ListInterface<E> clone() {
		// TODO Auto-generated method stub
		return null;
	}
	// Inner class for the implementation of the List class.

	private class Node {

	    E data;
	    Node prior,
	         next;

	    public Node(E d) {
	        this(d, null, null);
	    }

	    public Node(E data, Node prior, Node next) {
	        this.data = data == null ? null : data;
	        this.prior = prior;
	        this.next = next;
	    }

	}
}
