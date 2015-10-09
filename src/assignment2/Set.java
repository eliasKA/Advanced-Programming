package assignment2;



public class Set<E extends Data<E>> implements SetInterface<E> {

	ListInterface<E> dataList;

	public Set() {
		init();
	}

	@Override
	public SetInterface<E> clone() {
		Set<E> clone = new Set<E>();
		clone.dataList = dataList.clone();


		return clone;
	}

	@Override
	public SetInterface<E> init() {
		dataList = new List<E>();
		return this;
	}

	@Override
	public SetInterface<E> add(E data) {
		if (contains(data)) {
			return this;
		}

		dataList.insert(data);
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
	public SetInterface<E> remove(E data) {
		if (dataList.find(data)) {
			dataList.remove();
		}

		return this;
	}

	@Override
	public SetInterface<E> union(SetInterface<E> set) {
		SetInterface<E> result = this.clone();
		SetInterface<E> set2 = set.clone();

		while (!set2.isEmpty()) {
			result.add(set2.getElement());
		}

		return result;
	}

	@Override
	public SetInterface<E> intersection(SetInterface<E> set) {
		SetInterface<E> result = new Set<E>();

		SetInterface<E> set2 = set.clone();

		E data;
		while (!set2.isEmpty()) {
			data = set2.getElement();
			if (this.contains(data)) {
				result.add(data);
			}
		}

		return result;
	}

	@Override
	public SetInterface<E> symmetricDifference(SetInterface<E> set) {
		SetInterface<E> result = this.clone();
		SetInterface<E> set2 = set.clone();

		E data;
		while (!set2.isEmpty()) {
			data = set2.getElement();

			if (result.contains(data)) {
				result.remove(data);
			} else {
				result.add(data);
			}
		}

		return result;
	}

	@Override
	public SetInterface<E> complement(SetInterface<E> set) {
		SetInterface<E> result = this.clone();
		SetInterface<E> set2 = set.clone();

		E data;
		while (!set2.isEmpty()) {
			data = set2.getElement();

			if (result.contains(data)) {
				result.remove(data);
			}
		}

		return result;
	}
}
