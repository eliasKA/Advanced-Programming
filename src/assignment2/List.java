package assignment2;

public class List<E extends Data<E>> implements ListInterface{
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
	
	@Override
	public boolean isEmpty() {
		
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ListInterface init() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListInterface insert(Data d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Data retrieve() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListInterface remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean find(Data d) {
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
	public ListInterface clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
}