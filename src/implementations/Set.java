package implementations;

import specifications.*;

public class Set <E extends Data<E>> implements SetInterface<E> {

	ListInterface<E> dataList;
	
	Set(){
		dataList = new List<E>(); 
	}
	
	@Override
	public SetInterface<E> clone() {
		Set<E> clone = null;
		
		try {
			clone = (Set<E>) super.clone();
			clone.dataList = dataList.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return clone;
	}

	@Override
	public SetInterface<E> init() {
		dataList = dataList.init();
		return this;	
	}

	@Override
	public SetInterface<E> add(E data) {
		if(contains(data)){
			// do nothing
		}else{
			dataList.insert(data);
		}
		
		return this;
	}

	@Override
	public boolean contains(E data) {
		return dataList.find(data);
	}

	@Override
	public int size() {
		return dataList.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E getElement() {
		dataList.goToFirst();
		E element = dataList.retrieve();
		dataList.remove();
		return element;
	}

	@Override
	public SetInterface<E> removeIdentifier(E data) {
		if(dataList.find(data)){
			dataList.remove();
		}
		
		return this;
	}

	@Override
	public SetInterface<E> union(SetInterface<E> set) {
		Set<E> result = (Set<E>)this.clone();
		SetInterface<E> set2 = set.clone();
		
		return result.addSet(set2);
	}
	
	private SetInterface<E> addSet(SetInterface<E> set){
		while(!set.isEmpty()){
			add(set.getElement());
		}
		
		return this;
	}

	@Override
	public SetInterface<E> intersection(SetInterface<E> set) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SetInterface<E> symmetricDifference(SetInterface<E> set) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SetInterface<E> complement(SetInterface<E> set) {
		// TODO Auto-generated method stub
		return null;
	}

}
