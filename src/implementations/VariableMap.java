package implementations;

import specifications.*;

public class VariableMap<K extends Data<K>, V extends Clonable<V>> implements
		VariableMapInterface<K, V> {

	private class Variable implements Data<Variable>{
		K key;
		V value;

		Variable(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		V getValue(){
			return value;
		}
		
		K getKey(){
			return key;
		}
		
		void setValue(V value){
			this.value = value;
		}

		@Override
		public Variable clone() {
			return new Variable(key.clone(),value.clone());
		}

		@Override
		public int compareTo(Variable var) {
			return key.compareTo(var.key);
		}
		
	}

	List<Variable> variableList;

	VariableMap(){
		init();
	}
	
	@Override
	public VariableMapInterface init(){
		variableList = new List<Variable>();
		return this;
	}

	@Override
	public void add(K key, V value) {
		if(!isDuplicate(key)){
			variableList.insert(new Variable(key,value));
		}
	}

	@Override
	public boolean isDuplicate(K key) {
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V getVariable(K key) throws APException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeVariable(K key) {
		// TODO Auto-generated method stub

	}

	public VariableMap<K, V> clone() {
		return null;

	}

}
