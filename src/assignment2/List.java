package assignment2;

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
		} else if (current == first && current.data.compareTo(d) > 0) {
			current = first = first.prior = new Node(d.clone(), null, first);
		} else if (current == last) {
			last = current = current.next = new Node(d.clone(), current, null);
		} else {
			current = current.next = current.next.prior = new Node(d.clone(), current, current.next);
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
		if(isEmpty()){
			return this;
		}else if (size() == 1) {
			last = first = current = null;
		} else if (current == last) {
			current.prior.next = null;
			last = current = current.prior;
		} else if (current == first) {
			current.next.prior = null;
			first = current = current.next;
		} else{
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

		do {
			if (current.data.equals(d)) {
				return true;
			}
		} while (goToNext() && current.data.compareTo(d) <= 0);
		
		if (current.data.compareTo(d) > 0)
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
		if (!isEmpty()) {
			goToFirst();
			do {

				list.insert(retrieve());
			} while (goToNext());
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
