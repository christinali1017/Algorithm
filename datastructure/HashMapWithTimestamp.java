import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * HashMap with Timestamp.
 * 
 * get(key, timestamp), if timestamp is given, return the value with largest time <= timestamp.
 * get(key) if timestamp is not given, return the lastest value.
 * 
 * 
 * put(key, timestamp, value)
 *
 * @param <K> type of key
 * @param <V> type of value
 */
public class HashMapWithTimestamp<K, V> {
    private Map<K, TreeMap<Integer, V>> map = new HashMap<>();
    public V get(final K k, final Integer time) {
        TreeMap<Integer, V> treeMap = map.get(k);
        if (treeMap == null) {
            return null;
        } else {
            V val = treeMap.get(time);
            if (val != null) {
                return val;
            } else {
                return treeMap.lowerEntry(time).getValue();
            }
        }
    }

    public V get(final K k) {
        TreeMap<Integer, V> treeMap = map.get(k);
        if (treeMap == null) {
            return null;
        } else {
            Entry<Integer, V> entry = treeMap.lastEntry();
            if (entry != null) {
                return entry.getValue();
            } else {
                return null;
            }
        }
    }

    public void put(final K k, final V value, final Integer time) {
        TreeMap<Integer, V> treeMap = map.get(k);
        if (treeMap == null) {
            treeMap = new TreeMap<>();
        }
        treeMap.put(time, value);
        map.put(k, treeMap);
    }

    

    public static void main(String[] args) {
        HashMapWithTimestamp<String, String> map = new HashMapWithTimestamp<>();
        map.put("k1", "v1", 10);
        System.out.println(map.get("k1")); //v1

        System.out.println(map.get("k1", 11)); //v1

        map.put("k1", "v2", 20);
        System.out.println(map.get("k1", 15)); //v1
        System.out.println(map.get("k1", 11)); //v1
        System.out.println(map.get("k1", 21)); //v2
        System.out.println(map.get("k1")); //v2
    }
}
