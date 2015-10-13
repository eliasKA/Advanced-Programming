package assignment3;

import java.util.ArrayList;

public class CountMap<E extends Data<E>> implements CountMapInterface<E> {

	public ArrayList<CountObject> countMap;;
	
	CountMap(){
		init();
	}
	
	@Override
	public CountMapInterface<E> init() {
		countMap = new ArrayList<CountObject>();
		return this;
	}

	@Override
	public CountMapInterface<E> add(E key) {
		CountObject countObject = new CountObject(key);
		
		if(countMap.contains(countObject)){
			countMap.get(countMap.lastIndexOf(countObject)).increment();
		}else{
			countMap.add(countObject);
			
		}
		
		return this;
	}

	@Override
	public int getCount(E key) {
		CountObject countObject = new CountObject(key);
		
		if(countMap.contains(countObject)){
			return countMap.get(countMap.lastIndexOf(countObject)).counter;
		}else{
			return 0;
		}
	}
	
	public class CountObject{
		
		E value;
		int counter;
		
		CountObject(E value){
			this.value = value;
			counter = 1;
		}
		
		void increment(){
			counter+=1;
		}
		
		public boolean equals(Object obj){
			if (obj == null) {
				return false;
			} else if (obj == this) {
				return true;
			} else if (obj.getClass() != getClass()) {
				return false;
			}
			
			CountObject countObject = (CountObject)obj;
			return countObject.value.equals(this.value);
		}
		
		public String toString(){
			return "counter: " + counter + " identifier: " + value.toString(); 
		}
	}

}
