package datastructure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MyMap<K, V> {
	private int size;
	private int DEFAULT_CAPACITY = 10;
	@SuppressWarnings("unchecked")
	private MapEntry<K, V>[] map = new MapEntry[DEFAULT_CAPACITY];
	
	public V get(K key){
		for(int i = 0; i < size; i++){
			if(map[i] != null && map[i].getKey().equals(key)){
				return map[i].getValue();
			}
		}
		return null;
	}
	
	 public void put(K key, V value) {
	    boolean insert = true;
	    for (int i = 0; i < size; i++) {
	      if (map[i].getKey().equals(key)) {
	        map[i].setValue(value);
	        insert = false;
	      }
	    }
	    if (insert) {
	      ensureCapacity();
	      map[size++] = new MapEntry<K, V>(key, value);
	    }
	}
	 
	private void ensureCapacity() {
	    if (size == map.length) {
	      int newSize = map.length * 2;
	      map = Arrays.copyOf(map, newSize);
	    }
	}
	
	public int size(){
		return size;
	}
	
	public void remove(K key){
		for(int i = 0; i < size; i++){
			if(map[i].getKey().equals(key)){
				map[i] = null;
				size--;
				compactMap(i);
			}
		}
	}
	
	private void compactMap(int index){
		for(int i = index; i < size-1; i++){
			map[i] = map[i+1];
		}
	}
	
	public Set<K> keySet(){
		Set<K> set = new HashSet<K>();
		for(int i = 0; i < size; i++){
			set.add(map[i].getKey());
		}
		return set;
	}
	
	public static void main(String[] args) {
	    MyMap<String, Integer> map = new MyMap<String, Integer>();
	    for (int i = 0; i < 100; i++) {
	        map.put(String.valueOf(i), i);
	    }
	    System.out.println(map.keySet());
	}
}
