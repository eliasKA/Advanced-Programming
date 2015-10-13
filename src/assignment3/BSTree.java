package assignment3;

import java.util.ArrayList;
import java.util.Iterator;

public class BSTree<E extends Data<E>> implements BSTreeInterface<E> {

	private int numberOfElement;
	private Node root;
	ArrayList<E> list;

	public BSTree() {
		init();
	}

	@Override
	public BSTreeInterface<E> init() {
		numberOfElement = 0;
		root = null;
		list = new ArrayList<E>();
		return this;
	}
	
	@Override
	public Iterator<E> ascendingIterator() {
		list.clear();
		makeAscending(root);
		return list.iterator();
	}

	@Override
	public Iterator<E> descendingIterator() {
		list.clear();
		makeDescending(root);
		return list.iterator();
	}

	private void makeAscending(Node subRoot) {
		if (subRoot == null)
			return;
		else {
			makeAscending(subRoot.leftChild);
			list.add(subRoot.data);
			makeAscending(subRoot.rightChild);
		}
	}
	private void makeDescending(Node subRoot) {
		if (subRoot == null)
			return;
		else {
			makeDescending(subRoot.rightChild);
			list.add(subRoot.data);
			makeDescending(subRoot.leftChild);
			
		}
	}

	@Override
	public boolean isEmpty() {

		return size() == 0;
	}

	@Override
	public int size() {

		return numberOfElement;
	}

	

	private E smallest(Node subRoot) {

		return subRoot.leftChild == null ? subRoot.data : smallest(subRoot.leftChild);
	}

	private Node insertRecursive(Node subRoot, E data) {

		if (subRoot == null) {
			return new Node(data);
		} else if (data.compareTo(subRoot.data) < 0) {
			subRoot.leftChild =  insertRecursive(subRoot.leftChild, data);
		} else {
			subRoot.rightChild =  insertRecursive(subRoot.rightChild, data);
		}
		
		return subRoot;

	}

	private Node removeRecursive(Node subRoot, E data) {
		if (subRoot == null)
			return null;
		else if (data.compareTo(subRoot.data) < 0)
			subRoot.leftChild = removeRecursive(subRoot.leftChild, data);
		else if (data.compareTo(subRoot.data) > 0)
			subRoot.rightChild = removeRecursive(subRoot.rightChild, data);
		else {
			if (subRoot.leftChild == null)
				subRoot = subRoot.rightChild;
			else if (subRoot.rightChild == null)
				subRoot = subRoot.leftChild;
			else {
				subRoot.data = smallest(subRoot.rightChild);
				subRoot.rightChild = removeRecursive(subRoot.rightChild, subRoot.data);

			}

		}
		return subRoot;

	}

	@Override
	public BSTreeInterface<E> add(E data) {

		root = insertRecursive(root, data);
		numberOfElement+=1;
		return this;
	}


	@Override
	public BSTreeInterface<E> remove(E data) {
		root = removeRecursive(root, data);
		return this;
	}

	@Override
	public BSTreeInterface<E> clone() {
		BSTree<E> clone = new BSTree<E>();
		clone.root = root.clone();
		
		return clone;
	}

	private boolean containsRecursive(Node subRoot, E data) {

		if (subRoot == null)
			return false;
		else if (data.compareTo(subRoot.data) < 0) {
			return containsRecursive(subRoot.leftChild, data);
		} else if (data.compareTo(subRoot.data) == 0) {
			return true;
		} else {
			return containsRecursive(subRoot.rightChild, data);
		}
	}

	@Override
	public boolean contains(E data) {
		return containsRecursive(root, data);
	}

	class Node {

		private E data;

		private Node leftChild;
		private Node rightChild;

		public Node(E data) {
			this(data, null, null);
		}

		public Node(E data, Node leftChild, Node rightChild) {

			this.data = data == null ? null : data;
			this.leftChild = leftChild;
			this.rightChild = rightChild;

		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}

		public Node getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		public Node getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}
		
		public Node clone(){
			Node clone;
			
			if(leftChild == null && rightChild == null){
				clone = new Node(data.clone());
			}else if(leftChild==null){
				clone = new Node(data.clone(),null,rightChild.clone());
			}else if(rightChild==null){
				clone = new Node(data.clone(),leftChild.clone(),null);
			}else{
				clone = new Node(data.clone(),leftChild.clone(),rightChild.clone());
			}

			return clone;
		}
	}

}
