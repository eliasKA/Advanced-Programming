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
		
		Variable(K key){
			this.key = key;
			this.value = null;
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

	public VariableMap(){
		variableList = new List<Variable>();
	}
	
	@Override
	public VariableMapInterface<K,V> init(){
		variableList.init();
		return this;
	}

	@Override
	public VariableMapInterface<K, V> add(K key, V value) {
		Variable var = new Variable(key,value);
		
		if(!contains(key)){
			variableList.goToLast();
			variableList.insert(var);
		}else{
			variableList.retrieve().value = value;
		}
		
		return this;
	}

	@Override
	public boolean contains(K key) {
		Variable var = new Variable(key);
		return variableList.find(var);
	}

	@Override
	public int size() {
		return variableList.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V getValue(K key){
		Variable var = new Variable(key);
		variableList.find(var);
		return variableList.retrieve().value;
	}

	@Override
	public VariableMapInterface<K, V> removeVariable(K key) {
		if(contains(key)){
			variableList.remove();
		}
		return this;
	}

	public VariableMap<K, V> clone() {
		VariableMap<K, V> clone = new VariableMap<K, V>();
		clone.variableList = (List<VariableMap<K, V>.Variable>) variableList.clone();
		
		return clone;
	}

}
