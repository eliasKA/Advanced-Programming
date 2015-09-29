package implementations;

/*
 * inner class Variable does not have to be generic right ?
 */

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
			variableList.find(var);
			variableList.remove();
			variableList.insert(var);
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
	public V getVariable(K key) throws APException {
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
		VariableMap<K, V> clone = null;
		
		try {
			clone = (VariableMap<K, V>) super.clone();
			clone.variableList = (List<VariableMap<K, V>.Variable>) variableList.clone();
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clone;
	}

}
