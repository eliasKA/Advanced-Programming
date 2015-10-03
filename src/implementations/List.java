package implementations;

import specifications.*;

public class List<E extends Data<E>> implements ListInterface<E> {

	private int numberOfElement;

	private Node last, first, current;

	public List() {
		init();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public ListInterface<E> init() {
		first = last = null;
		current = first;
		numberOfElement = 0;
		return this;
	}

	@Override
	public int size() {

		return numberOfElement;
	}

	@Override
	public ListInterface<E> insert(E d) {
		find(d);
		
		if (isEmpty()) {
			// case : empty set
			current = first = last = new Node(d.clone());
		} else if (current.next == null) {
			current = current.next = new Node(d.clone(), current, null);
		} else if (current == null) {
			//in this case the data has to be inserted at the front
			current = first = first.prior = new Node(d.clone(), null, first);			
		} else {
			current = current.next.prior = current.next = new Node(d.clone(), current, current.next);
		}

		numberOfElement += 1;
		return this;
	}

	@Override
	public E retrieve() {

		return current.data.clone();
	}

	@Override
	public ListInterface<E> remove() {
		if (isEmpty()) {
			current = null;
		} else if (current.next == null) {
			current.prior.next = null;
			current = current.prior;

		} else if (current.prior == null) {
			current.next.prior = null;
			current = current.next;
		} else {
			// in between
			current.next.prior = current.prior;
			current.prior.next = current.next;
			current = current.next;
		}
		numberOfElement -= 1;
		return this;
	}

	@Override
	public boolean find(E d) {

		if (isEmpty()) {
			current = null;
			return false;
		}

		goToFirst();
		while (current != null && current.data.compareTo(d) < 0) {
			if (current.data.equals(d)) {
				return true;
			}
			goToNext();
		}
		goToPrevious();

		return false;
	}

	@Override
	public boolean goToFirst() {
		current = first;
		return !isEmpty();
	}

	@Override
	public boolean goToLast() {
		current = last;
		return !isEmpty();
	}

	@Override
	public boolean goToNext() {
		if (!isEmpty() && current.next != null) {
			current = current.next;
			return true;
		}
		return false;
	}

	@Override
	public boolean goToPrevious() {
		if (!isEmpty() && current.prior != null) {
			current = current.prior;
			return true;
		}
		return false;
	}

	@Override
	public ListInterface<E> clone() {
		ListInterface<E> list = new List<E>();
		while (!isEmpty()) {

		}
		return list;
	}
	// Inner class for the implementation of the List class.

	private class Node {

		E data;
		Node prior, next;

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
