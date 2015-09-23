/**
 * HashMap implementation.
 * Using separate chaining to handle collision.
 * 
 * @author wish
 *
 * @param <K> KEY
 * @param <V> VALUE
 */

public class MyHashMap<K, V> {
	static class Entry<K, V> {
		final K key;
		V value;
		Entry<K,V> next;
		Entry(final K key, final V value) {
			this.key = key;
			this.value = value;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(final V value) {
			this.value = value;
		}
	}
	private static final int DEFAULT_CAPACITY = 16;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private static final int MAXIMUM_CAPACITY = 1 << 30; 
	
	private Entry<K, V>[] array;
	private int size;
	private float loadFactor;
	
	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public MyHashMap(final int capacity, final float loadFactor) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Capacity must be a positive number");
		}
		this.array = (Entry<K, V>[]) (new Entry[capacity]);
		this.size = 0;
		this.loadFactor = loadFactor;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean containsValue(final V value) {
		if (isEmpty()) {
			return false;
		}
		for (Entry<K, V> entry : array) {
			while (entry != null) {
				if (equalsValue(entry.getValue(), value)) {
					return true;
				}
				entry = entry.next;
			}
		}
		return false;
	}
	
	private boolean equalsValue(final V v1, final V v2) {
		return v1 == v2 || (v1 != null && v1.equals(v2));
	}
	
	private int getIndex(final K key) {
		return hash(key) % array.length;
	}
	
	private int hash(final K key) {
		if (key == null) {
			return 0;
		}
		return key.hashCode() & 0X7FFFFFFF;
	}
	
	private Entry<K, V> getEntry(final K key) {
		if (isEmpty()) {
			return null;
		} 
		int index = getIndex(key);
		Entry<K, V> entry = array[index];
		while (entry != null) {
			if (equalsKey(entry.getKey(), key)) {
				return entry;
			}
			entry = entry.next;
		}
		return null;
	}
	
	private boolean equalsKey(final K key1, final K key2) {
		return key1 == key2 || (key1 != null && key1.equals(key2));
	}
	
	public boolean containsKey(K key) {
		return getEntry(key) != null;
	}
	
	public V put(K key, V value) {
		Entry<K, V> entry = getEntry(key);
		if (entry != null) {
			V oldValue = entry.getValue();
			entry.setValue(value);
			return oldValue;
		}
		int index = getIndex(key);
		Entry<K, V> head = new Entry<K, V>(key, value);
		head.next = array[index];
		array[index] = head;
		size++;
		if (needRehashing()) {
			rehashing();
		}
		return null;
	}
	private boolean needRehashing() {
		return size / array.length >= loadFactor;
	}
	
	private void rehashing() {
		resize(2 * array.length);
	}
	
	private void resize(int newCapacity) {
		Entry[] oldTable = array;
		int oldCapacity = array.length;
		if (oldCapacity == MAXIMUM_CAPACITY) {
			return;
		}
		Entry[] newTable = new Entry[newCapacity];
		transfer(newTable);
	}
	
	private void transfer(Entry[] newTable) {
		Entry[] old = array;
		int newCapacity = newTable.length;
		for (int i = 0; i < old.length; i++) {
			Entry<K, V> entry = old[i];
			if (entry != null) {
				old[i] = null;
				while (entry != null) {
					Entry<K, V> next = entry.next;
					int index = getIndex(entry.getKey());
					entry.next = newTable[i];
					newTable[i] = entry;
					entry = next;
				}
			}
		}
	}
}
