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
		return this;
	}

	@Override
	public int size() {

		return numberOfElement;
	}

	@Override
	public ListInterface<E> insert(E d) {
		
		Node node = new Node(d.clone());
		if (isEmpty()) {
			// case : empty set
			node.next = node.prior = null;
			first = last = node;

		} else if (current.next == null) {
			// case: after last
			node.next = last.next;
			node.prior = last;
			last.next = node;

			last = node;
		} else if (current == null) {
			// before first
			node.prior = first.prior;
			node.next = first;
			first.prior = node;

			first = node;
		} else {
			// in between
			node.next = current.next;
			node.prior = current;
			current.next = current.next.prior = node;
		}
		numberOfElement += 1;
		return this;
	}

	@Override
	public E retrieve() {

		return current.data;
	}

	@Override
	public ListInterface<E> remove() {
		if (current.next == null && current.prior == null) {
			current = null;
		}

		else if (current.next == null) {
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
		numberOfElement -=1;
		return this;
	}

	@Override
	public boolean find(E d) {
	
		
		if(isEmpty()){
			current = null;
			return false;
			}
		
		goToFirst();
		
		while(current.next!=null){
			if(current.data.equals(d)){
				return true;
			}
			goToNext();
		}
		if(first.data.compareTo(d)>0){
			current=first;
		}
		else 
			current=last;
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
			current = current.next;
			return true;
		}
		return false;
	}

	@Override
	public ListInterface<E> clone() {
		List<E> list = new List<E>();

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
